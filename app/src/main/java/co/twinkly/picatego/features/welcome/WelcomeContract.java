package co.twinkly.picatego.features.welcome;

import co.twinkly.picatego.features.base.BaseContract;
import co.twinkly.picatego.utils.services.database.models.Category;
import co.twinkly.picatego.utils.services.network.models.PhotoDetailResponseModel;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface WelcomeContract {

    interface View extends BaseContract.View {

        void setMostViewedImage(String url);

        Observable<Object> onCategoriesButtonClick();

        void startCategorySelectionActivity();
    }

    interface Presenter extends BaseContract.Presenter {

        void getMostViewedCategoryImage();

        Disposable observeOnCategoriesButtonClick();
    }

    interface Interactor extends BaseContract.Interactor {

        Observable<PhotoSearchResponseModel> getPhotosForTag(String tag);

        Observable<PhotoDetailResponseModel> getPhotoDetailForId(String id);

        Category getMostInterestedCategory();
    }
}
