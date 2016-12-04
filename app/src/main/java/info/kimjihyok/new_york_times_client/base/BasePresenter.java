package info.kimjihyok.new_york_times_client.base;

/**
 * Created by jkimab on 2016. 12. 5..
 */
public interface BasePresenter<V> {
    void attachView(V view);
    void detachView();
}
