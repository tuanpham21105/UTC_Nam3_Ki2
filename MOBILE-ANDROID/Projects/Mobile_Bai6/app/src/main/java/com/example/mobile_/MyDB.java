package com.example.mobile_;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {

    public static final String TableName = "User";
    public static final String Id = "Id";
    public static final String Name = "Name";
    public static final String PhoneNumber = "PhoneNumber";
    public static final String Image = "Image";
    public static final String Status = "Status";

    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MyDB(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists " + TableName +  "("
                + Id + " Integer Primary key AUTOINCREMENT, "
                + Name + " Text, "
                + PhoneNumber + " Text, "
                + Image + " Text, "
                + Status + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);

        onCreate(db);
    }

    public void addContact(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, user.getId());
        value.put(Name, user.getName());
        value.put(PhoneNumber, user.getPhonenumber());
        value.put(Image, user.getImgUrl());
        value.put(Status, user.isCheck() ? 1 : 0);
        db.insert(TableName, null, value);
        db.close();
    }

    public void updateContact(int id, User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, user.getId());
        value.put(Name, user.getName());
        value.put(PhoneNumber, user.getPhonenumber());
        value.put(Image, user.getImgUrl());
        value.put(Status, user.isCheck() ? 1 : 0);
        db.update(TableName, value, Id + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TableName, Id + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<User> getAllContact() {
        ArrayList<User> list = new ArrayList<>();

        String sql = " Select * from " + TableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                User user = new User(
                        cursor.getString(3),
                        cursor.getString(2),
                        cursor.getString(1),
                        cursor.getInt(0),
                        cursor.getInt(4));
                list.add(user);
            }
        }
        return list;
    }
}
