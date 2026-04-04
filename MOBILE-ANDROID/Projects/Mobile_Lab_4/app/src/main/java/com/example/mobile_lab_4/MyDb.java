package com.example.mobile_lab_4;

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

public class MyDb extends SQLiteOpenHelper {
    public static final String tableName = "User";
    public static final String id = "id";
    public static final String name = "name";
    public static final String phone = "phone";
    public static final String image = "image";

    public MyDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MyDb(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists " + tableName +  "("
                + id + " Integer Primary key AUTOINCREMENT, "
                + name + " Text, "
                + phone + " Text, "
                + image + " Text)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                User user = new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                users.add(user);
            }
        }

        return users;
    }

    public void add(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(id, user.id);
        value.put(name, user.name);
        value.put(phone, user.phone);
        value.put(image, user.image);
        db.insert(tableName, null, value);
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.id + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
