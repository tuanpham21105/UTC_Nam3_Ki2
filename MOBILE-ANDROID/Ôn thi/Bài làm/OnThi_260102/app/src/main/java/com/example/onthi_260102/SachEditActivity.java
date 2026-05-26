package com.example.onthi_260102;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SachEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sach_edit_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sachEditMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        setupView();
    }

    TextView maTv;
    EditText loaiEt;
    EditText tenEt;
    EditText tacGiaEt;
    EditText giaEt;
    EditText soLuongEt;
    Button suaBtn;
    Button quayVeBtn;
    void initView() {
        maTv = findViewById(R.id.textViewMa);
        loaiEt = findViewById(R.id.editTextLoai);
        tenEt = findViewById(R.id.editTextTen);
        tacGiaEt = findViewById(R.id.editTextTacGia);
        giaEt = findViewById(R.id.editTextGia);
        soLuongEt = findViewById(R.id.editTextSoLuong);
        suaBtn = findViewById(R.id.buttonSua);
        quayVeBtn = findViewById(R.id.buttonQuayVe);

        suaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endActivity(true);
            }
        });

        quayVeBtn.setOnClickListener(new View.OnClickListener() {
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
            position = bundle.getInt("index");
            maTv.setText(bundle.getString("ma"));
            tenEt.setText(bundle.getString("ten"));
            tacGiaEt.setText(bundle.getString("tacGia" ));
            giaEt.setText(String.valueOf(bundle.getFloat("gia")));
            soLuongEt.setText(String.valueOf(bundle.getInt("soLuong")));
        }
    }

    void endActivity(boolean state) {
        if (state) {
            Intent intent = new Intent(this, SachEditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("index", position);
            bundle.putString("ma", maTv.getText().toString());
            bundle.putString("ten", tenEt.getText().toString());
            bundle.putString("tacGia", tacGiaEt.getText().toString());
            bundle.putFloat("gia", Float.parseFloat(giaEt.getText().toString()));
            bundle.putInt("soLuong", Integer.parseInt(soLuongEt.getText().toString()));
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
