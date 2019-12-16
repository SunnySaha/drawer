package com.example.sunny.drawyer;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class profile_data_adapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<profile_data_model>itemList;

    public profile_data_adapter(Context context, List<profile_data_model> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //it return every view of every item
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleText.setText(itemList.get(position).getName());
        holder.subtitleText.setText(itemList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
