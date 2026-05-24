package com.example.onthi_240303;

public class BaiHat {
    private int id;
    private String ten;
    private String caSy;
    private int like;
    private int share;

    public BaiHat() {
    }

    public BaiHat(int id, String ten, String caSy, int like, int share) {
        this.id = id;
        this.ten = ten;
        this.caSy = caSy;
        this.like = like;
        this.share = share;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCaSy() {
        return caSy;
    }

    public void setCaSy(String caSy) {
        this.caSy = caSy;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int tinhDiem() {
        return like + (share * 5) + GlobalUtils.A;
    }

    public String tenCaSy() {
        String[] words = caSy.split(" ");

        return words[words.length - 1];
    }
}
