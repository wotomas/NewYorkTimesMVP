package info.kimjihyok.new_york_times_client.base.activity;

import dagger.Component;
import info.kimjihyok.new_york_times_client.base.application.MockApplicationComponent;
import info.kimjihyok.new_york_times_client.base.modules.ActivityModule;
import info.kimjihyok.new_york_times_client.base.modules.MockDataModule;
import info.kimjihyok.new_york_times_client.base.modules.PresenterModule;
import info.kimjihyok.new_york_times_client.post.detail.PostDetailActivity;
import info.kimjihyok.new_york_times_client.post.list.PostListActivity;

/**
 * Created by jihyokkim on 2017. 9. 27..
 */
@ActivityScope
@Component(
    dependencies = {
        MockApplicationComponent.class,
    }, modules = {
    ActivityModule.class,
    MockDataModule.class,
    PresenterModule.class
}
)
public interface MockActivityComponent extends ActivityComponent {
  void inject(PostListActivity postListActivity);
  void inject(PostDetailActivity postDetailActivity);
}
