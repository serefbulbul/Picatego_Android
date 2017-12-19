package co.twinkly.picatego.app;

import android.content.Context;

import co.twinkly.picatego.utils.managers.SharedPreferencesManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by serefbulbul on 19.12.2017.
 */

@Module
public class AppModule {

    private final Context context;

    AppModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    public Context context() {
        return context;
    }

    @AppScope
    @Provides
    public SharedPreferencesManager sharedPreferencesManager() {
        return SharedPreferencesManager.getInstance(context);
    }
}