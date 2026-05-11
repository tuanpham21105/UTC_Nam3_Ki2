package com.example.onthi_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BaiHatAdapter extends BaseAdapter {
    private Activity activity;
    public List<BaiHat> data;
    private LayoutInflater inflater;

    public BaiHatAdapter(Activity activity, List<BaiHat> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public BaiHatAdapter() {
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BaiHat getItem(int position) {
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
            v = inflater.inflate(R.layout.baihat_item, null);
        }

        BaiHat bh = getItem(position);

        TextView tenBaiHat = v.findViewById(R.id.textViewTenBaiHat);
        tenBaiHat.setText(bh.ten);

        TextView tenCaSi = v.findViewById(R.id.textViewTenCaSi);
        tenCaSi.setText(bh.tenCaSi);

        TextView diem = v.findViewById(R.id.textViewDiem);
        diem.setText(String.valueOf(bh.diem));

        return v;
    }
}
