package com.example.onthi_260102;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

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

        initView();

        initDb();

        initAdapter();
    }

    public void UpdateTrungBinh() {
        trungBinhTv.setText(String.valueOf(myAdapter.TrungBinh()));
    }

    TextView trungBinhTv;
    ListView listView;
    Button sortBtn;
    void initView() {
        trungBinhTv = findViewById(R.id.textViewTrungBinh);
        listView = findViewById(R.id.listView);
        sortBtn = findViewById(R.id.buttonSapXep);

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.SortData();
            }
        });

        registerForContextMenu(listView);
    }

    MyDb myDb;
    void initDb() {
        myDb = new MyDb(this, "SachDb", null, 1);

        myDb.add(new Sach(generateId(), "DEF", "D E F", 32410000, 10));
        myDb.add(new Sach(generateId(), "MNO", "M N O", 1040430000, 34100));
        myDb.add(new Sach(generateId(), "HIJ", "HIJ", 1003247582, 153300));
        myDb.add(new Sach(generateId(), "ABC", "A B C", 1000000, 100));
    }

    MyAdapter myAdapter;
    void initAdapter() {
        myAdapter = new MyAdapter(this, myDb.getAll());

        listView.setAdapter(myAdapter);

        UpdateTrungBinh();
    }

    String generateId() {
        int size = myDb.getSize();
        return "BK" + String.valueOf((GlobalUtils.B * size) + GlobalUtils.A);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (info == null) return super.onContextItemSelected(item);

        int position = info.position;

        Sach a = myAdapter.getItem(position);

        if (item.getItemId() == R.id.menuItemSua) {
            RedirectToActivity(position, a);
            return true;
        }
        else if (item.getItemId() == R.id.menuItemXoa) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void RedirectToActivity(int position, Sach a) {
        Intent intent = new Intent(this, SachEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("index", position);
        bundle.putString("ma", a.getMa());
        bundle.putString("ten", a.getTen());
        bundle.putString("tacGia", a.getTacGia());
        bundle.putFloat("gia", a.getGia());
        bundle.putInt("soLuong", a.getSoLuong());
        intent.putExtras(bundle);
        startActivityForResult(intent, 200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 400) return;

        Bundle bundle = data.getExtras();

        Sach a = new Sach(bundle.getString("ma"), bundle.getString("ten"), bundle.getString("tacGia"), bundle.getFloat("gia"), bundle.getInt("soLuong"));

        int position = bundle.getInt("index");

        if (requestCode == 200 && resultCode == 200) {
            updateItem(position, a);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    void addNewItem(Sach a) {
        myDb.add(a);

        myAdapter.data = myDb.getAll();

        myAdapter.notifyDataSetChanged();
    }

    void updateItem(int position, Sach a) {
        myDb.update(a);

        myAdapter.data.set(position, a);

        myAdapter.notifyDataSetChanged();
    }
}