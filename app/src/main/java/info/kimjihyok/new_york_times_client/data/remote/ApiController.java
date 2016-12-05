package info.kimjihyok.new_york_times_client.data.remote;

import java.util.HashMap;
import java.util.Map;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.post.list.TopStoryResult;
import rx.Observable;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class ApiController {
    public static final String TAG = "ApiManager";
    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final String BASE_URL = "https://api.nytimes.com/";
    public static final String API_KEY = "82bdc92794734ebd8150a3dfb154bfa9";
    public Map<String, String> queryMap = new HashMap<>();

    private NewYorkTimesApiInterface mJobListInterface;

    public ApiController(NewYorkTimesApiInterface mJobListInterface) {
        this.mJobListInterface = mJobListInterface;
        this.queryMap.put("api-key", API_KEY);
    }

    public Observable<TopStoryResult> getTopStoriesList() {
        return mJobListInterface.postList(queryMap);
    }
}
