package com.example.onthi_260201;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DonHangAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.donhang_add_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.donhang_layout_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        setupView();
    }

    TextView maEt;
    EditText ngayEt;
    EditText tenEt;
    EditText giaEt;
    Switch loaiSw;
    Button addBtn;
    Button cancelBtn;
    void initView() {
        maEt = findViewById(R.id.editTextMa);
        ngayEt = findViewById(R.id.editTextNgay);
        tenEt = findViewById(R.id.editTextTen);
        giaEt = findViewById(R.id.editTextGia);
        loaiSw = findViewById(R.id.switchLoai);
        addBtn = findViewById(R.id.buttonAdd1);
        cancelBtn = findViewById(R.id.buttonCancel);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endActivity(true);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endActivity(false);
            }
        });
    }

    void setupView() {
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            maEt.setText(bundle.getString("ma"));
            tenEt.setText("");
            ngayEt.setText(LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());
            giaEt.setText("0");
            loaiSw.setChecked(false);
        }
    }

    void endActivity(boolean state) {
        if (state) {
            Intent intent = new Intent(this, DonHangAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("ma", maEt.getText().toString());
            bundle.putString("ten", tenEt.getText().toString());
            bundle.putString("ngay", ngayEt.getText().toString());
            bundle.putFloat("gia", Float.parseFloat(giaEt.getText().toString()));
            bundle.putBoolean("loai", loaiSw.isChecked());
            intent.putExtras(bundle);

            setResult(200, intent);
            finish();
        }
        else {
            Intent intent = new Intent();

            setResult(400, intent);
            finish();
        }
    }
}

