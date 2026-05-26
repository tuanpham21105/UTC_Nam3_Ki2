# Tạo Activity mới

## Chuyển hướng sang Activity khác
```
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
```

## Xử lý khi Activity trả về kết quả
```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 400) return;

        Bundle bundle = data.getExtras();

        Sach a = new Sach(bundle.getString("ma"), bundle.getString("ten"), bundle.getString("tacGia"), bundle.getFloat("gia"), bundle.getInt("soLuong"));

        int position = bundle.getInt("index");

        if (requestCode == 200 && resultCode == 200) {
            
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
```

## Hàm thêm dữ liệu vào Db và Adapter
```
    void addNewItem(Sach a) { 
        myDb.add(a);
        
        myAdapter.data = myDb.getAll();
        
        myAdapter.notifyDataSetChanged();
    }
```

## Hàm cập nhật dữ liệu vào Db và Adapter
```
    void updateItem(int position, Sach a) {
        myDb.update(a);

        myAdapter.data.set(position, a);

        myAdapter.notifyDataSetChanged();
    }
```

## Tạo layout cho Activity mới

## Tạo lớp Activity mới
```
import android.os.Bundle;
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
    }

    TextView maTv;
    EditText loaiEt;
    EditText tenEt;
    EditText tacGiaEt;
    EditText giaEt;
    EditText soLuongEt;
    void initView() {
        maTv = findViewById(R.id.textViewMa);
        loaiEt = findViewById(R.id.editTextLoai);
        tenEt = findViewById(R.id.editTextTen);
        tacGiaEt = findViewById(R.id.editTextTacGia);
        giaEt = findViewById(R.id.editTextGia);
        soLuongEt = findViewById(R.id.editTextSoLuong);
    }
}
```

## Lấy dữ liệu được truyền
```
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
```

## Kết thúc Activity
```
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
```



## Thêm Activity mới vào AndroidManifest
```
        <activity android:name=".SachEditActivity"/>
```
