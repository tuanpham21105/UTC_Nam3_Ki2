package com.example.mobile_onthi1_2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText searchET;
    ListView baiHatLV;
    Button themBtn;
    Button suaBtn;
    EditText tenBaiHatEt;
    EditText tenCaSiEt;
    EditText diemEt;
    Button ringtoneBtn;

    ArrayList<BaiHat> baiHats;

    BaiHatAdapter adapter;

    int itemId;

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

        searchET = findViewById(R.id.editText_search);
        baiHatLV = findViewById(R.id.listView_baihat);
        tenBaiHatEt = findViewById(R.id.editText_name);
        tenCaSiEt = findViewById(R.id.editText_singer);
        diemEt = findViewById(R.id.editText_diem);
        themBtn = findViewById(R.id.button_them);
        suaBtn = findViewById(R.id.button_sua);
        ringtoneBtn = findViewById(R.id.button_playringtone);

        baiHats = new ArrayList<>();
        baiHats.add(new BaiHat(0, "Kiếp đỏ đen", 4.5f, "Duy Mạnh"));
        baiHats.add(new BaiHat(1, "Lạc Trôi", 7.5f, "Sơn Tùng"));
        baiHats.add(new BaiHat(2, "Xuân này con ko ", 6.52f, "Quang Lê"));

        adapter = new BaiHatAdapter(this, baiHats);

        baiHatLV.setAdapter(adapter);

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchET.getText().toString().trim().toLowerCase();

                adapter.data.clear();

                if (keyword.isEmpty()) {
                    adapter.data.addAll(adapter.backup);
                }
                else {
                    for (BaiHat b : adapter.backup) {
                        if (b.ten.toLowerCase().trim().contains(keyword)) {
                            adapter.data.add(b);
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        baiHatLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiHat bh = adapter.data.get(position);

                itemId = bh.ma;

                tenBaiHatEt.setText(bh.ten);
                tenCaSiEt.setText(bh.caSi);
                diemEt.setText(String.valueOf(bh.diemDanhGia));
            }
        });

        themBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = adapter.data.size();
                String tenBaiHat = tenBaiHatEt.getText().toString();
                String tenCaSi = tenCaSiEt.getText().toString();
                float diem = Float.parseFloat(diemEt.getText().toString());

                BaiHat bh = new BaiHat(id, tenBaiHat, diem, tenCaSi);

                adapter.backup.add(bh);
                adapter.data.clear();
                adapter.data.addAll(adapter.backup);

                adapter.notifyDataSetChanged();
            }
        });

        suaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiHat bh = adapter.backup.get(itemId);

                bh.ten = tenBaiHatEt.getText().toString();
                bh.caSi = tenCaSiEt.getText().toString();
                bh.diemDanhGia = Float.parseFloat(diemEt.getText().toString());

                adapter.data.clear();
                adapter.data.addAll(adapter.backup);

                adapter.notifyDataSetChanged();
            }
        });

        ringtoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RingtoneActivity.class);
                startActivity(intent);
            }
        });
    }
}