package co.twinkly.picatego.features.welcome;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;

import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;
import co.twinkly.picatego.features.categoryselection.CategorySelectionActivity;
import io.reactivex.Observable;

public class WelcomeView extends BaseView implements WelcomeContract.View {

    @BindView(R.id.image_view_welcome_most_viewed)
    AppCompatImageView imageViewMostViewed;
    @BindView(R.id.button_welcome_categories)
    AppCompatButton buttonCategories;

    public WelcomeView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_welcome, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setMostViewedImage(String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).fit().into(imageViewMostViewed);
    }

    @Override
    public Observable<Object> onCategoriesButtonClick() {
        return RxView.clicks(buttonCategories);
    }

    @Override
    public void startCategorySelectionActivity() {
        context.startActivity(CategorySelectionActivity.newIntent(context));
    }
}
