package com.example.onthi_3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class VeTauAdapter extends BaseAdapter {
    private Activity activity;
    public List<VeTau> data;
    private LayoutInflater inflater;

    public VeTauAdapter(Activity activity, List<VeTau> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public VeTauAdapter() {
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public VeTau getItem(int position) {
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
            v = inflater.inflate(R.layout.vetau_item, null);
        }

        VeTau a = getItem(position);

        TextView gaDi = v.findViewById(R.id.textViewGaDi);
        gaDi.setText(a.gaDi);

        TextView gaDen = v.findViewById(R.id.textViewGaDen);
        gaDen.setText(a.gaDen);

        TextView loai = v.findViewById(R.id.textViewLoai);
        loai.setText(a.loai ? "Khu hoi" : "Mot chieu");

        TextView gia = v.findViewById(R.id.textViewGia);
        gia.setText(String.valueOf(a.gia));

        return v;
    }
}
