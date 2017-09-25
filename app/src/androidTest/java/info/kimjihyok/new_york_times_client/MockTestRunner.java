package info.kimjihyok.new_york_times_client;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import info.kimjihyok.new_york_times_client.base.application.MockBaseApplication;

/**
 * Created by jihyokkim on 2017. 9. 25..
 */

public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader classLoader, String className, Context context)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException {
      // replace Application class with mock one
      return super.newApplication(classLoader, MockBaseApplication.class.getName(), context);
    }
}
