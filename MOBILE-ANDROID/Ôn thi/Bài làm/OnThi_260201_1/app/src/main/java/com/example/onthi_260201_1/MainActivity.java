package com.example.onthi_260201_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

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

        initView();

        initDb();

        initAdapter();
    }


    TextView trungBinhTv;
    ListView listView;
    Button sortBtn;
    FloatingActionButton addBtn;
    void initView() {
        trungBinhTv = findViewById(R.id.textViewTrungBinh);
        listView = findViewById(R.id.listView);
        sortBtn = findViewById(R.id.buttonSort);
        addBtn = findViewById(R.id.buttonAdd);

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.SortData();
            }
        });

        registerForContextMenu(listView);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedirectToActivity();
            }
        });
    }

    MyDb myDb;
    void initDb() {
        myDb = new MyDb(this, "DonHangDb", null, 2);

        myDb.add(new DonHang(generateId(), "DEF", LocalDate.now(), 32410000, true));
        myDb.add(new DonHang(generateId(), "MNO", LocalDate.now(), 1040430000, false));
        myDb.add(new DonHang(generateId(), "HIJ", LocalDate.now(), 1003247582, true));
        myDb.add(new DonHang(generateId(), "ABC", LocalDate.now(), 1000000, false));
    }

    String generateId() {
        int size = myDb.getSize();
        return "DH" + String.valueOf((GlobalUtils.B * size) + GlobalUtils.A);
    }


    MyAdapter myAdapter;
    void initAdapter() {
        myAdapter = new MyAdapter(this, myDb.getAll());

        listView.setAdapter(myAdapter);

        UpdateTrungBinh();
    }

    public void UpdateTrungBinh() {
        trungBinhTv.setText(String.valueOf(myAdapter.TrungBinh()));
    }


    void RedirectToActivity() {
        Intent intent = new Intent(this, DonHangAddActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("ma", generateId());
        intent.putExtras(bundle);
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 400) return;

        Bundle bundle = data.getExtras();

        DonHang a = new DonHang(bundle.getString("ma"), bundle.getString("ten"), parseNgay(bundle.getString("ngay")), bundle.getFloat("gia"), bundle.getBoolean("loai"));

        int position = bundle.getInt("index");

        if (requestCode == 200 && resultCode == 200) {
            addNewItem(a);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    LocalDate parseNgay(String ngayStr) {
        try {
            String[] parts = ngayStr.split("/");

            return LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
        }
        catch(Exception e) {
            return LocalDate.now();
        }
    }


    void addNewItem(DonHang a) {
        myDb.add(a);

        myAdapter.data = myDb.getAll();

        myAdapter.notifyDataSetChanged();
    }
}