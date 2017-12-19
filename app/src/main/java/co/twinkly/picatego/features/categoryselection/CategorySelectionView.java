package co.twinkly.picatego.features.categoryselection;

import android.content.Context;

import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;

public class CategorySelectionView extends BaseView implements CategorySelectionContract.View {

    public CategorySelectionView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_category_selection, this);
        ButterKnife.bind(this);
    }
}
