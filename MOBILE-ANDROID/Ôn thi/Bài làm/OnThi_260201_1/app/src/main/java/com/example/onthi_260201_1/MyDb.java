package com.example.onthi_260201_1;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MyDb extends SQLiteOpenHelper {
    public static final String tableName = "DonHang";
    public static final String ma = "ma";
    public static final String ten = "ten";
    public static final String ngay = "ngay";
    public static final String gia = "gia";
    public static final String loai = "loai";

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
                + ma + " Text, "
                + ten + " Text, "
                + ngay + " Text, "
                + gia + " Float, "
                + loai + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<DonHang> getAll() {
        ArrayList<DonHang> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                DonHang a = new DonHang(
                        cursor.getString(0),
                        cursor.getString(1),
                        LocalDate.parse(cursor.getString(2)),
                        cursor.getFloat(3),
                        cursor.getInt(4) == 1 ? true : false
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

    public void add( DonHang a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(ngay, a.getNgay().toString());
        value.put(gia, a.getGia());
        value.put(loai, a.isLoai() ? 1 : 0);
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(DonHang a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(ngay, a.getNgay().toString());
        value.put(gia, a.getGia());
        value.put(loai, a.isLoai() ? 1 : 0);
        db.update(tableName, value,this.ma + "=?", new String[]{String.valueOf(a.getMa())});
        db.close();
    }

    public void delete(String ma) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(ma)});
        db.close();
    }
}
