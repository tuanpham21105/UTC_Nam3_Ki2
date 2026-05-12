package com.example.onthi_3;

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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText searchEt;
    ListView veTauLv;

    VeTauAdapter adapter;

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
        veTauLv = findViewById(R.id.listView);

        db = new MyDb(this, "VeTauDb", null, 1);

        db.add(new VeTau(0, "A", "B", 100.100f, true));
        db.add(new VeTau(1, "C", "A", 200.100f, false));
        db.add(new VeTau(2, "D", "C", 300.100f, true));

        adapter = new VeTauAdapter(this, db.getAll());

        veTauLv.setAdapter(adapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().trim().toLowerCase();

                adapter.data.clear();

                if (keyword.isBlank()) {
                    adapter.data.addAll(db.getAll());
                }
                else {
                    for (VeTau v : db.getAll()) {
                        if (v.gaDen.trim().toLowerCase().contains(keyword) || v.gaDi.trim().toLowerCase().contains(keyword)) {
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

        registerForContextMenu(veTauLv);

        initBroadcastReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    float SumGiaVeKhuHoi() {
        float sum = 0;
        for (VeTau v : db.getAll()) {
            if (v.loai) {
                sum += v.gia;
            }
        }
        return sum;
    }

    float SumGiaVeMotChieu() {
        float sum = 0;
        for (VeTau v : db.getAll()) {
            if (!v.loai) {
                sum += v.gia;
            }
        }
        return sum;
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

        VeTau a = adapter.getItem(position);

        if (item.getItemId() == R.id.menuItemSua) {
            Intent intent = new Intent(this, VeTauEditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ma", a.ma);
            bundle.putInt("index", position);
            bundle.putString("gaDi", a.gaDi);
            bundle.putString("gaDen", a.gaDen);
            bundle.putFloat("gia", a.gia);
            bundle.putBoolean("loai", a.loai);
            intent.putExtras(bundle);
            startActivityForResult(intent, 200);

            return  true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 400) return;

        Bundle bundle = data.getExtras();

        if (requestCode == 200 && resultCode == 200) {
            VeTau a = new VeTau(bundle.getInt("ma"), bundle.getString("gaDen"), bundle.getString("gaDi"), bundle.getFloat("gia"), bundle.getBoolean("loai"));
            db.update(a);

            adapter.data.set(bundle.getInt("index"), a);

            adapter.notifyDataSetChanged();
        }

        super.onActivityResult(requestCode, resultCode, data);
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