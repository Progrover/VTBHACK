package com.platovco.vtb;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String MAPKIT_API_KEY = "7453c6b4-5183-42a5-adda-1545b247bf4c";
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}
