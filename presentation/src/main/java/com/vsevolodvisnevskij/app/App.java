package com.vsevolodvisnevskij.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.vsevolodvisnevskij.domain.interactors.CloneDbUseCase;
import com.vsevolodvisnevskij.injection.AppComponent;
import com.vsevolodvisnevskij.injection.AppModule;
import com.vsevolodvisnevskij.injection.DaggerAppComponent;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

public class App extends Application {
//    @Inject
//    public CloneDbUseCase cloneDbUseCase;
//    @Inject
//    Context context;

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        appComponent.inject(this);
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//            cloneDbUseCase.cloneDb().subscribeOn(Schedulers.io()).subscribe();
//        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
