package com.clarnc.infobot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class CurrencyConverter {

    public static String getCurrency(String[] args) throws IOException {
        String amount = "";
        OkHttpClient client = new OkHttpClient();
        String url = null;

        var apiUrl = "https://currency-converter5.p.rapidapi.com/currency/convert";
        var getParameters = new HashMap<String, String>(){{
            put("format", "json");
            put("from", args[1]);
            put("to", args[2]);
        }};
        getParameters.put("amount", args.length > 3 ? args[3] : "1");

        Request request = new Request.Builder()
                .url(UrlUtils.WithGet(apiUrl, getParameters))
                .get()
                .addHeader("X-RapidAPI-Host", "currency-converter5.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", Tokens.getCurrencyApi())
                .build();

        Response response = client.newCall(request).execute();
        if (response.body() == null) return null;

        String jsonData = response.body().string();
        JSONObject currencyData = new JSONObject(jsonData);
        JSONObject amountContainer = currencyData.getJSONObject("rates");
        JSONObject currency = amountContainer.getJSONObject(args[2]);
        amount = currency.getString("rate_for_amount");
        String fix_amount = String.format("%.3f", amount);
        return fix_amount + " " + args[2];
    }
}
