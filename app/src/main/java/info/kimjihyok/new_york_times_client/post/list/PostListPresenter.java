package info.kimjihyok.new_york_times_client.post.list;

import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.base.BasePresenter;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.PostItem;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class PostListPresenter implements BasePresenter<PostListPresenter.View> {
    private static final String TAG = "PostListPresenter";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private DataController mDataController;
    private View mMVPView;

    public interface View {
        void onPostItemClick(PostItem postItem);
        void onSubscribe(List<PostItem> list);
    }

    @Override
    public void attachView(View view) {
        mMVPView = view;

        mSubscriptions.add(
                mDataController.getCombinedPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onNext -> {
                            //if(DEBUG) Log.d(TAG, "onNext: " + onNext.size());
                            mMVPView.onSubscribe(onNext);
                        }, onError -> {
                            //if(DEBUG) Log.e(TAG, "onError: " + onError.getMessage());
                        }));

    }

    @Override
    public void detachView() {
        this.mMVPView = null;
        if(mSubscriptions != null) mSubscriptions.unsubscribe();
    }


    public PostListPresenter(DataController dataController) {
        mDataController = dataController;
    }
}
