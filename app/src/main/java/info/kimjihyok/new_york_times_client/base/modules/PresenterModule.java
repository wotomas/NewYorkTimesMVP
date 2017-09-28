package info.kimjihyok.new_york_times_client.base.modules;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.base.activity.ActivityScope;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.data.local.DataControllerInterface;
import info.kimjihyok.new_york_times_client.post.detail.PostDetailPresenter;
import info.kimjihyok.new_york_times_client.post.list.PostListPresenter;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */

@Module
public class PresenterModule {
  @Provides
  @ActivityScope
  PostListPresenter providesPostListPresenter(DataControllerInterface dataController) {
    return new PostListPresenter(dataController);
  }

  @Provides
  @ActivityScope
  PostDetailPresenter providesPostDetailPresenter(DataControllerInterface dataController) {
    return new PostDetailPresenter(dataController);
  }
}
