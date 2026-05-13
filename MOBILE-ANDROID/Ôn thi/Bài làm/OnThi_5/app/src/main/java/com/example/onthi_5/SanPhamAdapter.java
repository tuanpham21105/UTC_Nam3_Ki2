package com.example.onthi_5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    public Activity activity;
    public List<SanPham> data;
    public LayoutInflater inflater;

    public SanPhamAdapter() {
    }

    public SanPhamAdapter(Activity activity, List<SanPham> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public SanPham getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).ma;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.sanpham_item, null);
        }

        SanPham a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        ten.setText(a.ten);

        TextView gia = v.findViewById(R.id.textViewGia);
        gia.setText(a.gia);

        if (a.isGiamGia()) {
            int trueGia = Integer.parseInt(a.gia);
            trueGia = (int)((float)trueGia * 0.9f);

            TextView chiTiet = v.findViewById(R.id.textViewChiTiet);
            chiTiet.setText(String.format("Giam gia con %s", String.valueOf(trueGia)));
        }

        return v;
    }
}
