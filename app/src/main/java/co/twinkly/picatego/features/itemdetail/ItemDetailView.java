package co.twinkly.picatego.features.itemdetail;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;

public class ItemDetailView extends BaseView implements ItemDetailContract.View {

    @BindView(R.id.image_view_item_detail)
    AppCompatImageView imageView;
    @BindView(R.id.text_view_item_detail)
    AppCompatTextView textView;

    public ItemDetailView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_item_detail, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setImageFromUrl(String url) {
        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).fit().into(imageView);
    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }
}
