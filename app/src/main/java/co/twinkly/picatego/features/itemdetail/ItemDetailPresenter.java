package co.twinkly.picatego.features.itemdetail;

import co.twinkly.picatego.features.base.BasePresenter;

public class ItemDetailPresenter extends BasePresenter implements ItemDetailContract.Presenter {

    private final ItemDetailContract.View view;
    private final ItemDetailContract.Interactor interactor;

    public ItemDetailPresenter(ItemDetailContract.View view, ItemDetailContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void subscribe() {
        super.subscribe();

    }
}
