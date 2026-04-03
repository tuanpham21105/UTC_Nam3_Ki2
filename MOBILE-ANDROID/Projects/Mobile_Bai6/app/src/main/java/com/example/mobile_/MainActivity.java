package com.example.mobile_;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;

    ArrayList<String> contact;

    ArrayAdapter<String> lvAdapter;

    ArrayList<User> listUser;

    Adapter listUserAdapter;

    FloatingActionButton deleteButton;

    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;
    private BroadcastReceiver airplaneModeReceiver;
    int selectedid;
    MyDB myDB;

    EditText searchET;

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

        lvContact = findViewById(R.id.lvContact);
        myDB = new MyDB(this, "ContactDb", null, 1);
        myDB.addContact(
            new User("", "123456789", "Nam", 0)
        );
        myDB.addContact(
                new User("", "123456789", "HO VIET TUNG", 1)
        );
        myDB.addContact(
                new User("", "123456789", "TRAN TIEN SON BEO", 2)
        );

        listUser = myDB.getAllContact();

        listUserAdapter = new Adapter(this, listUser);

        lvContact.setAdapter(listUserAdapter);

        deleteButton = findViewById(R.id.floatingActionButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = listUser.size() - 1; i >= 0; i--) {
                    if (listUser.get(i).isCheck()) {
                        myDB.deleteContact(listUser.get(i).getId());
                        listUser.remove(listUser.get(i));
                    }
                }
                listUserAdapter.dataBackup = new ArrayList<>(myDB.getAllContact());
                listUserAdapter.notifyDataSetChanged();
            }
        });

        registerForContextMenu(lvContact);

        initNetworkCallback();
        initAirPlaneCallback();

        searchET = findViewById(R.id.searchEditText);

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listUserAdapter.getFilter().filter(s.toString());
                listUserAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initNetworkCallback() {
        connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Da ket noi mang", Toast.LENGTH_LONG).show();
                });
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Mat mang", Toast.LENGTH_LONG).show();
                });
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback);
        }
        else {
            NetworkRequest request = new NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build();
            connectivityManager.registerNetworkCallback(request, networkCallback);
        }
    }

    private void initAirPlaneCallback() {

        airplaneModeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
                    Toast.makeText(MainActivity.this, "Che do may bay thay doi", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (connectivityManager != null && networkCallback != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }

        unregisterReceiver(airplaneModeReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initNetworkCallback();


        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeReceiver, filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        getMenuInflater().inflate(R.menu.new_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_sort) {
            Toast.makeText(MainActivity.this, "Sort", Toast.LENGTH_LONG);
            Collections.sort(listUser, (u1, u2) -> {
                return u1.getName().toLowerCase().trim().compareToIgnoreCase(u2.getName().toLowerCase());
            });
            listUserAdapter.notifyDataSetChanged();
            lvContact.setAdapter(listUserAdapter);

        }
        else if (item.getItemId() == R.id.menu_item_add) {
            Intent intent = new Intent(MainActivity.this, AddUser.class);
            startActivityForResult(intent, 100);
        }

//        switch (item.getItemId()) {
//            case R.id.menu_item_sort:
//                break;
//            case R.id.menu_item_add:
//                break;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 400) {
            return;
        }

        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String name = bundle.getString("Name");
        String phone = bundle.getString("Phone");
        if (requestCode == 100 && resultCode == 200) {
            listUser.add(new User("", phone, name, id));
            myDB.addContact(new User("", phone, name, id));
            listUserAdapter.dataBackup = new ArrayList<>(myDB.getAllContact());
            listUserAdapter.notifyDataSetChanged();
        }
        else if (requestCode == 200 && resultCode == 201) {
            listUser.set(id, new User("", bundle.getString("Phone"), bundle.getString("Name"), id));
            myDB.updateContact(id, new User("", bundle.getString("Phone"), bundle.getString("Name"), id));
            listUserAdapter.dataBackup = new ArrayList<>(myDB.getAllContact());
            listUserAdapter.notifyDataSetChanged();
        }

        listUserAdapter.notifyDataSetChanged();
        lvContact.setAdapter(listUserAdapter);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info;

        User c = listUser.get(selectedid);

        selectedid = -1;

        if (item.getItemId() == R.id.cm_edit) {
            Intent intent = new Intent(this, AddUser.class);
            Bundle bundle = new Bundle();
            bundle.putInt("Id", c.getId());
            bundle.putString("Name", c.getName());
            bundle.putString("Phone", c.getPhonenumber());
            intent.putExtras(bundle);
            setResult(201, intent);
            startActivityForResult(intent, 200);
        }
        else if (item.getItemId() == R.id.cm_call) {

        }
        else if (item.getItemId() == R.id.cm_delete) {
            new AlertDialog.Builder(this).setTitle("Delete?")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    myDB.deleteContact(c.getId());
                                    listUser.remove(c.getId());
                                    lvContact.setAdapter(listUserAdapter);
                                    listUserAdapter.dataBackup = myDB.getAllContact();
                                    listUserAdapter.notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            }
                    )
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create().show();
        }

        return super.onContextItemSelected(item);
    }
}