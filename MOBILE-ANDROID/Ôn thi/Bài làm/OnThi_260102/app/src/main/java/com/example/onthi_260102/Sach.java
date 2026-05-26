package com.example.onthi_260102;

public class Sach {
    private String ma;
    private String ten;
    private String tacGia;
    private float gia;
    private int soLuong;
    private static int loai = GlobalUtils.A % 3;

    public Sach() {
    }

    public Sach(String ma, String ten, String tacGia, float gia, int soLuong) {
        this.ma = ma;
        this.ten = ten;
        this.tacGia = tacGia;
        this.gia = gia;
        this.soLuong = soLuong;
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

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float tinhThanhTien() {
        return gia * (float)soLuong * (loai == 0 ? 1f : (loai == 1 ? 0.95f : 0.9f));
    }
}
