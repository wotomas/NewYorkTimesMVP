package info.kimjihyok.new_york_times_client.post.detail;

import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.base.BasePresenter;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

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


  private DataController mDataController;
  private View mMVPView;
  private CompositeSubscription mSubscriptions;
  private String mPostUrlKey;

  public PostDetailPresenter(DataController dataController) {
    this.mDataController = dataController;
    this.mSubscriptions = new CompositeSubscription();
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
          if (postItem != null) {
            mMVPView.setTitle(postItem.getTitle());
            mMVPView.setSectionText(postItem.getSection());
            mMVPView.setAuthor(postItem.getByline());
            mMVPView.setCreatedDate(postItem.getCreated_date());
            mMVPView.setMainImage(getOptimizedMedia(postItem.getMultimedia()));
          }
        }, onError -> {
          //if (DEBUG) Log.e(TAG, "attachView() error: ", onError);
        }));
  }

  public Multimedia getOptimizedMedia(List<Multimedia> multimedias) {
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
    if (mSubscriptions != null) mSubscriptions.unsubscribe();
  }

  public interface View {
    void setTitle(String text);

    void setMainImage(Multimedia media);

    void setSectionText(String text);

    void setAuthor(String author);

    void setCreatedDate(String date);
  }
}
