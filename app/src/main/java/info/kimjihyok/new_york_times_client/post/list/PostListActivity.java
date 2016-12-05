package info.kimjihyok.new_york_times_client.post.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.BaseActivity;
import info.kimjihyok.new_york_times_client.base.BaseApplication;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.PostItem;
import info.kimjihyok.new_york_times_client.util.NavigationHelper;

/**
 * Start activity with news post items
 */
public class PostListActivity extends BaseActivity implements PostListPresenter.View {
    private static final String TAG = "PostListActivity";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private PostListPresenter mPresenter;
    private NavigationHelper mNavigationHelper;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PostListAdapter mPostListAdapter;
    private DataController mDataController;

    private View.OnClickListener itemClickListenter = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PostItem item = (PostItem) view.getTag();
            if(item != null) {
                onPostItemClick(item);
            } else {
                if (DEBUG) Log.d(TAG, "onClick(): view tag is null");
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        mDataController = new DataController(((BaseApplication) getApplication()).getDaoSession());
        mNavigationHelper = new NavigationHelper(this);
        mPresenter = new PostListPresenter(mDataController);
    }

    private void bindViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.container);
        // depends on grid orientation change params for layout manager
        // should reset layout manager on view rotate
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPostListAdapter = new PostListAdapter(new ArrayList<PostItem>());
        mPostListAdapter.addOnClickListener(itemClickListenter);
        mRecyclerView.setAdapter(mPostListAdapter);
        //mPostListAdapter = new PostListAdapter(DebugUtil.getDummyData());

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @Override
    public void onPostItemClick(PostItem postItem) {
        if (DEBUG) Log.d(TAG, "onPostItemClick(): view: " + postItem.getUrl());
        mNavigationHelper.goToPostDetailPage(1);
    }

    @Override
    public void onSubscribe(List<PostItem> list) {
        if (DEBUG) Log.d(TAG, "onSubscribe(): list " + list.size());
        mPostListAdapter.addAll(list);
    }

}
