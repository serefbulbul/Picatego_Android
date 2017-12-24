package co.twinkly.picatego.features.categoryselection;

import java.util.concurrent.TimeUnit;

import co.twinkly.picatego.features.base.BasePresenter;
import co.twinkly.picatego.utils.constants.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategorySelectionPresenter extends BasePresenter implements CategorySelectionContract.Presenter {

    private final CategorySelectionContract.View view;
    private final CategorySelectionContract.Interactor interactor;

    public CategorySelectionPresenter(CategorySelectionContract.View view, CategorySelectionContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void subscribe() {
        super.subscribe();

        compositeDisposable.add(observeOnItemSelected());

        updateData();
    }

    @Override
    public void updateData() {
        view.showProgressView();
        interactor.onCategoryItemsPrepared()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(categoryItems -> {
                    view.hideProgressView();
                    view.updateData(categoryItems);
                });
    }

    @Override
    public Disposable observeOnItemSelected() {
        return view.onItemSelected()
                .throttleFirst(Constants.THROTTLE_TIME_IN_MILLISECONDS, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribe(index -> {
                    interactor.increaseInterestForIndex(index);
                    view.startCategoryDetailActivity(interactor.getAllCategories().get(index).getName());
                });
    }
}
