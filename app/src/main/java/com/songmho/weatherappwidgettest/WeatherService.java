package com.songmho.weatherappwidgettest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by songmho on 2015-11-01.
 */
public class WeatherService extends Service {

    private Thread thread;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new WeatherForecastHandler(this);
        thread = new WeatherThread(handler);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!thread.isAlive())
            thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
