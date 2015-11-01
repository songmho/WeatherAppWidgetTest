package com.songmho.weatherappwidgettest;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

/**
 * Created by songmho on 2015-11-01.
 */
public class WeatherForecastHandler extends Handler {
    private Context context;
    public WeatherForecastHandler(Context context) {
        this.context=context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        WeatherForecast wf=(WeatherForecast)msg.obj;

        Intent intent=new Intent();
        intent.putExtra("region",wf.region);
        intent.putExtra("desc",wf.desc);
        intent.putExtra("ta",wf.ta);
        intent.setAction("android.intent.action.WEATHER_FORECAST_RECEIVED");

        context.sendBroadcast(intent);
    }
}
