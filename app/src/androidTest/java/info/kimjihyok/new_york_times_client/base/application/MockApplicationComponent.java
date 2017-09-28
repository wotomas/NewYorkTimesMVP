package info.kimjihyok.new_york_times_client.base.application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import info.kimjihyok.new_york_times_client.base.modules.MockApplicationModule;
import info.kimjihyok.new_york_times_client.base.modules.MockNetworkModule;
import info.kimjihyok.new_york_times_client.data.remote.ApiController;
import info.kimjihyok.new_york_times_client.db.DaoSession;

/**
 * Created by jihyokkim on 2017. 9. 25..
 */

@Singleton
@Component(modules = {
    MockApplicationModule.class,
    MockNetworkModule.class
})
public interface MockApplicationComponent extends ApplicationComponent {

}
