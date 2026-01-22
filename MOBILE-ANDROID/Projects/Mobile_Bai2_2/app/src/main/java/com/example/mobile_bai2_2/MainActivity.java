package com.example.mobile_bai2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, soTienEditText;
    RadioButton usdRadioButton, euroRadioButton, ndtRadioButton;
    CheckBox giamGiaCheckBox;
    Button ok;
    TextView resText;
    RadioGroup radioGroup;

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

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quyDoi = "VND";

                double ratio = 1;

                if (usdRadioButton.isChecked()) {
                    quyDoi = "USD";
                    ratio = 26000;
                }
                else if (euroRadioButton.isChecked()) {
                    quyDoi = "EURO";
                    ratio  = 30000;
                }
                else if (ndtRadioButton.isChecked()) {
                    quyDoi = "NDT";
                    ratio = 3.7;
                }

                double soTien = Double.parseDouble(soTienEditText.getText().toString()) / ratio;

                if (giamGiaCheckBox.isChecked()) {
                    soTien -= soTien * 0.1;
                }

                resText.setText("VND sang " + quyDoi + " la " + soTien);
            }
        });
    }

    private void initWidgets() {
        nameEditText = findViewById(R.id.nameEditText);
        soTienEditText = findViewById(R.id.soTienEditText);

        usdRadioButton = findViewById(R.id.usdRadioButton);
        euroRadioButton = findViewById(R.id.euroRadioButton);
        ndtRadioButton = findViewById(R.id.ndtRadioButton);

        giamGiaCheckBox = findViewById(R.id.giamGiaCheckBox);

        ok = findViewById(R.id.button);

        resText = findViewById(R.id.resTextView);

        radioGroup = findViewById(R.id.radioGroup);
    }
}