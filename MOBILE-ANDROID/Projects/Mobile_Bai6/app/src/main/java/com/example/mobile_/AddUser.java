package com.example.mobile_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity {

    EditText idET;
    EditText nameET;
    EditText phoneET;

    Button okButton;
    Button cancelButton;
    boolean isEdited;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        idET = findViewById(R.id.edit_text_id);
        nameET = findViewById(R.id.edit_text_name);
        phoneET = findViewById(R.id.edit_text_phone);
        okButton = findViewById(R.id.ok_button);
        cancelButton = findViewById(R.id.cancel_button);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            isEdited = true;

            int id = bundle.getInt("Id");
            String name = bundle.getString("Name");
            String phone = bundle.getString("Phone");
            idET.setText(String.valueOf(id));
            nameET.setText(name);
            phoneET.setText(phone);
            okButton.setText("Edit");

        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Tạo bundle là đối tượng để chứa dữ liệu
                Bundle bundle = new Bundle();
                // Put trong bundle để đẩy dữ liệu vào bundle, sau đó putExtras bundle đấy thêm nó vào intent
                bundle.putInt("Id", Integer.parseInt(idET.getText().toString()));
                bundle.putString("Name", nameET.getText().toString());
                bundle.putString("Phone", phoneET.getText().toString());
                intent.putExtras(bundle);
                setResult(isEdited?201:200, intent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
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
