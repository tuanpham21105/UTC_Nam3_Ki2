package com.example.mobile_;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<User> data;

    private LayoutInflater inflater;
    private ArrayList<User> dataBackup;

    private FloatingActionButton addBtn;

    public Adapter(Activity activity, ArrayList<User> data) {
        this.activity = activity;
        this.data = data;
        dataBackup = new ArrayList<>(data);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Adapter() {}

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
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.item, null);
        }

        ImageView image = v.findViewById(R.id.imageView);

        TextView name = v.findViewById(R.id.editTextText);
        name.setText(data.get(position).getName());

        TextView phoneNumber = v.findViewById(R.id.editTextText2);
        phoneNumber.setText(data.get(position).getPhonenumber());

        ImageView image2 = v.findViewById(R.id.imageView2);
        ImageView image3 = v.findViewById(R.id.imageView3);

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel: " + data.get(position).getPhonenumber()));

                activity.startActivity(in);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("sms: " + data.get(position).getPhonenumber()));

                activity.startActivity(in);
            }
        });

        CheckBox checkBox = v.findViewById(R.id.checkBox);
        checkBox.setChecked(false);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(position).setCheck(!data.get(position).isCheck());
            }
        });

        return v;
    }

}
