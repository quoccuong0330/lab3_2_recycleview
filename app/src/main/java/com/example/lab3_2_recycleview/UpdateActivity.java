package com.example.lab3_2_recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nameInput, phoneNumberInput;
    Button btnUpdate, btnDelete;
    String name, phoneNumber, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        nameInput = findViewById(R.id.title_name);
        phoneNumberInput = findViewById(R.id.title_phone_number);
        btnDelete =findViewById(R.id.delete_button);
        btnUpdate = findViewById(R.id.update_button);

        Intent intent = getIntent();
        if (intent != null) {
            Contact contact = (Contact) intent.getSerializableExtra("contact");
            if (contact != null) {
                // Use the 'contact' object in your UpdateActivity
            }
        }

        name = getIntent().getStringExtra("name");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        id = getIntent().getStringExtra("id");
        Log.e("TAG", id);
        nameInput.setText(name);
        phoneNumberInput.setText(phoneNumber);
        DatabaseHandler myDB = new DatabaseHandler(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                String name1 = nameInput.getText().toString();
                String phoneNumber1 = phoneNumberInput.getText().toString();
                myDB.updateContact(new Contact(Integer.parseInt(id),name1,phoneNumber1));

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                UpdateActivity.this.startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDB.deleteById(Integer.parseInt(id));

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                UpdateActivity.this.startActivity(intent);
            }
        });


    }



}