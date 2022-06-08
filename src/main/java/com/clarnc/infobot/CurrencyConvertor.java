package com.clarnc.infobot;
import com.clarnc.apis.tokens;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class CurrencyConvertor {
    public static String getCurrency(String[] args) throws IOException {
        String amount = "";
        OkHttpClient client = new OkHttpClient();
        String url = null;
        if(args.length>3) url = "https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from="+args[1]+"&to="+args[2]+"&amount="+args[3];else
            url = "https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from="+args[1]+"&to="+args[2]+"&amount=1";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Host", "currency-converter5.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key",tokens.getCurrencyApi())
                .build();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String jsondata= response.body().string();
        JSONObject currencyData = new JSONObject(jsondata);
       JSONObject ammount_container = currencyData.getJSONObject("rates");
       JSONObject ammount_currency = ammount_container.getJSONObject(args[2]);
        amount = ammount_currency.getString("rate_for_amount");
        String fix_amount = String.format("%.3f",amount);
        return fix_amount+" " + args[2];
    }
}
