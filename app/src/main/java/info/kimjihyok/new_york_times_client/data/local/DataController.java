package info.kimjihyok.new_york_times_client.data.local;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.data.remote.ApiController;
import info.kimjihyok.new_york_times_client.db.DaoSession;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.db.MultimediaDao;
import info.kimjihyok.new_york_times_client.db.PostItem;
import info.kimjihyok.new_york_times_client.db.PostItemDao;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class DataController {
    private static final String TAG = "DataController";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private ApiController mApiController;
    private DaoSession mDaoSession;

    public DataController(DaoSession mDaoSession, ApiController apiController) {
        this.mDaoSession = mDaoSession;
        this.mApiController = apiController;
    }

    public Observable<List<PostItem>> getInitLocalData() {
        return Observable.fromIterable(mDaoSession.getPostItemDao().loadAll())
            .flatMap(item -> Observable.just(mDaoSession.getMultimediaDao()
                .queryBuilder()
                .where(MultimediaDao.Properties.Post_url.eq(item.getUrl()))
                .list())
                .doOnNext(item::setMultimedia)
                .map(multimedia -> item))
            .toList().toObservable();
    }

    public Observable<List<PostItem>> getRemoteData() {
        return mApiController.getTopStoriesList()
            .flatMap(topStoryResult -> Observable.fromIterable(topStoryResult.getResults())
                .doOnNext(result -> mDaoSession.getPostItemDao().insertOrReplaceInTx(result))
                .doOnNext(result -> {
                    for (Multimedia media : result.getMultimedia()) {
                        media.setPostItem(result);
                        mDaoSession.getMultimediaDao().insertOrReplaceInTx(media);
                    }
                }))
            .toList().toObservable();
    }

    public Observable<List<PostItem>> getCombinedPosts() {
        return getRemoteData().publish(
            network -> Observable.merge(
                network,
                getInitLocalData().takeUntil(network)
            ))
            .debounce(200, TimeUnit.MILLISECONDS);
    }

    public Maybe<PostItem> getSinglePostItem(String url) {
        return Observable.fromIterable(mDaoSession.getPostItemDao().queryBuilder().where(PostItemDao.Properties.Url.eq(url)).list())
            .flatMap(postItem -> Observable.just(mDaoSession.getMultimediaDao().queryBuilder().where(MultimediaDao.Properties.Post_url.eq(url)).list())
                .doOnNext(postItem::setMultimedia)
                .map(mediaList -> postItem))
            .firstElement();
    }
}
