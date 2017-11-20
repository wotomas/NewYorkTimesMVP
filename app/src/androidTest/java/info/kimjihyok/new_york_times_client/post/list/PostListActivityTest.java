package info.kimjihyok.new_york_times_client.post.list;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import info.kimjihyok.new_york_times_client.DaggerActivityTestRule;
import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.DependencyGraph;
import info.kimjihyok.new_york_times_client.base.MockDependencyGraph;
import info.kimjihyok.new_york_times_client.base.activity.MockActivityComponent;
import info.kimjihyok.new_york_times_client.base.application.BaseApplication;
import info.kimjihyok.new_york_times_client.base.application.DaggerMockApplicationComponent;
import info.kimjihyok.new_york_times_client.base.application.MockApplicationComponent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by jihyokkim on 2017. 9. 22..
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PostListActivityTest {
  private static final String TAG = "PostListActivityTest";

  private MockApplicationComponent mockApplicationComponent;
  private MockActivityComponent mockActivityComponent;

  @Rule
  public ActivityTestRule<PostListActivity> activityTestRule = new DaggerActivityTestRule<>(PostListActivity.class,
      false,
      false,
      new DaggerActivityTestRule.OnActivityLaunchedListener<PostListActivity>() {
        @Override
        public void beforeActivityLaunched(@NonNull Application application) {
          Log.d(TAG, "beforeActivityLaunched(): " + application);
          mockApplicationComponent = DaggerMockApplicationComponent.builder().build();

          DependencyGraph dependencyGraph = new MockDependencyGraph();
          ((BaseApplication) application).setDependencyGraph(dependencyGraph);
      }
  });

  @Before
  public void setUp() throws Exception {
    Intent startIntent = new Intent(Intent.ACTION_MAIN);
    activityTestRule.launchActivity(startIntent);
  }

  @Test
  public void whenActivityIsStarted_shouldLoadProperly() throws Exception {
    onView(withId(R.id.activity_main))
        .check(matches(isDisplayed()));

    Thread.sleep(2000);
  }

  @Test
  public void whenRecyclerViewIsScolled_shouldScrollToPosition() throws Exception {
    onView(withId(R.id.container))
        .perform(scrollToPosition(15));
    //  .check() should check for mocked data at this point

    Thread.sleep(2000);
  }

  @Test
  public void whenItemIsClicked_shouldNotShowRecyclerView() throws Exception {
    onView(withId(R.id.container))
        .perform(actionOnItemAtPosition(0, click()))
        .check(doesNotExist());

    Thread.sleep(2000);
  }
}