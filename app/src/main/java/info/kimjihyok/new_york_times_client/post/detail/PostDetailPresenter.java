package info.kimjihyok.new_york_times_client.post.detail;

import info.kimjihyok.new_york_times_client.base.BasePresenter;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class PostDetailPresenter implements BasePresenter<PostDetailPresenter.View> {
    private DataController mDataController;
    private View mMVPView;
    private String mPostUrlKey;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public PostDetailPresenter(DataController dataController, String postUrlKey) {
        this.mDataController = dataController;
        this.mPostUrlKey = postUrlKey;
    }

    @Override
    public void attachView(View view) {
        mMVPView = view;

        mSubscriptions.add(mDataController.getSinglePostItem(mPostUrlKey).doOnNext(postItem -> {
            if(postItem != null) {
                if(postItem.getMultimedia() != null) mMVPView.setMainImage(postItem.getMultimedia().get(1));
                mMVPView.setTitle(postItem.getTitle());
                mMVPView.setSectionText(postItem.getSection());
                mMVPView.setAuthor(postItem.getByline());
                mMVPView.setCreatedDate(postItem.getCreated_date());
            }
        }).subscribe());
    }

    @Override
    public void detachView() {
        this.mMVPView = null;
        if(mSubscriptions != null) mSubscriptions.unsubscribe();
    }

    public interface View {
        void setTitle(String text);
        void setMainImage(Multimedia media);
        void setSectionText(String text);
        void setAuthor(String author);
        void setCreatedDate(String date);
    }
}
