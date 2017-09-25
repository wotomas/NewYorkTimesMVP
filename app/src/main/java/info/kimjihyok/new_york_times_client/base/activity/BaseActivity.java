package info.kimjihyok.new_york_times_client.base.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.application.BaseApplication;
import info.kimjihyok.new_york_times_client.base.modules.ActivityModule;
import info.kimjihyok.new_york_times_client.base.modules.DataModule;
import info.kimjihyok.new_york_times_client.base.modules.PresenterModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public abstract class BaseActivity extends AppCompatActivity {

  private static ActivityComponent activityComponent;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
        .setDefaultFontPath("fonts/english_font.ttf")
        .setFontAttrId(R.attr.fontPath)
        .build());

    activityComponent = buildActivityComponent();
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    //Add to customize font for UI
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);

    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      onScreenChangeToLandscape();
    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
      onScreenChangeToPortrait();
    }
  }

  protected abstract void onScreenChangeToLandscape();

  protected abstract void onScreenChangeToPortrait();

  protected ActivityComponent buildActivityComponent() {
    return DaggerActivityComponent.builder()
        .activityModule(new ActivityModule(this))
        .applicationComponent(((BaseApplication)getApplication()).getApplicationComponent())
        .dataModule(new DataModule())
        .presenterModule(new PresenterModule())
        .build();
  }

  public static ActivityComponent getActivityComponent() {
    return activityComponent;
  }
}
