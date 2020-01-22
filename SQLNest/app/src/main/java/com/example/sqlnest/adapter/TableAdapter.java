package com.example.sqlnest.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnest.R;
import com.example.sqlnest.model.Customer;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewModel> {
    private Context mContext;
    private ArrayList<Customer> customers;
    private String TAG = getClass().getSimpleName();
    Boolean showStar = false;
    Boolean showID = false;
    Boolean showName = false;
    Boolean showCity = false;
    Boolean showCountry = false;

    public TableAdapter(Context context, ArrayList<Customer> allItems, Boolean showStar, Boolean showID, Boolean showName, Boolean showCity, Boolean showCountry) {
        this.mContext = context;
        this.customers = allItems;
        this.showStar = showStar;
        this.showID = showID;
        this.showName = showName;
        this.showCity = showCity;
        this.showCountry = showCountry;
    }

    @Override
    public TableAdapter.ViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new TableAdapter.ViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        Customer customer = customers.get(position);

        holder.id.setText(String.valueOf(customer.getId()));
        holder.name.setText(customer.getName());
        holder.city.setText(customer.getCity());
        holder.country.setText(customer.getCountry());

        if (showStar) {
            holder.id.setVisibility(View.VISIBLE);
            holder.name.setVisibility(View.VISIBLE);
            holder.city.setVisibility(View.VISIBLE);
            holder.country.setVisibility(View.VISIBLE);
        }else {
            if(showID)
                holder.id.setVisibility(View.VISIBLE);
            else
                holder.id.setVisibility(View.GONE);

            if(showName)
                holder.name.setVisibility(View.VISIBLE);
            else
                holder.name.setVisibility(View.GONE);

            if(showCity)
                holder.city.setVisibility(View.VISIBLE);
            else
                holder.city.setVisibility(View.GONE);

            if(showCountry)
                holder.country.setVisibility(View.VISIBLE);
            else
                holder.country.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    @Override
    public int getItemViewType(int position) {
        return customers.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        public TextView id, name, city, country;

        public ViewModel(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvID);
            name = itemView.findViewById(R.id.tvName);
            city = itemView.findViewById(R.id.tvCity);
            country = itemView.findViewById(R.id.tvCountry);
        }
    }
}