package info.kimjihyok.new_york_times_client.base;

import android.app.Application;

import com.facebook.stetho.Stetho;

import info.kimjihyok.new_york_times_client.BuildConfig;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //set up stetho
        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }


}
