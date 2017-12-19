package co.twinkly.picatego.features.welcome;

import co.twinkly.picatego.features.base.BasePresenter;

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

    }
}
