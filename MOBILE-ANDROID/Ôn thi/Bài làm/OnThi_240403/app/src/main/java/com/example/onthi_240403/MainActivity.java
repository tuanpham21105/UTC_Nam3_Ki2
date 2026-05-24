package com.example.onthi_240403;

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

public class MainActivity extends AppCompatActivity {
    MyDb myDb;
    MyAdapter myAdapter;
    TextView giaTrungBinhTv;
    Button sapXepBtn;
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
    }

    void initView() {
        giaTrungBinhTv = findViewById(R.id.textViewGiaTrungBinh);
        sapXepBtn = findViewById(R.id.buttonSapXep);
        listView = findViewById(R.id.listView);

        sapXepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.SortData();
            }
        });

        registerForContextMenu(listView);
    }

    void initDb() {
        myDb = new MyDb(this, "HangHoaDb", null, 1);

        myDb.add(new HangHoa(generateId(), "FABC", 10000, true));
        myDb.add(new HangHoa(generateId(), "DABCD", 210000, true));
        myDb.add(new HangHoa(generateId(), "BABCE", 3100000, false));
        myDb.add(new HangHoa(generateId(), "CABCF", 1460000, true));
    }

    void initAdapter() {
        myAdapter = new MyAdapter(this, myDb.getAll());

        listView.setAdapter(myAdapter);

        UpdateTrungBinh();
    }

    int generateId() {
        return (GlobalUtils.B * myDb.getSize()) + GlobalUtils.A;
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

        HangHoa a = myAdapter.getItem(position);

        if (item.getItemId() == R.id.menuItemXoa) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm");
            builder.setMessage(String.format(
                    "Bạn có muốn xóa %s?\nGiá: %d\nGiảm còn: %d", a.getTen(), a.getGia(), a.tinhGiaBan())
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
        HangHoa a = myAdapter.getItem(position);

        myDb.delete(a.getMa());

        myAdapter.data.remove(position);

        myAdapter.notifyDataSetChanged();

        UpdateTrungBinh();
    }

    void UpdateTrungBinh() {
        float sum = 0;

        for (HangHoa a : myAdapter.data) {
            sum += a.tinhGiaBan();
        }

        giaTrungBinhTv.setText(String.valueOf(sum / myAdapter.getCount()));
    }
}