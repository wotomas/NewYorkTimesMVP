package info.kimjihyok.new_york_times_client.post.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.db.PostItem;

/**
 * Created by jkimab on 2016. 12. 5..
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListItemViewHolder> implements View.OnClickListener {
    private static final String TAG = "PostListAdapter";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private List<PostItem> mPostItems;

    public PostListAdapter(List<PostItem> mPostItems) {
        this.mPostItems = mPostItems;
    }

    @Override
    public PostListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_postlist_item, parent, false);
        return new PostListItemViewHolder(v, parent.getContext(), this);
    }

    @Override
    public void onBindViewHolder(PostListItemViewHolder holder, int position) {
        holder.setTitle(mPostItems.get(position).getTitle());
        holder.setImageUrl(mPostItems.get(position).getMultimedia().get(0));
    }



    @Override
    public int getItemCount() {
        return mPostItems.size();
    }

    @Override
    public void onClick(View view) {
        if (DEBUG) Log.d(TAG, "onClick(): view: " + view.getTag());
    }

    static class PostListItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mThumbnail;
        private TextView mTitle;
        private Context mContext;

        PostListItemViewHolder(View itemView, Context context, View.OnClickListener listener) {
            super(itemView);
            mContext = context;
            mThumbnail = (ImageView) itemView.findViewById(R.id.image_thumbnail);
            mTitle = (TextView) itemView.findViewById(R.id.news_title_text);
            mTitle.setOnClickListener(listener);
            mTitle.setTag("Post title");
        }

        public void setImageUrl(Multimedia multimedia) {
            if (DEBUG) Log.d(TAG, "setImageUrl(): " + multimedia.getUrl() + " " + multimedia.getHeight() + " " + multimedia.getWidth());

            Picasso.with(mContext).load(multimedia.getUrl())
                    //.resize(multimedia.getWidth(), multimedia.getHeight()).centerCrop()
                    .into(mThumbnail);
        }

        public void setTitle(String mTitle) {
            this.mTitle.setText(mTitle);
        }
    }
}
