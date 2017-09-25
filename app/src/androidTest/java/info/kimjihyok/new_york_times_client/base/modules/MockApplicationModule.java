package info.kimjihyok.new_york_times_client.base.modules;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.db.DaoMaster;
import info.kimjihyok.new_york_times_client.db.DaoSession;

import static org.mockito.Mockito.mock;

/**
 * Created by jihyokkim on 2017. 9. 25..
 */

@Module
public class MockApplicationModule extends ApplicationModule {
  public MockApplicationModule(Application application) {
    super(application);
  }

  @Provides
  @Singleton
  @Override
  DaoSession providesDaoSession(DaoMaster daoMaster) {
    return mock(DaoSession.class);
  }

  @Override
  DaoMaster providesDaoMaster(SQLiteDatabase db) {
    return mock(DaoMaster.class);
  }

  @Override
  SQLiteDatabase providesSQLiteDatabase() {
    return mock(SQLiteDatabase.class);
  }
}
