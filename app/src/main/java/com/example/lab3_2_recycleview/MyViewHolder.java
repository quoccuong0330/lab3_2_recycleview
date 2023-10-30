package com.example.lab3_2_recycleview;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView phoneNumber;
    Button editBtn, deleteBtn;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.tv_name);
        phoneNumber = itemView.findViewById(R.id.tv_phone_number);
        editBtn = itemView.findViewById(R.id.btn_edit);
    }
}
