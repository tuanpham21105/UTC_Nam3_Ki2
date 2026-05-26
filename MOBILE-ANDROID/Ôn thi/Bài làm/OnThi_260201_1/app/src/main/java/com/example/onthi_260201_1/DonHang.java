package com.example.onthi_260201_1;

import java.time.LocalDate;

public class DonHang {
    private String ma;
    private String ten;
    private LocalDate ngay;
    private float gia;
    private boolean loai;

    public DonHang() {
    }

    public DonHang(String ma, String ten, LocalDate ngay, float gia, boolean loai) {
        this.ma = ma;
        this.ten = ten;
        this.ngay = ngay;
        this.gia = gia;
        this.loai = loai;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public boolean isLoai() {
        return loai;
    }

    public void setLoai(boolean loai) {
        this.loai = loai;
    }

    public float tinhThanhTien() {
        float phiVanChuyen = (GlobalUtils.A + 1f) * gia < 1000000 ? 2f : 3f;
        return gia + phiVanChuyen + (loai ? 50f : 0f);
    }
}
