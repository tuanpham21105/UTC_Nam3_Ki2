package com.example.onthi_240403;

public class HangHoa {
    private int ma;
    private String ten;
    private int gia;
    private boolean giamGia;

    public HangHoa() {
    }

    public HangHoa(int ma, String ten, int gia, boolean giamGia) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.giamGia = giamGia;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public boolean isGiamGia() {
        return giamGia;
    }

    public void setGiamGia(boolean giamGia) {
        this.giamGia = giamGia;
    }

    public int tinhGiaBan() {
        int tiLe = 15;
        if (0 <= GlobalUtils.A && GlobalUtils.A < 30) {
            tiLe = 5;
        }
        else if (0 <= GlobalUtils.A && GlobalUtils.A < 30) {
            tiLe = 10;
        }

        return gia - (giamGia ? (tiLe * (gia / 100)) : 0);
    }
}
