package com.songmho.weatherappwidgettest;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * Created by songmho on 2015-11-01.
 */
public class WeatherAppWidgetProvider extends AppWidgetProvider {

    private boolean wf_onoff;
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        SharedPreferences settings= context.getSharedPreferences("weather", Context.MODE_PRIVATE);
        wf_onoff=settings.getBoolean("onoff",false);

        String region=intent.getStringExtra("region");
        String desc=intent.getStringExtra("desc");
        String ta=intent.getStringExtra("ta");

        if(region==null || !wf_onoff) {
            region = "unkown";
            desc="";
            ta="";

        }


            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            RemoteViews remote = new RemoteViews(context.getPackageName(), R.layout.weatherview);
            remote.setTextViewText(R.id.textView1, region);
            remote.setTextViewText(R.id.textView2, desc);
            remote.setTextViewText(R.id.textView3, ta);

            appWidgetManager.updateAppWidget(new ComponentName("com.songmho.weatherappwidgettest", "com.songmho.weatherappwidgettest.WeatherAppWidgetProvider"), remote);

    }


}
