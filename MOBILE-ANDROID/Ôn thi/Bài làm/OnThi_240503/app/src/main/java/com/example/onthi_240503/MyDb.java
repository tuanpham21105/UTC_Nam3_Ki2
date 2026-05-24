package com.example.onthi_240503;

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
    public static final String tableName = "KhachHang";
    public static final String ma = "ma";
    public static final String hoTen = "hoTen";
    public static final String soDienThoai = "soDienThoai";
    public static final String ngayDanhGia = "ngayDanhGia";
    public static final String binhChon = "binhChon";

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
                + hoTen + " Text, "
                + soDienThoai + " Text, "
                + ngayDanhGia + " Text, "
                + binhChon + " Float)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                KhachHang a = new KhachHang(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        LocalDateTime.parse(cursor.getString(3)),
                        cursor.getFloat(4)
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

    public void add( KhachHang a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(hoTen, a.getHoTen());
        value.put(soDienThoai, a.getSoDienThoai());
        value.put(ngayDanhGia, a.getNgayDanhGia().toString());
        value.put(binhChon, a.getBinhChon());
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(KhachHang a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(hoTen, a.getHoTen());
        value.put(soDienThoai, a.getSoDienThoai());
        value.put(ngayDanhGia, a.getNgayDanhGia().toString());
        value.put(binhChon, a.getBinhChon());
        db.update(tableName, value,this.ma + "=?", new String[]{String.valueOf(a.getMa())});
        db.close();
    }

    public void delete(String ma) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(ma)});
        db.close();
    }
}
