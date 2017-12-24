package co.twinkly.picatego.features.splash;

import android.content.Context;

import co.twinkly.picatego.utils.managers.SharedPreferencesManager;
import co.twinkly.picatego.utils.services.database.DatabaseService;
import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    private Context context;

    public SplashModule(Context context) {
        this.context = context;
    }

    @SplashScope
    @Provides
    public SplashContract.View view() {
        return new SplashView(context);
    }

    @SplashScope
    @Provides
    public SplashContract.Presenter presenter(SplashContract.View view, SplashContract.Interactor interactor, SharedPreferencesManager sharedPreferencesManager, DatabaseService databaseService) {
        return new SplashPresenter(view, interactor, sharedPreferencesManager, databaseService);
    }

    @SplashScope
    @Provides
    public SplashContract.Interactor interactor() {
        return new SplashInteractor();
    }
}
