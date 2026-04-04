package com.example.mobile_lab_4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private Activity activity;
    public ArrayList<User> data;
    private LayoutInflater inflater;

    public UserAdapter() {
    }

    public UserAdapter(Activity activity, ArrayList<User> data) {
        this.activity = activity;
        this.data = new ArrayList<>(data);
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
        return data.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.user_item, null);
        }

        User user = data.get(position);

        TextView name = v.findViewById(R.id.name_textview);
        name.setText(user.name);

        TextView phone = v.findViewById(R.id.phone_textview);
        phone.setText(user.phone);

        ImageView call = v.findViewById(R.id.call_imageview);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel: " + data.get(position).phone));

                activity.startActivity(in);
            }
        });

        ImageView mes = v.findViewById(R.id.message_imageview);
        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("sms: " + data.get(position).phone));

                activity.startActivity(in);
            }
        });

        return v;
    }
}
