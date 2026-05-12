package com.example.onthi_4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserAdapter extends BaseAdapter {
    public Activity activity;
    public List<User> data;
    public LayoutInflater inflater;
    public Set<Integer> checks = new HashSet<>();

    public UserAdapter() {
    }

    public UserAdapter(Activity activity, List<User> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public User getItem(int position) {
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
            v = inflater.inflate(R.layout.user_item, null);
        }

        User a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        ten.setText(a.ten);

        TextView sdt = v.findViewById(R.id.textViewSoDienThoai);
        sdt.setText(a.soDienThoai);

        CheckBox checkBox = v.findViewById(R.id.checkBox);
        checkBox.setChecked(checks.contains(a.ma));

        ImageView call = v.findViewById(R.id.imageViewCall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + a.soDienThoai));

                activity.startActivity(in);
            }
        });

        ImageView message = v.findViewById(R.id.imageViewMessage);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: " + a.soDienThoai));

                activity.startActivity(in);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checks.add(a.ma);
                } else {
                    checks.remove(a.ma);
                }
            }
        });

        return v;
    }
}
