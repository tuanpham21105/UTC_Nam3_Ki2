package com.example.mobile_lab_4;

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
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    EditText searchEt;
    ListView usersLv;
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

        searchEt = findViewById(R.id.search_edittext);
        usersLv = findViewById(R.id.user_listview);
        deleteBtn = findViewById(R.id.delete_btn);

        db = new MyDb(this, "ContactDb", null, 1);
        db.add(
                new User(0, "Nam", "123456789", "")
        );
        db.add(
                new User(1,"HO VIET TUNG", "123456789",  "")
        );
        db.add(
                new User(2, "TRAN TIEN SON BEO", "123456789", "")
        );

        adapter = new UserAdapter(this, db.getAll());

        usersLv.setAdapter(adapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().toLowerCase().trim();

                adapter.data.clear();

                if (keyword.isEmpty()) {
                    adapter.data = new ArrayList<>(db.getAll());
                }
                else {
                    for (User user : db.getAll()) {
                        if (user.name.toLowerCase().trim().contains(keyword))
                            adapter.data.add(user);
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

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = adapter.data.size() - 1; i >= 0; i--) {
                    if (isItemChecked(i)) {
                        db.delete(adapter.data.get(i).id);
                        adapter.data.remove(i);
                    }

                    adapter.notifyDataSetChanged();
                }
            }
        });

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

    private void sortByName() {
        Collections.sort(adapter.data, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.name.compareTo(u2.name);
            }
        });

        adapter.notifyDataSetChanged();
    }

    public boolean isItemChecked(int position) {
        return ((CheckBox) usersLv.getChildAt(position).findViewById(R.id.checkBox)).isChecked();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sort_menuitem) {
            sortByName();
        }

        return super.onOptionsItemSelected(item);
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