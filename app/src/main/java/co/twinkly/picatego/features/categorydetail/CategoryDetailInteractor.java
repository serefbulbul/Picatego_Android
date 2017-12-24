package co.twinkly.picatego.features.categorydetail;

import java.util.List;

import co.twinkly.picatego.features.base.BaseInteractor;
import co.twinkly.picatego.utils.services.network.NetworkService;
import co.twinkly.picatego.utils.services.network.models.Photo;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import io.reactivex.Observable;

public class CategoryDetailInteractor extends BaseInteractor implements CategoryDetailContract.Interactor {

    private final NetworkService networkService;
    private String categoryName;
    private List<Photo> photos;

    CategoryDetailInteractor(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public Observable<PhotoSearchResponseModel> getPhotosForTag() {
        return networkService.getPhotosForTag(categoryName);
    }
}
