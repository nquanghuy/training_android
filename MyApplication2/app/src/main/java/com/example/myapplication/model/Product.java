package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Product {
    @SerializedName("MASANPHAM")
    private String MASANPHAM;

    @SerializedName("TENSANPHAM")
    private String TENSANPHAM;
    @SerializedName("MOTASANPHAM")
    private String MOTASANPHAM;
    @SerializedName("MALOAI")
    private String MALOAI;
    @SerializedName("GIA")
    private String GIA;
    @SerializedName("HINHANH")
    private String HINHANH;

//    @SerializedName("....")
//    private LocalDateTime expriedDate;
//
//    @SerializedName("....")
//    private Category category;

    public Product() {
    }

    public Product(String MASANPHAM, String TENSANPHAM, String MOTASANPHAM, String MALOAI, String GIA, String HINHANH) {
        this.MASANPHAM = MASANPHAM;
        this.TENSANPHAM = TENSANPHAM;
        this.MOTASANPHAM = MOTASANPHAM;
        this.MALOAI = MALOAI;
        this.GIA = GIA;
        this.HINHANH = HINHANH;
    }

    public String getMASANPHAM() {
        return MASANPHAM;
    }

    public void setMASANPHAM(String MASANPHAM) {
        this.MASANPHAM = MASANPHAM;
    }

    public String getTENSANPHAM() {
        return TENSANPHAM;
    }

    public void setTENSANPHAM(String TENSANPHAM) {
        this.TENSANPHAM = TENSANPHAM;
    }

    public String getMOTASANPHAM() {
        return MOTASANPHAM;
    }

    public void setMOTASANPHAM(String MOTASANPHAM) {
        this.MOTASANPHAM = MOTASANPHAM;
    }

    public String getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(String MALOAI) {
        this.MALOAI = MALOAI;
    }

    public String getGIA() {
        return GIA;
    }

    public void setGIA(String GIA) {
        this.GIA = GIA;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }
}
