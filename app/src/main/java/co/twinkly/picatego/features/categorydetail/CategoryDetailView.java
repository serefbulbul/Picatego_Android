package co.twinkly.picatego.features.categorydetail;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;
import co.twinkly.picatego.features.itemdetail.ItemDetailActivity;
import io.reactivex.Observable;

public class CategoryDetailView extends BaseView implements CategoryDetailContract.View {

    @BindView(R.id.recycler_view_category_detail)
    RecyclerView recyclerView;

    private CategoryDetailAdapter categoryDetailAdapter;
    private GridLayoutManager gridLayoutManager;

    public CategoryDetailView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_category_detail, this);
        ButterKnife.bind(this);

        gridLayoutManager = new GridLayoutManager(context, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);

        categoryDetailAdapter = new CategoryDetailAdapter(context);
        recyclerView.setAdapter(categoryDetailAdapter);
    }

    @Override
    public void updateData(List<String> urls) {
        categoryDetailAdapter.updateData(urls);
    }

    @Override
    public Observable<Integer> onItemSelected() {
        return categoryDetailAdapter.getOnItemSelected();
    }

    @Override
    public void startItemDetailActivity(String categoryName, String title) {
        context.startActivity(ItemDetailActivity.newIntent(context, categoryName, title));
    }
}
