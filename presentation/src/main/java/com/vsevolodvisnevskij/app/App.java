package com.vsevolodvisnevskij.app;

import android.app.Application;

import com.vsevolodvisnevskij.injection.AppComponent;
import com.vsevolodvisnevskij.injection.AppModule;
import com.vsevolodvisnevskij.injection.DaggerAppComponent;

/**
 * Created by vsevolodvisnevskij on 19.03.2018.
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
