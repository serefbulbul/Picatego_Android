package co.twinkly.picatego.features.categorydetail;

import co.twinkly.picatego.features.base.BasePresenter;

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

    }
}
