package info.kimjihyok.new_york_times_client.base;

import android.app.Activity;
import android.app.Application;

import info.kimjihyok.new_york_times_client.base.activity.ActivityComponent;
import info.kimjihyok.new_york_times_client.base.application.ApplicationComponent;
import info.kimjihyok.new_york_times_client.base.modules.ActivityModule;
import info.kimjihyok.new_york_times_client.base.modules.ApplicationModule;
import info.kimjihyok.new_york_times_client.base.modules.DataModule;
import info.kimjihyok.new_york_times_client.base.modules.PresenterModule;

/**
 * Created by jihyokkim on 2017. 9. 28..
 */

public interface DependencyGraph {
  ActivityComponent defineActivityComponent(ApplicationComponent applicationComponent, Activity activity);
  ApplicationComponent defineApplicationComponent(Application application);
}
