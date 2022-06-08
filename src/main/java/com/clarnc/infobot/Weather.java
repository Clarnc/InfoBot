package com.clarnc.infobot;

import com.clarnc.apis.tokens;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class Weather {

    public static String getWeather(String secondarg) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/weather?q=" + secondarg + "&lat=0&lon=0&callback=test&lang=null&units=metric&mode=xml")
                .get()
                .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", tokens.getWeatherapi())
                .build();
        Response response = client.newCall(request).execute();

        assert response.body() != null;
        String res = response.body().string().substring(5);
        String jsondata = res.substring(0, res.length() - 1);
        JSONObject weatherdata = new JSONObject(jsondata);
        JSONObject temp = weatherdata.getJSONObject("main");
        return getTemp(temp);
    }

    private static String getTemp(JSONObject temp) {
        String curtemp = temp.get("temp") + "°C";
        String templike = temp.get("feels_like") + "°C";

        return "Current: " + curtemp + "\nFeels like: " + templike;
    }

}
