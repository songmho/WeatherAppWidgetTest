package com.songmho.weatherappwidgettest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    ToggleButton toggleBt;

    private boolean wf_onoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings= getSharedPreferences("weather", Context.MODE_PRIVATE);
        wf_onoff=settings.getBoolean("onoff",false);
        if(!wf_onoff){
            Intent intent=new Intent(this,WeatherService.class);
            stopService(intent);
        }

        title=(TextView)findViewById(R.id.title);
        toggleBt=(ToggleButton)findViewById(R.id.toggleBt);
        toggleBt.setChecked(wf_onoff);

        toggleBt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        ToggleButton tb=(ToggleButton)v;
        if(!tb.isChecked()){
            Intent intent=new Intent(this,WeatherService.class);
            stopService(intent);
            wf_onoff=false;
        }
        else{
            Intent intent=new Intent(this,WeatherService.class);
            startService(intent);
            wf_onoff=true;
        }

        SharedPreferences settings=getSharedPreferences("weather",MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putBoolean("onoff",wf_onoff);
        editor.commit();
    }
}
