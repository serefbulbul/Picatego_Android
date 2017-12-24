package co.twinkly.picatego.utils.services.database;

import android.content.Context;

import co.twinkly.picatego.app.AppScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by serefbulbul on 19.12.2017.
 */

@Module
public class DatabaseModule {

    @AppScope
    @Provides
    public DatabaseService databaseService(Context context) {
        return new DatabaseService(context);
    }
}
