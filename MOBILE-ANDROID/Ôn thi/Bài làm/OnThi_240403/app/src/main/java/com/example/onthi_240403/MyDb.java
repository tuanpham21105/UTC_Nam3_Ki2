package com.example.onthi_240403;

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
    public static final String tableName = "HangHoa";
    public static final String ma = "ma";
    public static final String ten = "ten";
    public static final String gia = "gia";
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
                + ma + " Integer, "
                + ten + " Text, "
                + gia + " Integer, "
                + giamGia + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<HangHoa> getAll() {
        ArrayList<HangHoa> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                HangHoa a = new HangHoa(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3) == 1 ? true : false
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

    public void add( HangHoa a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(gia, a.getGia());
        value.put(giamGia, a.isGiamGia());
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(HangHoa a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(gia, a.getGia());
        value.put(giamGia, a.isGiamGia());
        db.update(tableName, value,this.ma + "=?", new String[]{String.valueOf(a.getMa())});
        db.close();
    }

    public void delete(int ma) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(ma)});
        db.close();
    }
}
