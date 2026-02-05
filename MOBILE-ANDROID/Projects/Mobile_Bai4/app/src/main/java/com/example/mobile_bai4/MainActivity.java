package com.example.mobile_bai4;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    TextView textView;
    Button buttonLogin;

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

        gridView = findViewById(R.id.gridVIew);

        final String[] kytu = new String[] {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, kytu);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, kytu[position].toString(), Toast.LENGTH_SHORT).show();
            }
        });

        textView = (TextView) findViewById(R.id.textView);

        buttonLogin = findViewById(R.id.loginButton);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMyDialog();
            }

            private void callMyDialog() {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.login);
                EditText usernameEditText = dialog.findViewById(R.id.usernameEditText);
                EditText passwordEditText = dialog.findViewById(R.id.passwordEditText);
                Button buttonOk = dialog.findViewById(R.id.buttonOk);
                Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = usernameEditText.getText().toString();
                        String password = passwordEditText.getText().toString();

                        if (username.equals("abcd") && password.equals("1234")) {
                            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuGreen) {
            textView.setBackgroundColor(Color.GREEN);
        }
        else if (item.getItemId() == R.id.mnuRed) {
            textView.setBackgroundColor(Color.RED);
        }
        else if (item.getItemId() == R.id.mnuYellow) {
            textView.setBackgroundColor(Color.YELLOW);
        }
        return super.onOptionsItemSelected(item);
    }
}