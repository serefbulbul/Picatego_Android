package co.twinkly.picatego.features.welcome;

import android.content.Context;

import co.twinkly.picatego.utils.services.database.DatabaseService;
import co.twinkly.picatego.utils.services.network.NetworkService;
import dagger.Module;
import dagger.Provides;

@Module
public class WelcomeModule {

    private Context context;

    public WelcomeModule(Context context) {
        this.context = context;
    }

    @WelcomeScope
    @Provides
    public WelcomeContract.View view() {
        return new WelcomeView(context);
    }

    @WelcomeScope
    @Provides
    public WelcomeContract.Presenter presenter(WelcomeContract.View view, WelcomeContract.Interactor interactor) {
        return new WelcomePresenter(view, interactor);
    }

    @WelcomeScope
    @Provides
    public WelcomeContract.Interactor interactor(NetworkService networkService, DatabaseService databaseService) {
        return new WelcomeInteractor(networkService, databaseService);
    }
}
