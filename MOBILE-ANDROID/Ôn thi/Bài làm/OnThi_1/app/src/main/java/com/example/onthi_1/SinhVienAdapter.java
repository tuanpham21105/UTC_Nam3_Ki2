package com.example.onthi_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {
    private Activity activity;
    public ArrayList<SinhVien> data;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public SinhVien getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).ma;
    }

    public SinhVienAdapter(Activity activity, ArrayList<SinhVien> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public SinhVienAdapter() {}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.sinhvien_item, null);
        }

        SinhVien sv = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        ten.setText(sv.ten);

        TextView tongDiem = v.findViewById(R.id.textViewTongDiem);
        tongDiem.setText(String.valueOf(sv.TongDiem()));

        return v;
    }
}
