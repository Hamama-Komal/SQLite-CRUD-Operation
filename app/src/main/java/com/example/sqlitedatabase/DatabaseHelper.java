package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private  static  final String DATABASE_NAME = "myDatabase.db";
    private  static  final String TABLE_NAME = "informationTable";

    private  static  final String COL_1 = "ID";
    private  static  final String COL_2 = "NAME";
    private  static  final String COL_3 = "FATHER_NAME";
    private  static  final String COL_4 = "EMAIL";
    private  static  final String COL_5 = "CONTACT_NUMBER";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // Creating Table and its declare its coulumns

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE  " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT," +
                " FATHER_NAME TEXT, EMAIL TEXT, CONTACT_NUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    // METHOD TO INSERT DATA

    public  boolean insertData(String name,String fatherName, String email, String contactNumber ){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, fatherName);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, contactNumber);

        long success = database.insert(TABLE_NAME, null, contentValues);

        database.close();

        return success != -1;
    }

    // METHOD TO FETCH DATA

    public Cursor fetchData(){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(" Select * from "+ TABLE_NAME, null);

        return cursor;
    }

    // METHOD TO UPDATE DATA

    public boolean updateData( String id, String name, String fname, String email, String contact){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, fname);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, contact);

        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        db.close();


        return true;

    }


    //   METHOD TO DELETE DATA

    public Integer deleteData(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(TABLE_NAME," ID = ? ",new String[]{id});
    }


}
