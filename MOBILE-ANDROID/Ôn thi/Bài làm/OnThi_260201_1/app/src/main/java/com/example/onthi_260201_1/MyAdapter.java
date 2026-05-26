package com.example.onthi_260201_1;

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

import java.time.LocalDate;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    public Activity activity;
    public List<DonHang> data;
    public LayoutInflater inflater;

    public MyAdapter() {}

    public MyAdapter(Activity activity, List<DonHang> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public DonHang getItem(int position) {
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
            v = inflater.inflate(R.layout.donhang_item, null);
        }

        DonHang a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        TextView loai = v.findViewById(R.id.textViewLoai);
        TextView ngay = v.findViewById(R.id.textViewNgay);
        TextView gia = v.findViewById(R.id.textViewGia);
        TextView thanhTien = v.findViewById(R.id.textViewThanhTien);
        ConstraintLayout itemLayout = v.findViewById(R.id.item_layout);

        ten.setText(a.getTen());
        loai.setText(a.isLoai() ? "Nhanh" : "Thường");
        LocalDate date = a.getNgay();
        ngay.setText(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear());
        gia.setText("Giá: " + String.valueOf(a.getGia()));
        thanhTien.setText("Thành tiền: " + String.valueOf(a.tinhThanhTien()));
        itemLayout.setBackgroundColor(a.tinhThanhTien() > 1000000 ? Color.LTGRAY : Color.DKGRAY);

        return v;
    }

    public void SortData() {
        data.sort((a, b) -> b.getTen().compareTo(a.getTen()));

        this.notifyDataSetChanged();
    }

    public float TrungBinh() {
        float sum = 0;

        for (DonHang a : data) {
            sum += a.tinhThanhTien();
        }

        return sum / getCount();
    }
}