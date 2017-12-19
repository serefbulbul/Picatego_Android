package co.twinkly.picatego.features.itemdetail;

import android.content.Context;

import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;

public class ItemDetailView extends BaseView implements ItemDetailContract.View {

    public ItemDetailView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_item_detail, this);
        ButterKnife.bind(this);
    }
}
