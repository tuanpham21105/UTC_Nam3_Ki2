package com.example.onthi_240303;

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

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MyDb extends SQLiteOpenHelper {
    public static final String tableName = "BaiHat";
    public static final String id = "id";
    public static final String ten = "ten";
    public static final String caSy = "caSy";
    public static final String _like = "_like";
    public static final String share = "share";

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
                + id + " Integer, "
                + ten + " Text, "
                + caSy + " Text, "
                + _like + " Integer, "
                + share + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<BaiHat> getAll() {
        ArrayList<BaiHat> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                BaiHat a = new BaiHat(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                );
                list.add(a);
            }
        }

        return list;
    }

    public int getSize() {
        String sql = "SELECT COUNT(*) FROM " + tableName ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();

        return count;
    }

    public void add( BaiHat a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(id, a.getId());
        value.put(ten, a.getTen());
        value.put(caSy, a.getCaSy());
        value.put(_like, a.getLike());
        value.put(share, a.getShare());
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(BaiHat a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(id, a.getId());
        value.put(ten, a.getTen());
        value.put(caSy, a.getCaSy());
        value.put(_like, a.getLike());
        value.put(share, a.getShare());
        db.update(tableName, value,this.id + "=?", new String[]{String.valueOf(a.getId())});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.id + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
