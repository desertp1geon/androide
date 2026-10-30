
package com.example.iscip;

import androidx.lifecycle.LifecycleObserver;

import com.example.iscip.basic.koin.JavaKoin;

public class Application extends android.app.Application
        implements LifecycleObserver {

    public static final String TAG = "FruitifyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        JavaKoin.startKoinApplication(this);
    }
}
