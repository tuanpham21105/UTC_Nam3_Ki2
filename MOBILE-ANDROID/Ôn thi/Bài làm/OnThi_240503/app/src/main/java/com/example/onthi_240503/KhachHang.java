package com.example.onthi_240503;

import java.time.LocalDateTime;

public class KhachHang {
    private String ma;
    private String hoTen;
    private String soDienThoai;
    private LocalDateTime ngayDanhGia;
    private float binhChon;

    public KhachHang() {
    }

    public KhachHang(String ma, String hoTen, String soDienThoai, LocalDateTime ngayDanhGia, float binhChon) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.ngayDanhGia = ngayDanhGia;
        ClampBinhChon();
        this.binhChon = binhChon;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setNgayDanhGia(LocalDateTime ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    public void setBinhChon(float binhChon) {
        ClampBinhChon();
        this.binhChon = binhChon;
    }

    public String getMa() {
        return ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public LocalDateTime getNgayDanhGia() {
        return ngayDanhGia;
    }

    public float getBinhChon() {
        return binhChon;
    }

    public float tinhDiem() {
        return GlobalUtils.ceil((float)(binhChon + ((5f - binhChon) * ((Float.parseFloat(ma.substring(2)) + 1f) / 100f))), 1);
    }

    public void ClampBinhChon() {
        if (binhChon > 5f) {
            binhChon = 5f;
        }

        if (binhChon < 0f) {
            binhChon = 0f;
        }

        binhChon = GlobalUtils.ceil(binhChon, 1);
    }
}
