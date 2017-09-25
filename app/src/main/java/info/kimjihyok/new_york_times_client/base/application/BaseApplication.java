package info.kimjihyok.new_york_times_client.base.application;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.base.modules.ApplicationModule;
import info.kimjihyok.new_york_times_client.db.DaoSession;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class BaseApplication extends Application {
    private ApplicationComponent applicationComponent;

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

        applicationComponent = buildApplicationComponent();
    }

    protected ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
