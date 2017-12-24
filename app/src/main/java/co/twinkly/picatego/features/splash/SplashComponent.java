package co.twinkly.picatego.features.splash;

import co.twinkly.picatego.app.AppComponent;
import dagger.Component;

@SplashScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
