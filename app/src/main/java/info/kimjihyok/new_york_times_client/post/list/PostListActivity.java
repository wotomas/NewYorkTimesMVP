package info.kimjihyok.new_york_times_client.post.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.BaseActivity;
import info.kimjihyok.new_york_times_client.util.NavigationHelper;

/**
 * Start activity with news post items
 */
public class PostListActivity extends BaseActivity implements PostListPresenter.View {
    private PostListPresenter mPresenter;
    private NavigationHelper mNavigationHelper;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        mPresenter = new PostListPresenter();
        mNavigationHelper = new NavigationHelper(this);
    }

    private void bindViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.container);
        // depends on grid orientation change params for layout manager
        // should reset layout manager on view rotate
        mLayoutManager = new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);


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
