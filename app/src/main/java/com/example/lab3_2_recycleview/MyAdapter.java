package com.example.lab3_2_recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Contact> contactList;

    public MyAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(contactList.get(position).getName());
        holder.phoneNumber.setText((contactList.get(position).getPhoneNumber()));

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler db = new DatabaseHandler(context);
                Contact contact = contactList.get(position);
                Contact contactFromDB = db.getContact(contact.getId());

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(contactFromDB.getId()));
                intent.putExtra("name", contactFromDB.getName());
                intent.putExtra("phoneNumber",contactFromDB.getPhoneNumber());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
