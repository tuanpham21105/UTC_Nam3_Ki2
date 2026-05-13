package com.example.onthi_5;

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

public class MainActivity extends AppCompatActivity {
    EditText searchEt;
    ListView listView;

    SanPhamAdapter adapter;

    MyDb db;

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

        db = new MyDb(this, "SanPhamDb", null, 4);

        db.add(new SanPham(0, "C", "150000", "", true));
        db.add(new SanPham(0, "B", "450000", "", false));
        db.add(new SanPham(0, "A", "280000", "", true));
        db.add(new SanPham(0, "D", "280000", "", true));
        db.add(new SanPham(0, "E", "280000", "", true));

        adapter = new SanPhamAdapter(this, db.getAll());

        listView.setAdapter(adapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().trim().toLowerCase();

                adapter.data.clear();

                if (keyword.isBlank()) {
                    adapter.data.addAll(db.getAll());
                }
                else {
                    for (SanPham v : db.getAll()) {
                        if (v.ten.trim().toLowerCase().contains(keyword)) {
                            adapter.data.add(v);
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

        registerForContextMenu(listView);

        initBroadcastReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (info == null) return super.onContextItemSelected(item);

        int position = info.position;

        if (item.getItemId() == R.id.menuItemSort) {
            adapter.data.sort((a1, a2) -> a1.ten.compareTo(a2.ten));

            adapter.notifyDataSetChanged();

            return true;
        }
        else if (item.getItemId() == R.id.menuItemDelete) {
            for (int i = adapter.getCount() - 1; i > position; i--) {
                db.delete((int)adapter.getItemId(i));
                adapter.data.remove(i);
            }

            adapter.notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
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
                                "WiFi OFF",
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