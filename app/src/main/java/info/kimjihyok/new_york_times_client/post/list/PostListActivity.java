package info.kimjihyok.new_york_times_client.post.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.activity.BaseActivity;
import info.kimjihyok.new_york_times_client.base.application.BaseApplication;
import info.kimjihyok.new_york_times_client.base.modules.PresenterModule;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.PostItem;
import info.kimjihyok.new_york_times_client.util.NavigationHelper;

/**
 * Start activity with news post items
 */
public class PostListActivity extends BaseActivity implements PostListPresenter.View {
  private static final String TAG = "PostListActivity";

  private RecyclerView mRecyclerView;
  private RecyclerView.LayoutManager mLayoutManager;
  private PostListAdapter mPostListAdapter;

  @Inject DataController mDataController;
  @Inject NavigationHelper mNavigationHelper;
  @Inject PostListPresenter mPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle("New York Times");
    }

    bindViews();

    BaseActivity.getActivityComponent()
        .inject(this);
  }

  @Override
  protected void onScreenChangeToLandscape() {
    mPostListAdapter.onScreenChangeToLandscape();
  }

  @Override
  protected void onScreenChangeToPortrait() {
    mPostListAdapter.onScreenChangeToPortrait();
  }

  private void bindViews() {
    mRecyclerView = (RecyclerView) findViewById(R.id.container);
    mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(mLayoutManager);

    mPostListAdapter = new PostListAdapter(new ArrayList<>(), getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE);
    mPostListAdapter.addOnClickListener(view -> {
      PostItem item = (PostItem) view.getTag();
      if (item != null) onPostItemClick(item);
    });
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
  public void onPostItemClick(PostItem postItem) {
    mNavigationHelper.goToPostDetailPage(postItem.getUrl());
  }

  @Override
  public void onSubscribe(List<PostItem> list) {
    mPostListAdapter.addAll(list);
  }

}
