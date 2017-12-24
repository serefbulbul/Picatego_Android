package co.twinkly.picatego.features.welcome;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

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
public class WelcomePresenterTest {

    private WelcomeView view;
    private WelcomeInteractor interactor;
    private WelcomePresenter presenter;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Before
    public void setUp() throws Exception {

        view = Mockito.mock(WelcomeView.class);
        interactor = Mockito.mock(WelcomeInteractor.class);

        presenter = new WelcomePresenter(view, interactor);

        Category category = new Category("Motocycle", 1);

        PhotoSearchResponseModel photoSearchResponseModel = new PhotoSearchResponseModel();

        Photos photos = new Photos();
        Photo photo = new Photo();
        photo.setUrl("www.test.com");

        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo);

        photos.setPhoto(photoList);

        photoSearchResponseModel.setPhotos(photos);

        Mockito.when(interactor.getMostInterestedCategory()).thenReturn(category);
        Mockito.when(interactor.getPhotosForTag(category.getName())).thenReturn(Observable.just(photoSearchResponseModel));

        Mockito.when(view.onCategoriesButtonClick()).thenReturn(Observable.never());
    }

    @After
    public void tearDown() throws Exception {
        presenter.unsubscribe();
    }

    @Test
    public void testObserveCategoriesButtonClick() throws Exception {
        Mockito.when(view.onCategoriesButtonClick()).thenReturn(Observable.just(new Object()));

        presenter.subscribe();

        Mockito.verify(view, Mockito.times(1)).startCategorySelectionActivity();
    }

}