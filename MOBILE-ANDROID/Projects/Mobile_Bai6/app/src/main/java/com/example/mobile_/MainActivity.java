package com.example.mobile_;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
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
    }


}