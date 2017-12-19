package co.twinkly.picatego.features.itemdetail;

import co.twinkly.picatego.app.AppComponent;
import dagger.Component;

@ItemDetailScope
@Component(modules = ItemDetailModule.class, dependencies = AppComponent.class)
public interface ItemDetailComponent {
    void inject(ItemDetailActivity activity);
}
