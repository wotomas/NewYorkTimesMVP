package info.kimjihyok.new_york_times_client.base.application;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.base.DependencyGraph;
import info.kimjihyok.new_york_times_client.base.ProductionDependencyGraph;
import info.kimjihyok.new_york_times_client.base.modules.ApplicationModule;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

    private ApplicationComponent applicationComponent;
    private DependencyGraph dependencyGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate()");
        setDependencyGraph(new ProductionDependencyGraph());

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

        applicationComponent = dependencyGraph.defineApplicationComponent(
           this
        );
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public DependencyGraph getDependencyGraph() {
        return dependencyGraph;
    }

    public void setDependencyGraph(DependencyGraph dependencyGraph) {
        if (dependencyGraph instanceof ProductionDependencyGraph) {
            Log.d(TAG, "setDependencyGraph() production");
        } else {
            Log.d(TAG, "setDependencyGraph() mock");
        }

        this.dependencyGraph = dependencyGraph;
    }
}
