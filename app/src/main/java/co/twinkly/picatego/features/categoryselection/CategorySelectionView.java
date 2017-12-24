package co.twinkly.picatego.features.categoryselection;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.features.base.BaseView;
import co.twinkly.picatego.features.categorydetail.CategoryDetailActivity;
import co.twinkly.picatego.utils.constants.SharedPreferencesConstants;
import co.twinkly.picatego.utils.managers.SharedPreferencesManager;
import co.twinkly.picatego.utils.models.CategoryItem;
import io.reactivex.Observable;

public class CategorySelectionView extends BaseView implements CategorySelectionContract.View {

    @BindView(R.id.recycler_view_category_selection)
    RecyclerView recyclerView;

    private CategorySelectionAdapter categorySelectionAdapter;
    private GridLayoutManager gridLayoutManager;

    public CategorySelectionView(Context context) {
        super(context);

        rootView = inflate(getContext(), R.layout.activity_category_selection, this);
        ButterKnife.bind(this);

        boolean isFirstTimeUser = SharedPreferencesManager.getInstance(context).getBooleanValue(SharedPreferencesConstants.IS_FIRST_TIME_USER, true);

        gridLayoutManager = new GridLayoutManager(context, 3);
        if (isFirstTimeUser) {
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
        } else {
            updateGridLayoutManagerForNonFirstTimeUser();
        }
        recyclerView.setLayoutManager(gridLayoutManager);

        categorySelectionAdapter = new CategorySelectionAdapter(context);
        recyclerView.setAdapter(categorySelectionAdapter);
    }

    @Override
    public void updateData(List<CategoryItem> categoryItems) {
        categorySelectionAdapter.updateData(categoryItems);
    }

    @Override
    public Observable<Integer> onItemSelected() {
        return categorySelectionAdapter.getOnItemSelected();
    }

    @Override
    public void startCategoryDetailActivity(String categoryName) {
        context.startActivity(CategoryDetailActivity.newIntent(context, categoryName));
        updateGridLayoutManagerForNonFirstTimeUser();
    }

    private void updateGridLayoutManagerForNonFirstTimeUser() {
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position) {
                    case 0:
                        return 2;
                    case 5:
                        return 3;
                    default:
                        return 1;
                }
            }
        });
    }
}
