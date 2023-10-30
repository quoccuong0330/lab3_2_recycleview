package com.example.lab3_2_recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, phoneNumberInput;
    private Button buttonAdd, buttonDelete;
    private MyAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);


        nameInput = findViewById(R.id.et_name);
        phoneNumberInput = findViewById(R.id.et_phone_number);
        recyclerView = findViewById(R.id.recycler_view);
        showData(db);

        buttonAdd = findViewById(R.id.btn_add_new);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addContact(new Contact(nameInput.getText().toString(),
                        phoneNumberInput.getText().toString()));
                nameInput.setText("");
                phoneNumberInput.setText("");
                Toast.makeText(MainActivity.this, "Successfully",
                        Toast.LENGTH_SHORT).show();
                showData(db);
            }
        });

        buttonDelete = findViewById(R.id.btn_delete_all);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAllRecords();
                showData(db);

            }
        });


    }

    private void showData(DatabaseHandler db) {
        List<Contact> contactList = db.getAllContacts();
        adapter = new MyAdapter(this,contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}