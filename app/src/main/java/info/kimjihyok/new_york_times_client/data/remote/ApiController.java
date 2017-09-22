package info.kimjihyok.new_york_times_client.data.remote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.post.list.TopStoryResult;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class ApiController {
  public static final String TAG = "ApiManager";
  public static final boolean DEBUG = BuildConfig.DEBUG;
  public static final String BASE_URL = "https://api.nytimes.com/";
  public static final String API_KEY = "82bdc92794734ebd8150a3dfb154bfa9";
  public Map<String, String> queryMap = new HashMap<>();
  private static final int MAX_RETRIES = 3;

  private NewYorkTimesApiInterface mJobListInterface;

  public ApiController(NewYorkTimesApiInterface mJobListInterface) {
    this.mJobListInterface = mJobListInterface;
    this.queryMap.put("api-key", API_KEY);
  }

  public Observable<TopStoryResult> getTopStoriesList() {
    //Retry 3 times with exponential backoff interval, and if all fails, return empty observable
    return mJobListInterface.postList(queryMap)
        .retryWhen(errors -> errors
            .scan(0, (errCount, throwable) -> {
              if (errCount > MAX_RETRIES)
                throw new IOException("Network Retry Finished 3 times! Network not available");
              return errCount + 1;
            })
            .flatMap(retryCount -> Observable.timer((long) Math.pow(4, retryCount), TimeUnit.SECONDS))
        );
  }
}
