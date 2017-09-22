package info.kimjihyok.new_york_times_client.post.list;

import android.util.Log;

import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.base.BasePresenter;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.PostItem;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class PostListPresenter implements BasePresenter<PostListPresenter.View> {
  private static final String TAG = "PostListPresenter";
  private static final boolean DEBUG = BuildConfig.DEBUG;

  private CompositeDisposable mSubscriptions = new CompositeDisposable();
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
            .subscribe(onNext -> mMVPView.onSubscribe(onNext), onError -> {}));

  }

  @Override
  public void detachView() {
    this.mMVPView = null;
    if (mSubscriptions != null && !mSubscriptions.isDisposed()) mSubscriptions.dispose();
  }


  public PostListPresenter(DataController dataController) {
    mDataController = dataController;
  }
}
