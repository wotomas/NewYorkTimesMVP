package info.kimjihyok.new_york_times_client.data.local;

import java.util.List;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.data.remote.ApiController;
import info.kimjihyok.new_york_times_client.data.remote.NewYorkTimesApiInterface;
import info.kimjihyok.new_york_times_client.db.DaoSession;
import info.kimjihyok.new_york_times_client.db.Multimedia;
import info.kimjihyok.new_york_times_client.db.MultimediaDao;
import info.kimjihyok.new_york_times_client.db.PostItem;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class DataController {
    private static final String TAG = "DataController";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private ApiController mApiController;
    private DaoSession mDaoSession;

    public DataController(DaoSession mDaoSession) {
        this.mDaoSession = mDaoSession;
        init();
    }

    private void init() {
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
        mApiController = new ApiController(service);
        mApiController.getTopStoriesList().doOnNext(topStoryResult -> {
            for(PostItem result: topStoryResult.getResults()) {
                mDaoSession.getPostItemDao().insertOrReplaceInTx(result);
                for(Multimedia media : result.getMultimedia()) {
                    media.setPostItem(result);
                    mDaoSession.getMultimediaDao().insertOrReplaceInTx(media);
                }
            }
        }).subscribe();
    }


    public List<PostItem> getInitLocalData() {
        // Manually fetch media from local DB and put it to memory
        List<PostItem> items = mDaoSession.getPostItemDao().loadAll();

        for(PostItem item: items) {
            List<Multimedia> mediaList = mDaoSession.getMultimediaDao().queryBuilder().where(MultimediaDao.Properties.Post_id.eq(item.getId())).list();
            //if (DEBUG) Log.d(TAG, "getInitLocalData(): " + mediaList.size());
            item.setMultimedia(mediaList);
        }

        return items;
    }
}