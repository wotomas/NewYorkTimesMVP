package info.kimjihyok.new_york_times_client.base.modules;

import java.util.Collections;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.base.activity.ActivityScope;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.data.local.DataControllerInterface;
import info.kimjihyok.new_york_times_client.data.remote.ApiController;
import info.kimjihyok.new_york_times_client.db.DaoSession;
import info.kimjihyok.new_york_times_client.db.DaoSessionInterface;
import info.kimjihyok.new_york_times_client.db.PostItem;
import io.reactivex.Maybe;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jihyokkim on 2017. 9. 27..
 */
@Module
public class MockDataModule {

  @Provides
  @ActivityScope
  DataControllerInterface providesDataController() {
    DataControllerInterface controller = mock(DataControllerInterface.class);
    when(controller.getCombinedPosts()).thenReturn(getFakeCombinedPosts());
    when(controller.getInitLocalData()).thenReturn(getFakeInitLocalData());
    when(controller.getRemoteData()).thenReturn(getFaceRemoteData());
    when(controller.getSinglePostItem(anyString())).thenReturn(getFakeSinglePostItem());
    return controller;
  }

  private Maybe<PostItem> getFakeSinglePostItem() {
    return Maybe.just(new PostItem());
  }

  private Observable<List<PostItem>> getFaceRemoteData() {
    return Observable.just(Collections.emptyList());
  }

  private Observable<List<PostItem>> getFakeInitLocalData() {
    return Observable.just(Collections.emptyList());
  }

  private Observable<List<PostItem>> getFakeCombinedPosts() {
    return Observable.just(Collections.emptyList());
  }
}