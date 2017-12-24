package co.twinkly.picatego.features.splash;

import android.content.Context;
import android.content.Intent;

import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;
import co.twinkly.picatego.features.categoryselection.CategorySelectionActivity;
import co.twinkly.picatego.features.welcome.WelcomeActivity;

public class SplashView extends BaseView implements SplashContract.View {

    public SplashView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_splash, this);
        ButterKnife.bind(this);
    }


    @Override
    public void startCategorySelectionActivity() {
        context.startActivity(CategorySelectionActivity.newIntent(context).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void startWelcomeActivity() {
        context.startActivity(WelcomeActivity.newIntent(context).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
