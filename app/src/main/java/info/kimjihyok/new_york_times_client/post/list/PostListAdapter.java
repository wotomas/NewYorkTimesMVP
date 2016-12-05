package info.kimjihyok.new_york_times_client.post.list;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.db.PostItem;
import info.kimjihyok.new_york_times_client.util.ScreenUtil;

/**
 * Created by jkimab on 2016. 12. 5..
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListItemViewHolder> implements View.OnClickListener {
    private static final String TAG = "PostListAdapter";
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private List<PostItem> mPostItems;
    private View.OnClickListener mClickListener;

    public void addAll(List<PostItem> list) {
        // Add in to item list only if there are new posts
        // Since API calls the top stories, if the first elements are equal, should not add to adapter
        // if else, replace the whole list
        if(mPostItems == null || list == null || list.size() < 1) return;

        //if post item size is less than 0, just add all to list
        if(mPostItems.size() < 1) {
            mPostItems.addAll(list);
            notifyDataSetChanged();
        } else {
            String oldItemFirstUrlKey = mPostItems.get(0).getUrl();
            String newItemFirstUrlKey = list.get(0).getUrl();

            if (DEBUG) Log.d(TAG, "addAll(): " + oldItemFirstUrlKey + " " + newItemFirstUrlKey);

            if(!oldItemFirstUrlKey.equals(newItemFirstUrlKey)) {
                mPostItems.clear();
                mPostItems.addAll(list);
                notifyDataSetChanged();
            }
        }

    }


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
        PostItem postItem = mPostItems.get(position);
        if(postItem == null) return;
        holder.setTitle(postItem);

        List<Multimedia> postMediaList = postItem.getMultimedia();
        if (postMediaList != null && postMediaList.size() > 0 && postMediaList != Collections.EMPTY_LIST) {
            holder.setImageUrl(postMediaList.get(0));
        } else {
            //if (DEBUG) Log.d(TAG, "onBindViewHolder() list is empty use default image placeholder");
        }
    }



    @Override
    public int getItemCount() {
        return mPostItems.size();
    }

    @Override
    public void onClick(View view) {
        mClickListener.onClick(view);
    }

    public void addOnClickListener(View.OnClickListener itemClickListenter) {
        mClickListener = itemClickListenter;
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
            mTitle.setBackgroundColor(Color.parseColor("#AAAAAA"));
            mTitle.setOnClickListener(listener);
        }

        public void setImageUrl(Multimedia multimedia) {
            int screenWidth = ScreenUtil.getScreenWidth((Activity) mContext);
            //if (DEBUG) Log.d(TAG, "setImageUrl(): " + multimedia.getHeight() + " " + multimedia.getWidth() + " " + screenWidth);
            float widthPadding = ScreenUtil.getPixelFromDp(mContext, 52);
            int newWidth = (int) (screenWidth - widthPadding);
            Picasso.with(mContext).load(multimedia.getUrl())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.load_error_image)
                    .resize(newWidth / 2, newWidth / 2).centerInside()
                    .into(mThumbnail);
        }

        public void setTitle(PostItem item) {
            this.mTitle.setText(item.getTitle());
            this.mTitle.setTag(item);
        }
    }
}
