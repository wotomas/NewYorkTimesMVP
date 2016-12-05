package info.kimjihyok.new_york_times_client.post.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.api.ApiController;
import info.kimjihyok.new_york_times_client.api.NewYorkTimesApiInterface;
import info.kimjihyok.new_york_times_client.base.BaseActivity;
import info.kimjihyok.new_york_times_client.db.PostItem;
import info.kimjihyok.new_york_times_client.util.DebugUtil;
import info.kimjihyok.new_york_times_client.util.NavigationHelper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

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

    //TODO: temp testing data, should remove
    private List<PostItem> mTestingData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);

        // Create simple REST adapter which points to the Google API
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(ApiController.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())).build();

        NewYorkTimesApiInterface service = retrofit.create(NewYorkTimesApiInterface.class);
        ApiController apiController = new ApiController(service);
        apiController.getTopStoriesList().doOnNext(topStoryResult -> {
            if (DEBUG) {
                Log.d(TAG, "PostListActivity(): topStoryResult " + topStoryResult.status + " " + topStoryResult.getNum_results());
            }
        }).subscribe();
        mPresenter = new PostListPresenter();
        mNavigationHelper = new NavigationHelper(this);
    }

    private void bindViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.container);
        // depends on grid orientation change params for layout manager
        // should reset layout manager on view rotate
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPostListAdapter = new PostListAdapter(DebugUtil.getDummyData());
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
