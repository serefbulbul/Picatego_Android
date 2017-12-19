package co.twinkly.picatego.utils.services.network;

import android.content.Context;

import java.io.File;
import java.util.Arrays;

import co.twinkly.picatego.app.AppScope;
import co.twinkly.picatego.utils.constants.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by serefbulbul on 19.12.2017.
 */

@Module
public class NetworkModule {

    @AppScope
    @Provides
    public Cache cache(Context context) {
        return new Cache(new File(context.getCacheDir(), Constants.HTTP_CACHE_DIR),
                Constants.HTTP_CACHE_SIZE);
    }

    @AppScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(Timber::d);

        return httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @AppScope
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS,
                        ConnectionSpec.COMPATIBLE_TLS,
                        ConnectionSpec.CLEARTEXT))
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    @AppScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.google.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @AppScope
    @Provides
    public NetworkService networkService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }
}