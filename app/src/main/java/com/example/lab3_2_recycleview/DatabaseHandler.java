package com.example.lab3_2_recycleview;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.text.PrecomputedTextCompat;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " +
                TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
// Create tables again
        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }
    // Getting single contact
    public Contact getContact(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM "+ TABLE_CONTACTS +" WHERE id = " + id;
        Cursor cursor = db.rawQuery(select, null);
        Contact contact = new Contact("","");
        if(cursor.moveToFirst()) {
            do {
                contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                Log.e("TAG: ","id: "+contact.getName() + contact.getPhoneNumber() +" cursor: " +select);
            } while (cursor.moveToNext());
        } else {
            Log.e("TAG: ", "Does not exist");
            return contact;
        }

        db.close();
        return contact;

    }
    // Getting All Contacts
    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> contactList = new ArrayList<>();
        String select = "SELECT * FROM "+ TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                Log.e("TAG: ","id: "+contact.getName() + contact.getPhoneNumber() +" cursor: " +select);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();

        return contactList;
    }
    //    // Updating single contact
    public void updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // Update the contact with the specified ID
        db.update(TABLE_CONTACTS, values, KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
//    // Deleting single contact
//
    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
        db.close();
    }
}
