package com.example.onthi_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VeTauEditActivity extends AppCompatActivity {
    EditText gaDiEt;
    EditText gaDenEt;
    EditText giaEt;
    RadioButton khuHoiRb;
    RadioButton motChieuRb;
    Button suaBtn;
    Button quayVeBtn;

    int id;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vetau_edit_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.veTauEditMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gaDiEt = findViewById(R.id.editTextGaDi);
        gaDenEt = findViewById(R.id.editTextGaDen);
        giaEt = findViewById(R.id.editTextGia);
        khuHoiRb = findViewById(R.id.radioButtonKhuHoi);
        motChieuRb = findViewById(R.id.radioButtonMotChieu);
        suaBtn = findViewById(R.id.buttonSua);
        quayVeBtn = findViewById(R.id.buttonQuayVe);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            id = bundle.getInt("ma");
            index = bundle.getInt("index");
            gaDiEt.setText(bundle.getString("gaDi"));
            gaDenEt.setText(bundle.getString("gaDen"));
            giaEt.setText(String.valueOf(bundle.getFloat("gia")));
            if (bundle.getBoolean("loai")) {
                khuHoiRb.setChecked(true);
            } else {
                motChieuRb.setChecked(true);
            }
        }

        suaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putInt("ma", id);
                bundle.putInt("index", index);
                bundle.putString("gaDi", gaDiEt.getText().toString());
                bundle.putString("gaDen", gaDenEt.getText().toString());
                bundle.putFloat("gia", Float.parseFloat(giaEt.getText().toString()));
                bundle.putBoolean("loai", khuHoiRb.isChecked());
                intent.putExtras(bundle);

                setResult(200, intent);
                finish();
            }
        });

        quayVeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                setResult(400, intent);
                finish();
            }
        });
    }
}
