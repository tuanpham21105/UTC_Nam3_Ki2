package com.example.onthi_1;

public class SinhVien {
    public int ma;
    public String ten;
    public int diemToan;
    public int diemHoa;
    public int diemLy;

    public SinhVien(int ma, String ten, int diemToan, int diemHoa, int diemLy) {
        this.ma = ma;
        this.ten = ten;
        this.diemToan = diemToan;
        this.diemHoa = diemHoa;
        this.diemLy = diemLy;
    }

    public int TongDiem() {
        return diemHoa + diemLy + diemToan;
    }
}
