package info.kimjihyok.new_york_times_client.base.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.db.DaoMaster;
import info.kimjihyok.new_york_times_client.db.DaoSession;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */

@Module
public class ApplicationModule {
  private Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  DaoSession providesDaoSession(DaoMaster daoMaster) {
    return daoMaster.newSession();
  }

  @Provides
  @Singleton
  DaoMaster providesDaoMaster(SQLiteDatabase db) {
    return new DaoMaster(db);
  }

  @Provides
  @Singleton
  SQLiteDatabase providesSQLiteDatabase() {
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application, "user-db", null);
    return helper.getWritableDatabase();
  }
}
