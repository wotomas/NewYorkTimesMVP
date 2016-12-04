package info.kimjihyok.new_york_times_client.post.list;

import info.kimjihyok.new_york_times_client.base.BasePresenter;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class PostListPresenter implements BasePresenter<PostListPresenter.View> {
    @Override
    public void attachView(View view) {

    }

    @Override
    public void detachView() {

    }

    public interface View {
        void onPostItemClick(int postItemKey);

    }

    public PostListPresenter() {

    }
}
