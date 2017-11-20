package info.kimjihyok.new_york_times_client.base.modules;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.db.DaoMaster;
import info.kimjihyok.new_york_times_client.db.DaoSession;
import info.kimjihyok.new_york_times_client.db.DaoSessionInterface;

import static org.mockito.Mockito.mock;

/**
 * Created by jihyokkim on 2017. 9. 25..
 */

@Module
public class MockApplicationModule {

  @Provides
  @Singleton
  DaoSessionInterface providesDaoSession() {
    return mock(DaoSessionInterface.class);
  }
}
