package com.example.mobile_onthi_1_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VeTauEdit extends AppCompatActivity {

    EditText gaDiEt;
    EditText gaDenEt;
    EditText giaEt;
    RadioGroup loaiRg;
    RadioButton khuHoiRb;
    RadioButton motChieuRb;
    Button suaBtn;
    Button huyBtn;

    int id;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ve_tau_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gaDiEt = findViewById(R.id.editTextText2);
        gaDenEt = findViewById(R.id.editTextText3);
        giaEt = findViewById(R.id.editTextText4);
        khuHoiRb = findViewById(R.id.khu_hoi);
        motChieuRb = findViewById(R.id.mot_chieu);
        suaBtn = findViewById(R.id.Sua_btn);
        huyBtn = findViewById(R.id.quayve_btn);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            id = bundle.getInt("ma");
            position = bundle.getInt("pos");
            gaDiEt.setText(bundle.getString("ga di"));
            gaDenEt.setText(bundle.getString("ga den"));
            giaEt.setText(String.valueOf(bundle.getFloat("gia")));
            if (bundle.getBoolean("loai"))
                khuHoiRb.setChecked(true);
            else
                motChieuRb.setChecked(true);
        }

        suaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                bundle.putInt("ma", id);
                bundle.putString("ga di", gaDiEt.getText().toString());
                bundle.putString("ga den", gaDenEt.getText().toString());
                bundle.putFloat("gia", Float.parseFloat(giaEt.getText().toString()));
                bundle.putBoolean("loai", khuHoiRb.isChecked());
                intent.putExtras(bundle);
                setResult(201, intent);
                finish();
            }
        });

        huyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                Bundle bundle = new Bundle();

                setResult(400);
                finish();
            }
        });
    }
}