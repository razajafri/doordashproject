package com.doordash.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Restaurant {
    private String name;
    private String thumbnailUrl;
    private String description;
    private String status;

    public String getName() {
        return name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public static Restaurant fromJson(JSONObject jsonObject) {
        Restaurant restaurant = new Restaurant();
        try {
            // Deserialize json into object fields
            restaurant.name = jsonObject.getJSONObject("business").getString("name");
            restaurant.thumbnailUrl = jsonObject.getString("cover_img_url");
            restaurant.description = jsonObject.getString("description");
            restaurant.status = jsonObject.getString("status");
            if (restaurant.status.toLowerCase().startsWith("pre-order")) {
                // unclear as to what pre-order for pre-order means
                // shortening it to pre-order
                restaurant.status = "Pre-order";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return restaurant;
    }

    public static List<Restaurant> fromJson(JSONArray jsonArray, int max) {
        List<Restaurant> restaurants = new LinkedList();
        int len = Math.min(max, jsonArray.length());
        for (int i = 0; i < len; i++) {
            JSONObject restaurantJson = null;
            try {
                restaurantJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Restaurant restaurant = fromJson(restaurantJson);
            if (restaurant != null) {
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }
}
