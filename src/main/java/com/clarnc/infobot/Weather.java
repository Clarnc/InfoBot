package com.clarnc.infobot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Weather {

    public static String getWeather(String query) throws IOException {
        var apiUrl = "https://community-open-weather-map.p.rapidapi.com/weather";
        var getParameters = new HashMap<String, String>(){{
            put("q", query);
            put("lat", "0");
            put("lon", "0");
            put("lang", "null");
            put("units", "metric");
            put("mode", "xml");
        }};

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(UrlUtils.WithGet(apiUrl, getParameters))
                .get()
                .addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", Tokens.getWeatherApi())
                .build();
        Response response = client.newCall(request).execute();
        if(response.body() == null) return null;

        JSONObject weatherData = new JSONObject(response.body().string());
        JSONObject temp = weatherData.getJSONObject("main");
        return getTemp(temp);
    }

    private static String getTemp(JSONObject temp) {
        String realTemp = temp.get("temp") + "°C";
        String feelsLike = temp.get("feels_like") + "°C";

        return "Current: " + realTemp + "\nFeels like: " + feelsLike;
    }

}
