package com.example.mobile_onthi1_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BaiHatAdapter extends BaseAdapter {
    public Activity activity;
    public ArrayList<BaiHat> data;
    public ArrayList<BaiHat> backup;
    public LayoutInflater inflater;

    public BaiHatAdapter() {}

    public BaiHatAdapter(Activity activity, ArrayList<BaiHat> data) {
        this.activity = activity;
        this.data = new ArrayList<>(data);
        this.backup = new ArrayList<>(data);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.bai_hat_layout, null);
        }

        TextView tenbaihat = view.findViewById(R.id.textView_tenbaihat);
        tenbaihat.setText(data.get(position).ten);

        TextView tencasi = view.findViewById(R.id.textView_tencasi);
        tencasi.setText(data.get(position).caSi);

        TextView diemso = view.findViewById(R.id.textView_diemso);
        diemso.setText(String.valueOf(data.get(position).diemDanhGia));

        return view;
    }


}
