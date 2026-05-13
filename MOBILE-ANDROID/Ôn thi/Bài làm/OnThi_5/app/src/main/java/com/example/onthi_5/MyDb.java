package com.example.onthi_5;

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
    public static final String gia = "gia";
    public static final String chiTiet = "chiTiet";
    public static final String giamGia = "giamGia";

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
                + gia + " Text, "
                + chiTiet + " Text, "
                + giamGia + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                SanPham a = new SanPham(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4) == 1 ? true : false
                );
                list.add(a);
            }
        }

        return list;
    }

    public void add(SanPham a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
//        value.put(ma, a.ma);
        value.put(ten, a.ten);
        value.put(gia, a.gia);
        value.put(chiTiet, a.chiTiet);
        value.put(giamGia, a.giamGia ? 1 : 0);
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(SanPham a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
//        value.put(ma, a.ma);
        value.put(ten, a.ten);
        value.put(gia, a.gia);
        value.put(chiTiet, a.chiTiet);
        value.put(giamGia, a.giamGia ? 1 : 0);
        db.update(tableName, value, this.ma + "=?", new String[]{String.valueOf(a.ma)});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
