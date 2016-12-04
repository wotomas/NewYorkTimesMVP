package info.kimjihyok.new_york_times_client.post.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.db.PostItem;

/**
 * Created by jkimab on 2016. 12. 5..
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListItemViewHolder> {
    List<PostItem> mPostItems;

    public PostListAdapter(List<PostItem> mPostItems) {
        this.mPostItems = mPostItems;
    }

    @Override
    public PostListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_postlist_item, parent, false);
        return new PostListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostListItemViewHolder holder, int position) {
        holder.setTitle(mPostItems.get(position).getTitle());
        holder.setImageUrl(mPostItems.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return mPostItems.size();
    }

    static class PostListItemViewHolder extends RecyclerView.ViewHolder {
        private String mTitle;
        private String mImageUrl;

        PostListItemViewHolder(View itemView) {
            super(itemView);
        }

        public String getImageUrl() {
            return mImageUrl;
        }

        public void setImageUrl(String mImageUrl) {
            this.mImageUrl = mImageUrl;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }
    }
}
