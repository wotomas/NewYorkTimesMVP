package info.kimjihyok.new_york_times_client.data.local;

import java.util.List;

import info.kimjihyok.new_york_times_client.db.PostItem;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by jihyokkim on 2017. 9. 28..
 */

public interface DataControllerInterface {
  Observable<List<PostItem>> getInitLocalData();
  Observable<List<PostItem>> getRemoteData();
  Observable<List<PostItem>> getCombinedPosts();
  Maybe<PostItem> getSinglePostItem(String url);
}
