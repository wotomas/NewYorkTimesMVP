package info.kimjihyok.new_york_times_client.base.application;

import javax.inject.Singleton;

import dagger.Component;
import info.kimjihyok.new_york_times_client.base.modules.ApplicationModule;
import info.kimjihyok.new_york_times_client.db.DaoSession;

/**
 * Created by jihyokkim on 2017. 9. 21..
 */

@Singleton
@Component(modules = {
    ApplicationModule.class
})
public interface ApplicationComponent {
  void inject(BaseApplication baseApplication);

  DaoSession getDaoSession();
}
