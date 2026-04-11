package com.example.mobile_onthi_1_5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SanPhamAdapter extends BaseAdapter {
    private Activity activity;
    public ArrayList<SanPham> data;
    private LayoutInflater inflater;


    public SanPhamAdapter(Activity activity, ArrayList<SanPham> data) {
        this.activity = activity;
        this.data = new ArrayList<>(data);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public SanPhamAdapter() {
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

        TextView ten = v.findViewById(R.id.textView_ten);
        ten.setText(getItem(position).ten);

        TextView giatien = v.findViewById(R.id.textView_giatien);
        giatien.setText(getItem(position).giatien);

        TextView chitiet = v.findViewById(R.id.textView_chitiet);
        chitiet.setText(getItem(position).giamgia ? getItem(position).chitiet : "");

        return v;
    }
}
