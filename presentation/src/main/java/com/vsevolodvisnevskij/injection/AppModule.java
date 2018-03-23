package com.vsevolodvisnevskij.injection;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.vsevolodvisnevskij.data.net.RestApi;
import com.vsevolodvisnevskij.data.net.RestService;
import com.vsevolodvisnevskij.data.repositories.UserRepositoryImpl;
import com.vsevolodvisnevskij.domain.executor.PostExecutionThread;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;
import com.vsevolodvisnevskij.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

@Module
public class AppModule {


    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @Provides
    @Singleton
    public PostExecutionThread getUiThread() {
        return new UIThread();
    }

    @Provides
    public UserRepository getUserRepository(Context context, RestService restService) {
        return new UserRepositoryImpl(context, restService);
    }

    @Provides
    @Singleton
    public Retrofit getRetrofit() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("https://api.backendless.com/AFBA53A5-EC03-38A9-FFE5-36B3315DD900/2FE78199-7AC8-D9DD-FF68-E1085DEA3700/").build();
    }

    @Provides
    @Singleton
    public RestApi getRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }
}
