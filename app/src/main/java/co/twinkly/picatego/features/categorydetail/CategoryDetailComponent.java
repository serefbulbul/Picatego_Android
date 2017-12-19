package co.twinkly.picatego.features.categorydetail;

import co.twinkly.picatego.app.AppComponent;
import dagger.Component;

@CategoryDetailScope
@Component(modules = CategoryDetailModule.class, dependencies = AppComponent.class)
public interface CategoryDetailComponent {
    void inject(CategoryDetailActivity activity);
}
