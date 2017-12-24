package co.twinkly.picatego.features.categoryselection;

import java.util.ArrayList;
import java.util.List;

import co.twinkly.picatego.features.base.BaseInteractor;
import co.twinkly.picatego.utils.constants.SharedPreferencesConstants;
import co.twinkly.picatego.utils.managers.SharedPreferencesManager;
import co.twinkly.picatego.utils.models.CategoryItem;
import co.twinkly.picatego.utils.services.database.DatabaseService;
import co.twinkly.picatego.utils.services.database.models.Category;
import co.twinkly.picatego.utils.services.network.NetworkService;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class CategorySelectionInteractor extends BaseInteractor implements CategorySelectionContract.Interactor {

    private final NetworkService networkService;
    private final DatabaseService databaseService;
    private final SharedPreferencesManager sharedPreferencesManager;

    private List<Category> categories;
    private List<Category> categoriesForItems;
    private List<CategoryItem> categoryItems;

    private PublishSubject<List<CategoryItem>> categoryItemsPublishSubject = PublishSubject.create();

    CategorySelectionInteractor(NetworkService networkService, DatabaseService databaseService, SharedPreferencesManager sharedPreferencesManager) {
        this.networkService = networkService;
        this.databaseService = databaseService;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }

    @Override
    public List<Category> getAllCategories() {
        if (categories == null) {
            prepareSortedCategories();
        }

        return categories;
    }

    @Override
    public Observable<List<CategoryItem>> onCategoryItemsPrepared() {
        prepareSortedCategories();

        categoryItems = new ArrayList<>();
        fillCategoryItemWithCategory(categoriesForItems.get(0));

        return categoryItemsPublishSubject;
    }

    @Override
    public Observable<PhotoSearchResponseModel> getPhotosForTag(String tag) {
        return networkService.getPhotosForTag(tag);
    }

    @Override
    public void increaseInterestForIndex(int index) {
        sharedPreferencesManager.put(SharedPreferencesConstants.IS_FIRST_TIME_USER, false);
        databaseService.increaseInterestForCategory(getAllCategories().get(index));
    }

    private void prepareSortedCategories() {
        List<Category> descendingCategories = databaseService.getAllCategoriesInDescendingInterest();

        categoriesForItems = new ArrayList<>();
        categoriesForItems.add(descendingCategories.get(1));
        for (int i = 2; i < descendingCategories.size(); i++) {
            categoriesForItems.add(descendingCategories.get(i));
        }
        categoriesForItems.add(descendingCategories.get(0));

        categories = new ArrayList<>(categoriesForItems);
    }

    private void fillCategoryItemWithCategory(Category category) {
        networkService.getPhotosForTag(category.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(photoSearchResponseModel -> {
                    categoryItems.add(new CategoryItem(category, photoSearchResponseModel.getPhotos().getPhoto().get(0).getUrl()));

                    categoriesForItems.remove(0);
                    if (categoriesForItems.size() > 0) {
                        fillCategoryItemWithCategory(categoriesForItems.get(0));
                    } else {
                        categoryItemsPublishSubject.onNext(categoryItems);
                    }
                });
    }

}
