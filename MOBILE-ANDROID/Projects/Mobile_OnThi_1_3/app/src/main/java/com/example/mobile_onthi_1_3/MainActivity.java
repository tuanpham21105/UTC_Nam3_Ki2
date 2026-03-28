package com.example.mobile_onthi_1_3;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText searchEt;
    ListView veTauLv;

    ArrayList<VeTau> veTaus = new ArrayList<>();

    VeTauAdapter adapter;

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

        searchEt = findViewById(R.id.editTextText);
        veTauLv = findViewById(R.id.listView);

        veTaus.add(new VeTau(0, "Vinh", "Ha Noi", 101.12f, true));
        veTaus.add(new VeTau(1, "Ha Noi", "Thanh Hoa", 31101.12f, false));
        veTaus.add(new VeTau(2, "Ha Noi", "Quang Ninh", 3115401.12f, false));
        veTaus.add(new VeTau(3, "Hung Yen", "Thanh Hoa", 501.12f, true));

        adapter = new VeTauAdapter(this, veTaus);

        veTauLv.setAdapter(adapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().trim().toLowerCase();

                adapter.data.clear();

                if (keyword.isEmpty()) {
                    adapter.data = new ArrayList<>(adapter.backup);
                }
                else {
                    for (VeTau v : adapter.backup) {
                        if (v.gaDi.trim().toLowerCase().contains(keyword) || v.gaDen.trim().toLowerCase().contains(keyword)) {
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


    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                    Toast.makeText(MainActivity.this, "Wifi da duoc bat/tat", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sum1) {
            Toast.makeText(this, "Tong tien ve khu hoi: " + KhuHoiSum(), Toast.LENGTH_LONG).show();
        }
        else {

            Toast.makeText(this, "Tong tien ve mot chieu: " + MotChieuSum(), Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public float KhuHoiSum() {
        float sum = 0;
        for (VeTau v : adapter.data) {
            if (v.loai) {
                sum += v.donGia;
            }
        }
        return  sum;
    }

    public float MotChieuSum() {
        float sum = 0;
        for (VeTau v : adapter.data) {
            if (!v.loai) {
                sum += v.donGia;
            }
        }
        return sum;
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

        VeTau v = adapter.data.get(position);

        if (item.getItemId() == R.id.cm_sua) {
            Intent intent = new Intent(this, VeTauEdit.class);
            Bundle bundle = new Bundle();
            bundle.putInt("pos", position);
            bundle.putInt("ma", v.ma);
            bundle.putString("ga di", v.gaDi);
            bundle.putString("ga den", v.gaDen);
            bundle.putFloat("gia", (v.donGia));
            bundle.putBoolean("loai", v.loai);
            intent.putExtras(bundle);
            startActivityForResult(intent, 200);

            return true;
        }
        else {

        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 400) {
            return;
        }

        Bundle bundle = data.getExtras();

        if (requestCode == 200 && resultCode == 201) {
            adapter.data.set(bundle.getInt("pos"), new VeTau(bundle.getInt("ma"), bundle.getString("ga den"), bundle.getString("ga di"), bundle.getFloat("gia"), bundle.getBoolean("loai")));
            adapter.backup.forEach(v -> {
                if (v.ma == bundle.getInt("ma")) {
                    v.gaDi = bundle.getString("ga di");
                    v.gaDen = bundle.getString("ga den");
                    v.donGia = bundle.getFloat("gia");
                    v.loai = bundle.getBoolean("loai");
                }
            });
        }

        adapter.notifyDataSetChanged();
        veTauLv.setAdapter(adapter);

        super.onActivityResult(requestCode, resultCode, data);
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