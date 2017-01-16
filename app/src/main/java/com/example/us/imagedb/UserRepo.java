package com.example.us.imagedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by us on 31.12.2016.
 */

public class UserRepo {

    private DbHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DbHelper(context);
    }
    /**
     * Storing user details in database
     * */
    public void addUser(String email, String password) {
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_EMAIL, email);
        values.put(User.COLUMN_PASS, password);

        long id = db.insert(User.USER_TABLE, null, values);
        db.close();

        Log.d(dbHelper.TAG, "user inserted" + id);
    }


    public void deleteUser(int user_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        long user_delete = db.delete(User.USER_TABLE, User.COLUMN_ID + "= ?", new String[] { String.valueOf(user_id) });
        Log.d(DbHelper.TAG, "user delete: " + user_id );
        db.close(); // Closing database connection
    }
    public void updateUser(User user) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.COLUMN_EMAIL, user.email);
        values.put(User.COLUMN_PASS,user.password);

        // It's a good practice to use parameter ?, instead of concatenate string
        long user_update= db.update(User.USER_TABLE, values, User.COLUMN_ID + "= ?", new String[] { String.valueOf(user.user_id) });
        Log.d(DbHelper.TAG, "user update: " + user_update );
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getUserList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.COLUMN_ID+ "," +
                User.COLUMN_EMAIL + "," +
                User.COLUMN_PASS + "," +
                " FROM " + User.USER_TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(User.COLUMN_ID)));
                user.put("email", cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;

    }

    public boolean getUser(String email, String pass){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + User.USER_TABLE + " where " +
                User.COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + User.COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public User getUserById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.COLUMN_ID + "," +
                User.COLUMN_EMAIL + "," +
                User.COLUMN_PASS + "," +
                " FROM " + User.USER_TABLE
                + " WHERE " +
                User.COLUMN_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        User us = new User();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                us.user_id =cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID));
                us.email =cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL));
                us.password  =cursor.getString(cursor.getColumnIndex(User.COLUMN_PASS));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return us;
    }

    public List<String> getRecord(String uname, String pword) {

        List<String> recordList = new ArrayList<String>();

        SQLiteDatabase dataBase = dbHelper.getReadableDatabase();

        String getSQL = "SELECT * FROM " + User.USER_TABLE + " WHERE " + User.COLUMN_EMAIL + " = '" + uname + "' AND " + User.COLUMN_PASS + " = '" + pword + "'";

        Cursor cursor = dataBase.rawQuery(getSQL , null);

        Log.d("getRecord()", getSQL + "##Count = " + cursor.getCount());

        cursor.moveToFirst();



        String eMail = cursor.getString(3);

        String ph = cursor.getString(4);

        Log.d("getRecord()",  "Email: " + eMail + "Phone" + ph);

        recordList.add(eMail);

        recordList.add(ph);

        dataBase.close();

        return recordList;

    }

    public SQLiteDatabase db;

    public String getUserName(String userName) {

        Cursor cursor=db.query("LOGIN", new String[]{userName}, null, null, null, null, null);

        cursor.moveToFirst();
        String user = cursor.getString(cursor.getColumnIndex("email"));
        cursor.close();
        return user;
    }
}
