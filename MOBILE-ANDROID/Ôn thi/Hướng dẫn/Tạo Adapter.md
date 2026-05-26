# Tạo Adapter

## Tạo item layout

## Tạo lớp Adapter
```
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    public Activity activity;
    public List<Sach> data;
    public LayoutInflater inflater;

    public MyAdapter() {}

    public MyAdapter(Activity activity, List<Sach> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Sach getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.sach_item, null);
        }

        Sach a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        TextView tacGia = v.findViewById(R.id.textViewTacGia);
        TextView gia = v.findViewById(R.id.textViewGia);
        TextView soLuong = v.findViewById(R.id.textViewSoLuong);
        TextView thanhTien = v.findViewById(R.id.textViewThanhTien);
        ConstraintLayout itemLayout = v.findViewById(R.id.item_layout);

        ten.setText(a.getTen());
        tacGia.setText(a.getTacGia());
        gia.setText(String.valueOf(a.getGia()));
        soLuong.setText(String.valueOf(a.getSoLuong()));
        thanhTien.setText(String.valueOf(a.tinhThanhTien()));
        itemLayout.setBackgroundColor(a.tinhThanhTien() > 1000000 ? Color.GREEN : Color.WHITE);

        return v;
    }

    public void SortData() {
        data.sort((a, b) -> a.getTen().compareTo(b.getTen()));

        this.notifyDataSetChanged();
    }

    public float TrungBinh() {
        float sum = 0;

        for (Sach a : data) {
            sum += a.tinhThanhTien();
        }

        return sum / getCount();
    }
}
```

## Thêm Adapter vào MainActivity
```
    MyAdapter myAdapter;
    void initAdapter() {
        myAdapter = new MyAdapter(this, myDb.getAll());

        listView.setAdapter(myAdapter);

        UpdateTrungBinh();
    }
```

## Tạo hàm cập nhật Trung bình
```
    public void UpdateTrungBinh() {
        trungBinhTv.setText(String.valueOf(myAdapter.TrungBinh()));
    }
```

