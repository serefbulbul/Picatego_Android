package co.twinkly.picatego.features.categoryselection;

import co.twinkly.picatego.features.base.BasePresenter;

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

    }
}
