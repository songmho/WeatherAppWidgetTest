package com.songmho.weatherappwidgettest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    ToggleButton toggleBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=(TextView)findViewById(R.id.title);
        toggleBt=(ToggleButton)findViewById(R.id.toggleBt);
        toggleBt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        ToggleButton tb=(ToggleButton)v;
        if(!tb.isChecked()){
            Intent intent=new Intent(this,WeatherService.class);
            stopService(intent);
        }
        else{
            Intent intent=new Intent(this,WeatherService.class);
            startService(intent);
        }
    }
}
