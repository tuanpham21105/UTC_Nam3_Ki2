package com.example.mobile_onthi_1_3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class VeTauAdapter extends BaseAdapter {
    private Activity activity;
    public ArrayList<VeTau> data;
    public ArrayList<VeTau> backup;
    private LayoutInflater inflater;

    public VeTauAdapter() {}


    public VeTauAdapter(Activity activity, ArrayList<VeTau> data) {
        this.activity = activity;
        this.data = data;
        backup = new ArrayList<>(data);
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
        return data.get(position).ma;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.item, null);
        }

        TextView gaDiTv = v.findViewById(R.id.textView);
        gaDiTv.setText(data.get(position).gaDi);

        TextView gaDenTv = v.findViewById(R.id.textView4);
        gaDenTv.setText(data.get(position).gaDen);

        TextView giaTv = v.findViewById(R.id.textView5);
        giaTv.setText(String.valueOf(data.get(position).donGia));

        TextView loaiTv = v.findViewById(R.id.textView6);
        loaiTv.setText(data.get(position).loai ? "Khứ Hồi" : "Một Chiều");

        return v;
    }
}
