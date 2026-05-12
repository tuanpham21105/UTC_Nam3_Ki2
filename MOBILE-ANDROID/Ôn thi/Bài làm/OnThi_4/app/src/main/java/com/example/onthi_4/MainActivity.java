package com.example.onthi_4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    EditText searchEt;
    ListView userLv;
    Button addBtn;
    Button deleteBtn;

    UserAdapter adapter;

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
        userLv = findViewById(R.id.listView);
        addBtn = findViewById(R.id.buttonAdd);
        deleteBtn = findViewById(R.id.buttonDelete);

        db = new MyDb(this, "UserDb", null, 3);

        db.add(new User(0, "C", "0123", "", false));
        db.add(new User(1, "B", "0456", "", false));
        db.add(new User(2, "A", "0789", "", false));

        adapter = new UserAdapter(this, db.getAll());

        userLv.setAdapter(adapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().trim().toLowerCase();

                adapter.data.clear();

                if (keyword.isBlank()) {
                    adapter.data.addAll(db.getAll());
                }
                else {
                    for (User a : db.getAll()) {
                        if (a.ten.trim().toLowerCase().contains(keyword) ||a.soDienThoai.trim().toLowerCase().contains(keyword)) {
                            adapter.data.add(a);
                        }
                    }
                }

                adapter.notifyDataSetChanged();
                userLv.setAdapter(adapter);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Integer a : adapter.checks) {
                    db.delete(a);
                }

                adapter.data.removeIf(a -> adapter.checks.contains(a.ma));

                adapter.notifyDataSetChanged();
                userLv.setAdapter(adapter);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuItemSort) {
            adapter.data.sort((u1, u2) -> u1.ten.compareTo(u2.ten));

            adapter.notifyDataSetChanged();
            userLv.setAdapter(adapter);

            return  true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                    Toast.makeText(MainActivity.this, "Wifi da duoc dieu chinh", Toast.LENGTH_LONG).show();
                }

                int state = intent.getIntExtra(
                        WifiManager.EXTRA_WIFI_STATE,
                        WifiManager.WIFI_STATE_UNKNOWN
                );

                switch (state) {

                    case WifiManager.WIFI_STATE_ENABLED:
                        Toast.makeText(context,
                                "WiFi ON",
                                Toast.LENGTH_SHORT).show();
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