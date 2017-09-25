package info.kimjihyok.new_york_times_client.post.list;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import info.kimjihyok.new_york_times_client.R;
import info.kimjihyok.new_york_times_client.base.application.BaseApplication;

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

  @Rule
  public ActivityTestRule<PostListActivity> activityTestRule = new ActivityTestRule<>(PostListActivity.class);

  @Before
  public void setUp() throws Exception {
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    BaseApplication baseApplication = (BaseApplication)instrumentation.getTargetContext().getApplicationContext();

    PostListActivity postListActivity = activityTestRule.getActivity();
    postListActivity.
  }

  @Test
  public void whenActivityIsStarted_shouldLoadProperly() throws Exception {
    onView(withId(R.id.activity_main))
        .check(matches(isDisplayed()));
  }

  @Test
  public void whenRecyclerViewIsScolled_shouldScrollToPosition() throws Exception {
    onView(withId(R.id.container))
        .perform(scrollToPosition(15));
    //  .check() should check for mocked data at this point
  }

  @Test
  public void whenItemIsClicked_shouldNotShowRecyclerView() throws Exception {
    onView(withId(R.id.container))
        .perform(actionOnItemAtPosition(0, click()))
        .check(doesNotExist());
  }
}