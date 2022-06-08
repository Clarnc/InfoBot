package com.clarnc.apis;


import org.json.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class tokens {

    static FileReader file;

    static {
        try {
            file = new FileReader("api.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    static JSONTokener jsonTokener = new JSONTokener(file);
    static JSONObject jsonObject = new JSONObject(jsonTokener);


    public static String botk = jsonObject.getString("bot_token");
    public static String weatherapi = jsonObject.getString("weather_token");
    public static String currencyApi = jsonObject.getString("currency_token");

    public static String getCurrencyApi() {
        return currencyApi;
    }

    public static String getWeatherapi() {
        return weatherapi;
    }

    public static String getBotToken() {
        return botk;
    }
}





