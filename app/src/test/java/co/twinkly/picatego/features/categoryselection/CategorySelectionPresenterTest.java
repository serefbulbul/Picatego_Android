package co.twinkly.picatego.features.categoryselection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import co.twinkly.picatego.utils.models.CategoryItem;
import co.twinkly.picatego.utils.services.database.models.Category;
import co.twinkly.picatego.utils.services.network.models.Photo;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import co.twinkly.picatego.utils.services.network.models.Photos;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by serefbulbul on 24.12.2017.
 */
public class CategorySelectionPresenterTest {

    private CategorySelectionView view;
    private CategorySelectionInteractor interactor;
    private CategorySelectionPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Before
    public void setUp() throws Exception {

        view = Mockito.mock(CategorySelectionView.class);
        interactor = Mockito.mock(CategorySelectionInteractor.class);

        presenter = new CategorySelectionPresenter(view, interactor);

        Category category = new Category("Motocycle", 1);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        PhotoSearchResponseModel photoSearchResponseModel = new PhotoSearchResponseModel();

        Photos photos = new Photos();
        Photo photo = new Photo();
        photo.setUrl("www.test.com");

        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo);

        photos.setPhoto(photoList);

        photoSearchResponseModel.setPhotos(photos);

        CategoryItem categoryItem = new CategoryItem(category, "www.test.com");
        List<CategoryItem> categoryItems = new ArrayList<>();
        categoryItems.add(categoryItem);

        Mockito.when(interactor.getAllCategories()).thenReturn(categories);
        Mockito.when(interactor.getPhotosForTag(category.getName())).thenReturn(Observable.just(photoSearchResponseModel));
        Mockito.when(interactor.onCategoryItemsPrepared()).thenReturn(Observable.just(categoryItems));

        Mockito.when(view.onItemSelected()).thenReturn(Observable.never());
    }

    @After
    public void tearDown() throws Exception {
        presenter.unsubscribe();
    }

    @Test
    public void testObserveOnItemSelected() throws Exception {
        Mockito.when(view.onItemSelected()).thenReturn(Observable.just(0));

        presenter.subscribe();

        Mockito.verify(view, Mockito.times(1)).startCategoryDetailActivity("Motocycle");
    }


}