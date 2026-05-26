package com.example.phamminhtuan_231230946;

import java.time.LocalDate;

public class BanBe {
    private String ma;
    private String ten;
    private float yeuThich;
    private LocalDate ngaySinh;
    private float danhGia;
    private static int trongSo = GlobalUtils.A % 3;
    private boolean loai;
    private String lop;

    public BanBe() {
    }

    public BanBe(String ma, String ten, float yeuThich, LocalDate ngaySinh, float danhGia, boolean loai, String lop) {
        this.ma = ma;
        this.ten = ten;
        this.yeuThich = yeuThich;
        this.ngaySinh = ngaySinh;
        this.danhGia = danhGia;
        this.loai = loai;
        this.lop = lop;
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

    public float getYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(float yeuThich) {
        this.yeuThich = yeuThich;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }

    public static int getTrongSo() {
        return trongSo;
    }

    public static void setTrongSo(int trongSo) {
        BanBe.trongSo = trongSo;
    }

    public boolean isLoai() {
        return loai;
    }

    public void setLoai(boolean loai) {
        this.loai = loai;
    }

    public float tinhDanhGia() {
        danhGia = (yeuThich + ((float)trongSo * (yeuThich < 5 ? 2f : 3f))) / (float)GlobalUtils.B;
        return danhGia;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
