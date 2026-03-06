package com.example.mobile_;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvContact;

    ArrayList<String> contact;

    ArrayAdapter<String> lvAdapter;

    ArrayList<User> listUser;

    Adapter listUserAdapter;

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
    }
}