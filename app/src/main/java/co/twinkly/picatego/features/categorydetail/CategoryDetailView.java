package co.twinkly.picatego.features.categorydetail;

import android.content.Context;

import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;

public class CategoryDetailView extends BaseView implements CategoryDetailContract.View {

    public CategoryDetailView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_category_detail, this);
        ButterKnife.bind(this);
    }
}
