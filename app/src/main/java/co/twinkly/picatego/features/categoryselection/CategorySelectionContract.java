package co.twinkly.picatego.features.categoryselection;

import java.util.List;

import co.twinkly.picatego.features.base.BaseContract;
import co.twinkly.picatego.utils.models.CategoryItem;
import co.twinkly.picatego.utils.services.database.models.Category;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface CategorySelectionContract {

    interface View extends BaseContract.View {

        void updateData(List<CategoryItem> categoryItems);

        Observable<Integer> onItemSelected();

        void startCategoryDetailActivity(String categoryName);
    }

    interface Presenter extends BaseContract.Presenter {

        void updateData();

        Disposable observeOnItemSelected();
    }

    interface Interactor extends BaseContract.Interactor {

        List<Category> getAllCategories();

        Observable<List<CategoryItem>> onCategoryItemsPrepared();

        Observable<PhotoSearchResponseModel> getPhotosForTag(String tag);

        void increaseInterestForIndex(int index);
    }
}
