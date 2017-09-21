package info.kimjihyok.new_york_times_client.base.activity;

import dagger.Component;
import info.kimjihyok.new_york_times_client.base.application.ApplicationComponent;
import info.kimjihyok.new_york_times_client.post.detail.PostDetailActivity;
import info.kimjihyok.new_york_times_client.post.list.PostListActivity;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */
@ActivityScope
@Component(
    dependencies = {
        ApplicationComponent.class,
    }, modules = {
}
)
public interface ActivityComponent {
  void inject(PostListActivity postListActivity);
  void inject(PostDetailActivity postDetailActivity);
}
