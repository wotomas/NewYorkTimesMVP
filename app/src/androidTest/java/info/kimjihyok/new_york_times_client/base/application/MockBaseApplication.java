package info.kimjihyok.new_york_times_client.base.application;

import info.kimjihyok.new_york_times_client.base.modules.MockApplicationModule;
import info.kimjihyok.new_york_times_client.base.modules.MockNetworkModule;

/**
 * Created by jihyokkim on 2017. 9. 25..
 */

public class MockBaseApplication extends BaseApplication {
  @Override
  protected ApplicationComponent buildApplicationComponent() {
    return DaggerApplicationComponent.builder()
        .applicationModule(new MockApplicationModule(this))
        .networkModule(new MockNetworkModule())
        .build();
  }
}
