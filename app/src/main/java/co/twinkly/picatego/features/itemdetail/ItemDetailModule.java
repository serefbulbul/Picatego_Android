package co.twinkly.picatego.features.itemdetail;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemDetailModule {

    private Context context;

    public ItemDetailModule(Context context) {
        this.context = context;
    }

    @ItemDetailScope
    @Provides
    public ItemDetailContract.View view() {
        return new ItemDetailView(context);
    }

    @ItemDetailScope
    @Provides
    public ItemDetailContract.Presenter presenter(ItemDetailContract.View view, ItemDetailContract.Interactor interactor) {
        return new ItemDetailPresenter(view, interactor);
    }

    @ItemDetailScope
    @Provides
    public ItemDetailContract.Interactor interactor() {
        return new ItemDetailInteractor();
    }
}
