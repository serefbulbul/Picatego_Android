package co.twinkly.picatego.features.categoryselection;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.twinkly.picatego.R;
import co.twinkly.picatego.utils.models.CategoryItem;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by serefbulbul on 24.12.2017.
 */

public class CategorySelectionAdapter extends RecyclerView.Adapter<CategorySelectionAdapter.ViewHolder> {

    private final Context context;
    private List<CategoryItem> categories;

    private PublishSubject<Integer> onItemSelected = PublishSubject.create();

    public PublishSubject<Integer> getOnItemSelected() {
        return onItemSelected;
    }

    public CategoryItem getItem(int position) {
        return categories.get(position);
    }

    public CategorySelectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_category_selection, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CategoryItem categoryItem = categories.get(position);

        Picasso.with(context).load(categoryItem.getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            onItemSelected.onNext(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        if (categories == null) {
            return 0;
        } else {
            return categories.size();
        }
    }

    public void updateData(List<CategoryItem> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_recycler_view_category_selection)
        AppCompatImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}