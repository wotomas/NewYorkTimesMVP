package info.kimjihyok.new_york_times_client.post.detail;

import info.kimjihyok.new_york_times_client.base.BasePresenter;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class PostDetailPresenter implements BasePresenter<PostDetailPresenter.View> {

    @Override
    public void attachView(View view) {

    }

    @Override
    public void detachView() {

    }

    public interface View {
        void setTitle(String text);
        void setMainImage(String url);
        void setBodyText(String text);
    }
}
