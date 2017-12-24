package co.twinkly.picatego.app;

import android.app.Activity;
import android.app.Application;
import android.support.compat.BuildConfig;

import co.twinkly.picatego.utils.constants.Constants;
import timber.log.Timber;

/**
 * Created by serefbulbul on 19.12.2017.
 */

public class App extends Application {

    private AppComponent appComponent;

    public static boolean isActivityActive;
    public static int activityCount;

    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    super.log(priority, Constants.TIMBER_LOG, message, t);
                }
            });
        }

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        activityCount = 0;
    }

    public AppComponent component() {
        return appComponent;
    }
}