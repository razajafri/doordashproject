package com.doordash.data;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import doordash.com.doordash.R;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private Context context;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants, RecyclerView recyclerView) {
        this.restaurants = restaurants;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView coverImage;
        public TextView restaurantName;
        public TextView description;
        public TextView status;

        public ViewHolder(final View itemView) {
            super(itemView);

            coverImage = itemView.findViewById(R.id.coverImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View restaurantView = inflater.inflate(R.layout.item_restaurant, parent, false);
        ViewHolder viewHolder = new ViewHolder(restaurantView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ViewHolder viewHolder, int position) {
        Restaurant restaurant = restaurants.get(position);
        viewHolder.restaurantName.setText(restaurant.getName());
        viewHolder.description.setText(restaurant.getDescription());
        viewHolder.status.setText(restaurant.getStatus());
        Picasso.with(context).load(Uri.parse(restaurant.getThumbnailUrl())).into(viewHolder.coverImage);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

}