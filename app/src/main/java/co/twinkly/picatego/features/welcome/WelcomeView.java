package co.twinkly.picatego.features.welcome;

import android.content.Context;

import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;

public class WelcomeView extends BaseView implements WelcomeContract.View {

    public WelcomeView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_welcome, this);
        ButterKnife.bind(this);
    }
}
