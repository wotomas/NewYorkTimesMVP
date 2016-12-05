package info.kimjihyok.new_york_times_client.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import info.kimjihyok.new_york_times_client.BuildConfig;
import info.kimjihyok.new_york_times_client.db.DaoMaster;
import info.kimjihyok.new_york_times_client.db.DaoSession;

/**
 * Created by jkimab on 2016. 12. 5..
 */

public class BaseApplication extends Application {
    private DaoSession mDaoSession;

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

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "user-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


}
