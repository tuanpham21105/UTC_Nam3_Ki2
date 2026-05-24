package com.example.onthi_240403;

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
    public List<HangHoa> data;
    public LayoutInflater inflater;

    public MyAdapter() {}

    public MyAdapter(Activity activity, List<HangHoa> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HangHoa getItem(int position) {
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
            v = inflater.inflate(R.layout.hanghoa_item, null);
        }

        HangHoa a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        TextView gia = v.findViewById(R.id.textViewGia);
        TextView giamGia = v.findViewById(R.id.textViewGiamGia);
        TextView gia2 = v.findViewById(R.id.textViewGia2);
        ConstraintLayout itemLayout = v.findViewById(R.id.item_layout);

        ten.setText(a.getTen());
        gia.setText(String.valueOf(a.getGia()));
        giamGia.setText(a.isGiamGia() ? "Giảm giá còn" : "Không giảm giá");
        gia2.setText(String.valueOf(a.tinhGiaBan()));
        itemLayout.setBackgroundColor(a.isGiamGia() ? Color.GREEN : Color.WHITE);

        return v;
    }

    public void SortData() {
        data.sort((a, b) -> a.getTen().compareTo(b.getTen()));

        this.notifyDataSetChanged();
    }
}
