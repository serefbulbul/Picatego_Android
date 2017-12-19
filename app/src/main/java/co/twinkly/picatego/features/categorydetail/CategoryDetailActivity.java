package co.twinkly.picatego.features.categorydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import co.twinkly.picatego.app.App;
import co.twinkly.picatego.features.base.BaseActivity;

public class CategoryDetailActivity extends BaseActivity {

    @Inject
    CategoryDetailContract.Presenter presenter;

    @Inject
    CategoryDetailContract.View view;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerCategoryDetailComponent.builder()
                .CategoryDetailModule(new CategoryDetailModule(this))
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
