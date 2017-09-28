package info.kimjihyok.new_york_times_client.base;

import android.app.Activity;
import android.app.Application;

import info.kimjihyok.new_york_times_client.base.activity.ActivityComponent;
import info.kimjihyok.new_york_times_client.base.activity.BaseActivity;
import info.kimjihyok.new_york_times_client.base.activity.DaggerMockActivityComponent;
import info.kimjihyok.new_york_times_client.base.application.ApplicationComponent;
import info.kimjihyok.new_york_times_client.base.application.DaggerMockApplicationComponent;
import info.kimjihyok.new_york_times_client.base.modules.ActivityModule;
import info.kimjihyok.new_york_times_client.base.modules.MockDataModule;
import info.kimjihyok.new_york_times_client.base.modules.PresenterModule;

/**
 * Created by jihyokkim on 2017. 9. 28..
 */

public class MockDependencyGraph implements DependencyGraph {

  @Override
  public ActivityComponent defineActivityComponent(ApplicationComponent applicationComponent, Activity activity) {
    return DaggerMockActivityComponent.builder()
        .mockApplicationComponent(DaggerMockApplicationComponent.builder().build())
        .activityModule(new ActivityModule((BaseActivity) activity))
        .mockDataModule(new MockDataModule())
        .presenterModule(new PresenterModule())
        .build();
  }

  @Override
  public ApplicationComponent defineApplicationComponent(Application application) {
    return DaggerMockApplicationComponent.builder().build();
  }
}
