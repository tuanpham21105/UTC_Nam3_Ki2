package com.example.mobile_onthi_1_5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    EditText searchEt;
    ListView sanphamLv;

    MyDb db;
    SanPhamAdapter adapter;

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

        searchEt = findViewById(R.id.editText_search);
        sanphamLv = findViewById(R.id.listView_sanpham);

        db = new MyDb(this, "SanPhamDb", null, 1);

        db.add(new SanPham(0, "SamSung 14", "200", "Giam con 180", true));
        db.add(new SanPham(1, "Iphone 14", "140", "Giam con 130", true));
        db.add(new SanPham(2, "Tv Sony 15", "30", "Giam con 1", false));

        adapter = new SanPhamAdapter(this, db.getAll());

        sanphamLv.setAdapter(adapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().toLowerCase().trim();

                adapter.data.clear();

                if (keyword.isEmpty()) {
                    adapter.data = new ArrayList<>(db.getAll());
                }
                else {
                    for (SanPham sp : db.getAll()) {
                        if (sp.ten.toLowerCase().trim().contains(keyword))
                            adapter.data.add(sp);
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

        registerForContextMenu(sanphamLv);

        initBroadcastReceiver();
    }

    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                    Toast.makeText(MainActivity.this, "Wifi da tat", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public boolean isSanPhamGiamGia(int i) {
        return adapter.getItem(i).giamgia;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (info == null) return super.onContextItemSelected(item);

        int position = info.position;

        SanPham v = adapter.data.get(position);

        int giatien = Integer.parseInt(v.giatien);

        if (item.getItemId() == R.id.sort) {
            sortByTen(adapter.data);

            adapter.notifyDataSetChanged();
            return true;
        }
        else if (item.getItemId() == R.id.delete) {
            for (int i = adapter.data.size() - 1; i >= 0; i--) {
                if (Integer.parseInt(adapter.getItem(i).giatien) <= giatien) {
                    db.delete(adapter.getItem(i).ma);
                    adapter.data.remove(i);
                }
            }
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    public static void sortByTen(ArrayList<SanPham> list) {
        list.sort(Comparator.comparing(sp -> sp.ten));
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