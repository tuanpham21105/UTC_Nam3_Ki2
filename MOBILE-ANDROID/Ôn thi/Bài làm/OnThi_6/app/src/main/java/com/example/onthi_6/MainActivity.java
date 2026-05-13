package com.example.onthi_6;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText searchEt;
    ListView listView;
    FloatingActionButton addBtn;

    MyDb db;

    NhaHangAdapter adapter;

    BroadcastReceiver broadcastReceiver;

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

        searchEt = findViewById(R.id.editTextSearch);
        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.floatingButtonAdd);

        db = new MyDb(this, "NhaHangDb", null, 1);
        adapter = new NhaHangAdapter(this, new ArrayList<>());
        listView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.add(new NhaHang(0, "A", "123 ABC", 8.6f));
                db.add(new NhaHang(0, "B", "123 ABC", 1.2f));
                db.add(new NhaHang(0, "C", "123 ABC", 5.4f));
                db.add(new NhaHang(0, "D", "123 ABC", 3.9f));
                db.add(new NhaHang(0, "E", "123 ABC", 10.0f));

                adapter.data.addAll(db.getAll());

                adapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "Them du lieu thanh cong", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                NhaHang a = adapter.getItem(position);
                builder.setTitle("Delete");
                builder.setMessage(String.format("Bạn có muốn xóa tất cả các nhà hàng có điểm nhỏ hơn %s ko?", a.danhGia));

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (NhaHang b : db.getAll()) {
                            if (b.danhGia < a.danhGia) {
                                db.delete(b.ma);
                            }
                        }

                        for (int i = adapter.getCount() - 1; i >= 0; i--) {
                            NhaHang b = adapter.getItem(i);
                            if (b.danhGia < a.danhGia) {
                                adapter.data.remove(b);
                            }
                        }

                        adapter.notifyDataSetChanged();
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

                return false;
            }
        });

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().toLowerCase().trim();

                adapter.data.clear();

                if (keyword.isBlank()) {
                    adapter.data.addAll(db.getAll());
                }
                else {
                    for (NhaHang sv : db.getAll()) {
                        if (sv.ten.toLowerCase().trim().contains(keyword)) {
                            adapter.data.add(sv);
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

        initBroadcastReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//                if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
//                    Toast.makeText(MainActivity.this, "Wifi da duoc dieu chinh", Toast.LENGTH_LONG).show();
//                }

                int state = intent.getIntExtra(
                        WifiManager.EXTRA_WIFI_STATE,
                        WifiManager.WIFI_STATE_UNKNOWN
                );

                switch (state) {

                    case WifiManager.WIFI_STATE_ENABLED:
//                        Toast.makeText(context,
//                                "WiFi ON",
//                                Toast.LENGTH_SHORT).show();
                        break;

                    case WifiManager.WIFI_STATE_DISABLED:
                        Toast.makeText(context,
                                "Mat mang",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case WifiManager.WIFI_STATE_ENABLING:
                        break;

                    case WifiManager.WIFI_STATE_DISABLING:
                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }
}