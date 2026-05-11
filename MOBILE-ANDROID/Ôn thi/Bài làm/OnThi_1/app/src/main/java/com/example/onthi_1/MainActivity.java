package com.example.onthi_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText searchEt;

    ListView sinhVienLv;

    SinhVienAdapter adapter;

    MyDb db;

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

        searchEt = findViewById(R.id.editTextSearch);
        sinhVienLv = findViewById(R.id.listViewSinhVien);

        db = new MyDb(this, "SinhVienDb", null, 1);

        db.add(new SinhVien(0, "A", 10, 9, 8));
        db.add(new SinhVien(1, "B", 7, 6,5));
        db.add(new SinhVien(2, "C", 5, 4, 3));

        adapter = new SinhVienAdapter(this, db.getAll());

        sinhVienLv.setAdapter(adapter);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyword = searchEt.getText().toString().toLowerCase().trim();

                adapter.data.clear();

                if (keyword.isBlank()) {
                    adapter.data.addAll(db.getAll());
                }
                else {
                    for (SinhVien sv : db.getAll()) {
                        if (sv.ten.toLowerCase().trim().contains(keyword)) {
                            adapter.data.add(sv);
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        registerForContextMenu(sinhVienLv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (info == null) return super.onContextItemSelected(item);

        int position = info.position;

        SinhVien v = adapter.data.get(position);

        if (item.getItemId() == R.id.menuItemDelete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Delete");
            builder.setMessage("Bạn có muốn xóa tất cả các sinh viên có tổng điểm nhỏ hơn 25 ko?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteSinhVienUnder25();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // User clicked No
                    dialog.dismiss();
                }
            });

            builder.show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    void deleteSinhVienUnder25() {
        for (SinhVien sv : db.getAll()) {
            if (sv.TongDiem() < 25) {
                db.delete(sv.ma);
            }
        }

        adapter.data.clear();

        adapter.data.addAll(db.getAll());

        adapter.notifyDataSetChanged();
    }
}