package com.example.onthi_260201;

import java.time.LocalDateTime;

public class DonHang {
    private String ma;
    private String ten;
    private LocalDateTime ngayDat;
    private float gia;
    private boolean loai;

    public DonHang() {
    }

    public DonHang(String ma, String ten, LocalDateTime ngayDat, float gia, boolean loai) {
        this.ma = ma;
        this.ten = ten;
        this.ngayDat = ngayDat;
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

    public LocalDateTime getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(LocalDateTime ngayDat) {
        this.ngayDat = ngayDat;
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
        float phiVanChuyen = (GlobalUtils.A + 1) * gia < 1000000 ? 2f : 3f;
        return gia + phiVanChuyen + (loai ? 50f : 0f);
    }
}
