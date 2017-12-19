package co.twinkly.picatego.features.welcome;

import co.twinkly.picatego.app.AppComponent;
import dagger.Component;

@WelcomeScope
@Component(modules = WelcomeModule.class, dependencies = AppComponent.class)
public interface WelcomeComponent {
    void inject(WelcomeActivity activity);
}
