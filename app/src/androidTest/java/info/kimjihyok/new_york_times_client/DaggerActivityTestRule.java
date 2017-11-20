package info.kimjihyok.new_york_times_client;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

/**
 * {@link ActivityTestRule} which provides hook for
 * {@link ActivityTestRule#beforeActivityLaunched()} method. Can be used for test dependency
 * injection especially in Espresso based tests.
 *
 * @author Tomasz Rozbicki
 */
public class DaggerActivityTestRule<T extends Activity> extends ActivityTestRule<T> {

  private final OnActivityLaunchedListener<T> mListener;

  public DaggerActivityTestRule(Class<T> activityClass,
                                @NonNull OnActivityLaunchedListener<T> listener) {
    this(activityClass, false, listener);
  }

  public DaggerActivityTestRule(Class<T> activityClass, boolean initialTouchMode,
                                @NonNull OnActivityLaunchedListener<T> listener) {
    this(activityClass, initialTouchMode, true, listener);
  }

  public DaggerActivityTestRule(Class<T> activityClass, boolean initialTouchMode,
                                boolean launchActivity,
                                @NonNull OnActivityLaunchedListener<T> listener) {
    super(activityClass, initialTouchMode, launchActivity);
    mListener = listener;
  }

  @Override
  protected void beforeActivityLaunched() {
    super.beforeActivityLaunched();
    mListener.beforeActivityLaunched(
        (Application) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());
  }

  public interface OnActivityLaunchedListener<T> {
    void beforeActivityLaunched(@NonNull Application application);
  }
}
