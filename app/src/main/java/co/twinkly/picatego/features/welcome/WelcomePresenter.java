package co.twinkly.picatego.features.welcome;

import java.util.concurrent.TimeUnit;

import co.twinkly.picatego.features.base.BasePresenter;
import co.twinkly.picatego.utils.constants.Constants;
import co.twinkly.picatego.utils.services.database.models.Category;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WelcomePresenter extends BasePresenter implements WelcomeContract.Presenter {

    private final WelcomeContract.View view;
    private final WelcomeContract.Interactor interactor;

    public WelcomePresenter(WelcomeContract.View view, WelcomeContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void subscribe() {
        super.subscribe();

        compositeDisposable.add(observeOnCategoriesButtonClick());

        getMostViewedCategoryImage();
    }

    @Override
    public void getMostViewedCategoryImage() {
        Category mostViewedCategory = interactor.getMostInterestedCategory();

        view.showProgressView();
        interactor.getPhotosForTag(mostViewedCategory.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(photoSearchResponseModel -> {
                    view.hideProgressView();
                    view.setMostViewedImage(photoSearchResponseModel.getPhotos().getPhoto().get(0).getUrl());
                });
    }

    @Override
    public Disposable observeOnCategoriesButtonClick() {
        return view.onCategoriesButtonClick()
                .throttleFirst(Constants.THROTTLE_TIME_IN_MILLISECONDS, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribe(o -> view.startCategorySelectionActivity());
    }
}
