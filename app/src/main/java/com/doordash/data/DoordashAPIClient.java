package com.doordash.data;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class DoordashAPIClient {
    private static final String API_BASE_URL = "https://api.doordash.com/v2/restaurant/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    private static String getApiUrl() {
        return API_BASE_URL;
    }

    //https://api.doordash.com/v2/restaurant/?lat=37.422740&lng=-122.139956
    public static void getRestaurants(String latitude, String longitude, int offset, JsonHttpResponseHandler handler) {
        String url = getApiUrl();
        RequestParams params = new RequestParams("lng", longitude);
        params.add("lat", latitude);
        params.add("limit", "10");
        params.add("page", String.valueOf(offset));
        client.get(url, params, handler);
    }
}
