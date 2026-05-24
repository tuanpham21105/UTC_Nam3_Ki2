package com.example.onthi_240303;

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
    public List<BaiHat> data;
    public LayoutInflater inflater;

    public MyAdapter() {}

    public MyAdapter(Activity activity, List<BaiHat> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.baihat_item, null);
        }

        BaiHat a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        TextView diem = v.findViewById(R.id.textViewDiem);
        TextView caSy = v.findViewById(R.id.textViewCaSy);
        TextView like = v.findViewById(R.id.textViewLike);
        TextView share = v.findViewById(R.id.textViewShare);
        ConstraintLayout layout = v.findViewById(R.id.item_layout);

        ten.setText(a.getTen());
        diem.setText(String.valueOf(a.tinhDiem()));
        caSy.setText(a.getCaSy());
        like.setText(String.valueOf(a.getLike()));
        share.setText(String.valueOf(a.getShare()));
        layout.setBackgroundColor(a.tinhDiem() > 160 ? Color.GREEN : Color.WHITE);

        return v;
    }

    public void SortData() {
        data.sort((a, b) -> a.getTen().compareTo(b.getTen()));

        this.notifyDataSetChanged();
    }
}
