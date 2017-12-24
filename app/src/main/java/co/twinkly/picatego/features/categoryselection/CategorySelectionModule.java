package co.twinkly.picatego.features.categoryselection;

import android.content.Context;

import co.twinkly.picatego.utils.managers.SharedPreferencesManager;
import co.twinkly.picatego.utils.services.database.DatabaseService;
import co.twinkly.picatego.utils.services.network.NetworkService;
import dagger.Module;
import dagger.Provides;

@Module
public class CategorySelectionModule {

    private Context context;

    public CategorySelectionModule(Context context) {
        this.context = context;
    }

    @CategorySelectionScope
    @Provides
    public CategorySelectionContract.View view() {
        return new CategorySelectionView(context);
    }

    @CategorySelectionScope
    @Provides
    public CategorySelectionContract.Presenter presenter(CategorySelectionContract.View view, CategorySelectionContract.Interactor interactor) {
        return new CategorySelectionPresenter(view, interactor);
    }

    @CategorySelectionScope
    @Provides
    public CategorySelectionContract.Interactor interactor(NetworkService networkService, DatabaseService databaseService, SharedPreferencesManager sharedPreferencesManager) {
        return new CategorySelectionInteractor(networkService, databaseService, sharedPreferencesManager);
    }
}
