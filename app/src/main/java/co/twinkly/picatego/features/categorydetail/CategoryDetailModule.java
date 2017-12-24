package co.twinkly.picatego.features.categorydetail;

import android.content.Context;

import co.twinkly.picatego.utils.services.network.NetworkService;
import dagger.Module;
import dagger.Provides;

@Module
public class CategoryDetailModule {

    private Context context;

    public CategoryDetailModule(Context context) {
        this.context = context;
    }

    @CategoryDetailScope
    @Provides
    public CategoryDetailContract.View view() {
        return new CategoryDetailView(context);
    }

    @CategoryDetailScope
    @Provides
    public CategoryDetailContract.Presenter presenter(CategoryDetailContract.View view, CategoryDetailContract.Interactor interactor) {
        return new CategoryDetailPresenter(view, interactor);
    }

    @CategoryDetailScope
    @Provides
    public CategoryDetailContract.Interactor interactor(NetworkService networkService) {
        return new CategoryDetailInteractor(networkService);
    }
}
