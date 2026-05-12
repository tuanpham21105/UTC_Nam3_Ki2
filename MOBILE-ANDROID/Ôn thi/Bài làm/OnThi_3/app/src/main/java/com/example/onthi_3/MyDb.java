package com.example.onthi_3;

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
    public static final String tableName = "VeTau";
    public static final String ma = "ma";
    public static final String gaDen = "gaDen";
    public static final String gaDi = "gaDi";
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
                + ma + " Integer Primary key AUTOINCREMENT, "
                + gaDen + " Text, "
                + gaDi + " Text, "
                + gia + " Float, "
                + loai + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<VeTau> getAll() {
        ArrayList<VeTau> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                VeTau a = new VeTau(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getFloat(3),
                        cursor.getInt(4) == 1 ? true : false
                );
                list.add(a);
            }
        }

        return list;
    }

    public void add(VeTau a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
//        value.put(ma, a.ma);
        value.put(gaDen, a.gaDen);
        value.put(gaDi, a.gaDi);
        value.put(gia, a.gia);
        value.put(loai, a.loai ? 1 : 0);
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(VeTau a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
//        value.put(ma, a.ma);
        value.put(gaDen, a.gaDen);
        value.put(gaDi, a.gaDi);
        value.put(gia, a.gia);
        value.put(loai, a.loai ? 1 : 0);
        db.update(tableName, value, this.ma + "=?", new String[]{String.valueOf(a.ma)});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
