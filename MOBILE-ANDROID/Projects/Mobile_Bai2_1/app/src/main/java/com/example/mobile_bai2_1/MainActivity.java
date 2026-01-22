package com.example.mobile_bai2_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox, checkBox1;
    RadioButton radioButton, radioButton1;
    Button okButton;

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

        initWidgets();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "";
                if (checkBox.isChecked()) {
                    s += checkBox.getText().toString();
                }

                if (checkBox1.isChecked()) {
                    s += checkBox1.getText().toString();
                }

                if (radioButton.isChecked()) {
                    s += radioButton.getText().toString();
                }
                else if (radioButton1.isChecked()) {
                    s += radioButton1.getText().toString();
                }

                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void initWidgets() {
        checkBox = findViewById(R.id.checkBox);
        checkBox1 = findViewById(R.id.checkBox2);

        radioButton = findViewById(R.id.radioButton);
        radioButton1 = findViewById(R.id.radioButton2);

        okButton = findViewById(R.id.button);
    }
}