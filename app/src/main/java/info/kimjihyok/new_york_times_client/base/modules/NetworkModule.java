package info.kimjihyok.new_york_times_client.base.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.kimjihyok.new_york_times_client.data.remote.ApiController;
import info.kimjihyok.new_york_times_client.data.remote.NewYorkTimesApiInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jihyokkim on 2017. 9. 22..
 */
@Module
public class NetworkModule {

  @Provides
  @Singleton
  RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Provides
  @Singleton
  GsonConverterFactory providesGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
    return new Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(ApiController.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build();
  }

  @Provides
  @Singleton
  NewYorkTimesApiInterface providesNewYorkTimesApiInterface(Retrofit retrofit) {
    return retrofit.create(NewYorkTimesApiInterface.class);
  }


  @Provides
  @Singleton
  ApiController providesApiController(NewYorkTimesApiInterface service) {
    return new ApiController(service);
  }


  @Provides
  @Singleton
  OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.addInterceptor(httpLoggingInterceptor);
    return builder.build();
  }

  @Provides
  @Singleton
  HttpLoggingInterceptor providesHttpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return httpLoggingInterceptor;
  }
}
