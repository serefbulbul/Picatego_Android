package co.twinkly.picatego.features.splash;

import co.twinkly.picatego.features.base.BaseContract;

public interface SplashContract {

    interface View extends BaseContract.View {

        void startCategorySelectionActivity();

        void startWelcomeActivity();
    }

    interface Presenter extends BaseContract.Presenter {

        void createCategories();
    }

    interface Interactor extends BaseContract.Interactor {

    }
}
