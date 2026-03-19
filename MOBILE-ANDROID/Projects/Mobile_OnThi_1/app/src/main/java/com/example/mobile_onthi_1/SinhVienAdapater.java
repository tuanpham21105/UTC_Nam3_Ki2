package com.example.mobile_onthi_1;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class SinhVienAdapater extends BaseAdapter {
    public Activity activity;
    public ArrayList<SinhVien> data;
    public ArrayList<SinhVien> backup;
    public LayoutInflater inflater;

    public SinhVienAdapater() {}
    public SinhVienAdapater(Activity activity, ArrayList<SinhVien> l) {
        this.activity = activity;
        this.data = new ArrayList<>(l);
        this.backup = l;
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
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.sinh_vien_view, null);
        }

        TextView name = view.findViewById(R.id.name);
        name.setText(data.get(position).ten);

        TextView score = view.findViewById(R.id.score);
        score.setText(String.valueOf(data.get(position).Sum()));

        return view;
    }

    public void searchByName(String keyword) {
        String k = keyword.toLowerCase().trim();

        Log.d("Adapter", "search by name keyword: " + keyword);

        data.clear();

        if (k.isEmpty()) {
            // Restore all data from backup
            data.addAll(backup);
        }
        else {
            // Filter and add matching items
            for (SinhVien s : backup) {
                if (s.ten.toLowerCase().contains(k)) {
                    Log.d("Adapter", "search by name item: " + s.ten);
                    data.add(s);
                }
            }
        }

        Log.d("Adapter", "Results found: " + data.size());
        notifyDataSetChanged();
    }

    public void deleteSinhVienUnder25() {
        data.clear();

        Iterator<SinhVien> iterator = backup.iterator();
        while (iterator.hasNext()) {
            SinhVien sv = iterator.next();
            if (sv.Sum() < 25) {
                iterator.remove(); // Safe removal
            }
        }

        data = (ArrayList<SinhVien>) backup.clone();

        notifyDataSetChanged();
    }
}
