package co.twinkly.picatego.features.welcome;

import co.twinkly.picatego.features.base.BaseInteractor;
import co.twinkly.picatego.utils.services.database.DatabaseService;
import co.twinkly.picatego.utils.services.database.models.Category;
import co.twinkly.picatego.utils.services.network.NetworkService;
import co.twinkly.picatego.utils.services.network.models.PhotoDetailResponseModel;
import co.twinkly.picatego.utils.services.network.models.PhotoSearchResponseModel;
import io.reactivex.Observable;

public class WelcomeInteractor extends BaseInteractor implements WelcomeContract.Interactor {

    private final NetworkService networkService;
    private final DatabaseService databaseService;

    WelcomeInteractor(NetworkService networkService, DatabaseService databaseService) {
        this.networkService = networkService;
        this.databaseService = databaseService;
    }

    @Override
    public Observable<PhotoSearchResponseModel> getPhotosForTag(String tag) {
        return networkService.getPhotosForTag(tag);
    }

    @Override
    public Observable<PhotoDetailResponseModel> getPhotoDetailForId(String id) {
        return networkService.getPhotoDetailForId(id);
    }

    @Override
    public Category getMostInterestedCategory() {
        return databaseService.getMostInterestedCategory();
    }
}
