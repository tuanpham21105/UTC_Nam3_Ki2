package com.example.mobile_onthi_1_5;

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
    public static final String tableName = "SanPham";
    public static final String ma = "ma";
    public static final String ten = "ten";
    public static final String giatien = "giatien";
    public static final String chitiet = "chitiet";
    public static final String giamgia = "giamgia";

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
                + ma + " Integer Primary key AUTOINCREMENT, "
                + ten + " Text, "
                + giatien + " Text, "
                + chitiet + " Text, "
                + giamgia + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> sps = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                SanPham sp = new SanPham(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 0 ? false : true
                );
                sps.add(sp);
            }
        }

        return sps;
    }

    public void add(SanPham sp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, sp.ma);
        value.put(ten, sp.ten);
        value.put(giatien, sp.giatien);
        value.put(chitiet, sp.chitiet);
        value.put(giamgia, sp.giamgia ? 1 : 0);
        db.insert(tableName, null, value);
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
