package com.example.myapplication.data.local.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_local")
public class ProductLocal {
    @PrimaryKey
    @NonNull
    private String MASANPHAM;

    @ColumnInfo(name = "TENSANPHAM")
    private String TENSANPHAM;

    @ColumnInfo(name = "MOTASANPHAM")
    private String MOTASANPHAM;

    @ColumnInfo(name = "MALOAI")
    private String MALOAI;

    @ColumnInfo(name = "GIA")
    private String GIA;

    @ColumnInfo(name = "HINHANH")
    private String HINHANH;

    public ProductLocal(@NonNull String MASANPHAM, String TENSANPHAM, String MOTASANPHAM, String MALOAI, String GIA, String HINHANH) {
        this.MASANPHAM = MASANPHAM;
        this.TENSANPHAM = TENSANPHAM;
        this.MOTASANPHAM = MOTASANPHAM;
        this.MALOAI = MALOAI;
        this.GIA = GIA;
        this.HINHANH = HINHANH;
    }

    @NonNull
    public String getMASANPHAM() {
        return MASANPHAM;
    }

    public void setMASANPHAM(@NonNull String MASANPHAM) {
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