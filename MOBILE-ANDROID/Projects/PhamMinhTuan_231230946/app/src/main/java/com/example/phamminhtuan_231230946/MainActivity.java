package com.example.phamminhtuan_231230946;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    MyDb myDb;
    void initDb() {
        myDb = new MyDb(this, "SachDb", null, 9);

        myDb.add(new BanBe(generateId(), "Nguyễn Hải An", 100, LocalDate.of(2005, 1, 15), 10, true, "CNTT 3"));
        myDb.add(new BanBe(generateId(), "Trần Mai Nhất", 200, LocalDate.of(2005, 1, 20), 8, true, "CNTT 2"));
        myDb.add(new BanBe(generateId(), "Nguyễn Hà Anh", 10, LocalDate.of(2005, 1, 28), 20, false, "CNTT 1"));
        myDb.add(new BanBe(generateId(), "Ma Công Nam", 0, LocalDate.of(2005, 1, 30), 1, false, "CNTT 4"));
    }

    String generateId() {
        int size = myDb.getSize();
        return "DH" + String.valueOf((GlobalUtils.C * size) + GlobalUtils.B);
    }

    TextView trungBinhTv;
    ListView listView;
    Button sortBtn;
    FloatingActionButton addBtn;
    void initView() {
        trungBinhTv = findViewById(R.id.textViewTrungBinh);
        listView = findViewById(R.id.listView);
        sortBtn = findViewById(R.id.buttonSort);
        addBtn = findViewById(R.id.floatingActionButton);

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.SortData();
            }
        });

        registerForContextMenu(listView);
    }

    MyAdapter myAdapter;
    void initAdapter() {
        myAdapter = new MyAdapter(this, myDb.getAll());

        listView.setAdapter(myAdapter);

        UpdateTrungBinh();
    }

    public void UpdateTrungBinh() {
        trungBinhTv.setText(String.valueOf(GlobalUtils.ceil(myAdapter.TrungBinh(), 2)));
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

        BanBe a = myAdapter.getItem(position);

        if (item.getItemId() == R.id.menuItemSua) {

            return true;
        }
        else if (item.getItemId() == R.id.menuItemXoa) {
            OpenFloatingDialog(a, position);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void OpenFloatingDialog(BanBe a, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete");
        builder.setMessage(
                "Bạn có muốn xóa bạn bè có điểm đánh giá < " + String.valueOf(GlobalUtils.ceil(a.tinhDanhGia(), 2))
        );

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Delete(a.tinhDanhGia());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No
                dialog.dismiss();
            }
        });

        builder.show();
    }

    void Delete(float danhGia) {
        List<Integer> indexs = new ArrayList<>();

        for (int i = myAdapter.data.size() - 1; i >= 0; i--) {
            if (danhGia > myAdapter.data.get(i).tinhDanhGia()) {
                myDb.delete(myAdapter.data.get(i).getMa());
                myAdapter.data.remove(i);
            }
        }

        myAdapter.notifyDataSetChanged();

        UpdateTrungBinh();
    }
}