package info.kimjihyok.new_york_times_client.base.application;

import javax.inject.Singleton;

import dagger.Component;
import info.kimjihyok.new_york_times_client.base.modules.ApplicationModule;
import info.kimjihyok.new_york_times_client.base.modules.NetworkModule;
import info.kimjihyok.new_york_times_client.data.remote.ApiController;
import info.kimjihyok.new_york_times_client.db.DaoSession;
import info.kimjihyok.new_york_times_client.db.DaoSessionInterface;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */

@Singleton
@Component(modules = {
    ApplicationModule.class,
    NetworkModule.class
})
public interface ApplicationComponent {
  void inject(BaseApplication baseApplication);

  DaoSessionInterface getDaoSession();
  ApiController getApiController();
}
