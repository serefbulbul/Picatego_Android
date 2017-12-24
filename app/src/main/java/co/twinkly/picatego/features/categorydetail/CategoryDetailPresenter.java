package co.twinkly.picatego.features.categorydetail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import co.twinkly.picatego.features.base.BasePresenter;
import co.twinkly.picatego.utils.constants.Constants;
import co.twinkly.picatego.utils.services.network.models.Photo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryDetailPresenter extends BasePresenter implements CategoryDetailContract.Presenter {

    private final CategoryDetailContract.View view;
    private final CategoryDetailContract.Interactor interactor;

    public CategoryDetailPresenter(CategoryDetailContract.View view, CategoryDetailContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void subscribe() {
        super.subscribe();

        compositeDisposable.add(observeOnItemSelected());

        getPhotosForCategory();
    }

    @Override
    public void getPhotosForCategory() {
        interactor.getPhotosForTag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(photoSearchResponseModel -> {
                    view.hideProgressView();

                    interactor.setPhotos(photoSearchResponseModel.getPhotos().getPhoto());

                    List<String> urls = new ArrayList<>();

                    for (Photo photo: photoSearchResponseModel.getPhotos().getPhoto()) {
                        urls.add(photo.getUrl());
                    }

                    view.updateData(urls);
                });
    }

    @Override
    public void setCategoryName(String categoryName) {
        interactor.setCategoryName(categoryName);
    }

    @Override
    public Disposable observeOnItemSelected() {
        return view.onItemSelected()
                .throttleFirst(Constants.THROTTLE_TIME_IN_MILLISECONDS, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribe(index -> {
                    Photo photo = interactor.getPhotos().get(index);
                    view.startItemDetailActivity(photo.getUrl(), photo.getTitle());
                });
    }
}
