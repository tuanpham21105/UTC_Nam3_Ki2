package com.example.mobile_onthi_1;

public class SinhVien {
    public int masv;
    public String ten;
    public int diemToan;
    public int diemHoa;
    public int diemLy;

    public SinhVien(int masv, String ten, int diemToan, int diemHoa, int diemLy) {
        this.masv = masv;
        this.ten = ten;
        this.diemToan = diemToan;
        this.diemHoa = diemHoa;
        this.diemLy = diemLy;
    }

    public SinhVien(SinhVien a) {
        this.masv = a.masv;
        this.ten = a.ten;
        this.diemToan = a.diemToan;
        this.diemLy = a.diemLy;
        this.diemHoa = a.diemHoa;
    }

    public int Sum() {
        return (diemToan + diemHoa + diemLy);
    }
}
