package co.twinkly.picatego.features.categorydetail;

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
import io.reactivex.subjects.PublishSubject;

/**
 * Created by serefbulbul on 24.12.2017.
 */

public class CategoryDetailAdapter extends RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder> {

    private final Context context;
    private List<String> urls;

    private PublishSubject<Integer> onItemSelected = PublishSubject.create();

    public PublishSubject<Integer> getOnItemSelected() {
        return onItemSelected;
    }

    public String getItem(int position) {
        return urls.get(position);
    }

    public CategoryDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CategoryDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_category_detail, parent, false);
        CategoryDetailAdapter.ViewHolder viewHolder = new CategoryDetailAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CategoryDetailAdapter.ViewHolder holder, int position) {
        final String url = urls.get(position);

        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).fit().into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            onItemSelected.onNext(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        if (urls == null) {
            return 0;
        } else {
            return urls.size();
        }
    }

    public void updateData(List<String> urls) {
        this.urls = urls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_recycler_view_category_detail)
        AppCompatImageView imageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}