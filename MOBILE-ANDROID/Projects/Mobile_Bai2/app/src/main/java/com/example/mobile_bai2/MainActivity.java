package com.example.mobile_bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText weightEditText;
    EditText heightEditText;
    Button calculateButton;
    TextView resText;
    ImageView imageView;

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

        calculateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double h, w;
                h = Double.parseDouble(heightEditText.getText().toString());
                w = Double.parseDouble(weightEditText.getText().toString());

                double res = w / (h * h);

                if (res <= 12) {
                    imageView.setImageResource(R.drawable.skinny);
                }
                else if (res > 12 && res <= 30) {
                    imageView.setImageResource(R.drawable.normal);
                }
                else {
                    imageView.setImageResource(R.drawable.fat);
                }
                imageView.setVisibility(View.VISIBLE);

                resText.setText("" + res);
            }
        });

    }

    private void initWidgets() {
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resText = findViewById(R.id.resText);
        imageView = findViewById(R.id.imageView);
    }
}