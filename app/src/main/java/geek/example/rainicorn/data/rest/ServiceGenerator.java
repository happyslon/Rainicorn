package geek.example.rainicorn.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import geek.example.rainicorn.BuildConfig;
import geek.example.rainicorn.data.Endpoints;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private Gson gson = new GsonBuilder().create();///setDateFormat("yyyy-MM-dd")

    public <S> S createService(Class<S> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
                .create(serviceClass);
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
