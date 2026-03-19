package com.example.mobile_;

import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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
        listUser = new ArrayList<>();
        listUser.add(
            new User("", "123456789", "Nam", 1)
        );
        listUser.add(
                new User("", "123456789", "HO VIET TUNG", 2)
        );
        listUser.add(
                new User("", "123456789", "TRAN TIEN SON BEO", 3)
        );

        listUserAdapter = new Adapter(this, listUser);

        lvContact.setAdapter(listUserAdapter);

        deleteButton = findViewById(R.id.floatingActionButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = listUser.size() - 1; i >= 0; i--) {
                    if (listUser.get(i).isCheck()) {
                        listUser.remove(listUser.get(i));
                        listUserAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        initNetworkCallback();
        initAirPlaneCallback();
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


}