package com.songmho.weatherappwidgettest;

import android.os.Handler;
import android.os.Message;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by songmho on 2015-11-01.
 */
public class WeatherThread extends Thread {
    private Handler handler;

    public WeatherThread(Handler handler) {
        this.handler=handler;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                URL url=new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");

                XmlPullParserFactory parserFactory=XmlPullParserFactory.newInstance();
                XmlPullParser parser= parserFactory.newPullParser();
                parser.setInput(url.openStream(),"utf-8");
                int parserEvent=parser.getEventType();

                String desc="";
                String ta="";
                String region="";
                String name="";

                while (parserEvent != XmlPullParser.END_DOCUMENT){
                    switch (parserEvent){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            name=parser.getName();
                            if(name!=null && "local".equals(name)){
                                desc=parser.getAttributeValue(null,"desc");
                                ta=parser.getAttributeValue(null,"ta");
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if(name != null && "local".equals(name)){
                                region=parser.getText();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if(name != null && "local".equals(name)){
                                Message msg=handler.obtainMessage();
                                WeatherForecast wf=new WeatherForecast();
                                wf.desc=desc;
                                wf.region=region;
                                wf.ta=ta;
                                msg.obj=wf;
                                handler.sendMessage(msg);
                            }
                            name="";
                            break;

                    }


                    Thread.sleep(1000);
                    parserEvent=parser.next();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
