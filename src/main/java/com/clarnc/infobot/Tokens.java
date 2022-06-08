package com.clarnc.infobot;


import org.json.*;


import java.io.FileNotFoundException;
import java.io.FileReader;

public class Tokens {

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


    private static final String BOT_TOKEN = jsonObject.getString("bot_token");
    private static final String WEATHER_API = jsonObject.getString("weather_token");
    private static final String CURRENCY_API = jsonObject.getString("currency_token");

    public static String getCurrencyApi() {
        return CURRENCY_API;
    }

    public static String getWeatherApi() {
        return WEATHER_API;
    }

    public static String getBotToken() {
        return BOT_TOKEN;
    }
}





