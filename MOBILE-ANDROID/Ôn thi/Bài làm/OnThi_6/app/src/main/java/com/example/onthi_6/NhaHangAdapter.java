package com.example.onthi_6;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NhaHangAdapter extends BaseAdapter {
    public Activity activity;
    public List<NhaHang> data;
    public LayoutInflater inflater;

    public NhaHangAdapter() {}

    public NhaHangAdapter(Activity activity, List<NhaHang> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NhaHang getItem(int position) {
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
            v = inflater.inflate(R.layout.nhahang_item, null);
        }

        NhaHang a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        ten.setText(a.ten);

        TextView diem = v.findViewById(R.id.textViewDiem);
        diem.setText(String.valueOf(a.danhGia));

        TextView diaChi = v.findViewById(R.id.textViewDiaChi);
        diaChi.setText(a.diaChi);

        return v;
    }
}
