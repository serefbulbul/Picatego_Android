package co.twinkly.picatego.features.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import co.twinkly.picatego.app.App;
import co.twinkly.picatego.features.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Inject
    WelcomeContract.Presenter presenter;

    @Inject
    WelcomeContract.View view;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWelcomeComponent.builder()
                .WelcomeModule(new WelcomeModule(this))
                .appComponent(App.get(this).component())
                .build().inject(this);

        setContentView(view.getRootView());
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.unsubscribe();
    }
}
