package com.example.phamminhtuan_231230946;

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
    public static final String tableName = "BanBe";
    public static final String ma = "ma";
    public static final String ten = "ten";
    public static final String yeuThich = "yeuThich";
    public static final String ngaySinh = "ngaySinh";
    public static final String danhGia = "danhGia";
    public static final String loai = "loai";
    public static final String lop = "lop";


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
                + yeuThich + " Float, "
                + ngaySinh + " Text, "
                + danhGia + " Float, "
                + loai + " Integer, "
                + lop + " Text) ";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<BanBe> getAll() {
        ArrayList<BanBe> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                BanBe a = new BanBe(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getFloat(2),
                        LocalDate.parse(cursor.getString(3)),
                        cursor.getFloat(4),
                        cursor.getInt(5) == 1 ? true : false,
                        cursor.getString(6)
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

    public void add( BanBe a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(yeuThich, a.getYeuThich());
        value.put(ngaySinh, a.getNgaySinh().toString());
        value.put(danhGia, a.getDanhGia());
        value.put(loai, a.isLoai() ? 1 : 0);
        value.put(lop, a.getLop());
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(BanBe a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(yeuThich, a.getYeuThich());
        value.put(ngaySinh, a.getNgaySinh().toString());
        value.put(danhGia, a.getDanhGia());
        value.put(loai, a.isLoai() ? 1 : 0);
        value.put(lop, a.getLop());
        db.update(tableName, value,this.ma + "=?", new String[]{String.valueOf(a.getMa())});
        db.close();
    }

    public void delete(String ma) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(ma)});
        db.close();
    }
}
