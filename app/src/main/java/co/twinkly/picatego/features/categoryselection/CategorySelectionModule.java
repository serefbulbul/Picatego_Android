package co.twinkly.picatego.features.categoryselection;

import android.content.Context;

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
    public CategorySelectionContract.Interactor interactor() {
        return new CategorySelectionInteractor();
    }
}
