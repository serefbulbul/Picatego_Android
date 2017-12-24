package co.twinkly.picatego.features.categoryselection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import co.twinkly.picatego.app.App;
import co.twinkly.picatego.features.base.BaseActivity;

public class CategorySelectionActivity extends BaseActivity {

    @Inject
    CategorySelectionContract.Presenter presenter;

    @Inject
    CategorySelectionContract.View view;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CategorySelectionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerCategorySelectionComponent.builder()
                .categorySelectionModule(new CategorySelectionModule(this))
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
