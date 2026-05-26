package com.example.onthi_260201;

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
        TextView ngayDat = v.findViewById(R.id.textViewNgayDat);
        TextView gia = v.findViewById(R.id.textViewGia);
        TextView thanhTien = v.findViewById(R.id.textViewThanhTien);
        ConstraintLayout itemLayout = v.findViewById(R.id.item_layout);

        ten.setText(a.getTen());
        loai.setText(a.isLoai() ? "Nhanh" : "Thường");
        gia.setText("Giá: " + String.valueOf(a.getGia()));
        ngayDat.setText(String.valueOf(a.getNgayDat().getDayOfMonth() + "/" + a.getNgayDat().getMonthValue() + "/" + a.getNgayDat().getYear()));
        thanhTien.setText("Thành tiền: " + String.valueOf(a.tinhThanhTien()));
        itemLayout.setBackgroundColor(a.isLoai() ? Color.GREEN : Color.BLUE);

        return v;
    }

    public void SortData() {
        data.sort((a, b) -> Float.compare(b.tinhThanhTien(), a.tinhThanhTien()));

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
