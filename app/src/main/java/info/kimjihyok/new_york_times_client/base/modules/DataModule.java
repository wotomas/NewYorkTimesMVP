package info.kimjihyok.new_york_times_client.base.modules;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.base.activity.ActivityScope;
import info.kimjihyok.new_york_times_client.data.local.DataController;
import info.kimjihyok.new_york_times_client.data.remote.ApiController;
import info.kimjihyok.new_york_times_client.db.DaoSession;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */

@Module
public class DataModule {

  @Provides
  @ActivityScope
  DataController providesDataController(DaoSession daoSession, ApiController apiController) {
    return new DataController(daoSession, apiController);
  }
}
