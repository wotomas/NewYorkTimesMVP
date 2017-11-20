package info.kimjihyok.new_york_times_client.base;

import android.app.Activity;
import android.app.Application;

import info.kimjihyok.new_york_times_client.base.activity.ActivityComponent;
import info.kimjihyok.new_york_times_client.base.activity.BaseActivity;
import info.kimjihyok.new_york_times_client.base.activity.DaggerActivityComponent;
import info.kimjihyok.new_york_times_client.base.application.ApplicationComponent;
import info.kimjihyok.new_york_times_client.base.application.DaggerApplicationComponent;
import info.kimjihyok.new_york_times_client.base.modules.ActivityModule;
import info.kimjihyok.new_york_times_client.base.modules.ApplicationModule;
import info.kimjihyok.new_york_times_client.base.modules.DataModule;
import info.kimjihyok.new_york_times_client.base.modules.PresenterModule;

/**
 * Created by jihyokkim on 2017. 9. 28..
 */

public class ProductionDependencyGraph implements DependencyGraph {

  @Override
  public ActivityComponent defineActivityComponent(ApplicationComponent applicationComponent, Activity activity) {
    return  DaggerActivityComponent.builder()
        .applicationComponent(applicationComponent)
        .activityModule(new ActivityModule((BaseActivity) activity))
        .dataModule(new DataModule())
        .presenterModule(new PresenterModule())
        .build();
  }

  @Override
  public ApplicationComponent defineApplicationComponent(Application application) {
    return DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(application))
        .build();
  }

//  @Override
//  public ApplicationComponent getApplicationComponent() {
//    return applicationComponent;
//  }
//
//  @Override
//  public ActivityComponent getActivityComponent() {
//    return activityComponent;
//  }
//
//  @Override
//  public void clearActivityComponent() {
//    activityComponent = null;
//  }
}
