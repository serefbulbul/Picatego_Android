package co.twinkly.picatego.features.categorydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import co.twinkly.picatego.app.App;
import co.twinkly.picatego.features.base.BaseActivity;

public class CategoryDetailActivity extends BaseActivity {

    private static final String CATEGORY_NAME = "CATEGORY_NAME";

    @Inject
    CategoryDetailContract.Presenter presenter;

    @Inject
    CategoryDetailContract.View view;

    public static Intent newIntent(Context context, String categoryName) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(CATEGORY_NAME, categoryName);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerCategoryDetailComponent.builder()
                .categoryDetailModule(new CategoryDetailModule(this))
                .appComponent(App.get(this).component())
                .build().inject(this);

        setContentView(view.getRootView());

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            presenter.setCategoryName(extras.getString(CATEGORY_NAME));
        }
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
