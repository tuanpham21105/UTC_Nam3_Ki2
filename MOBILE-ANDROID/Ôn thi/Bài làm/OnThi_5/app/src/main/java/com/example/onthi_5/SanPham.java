package com.example.onthi_5;

public class SanPham {
    public int ma;
    public String ten;
    public String gia;
    public String chiTiet;
    public boolean giamGia;

    public boolean isGiamGia() {
        return giamGia;
    }

    public SanPham(int ma, String ten, String gia, String chiTiet, boolean giamGia) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.chiTiet = chiTiet;
        this.giamGia = giamGia;
    }
}
