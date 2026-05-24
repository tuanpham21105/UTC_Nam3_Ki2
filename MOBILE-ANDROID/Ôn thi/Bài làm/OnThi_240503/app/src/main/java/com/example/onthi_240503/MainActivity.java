package com.example.onthi_240503;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    MyDb myDb;

    MyAdapter myAdapter;

    TextView trungBinhEt;
    ListView listView;
    Button sapXepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        InitView();

        InitDb();

        InitAdapter();
    }

    void InitView() {
        trungBinhEt = findViewById(R.id.textViewTrungBinh);
        listView = findViewById(R.id.listView);
        sapXepBtn = findViewById(R.id.buttonSapXep);

        sapXepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.SortData();
            }
        });

        registerForContextMenu(listView);
    }

    void InitDb() {
        myDb = new MyDb(this, "KhachHangDb", null, 1);
        myDb.add(new KhachHang(GenerateKhachHangMa(), "ABC", "123", LocalDateTime.now(), 1.123f));
        myDb.add(new KhachHang(GenerateKhachHangMa(), "ABC", "456", LocalDateTime.now(), 7.123f));
        myDb.add(new KhachHang(GenerateKhachHangMa(), "ABC", "789", LocalDateTime.now(), 6.123f));

    }

    void InitAdapter() {
        myAdapter = new MyAdapter(this, myDb.getAll());

        listView.setAdapter(myAdapter);

        UpdateTrungBinh();
    }

    void UpdateTrungBinh() {
        float sum = 0;

        for (KhachHang a : myAdapter.data) {
            sum += a.tinhDiem();
        }

        trungBinhEt.setText(String.valueOf(sum / myAdapter.getCount()));
    }

    String GenerateKhachHangMa() {
        int size = myDb.getSize();

        int id = (size * GlobalUtils.B) + GlobalUtils.A;

        return "KH" + String.valueOf(id);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (info == null) return super.onContextItemSelected(item);

        int position = info.position;

        KhachHang a = myAdapter.getItem(position);

        if (item.getItemId() == R.id.menuItemXoa) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete");
            builder.setMessage(String.format(
                    "Bạn có muốn xóa khách hàng với thông tin \n" +
                    "%s\n%s\n%s\n%s\n%.1f", a.getMa(), a.getHoTen(), a.getSoDienThoai(), a.getNgayDanhGia().toString(), a.tinhDiem())
            );

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DeleteItem(position);
                }
            });

            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // User clicked No
                    dialog.dismiss();
                }
            });

            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void DeleteItem(int position) {
        KhachHang a = myAdapter.getItem(position);

        myDb.delete(a.getMa());

        myAdapter.data.remove(position);

        myAdapter.notifyDataSetChanged();

        UpdateTrungBinh();
    }
}