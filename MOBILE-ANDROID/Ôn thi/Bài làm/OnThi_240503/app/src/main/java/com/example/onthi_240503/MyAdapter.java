package com.example.onthi_240503;

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
    public List<KhachHang> data;
    public LayoutInflater inflater;

    public MyAdapter() {}

    public MyAdapter(Activity activity, List<KhachHang> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public KhachHang getItem(int position) {
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
            v = inflater.inflate(R.layout.khachhang_item, null);
        }

        KhachHang a = getItem(position);

        TextView hoten = v.findViewById(R.id.textViewHoTen);
        hoten.setText(a.getHoTen());

        TextView ngay = v.findViewById(R.id.textViewNgay);
        ngay.setText(a.getNgayDanhGia().getDayOfMonth() + "/" + a.getNgayDanhGia().getMonthValue() + "/" + a.getNgayDanhGia().getYear());

        TextView sdt = v.findViewById(R.id.textViewSoDienThoai);
        sdt.setText(a.getSoDienThoai());

        TextView diem = v.findViewById(R.id.textViewDiem);
        diem.setText(String.valueOf(a.tinhDiem()));

        ConstraintLayout layout = v.findViewById(R.id.item_layout);

        if (a.tinhDiem() < 4) {
            layout.setBackgroundColor(Color.GREEN);
        }
        else {

            layout.setBackgroundColor(Color.WHITE);
        }

        return v;
    }

    public void SortData() {
        data.sort((a, b) -> Float.compare(a.tinhDiem(), b.tinhDiem()));

        this.notifyDataSetChanged();
    }
}
