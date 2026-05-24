package com.example.onthi_240303;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MyDb myDb;
    MyAdapter myAdapter;
    TextView trungBinhTv;
    ListView listView;
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

        myAdapter.SortData();
    }

    void initView() {
        trungBinhTv = findViewById(R.id.textViewTrungBinh);
        listView = findViewById(R.id.listView);

        registerForContextMenu(listView);
    }

    void initDb() {
        myDb = new MyDb(this, "BaiHat2", null, 1);

        myDb.add(new BaiHat(generateId(), "DABC", "A B C", 1, 1));
        myDb.add(new BaiHat(generateId(), "ADEF", "D E F", 50, 50));
        myDb.add(new BaiHat(generateId(), "BGHI", "G H I", 1100, 50));
    }

    void initAdapter() {
        myAdapter = new MyAdapter(this, myDb.getAll());

        listView.setAdapter(myAdapter);

        UpdateTrungBinh();
    }

    int generateId() {
        return (GlobalUtils.B * myDb.getSize()) + GlobalUtils.A;
    }

    void UpdateTrungBinh() {
        float sum = 0;

        for (BaiHat a : myAdapter.data) {
            sum += a.tinhDiem();
        }

        trungBinhTv.setText(String.valueOf(sum / myAdapter.getCount()));
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

        BaiHat a = myAdapter.getItem(position);

        if (item.getItemId() == R.id.menuItemXoa) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm");
            builder.setMessage(String.format(
                    "Bạn có muốn xóa Bài hát %s?\nCa sỹ: %s\nĐiểm: %d", a.getTen(), a.getCaSy(), a.tinhDiem())
            );

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DeleteItem(position);
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

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void DeleteItem(int position) {
        BaiHat a = myAdapter.getItem(position);

        myDb.delete(a.getId());

        myAdapter.data.remove(position);

        myAdapter.notifyDataSetChanged();

        UpdateTrungBinh();
    }
}