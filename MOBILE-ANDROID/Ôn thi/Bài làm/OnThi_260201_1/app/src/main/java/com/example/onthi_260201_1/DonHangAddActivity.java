package com.example.onthi_260201_1;

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

import java.time.LocalDate;

public class DonHangAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.donhang_add_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.donhang_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        setupView();
    }

    TextView maTv;
    EditText ngayEt;
    EditText tenEt;
    EditText giaEt;
    Switch loaiSw;
    Button addBtn;
    Button backBtn;
    void initView() {
        maTv = findViewById(R.id.textViewMa);
        ngayEt = findViewById(R.id.editTextNgay);
        tenEt = findViewById(R.id.editTextTen);
        giaEt = findViewById(R.id.editTextGia);
        loaiSw = findViewById(R.id.switchLoai);
        addBtn = findViewById(R.id.buttonAdd1);
        backBtn = findViewById(R.id.buttonBack);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endActivity(true);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endActivity(false);
            }
        });
    }

    int position;
    void setupView() {
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            maTv.setText(bundle.getString("ma"));
            ngayEt.setText(LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());
        }
    }

    void endActivity(boolean state) {
        if (state) {
            Intent intent = new Intent(this, DonHangAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            bundle.putString("ma", maTv.getText().toString());
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
