package com.example.onthi_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText tenBaiHatEt;
    EditText tenCaSiEt;
    EditText diemEt;
    Button themBtn;
    Button suaBtn;
    Button playBtn;
    ListView baiHatLv;

    BaiHatAdapter adapter;

    MyDb db;

    int maBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.playMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tenBaiHatEt = findViewById(R.id.editTextTenBaiHat);
        tenCaSiEt = findViewById(R.id.editTextTenCaSi);
        diemEt = findViewById(R.id.editTextDiem);
        themBtn = findViewById(R.id.buttonThem);
        suaBtn = findViewById(R.id.buttonSua);
        playBtn = findViewById(R.id.buttonPlay);
        baiHatLv = findViewById(R.id.listViewBaiHat);

        db = new MyDb(this, "BaiHatDb", null, 1);

        db.add(new BaiHat(0, "A", 4.5f, "ABC"));
        db.add(new BaiHat(1, "B", 5.5f, "ABC"));
        db.add(new BaiHat(2, "C", 6.5f, "ABC"));

        adapter = new BaiHatAdapter(this, db.getAll());

        baiHatLv.setAdapter(adapter);

        themBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.add(new BaiHat(-1,  tenBaiHatEt.getText().toString(), Float.parseFloat(diemEt.getText().toString()), tenCaSiEt.getText().toString()));
                adapter.data.clear();
                adapter.data = db.getAll();
                adapter.notifyDataSetChanged();
            }
        });

        suaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.update(new BaiHat(maBaiHat,  tenBaiHatEt.getText().toString(), Float.parseFloat(diemEt.getText().toString()), tenCaSiEt.getText().toString()));
                adapter.data.clear();
                adapter.data = db.getAll();
                adapter.notifyDataSetChanged();
            }
        });

        baiHatLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiHat baiHat = adapter.getItem(position);
                maBaiHat = baiHat.ma;
                tenBaiHatEt.setText(baiHat.ten);
                tenCaSiEt.setText(baiHat.tenCaSi);
                diemEt.setText(String.valueOf(baiHat.diem));
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getApplicationContext(), PlayActivity.class),
                        200
                );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}