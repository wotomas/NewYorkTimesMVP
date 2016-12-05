package info.kimjihyok.new_york_times_client.post.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

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

    //TODO: temp testing data, should remove
    private List<PostItem> mTestingData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataController = new DataController(((BaseApplication) getApplication()).getDaoSession());

        bindViews();
        mPresenter = new PostListPresenter();
        mNavigationHelper = new NavigationHelper(this);
    }

    private void bindViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.container);
        // depends on grid orientation change params for layout manager
        // should reset layout manager on view rotate
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPostListAdapter = new PostListAdapter(mDataController.getInitLocalData());
        //mPostListAdapter = new PostListAdapter(DebugUtil.getDummyData());
        mRecyclerView.setAdapter(mPostListAdapter);
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
    public void onPostItemClick(int postItemKey) {
        mNavigationHelper.goToPostDetailPage(postItemKey);
    }

}
