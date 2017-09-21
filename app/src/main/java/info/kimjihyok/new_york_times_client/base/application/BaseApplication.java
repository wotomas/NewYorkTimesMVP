package info.kimjihyok.new_york_times_client.base.application;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.base.DaggerApplicationComponent;
import info.kimjihyok.new_york_times_client.base.application.ApplicationComponent;
import info.kimjihyok.new_york_times_client.base.application.ApplicationModule;
import info.kimjihyok.new_york_times_client.db.DaoSession;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class BaseApplication extends Application {
    @Inject DaoSession mDaoSession;

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //set up stetho and leak canary
        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }

        applicationComponent = buildApplicationComponent().build();
        applicationComponent.inject(this);
    }

    protected DaggerApplicationComponent.Builder buildApplicationComponent() {
        return DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this));
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
