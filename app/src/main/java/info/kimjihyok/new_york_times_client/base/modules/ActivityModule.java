package info.kimjihyok.new_york_times_client.base.modules;

import android.app.Activity;
import android.support.v7.app.ActionBar;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.base.activity.ActivityScope;
import info.kimjihyok.new_york_times_client.base.activity.BaseActivity;
import info.kimjihyok.new_york_times_client.util.NavigationHelper;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */
@Module
public class ActivityModule {
  BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }

  @Provides
  @ActivityScope
  NavigationHelper providesNavigationHelper() {
    return new NavigationHelper(activity);
  }
}
