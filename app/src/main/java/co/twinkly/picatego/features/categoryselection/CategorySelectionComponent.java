package co.twinkly.picatego.features.categoryselection;

import co.twinkly.picatego.app.AppComponent;
import dagger.Component;

@CategorySelectionScope
@Component(modules = CategorySelectionModule.class, dependencies = AppComponent.class)
public interface CategorySelectionComponent {
    void inject(CategorySelectionActivity activity);
}
