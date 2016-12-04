package info.kimjihyok.new_york_times_client.post.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.BaseActivity;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.db.PostItem;
import info.kimjihyok.new_york_times_client.util.NavigationHelper;

/**
 * Start activity with news post items
 */
public class PostListActivity extends BaseActivity implements PostListPresenter.View {
    private PostListPresenter mPresenter;
    private NavigationHelper mNavigationHelper;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PostListAdapter mPostListAdapter;

    //TODO: temp testing data, should remove
    private List<PostItem> mTestingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestingData = new ArrayList<>();
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
        mPostListAdapter = new PostListAdapter(getDummyData());

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

    public List<PostItem> getDummyData() {
        Multimedia media1 = new Multimedia();
        media1.setUrl("https://static01.nyt.com/images/2016/12/04/world/DIPLO1/DIPLO1-thumbLarge.jpg");
        media1.setHeight(75);
        media1.setWidth(75);
        List<Multimedia> mediaList = new ArrayList<>();
        mediaList.add(media1);

        PostItem item1 = new PostItem();
        item1.setTitle("Dummy Data");
        item1.setMultimedia(mediaList);

        PostItem item2 = new PostItem();
        item2.setTitle("Dummy Data2");
        item2.setMultimedia(mediaList);

        PostItem item3 = new PostItem();
        item3.setTitle("Dummy Data3");
        item3.setMultimedia(mediaList);

        PostItem item4 = new PostItem();
        item4.setTitle("Dummy Data4");
        item4.setMultimedia(mediaList);

        PostItem item5 = new PostItem();
        item5.setTitle("Dummy Data5");
        item5.setMultimedia(mediaList);


        mTestingData.add(item1);
        mTestingData.add(item2);
        mTestingData.add(item3);
        mTestingData.add(item4);
        mTestingData.add(item5);

        return mTestingData;
    }
}
