package com.bobby.sqlitemvp.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "USERNAME";
    public static final String COL_5 = "MOBILE";
    public static final String COL_6 = "DOB";
    public static final String COL_7 = "GENDER";
    public static final String COL_8 = "PASSWORD";

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT, EMAIL TEXT, USERNAME TEXT, MOBILE TEXT, DOB TEXT, GENDER TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean insertData(String name, String username, String email, String password, String dob, String gender, String mobile)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_4, username);
        contentValues.put(COL_3, email);
        contentValues.put(COL_8, password);
        contentValues.put(COL_6, dob);
        contentValues.put(COL_7, gender);
        contentValues.put(COL_5, mobile);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
    }

    public boolean updateData(String id, String name, String username, String email, String password, String dob, String gender, String mobile)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_4, username);
        contentValues.put(COL_3, email);
        contentValues.put(COL_8, password);
        contentValues.put(COL_6, dob);
        contentValues.put(COL_7, gender);
        contentValues.put(COL_5, mobile);

        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});

        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public boolean checkData(String value, String column)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {column},
                column + " LIKE ?", new String[] {"%" + value + "%"},
                null, null, null);

        return cursor.getCount() > 0;
    }

    public boolean checkPassword(String username, String password)
    {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            /*cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "
                    +username+"=? AND "+password+"=?", null);*/
            cursor = sqLiteDatabase.query(TABLE_NAME, null, "USERNAME=? and PASSWORD=?",
                    new String[] {username,password}, null, null, null);


        } catch (Exception e) {
            new DialogBox(context, "Exception", ""+e.getMessage());
        }

        return cursor.getCount() > 0;
    }
}
