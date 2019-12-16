package com.example.sunny.drawyer;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView titleText;
    TextView subtitleText;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        titleText = itemView.findViewById(R.id.title);
        subtitleText = itemView.findViewById(R.id.subtitle);

    }
}
