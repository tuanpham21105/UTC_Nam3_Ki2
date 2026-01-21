package com.example.ltmobile_bai_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button buttonOk;
    TextView textView;
    TextView editTextA;
    TextView editTextB;
    TextView editTextC;
    TextView editTextOperator;

    @SuppressLint("MissingInflatedId")
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

        buttonOk .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(editTextA.getText().toString());
                double b = Double.parseDouble(editTextB.getText().toString());
                double c = Double.parseDouble(editTextC.getText().toString());
                String o = editTextOperator.getText().toString();
                double kq = 0;

                if (o.equals("+")) {
                    kq += a + b + c;
                }
                else if (o.equals("-")) {
                    kq += a - b - c;
                }
                else if (o.equals("*")) {
                    kq += a * b * c;
                }
                else {
                    kq += a / b / c;
                }

                textView.setText("Ket qua la: " + kq);

//                Toast.makeText(MainActivity.this, "Xin chao " + ht, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initWidgets() {
        buttonOk = (Button)  findViewById(R.id.buttonOk);
        textView = (TextView) findViewById(R.id.textView);
        editTextA = (EditText) findViewById(R.id.editTextA);
        editTextB = (EditText) findViewById(R.id.editTextB);
        editTextC = (EditText) findViewById(R.id.editTextC);
        editTextOperator = (EditText) findViewById(R.id.editTextOperator);
    }
}