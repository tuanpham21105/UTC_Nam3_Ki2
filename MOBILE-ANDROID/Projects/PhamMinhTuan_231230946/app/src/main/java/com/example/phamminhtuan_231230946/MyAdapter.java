package com.example.phamminhtuan_231230946;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    public Activity activity;
    public List<BanBe> data;
    public LayoutInflater inflater;

    public MyAdapter() {}

    public MyAdapter(Activity activity, List<BanBe> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BanBe getItem(int position) {
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
            v = inflater.inflate(R.layout.banbe_item, null);
        }

        BanBe a = getItem(position);

        TextView ten = v.findViewById(R.id.textViewTen);
        TextView ngay = v.findViewById(R.id.textViewNgay);
        ImageView icon = v.findViewById(R.id.imageViewLoai);
        TextView danhGia = v.findViewById(R.id.textViewDanhGia);
        TextView lop = v.findViewById(R.id.textView123);
        ConstraintLayout itemLayout = v.findViewById(R.id.item_layout);
        ConstraintLayout innerLayer = v.findViewById(R.id.inner);

        ten.setText(a.getTen());
        ngay.setText(a.getNgaySinh().getDayOfMonth() + "/" + a.getNgaySinh().getMonthValue() + "/" + a.getNgaySinh().getYear());
        icon.setVisibility(a.isLoai() ? VISIBLE : INVISIBLE);
        danhGia.setText(String.valueOf(GlobalUtils.ceil(a.tinhDanhGia(), 2)));
        itemLayout.setBackgroundColor(a.tinhDanhGia() > 6 ? Color.GREEN : Color.TRANSPARENT);;
        innerLayer.setBackgroundColor(a.tinhDanhGia() > 6 ? Color.RED : Color.TRANSPARENT);
        lop.setText("Lớp " + a.getLop());

        return v;
    }

    public void SortData() {
        data.sort((a, b) -> b.getTen().compareTo(a.getTen()));

        this.notifyDataSetChanged();
    }

    public float TrungBinh() {
        float sum = 0;

        for (BanBe a : data) {
            sum += a.tinhDanhGia();
        }

        return sum / getCount();
    }
}
