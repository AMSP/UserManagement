package com.example.mystrymachine.usermanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MYSTRY MACHINE on 4/8/2015.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dbUser";

    // Contacts table name
    private static final String TABLE_NAME = "users";

    // Contacts Table Columns names
    public static final String COLUMN_ID = "u_id";
    public static final String COLUMN_NAME = "u_name";
    public static final String COLUMN_GENDER = "u_gender";
    public static final String COLUMN_BDATE = "u_bDate";
    public static final String COLUMN_MNUMBER = "u_mNumber";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_GENDER +" TEXT,"
                + COLUMN_BDATE + " DATE,"
                + COLUMN_MNUMBER + " TEXT " + ")";
        db.execSQL(CREATE_USERS_TABLE_QUERY);
        Log.d("Create","db has been created......");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
    //insert records of user
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getUser_id());
        values.put(COLUMN_NAME, user.getUser_name());
        values.put(COLUMN_GENDER, user.getUser_gender());
        values.put(COLUMN_BDATE, user.getUser_bDate().toString());
        values.put(COLUMN_MNUMBER, user.getUser_mNumber());


        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    //getting single User details
    User getUser(String id) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,
                        COLUMN_NAME, COLUMN_GENDER,COLUMN_BDATE,COLUMN_MNUMBER }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        int idIndex = cursor.getColumnIndex(DBHandler.COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(DBHandler.COLUMN_NAME);
        int genderIndex = cursor.getColumnIndex(DBHandler.COLUMN_GENDER);
        int bDateIndex = cursor.getColumnIndex(DBHandler.COLUMN_BDATE);
        int mNumberIndex = cursor.getColumnIndex(DBHandler.COLUMN_MNUMBER);


        User user=new User(cursor.getString(idIndex),cursor.getString(nameIndex),cursor.getString(genderIndex),
                cursor.getString(bDateIndex),cursor.getString(mNumberIndex));

        return user;
    }

    public Cursor selectQuery(String query) {
        Cursor c1 = null;

            SQLiteDatabase db = this.getReadableDatabase();
            c1 = db.rawQuery(query, null);

        return c1;

    }



    public void delectUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getUser_id())});
        db.close();

    }
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getUser_name());
        values.put(COLUMN_BDATE,user.getUser_bDate());
        values.put(COLUMN_GENDER, user.getUser_gender());
        values.put(COLUMN_MNUMBER,user.getUser_mNumber());


        // updating row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getUser_id()) });
    }


    public List<User> selectAll() {
        ArrayList<User> contactList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User contact = new User();
                contact.setUser_id(cursor.getString(0));
                contact.setUser_name(cursor.getString(1));
                contact.setUser_gender(cursor.getString(2));
                contact.setUser_bDate(cursor.getString(3));
                contact.setUser_mNumber(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

 cursor.close();
        // return contact list
        return contactList;
    }

    public List<User> searchUser(String query) {
        ArrayList<User> contactList = new ArrayList<User>();
        // Select All Query
        String selectQuery = query;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User contact = new User();
                contact.setUser_id(cursor.getString(0));
                contact.setUser_name(cursor.getString(1));
                contact.setUser_gender(cursor.getString(2));
                contact.setUser_bDate(cursor.getString(3));
                contact.setUser_mNumber(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return contactList;
    }

//
//    public ArrayList<User> selectAll(String Query) {
//        ArrayList<User> contactList = new ArrayList<User>();
//        // Select All Query
//
//        SQLiteDatabase db = this.getReadableDatabase();
////       1 Cursor cursor = db.query(TABLE_NAME, new String[]
////                        { COLUMN_ID,COLUMN_NAME, COLUMN_GENDER,COLUMN_BDATE,COLUMN_MNUMBER }, COLUMN_ID + "=?",
////                                             new String[] { String.valueOf(Query) }, null, null, null, null);
//
////        String[] gen = {""+Query};
////        Cursor cursor = db.query(TABLE_NAME,null,COLUMN_GENDER +"=?",gen,null,null,null,null );
//          // Cursor cursor = db.query(Query,null);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                User contact = new User();
//                contact.setUser_id(cursor.getString(0));
//                contact.setUser_name(cursor.getString(1));
//                contact.setUser_gender(cursor.getString(2));
//                contact.setUser_bDate(cursor.getString(3));
//                contact.setUser_mNumber(cursor.getString(4));
//                // Adding contact to list
//                contactList.add(contact);
//            } while (cursor.moveToNext());
//        }
//
//
//        // return contact list
//        return contactList;
//
//    }
    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

         return cursor.getCount();
    }


}
