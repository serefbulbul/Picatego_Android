package co.twinkly.picatego.app;

import android.content.Context;

import co.twinkly.picatego.utils.managers.SharedPreferencesManager;
import co.twinkly.picatego.utils.services.database.DatabaseModule;
import co.twinkly.picatego.utils.services.database.DatabaseService;
import co.twinkly.picatego.utils.services.network.NetworkModule;
import co.twinkly.picatego.utils.services.network.NetworkService;
import dagger.Component;

/**
 * Created by serefbulbul on 19.12.2017.
 */

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {

    Context getContext();

    SharedPreferencesManager sharedPreferencesManager();

    DatabaseService databaseService();

    NetworkService networkService();
}