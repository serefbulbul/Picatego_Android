package co.twinkly.picatego.features.itemdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import co.twinkly.picatego.app.App;
import co.twinkly.picatego.features.base.BaseActivity;

public class ItemDetailActivity extends BaseActivity {

    private static final String IMAGE_URL = "IMAGE_URL";
    private static final String IMAGE_TITLE = "IMAGE_TITLE";

    @Inject
    ItemDetailContract.Presenter presenter;

    @Inject
    ItemDetailContract.View view;

    public static Intent newIntent(Context context, String imageUrl, String imageTitle) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(IMAGE_URL, imageUrl);
        intent.putExtra(IMAGE_TITLE, imageTitle);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerItemDetailComponent.builder()
                .itemDetailModule(new ItemDetailModule(this))
                .appComponent(App.get(this).component())
                .build().inject(this);

        setContentView(view.getRootView());

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            view.setImageFromUrl(extras.getString(IMAGE_URL));
            view.setText(extras.getString(IMAGE_TITLE));
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
