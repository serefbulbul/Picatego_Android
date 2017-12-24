package co.twinkly.picatego.features.categorydetail;

import java.util.List;

import co.twinkly.picatego.features.base.BaseContract;
import co.twinkly.picatego.utils.services.network.models.Photo;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface CategoryDetailContract {

    interface View extends BaseContract.View {

        void updateData(List<String> urls);

        Observable<Integer> onItemSelected();

        void startItemDetailActivity(String categoryName, String title);
    }

    interface Presenter extends BaseContract.Presenter {

        void getPhotosForCategory();

        void setCategoryName(String categoryName);

        Disposable observeOnItemSelected();
    }

    interface Interactor extends BaseContract.Interactor {

        String getCategoryName();

        void setCategoryName(String categoryName);

        List<Photo> getPhotos();

        void setPhotos(List<Photo> photos);

        Observable<PhotoSearchResponseModel> getPhotosForTag();
    }
}
