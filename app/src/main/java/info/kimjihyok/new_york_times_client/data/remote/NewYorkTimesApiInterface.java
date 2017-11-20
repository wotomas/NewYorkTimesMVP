package info.kimjihyok.new_york_times_client.data.remote;

import java.util.Map;

import info.kimjihyok.new_york_times_client.post.list.TopStoryResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public interface NewYorkTimesApiInterface {
    // GET https://api.nytimes.com/
    // https://api.nytimes.com/svc/topstories/v2/home.json?api-key=82bdc92794734ebd8150a3dfb154bfa9

    @GET("/svc/topstories/v2/home.json")
    Observable<TopStoryResult> postList (@QueryMap Map<String, String> query);
}
