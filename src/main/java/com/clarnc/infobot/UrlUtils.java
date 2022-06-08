package com.clarnc.infobot;

import java.util.Map;

public class UrlUtils {
    public static String WithGet(String apiUrl, Map<String, String> params) {
        if(params.isEmpty()) return apiUrl;
        boolean first = true;

        StringBuilder apiUrlBuilder = new StringBuilder(apiUrl);
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(first) {
                apiUrlBuilder.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                first = false;
            } else {
                apiUrlBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return apiUrlBuilder.toString();
    }
}
