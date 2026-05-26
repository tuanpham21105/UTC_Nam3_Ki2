# Tạo CSDL

## Lớp CSDL
```
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
    public static final String tableName = "Sach";
    public static final String ma = "ma";
    public static final String ten = "ten";
    public static final String tacGia = "tacGia";
    public static final String gia = "gia";
    public static final String soLuong = "soLuong";

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
                + tacGia + " Text, "
                + gia + " Float, "
                + soLuong + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tableName);

        onCreate(db);
    }

    public ArrayList<Sach> getAll() {
        ArrayList<Sach> list = new ArrayList<>();

        String sql = " Select * from " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Sach a = new Sach(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getFloat(3),
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

    public void add( Sach a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(tacGia, a.getTacGia());
        value.put(gia, a.getGia());
        value.put(soLuong, a.getSoLuong());
        db.insert(tableName, null, value);
        db.close();
    }

    public void update(Sach a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(ma, a.getMa());
        value.put(ten, a.getTen());
        value.put(tacGia, a.getTacGia());
        value.put(gia, a.getGia());
        value.put(soLuong, a.getSoLuong());
        db.update(tableName, value,this.ma + "=?", new String[]{String.valueOf(a.getMa())});
        db.close();
    }

    public void delete(String ma) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, this.ma + "=?", new String[]{String.valueOf(ma)});
        db.close();
    }
}
```

## Thêm CSDL vào MainActivity
```
    MyDb myDb;
    void initDb() {
        myDb = new MyDb(this, "SachDb", null, 1);

        myDb.add(new Sach(generateId(), "DEF", "D E F", 32410000, 10));
        myDb.add(new Sach(generateId(), "MNO", "M N O", 1040430000, 34100));
        myDb.add(new Sach(generateId(), "HIJ", "HIJ", 1003247582, 153300));
        myDb.add(new Sach(generateId(), "ABC", "A B C", 1000000, 100));
    }
```

## Thêm hàm khởi tạo Id vào MainActivity
```
    String generateId() {
        int size = myDb.getSize();
        return "BK" + String.valueOf((GlobalUtils.B * size) + GlobalUtils.A);
    }
```
