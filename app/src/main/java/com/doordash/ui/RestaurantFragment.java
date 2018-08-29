package com.doordash.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doordash.data.DoordashAPIClient;
import com.doordash.data.Restaurant;
import com.doordash.data.RestaurantAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import doordash.com.doordash.R;

public class RestaurantFragment extends Fragment {

    private View layout;
    private RecyclerView recyclerView;
    private List<Restaurant> restaurantList;
    private RestaurantAdapter restaurantAdapter;

    public RestaurantFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_restaurant, container, false);
        recyclerView = layout.findViewById(R.id.restaurants);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));
        getRestaurants();
        return layout;
    }

    private void getRestaurants() {
        restaurantList = new LinkedList();
        restaurantAdapter = new RestaurantAdapter(getActivity(), restaurantList, recyclerView);
        recyclerView.setAdapter(restaurantAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                fetchRestaurants(page);
            }
        });
        fetchRestaurants(1);
    }

    public void fetchRestaurants(final int offset) {
        DoordashAPIClient.getRestaurants("37.422740", "-122.139956", offset,
                new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
                if (jsonArray != null) {
                    List<Restaurant> restaurants = Restaurant.fromJson(jsonArray, 20);
                    restaurantList.addAll(restaurants);
                    restaurantAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
