package info.kimjihyok.new_york_times_client.post.detail;

import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.base.BasePresenter;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.data.local.DataControllerInterface;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class PostDetailPresenter implements BasePresenter<PostDetailPresenter.View> {
  private static final String TAG = "PostDetailPresenter";
  private static final boolean DEBUG = BuildConfig.DEBUG;

  private static final String MIDEA_TYPE_STANDARD_THUMBNAIL = "Standard Thumbnail";
  private static final String MIDEA_TYPE_LARGE_THUMBNAIL = "thumbLarge";
  private static final String MIDEA_TYPE_NORMAL = "Normal";
  private static final String MIDEA_TYPE_SUPER_JUMBO = "superJumbo";


  private DataControllerInterface mDataController;
  private View mMVPView;
  private CompositeDisposable mSubscriptions;
  private String mPostUrlKey;

  public PostDetailPresenter(DataControllerInterface dataController) {
    this.mDataController = dataController;
    this.mSubscriptions = new CompositeDisposable();
  }

  public void setPostUrlKey(String mPostUrlKey) {
    this.mPostUrlKey = mPostUrlKey;
  }

  @Override
  public void attachView(View view) {
    mMVPView = view;

    mSubscriptions.add(mDataController.getSinglePostItem(mPostUrlKey)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(postItem -> {
            mMVPView.setTitle(postItem.getTitle());
            mMVPView.setSectionText(postItem.getSection());
            mMVPView.setAuthor(postItem.getByline());
            mMVPView.setCreatedDate(postItem.getCreated_date());
            mMVPView.setMainImage(getOptimizedMedia(postItem.getMultimedia()));
        }, onError -> {
          //if (DEBUG) Log.e(TAG, "attachView() error: ", onError);
        }));
  }

  private Multimedia getOptimizedMedia(List<Multimedia> multimedias) {
    if (multimedias == null) return null;

    for (Multimedia media : multimedias) {
      if (media.getFormat().equals(MIDEA_TYPE_SUPER_JUMBO)) {
        return media;
      }
    }

    return null;
  }

  @Override
  public void detachView() {
    this.mMVPView = null;
    if (mSubscriptions != null && !mSubscriptions.isDisposed()) mSubscriptions.dispose();
  }

  public interface View {
    void setTitle(String text);

    void setMainImage(Multimedia media);

    void setSectionText(String text);

    void setAuthor(String author);

    void setCreatedDate(String date);
  }
}
