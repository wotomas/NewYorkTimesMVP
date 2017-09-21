package info.kimjihyok.new_york_times_client.base.modules;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.base.activity.ActivityScope;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.post.list.PostListPresenter;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */

@Module
public class PresenterModule {

  @Provides
  @ActivityScope
  PostListPresenter providesPostListPresenter(DataController dataController) {
    return new PostListPresenter(dataController);
  }
}
