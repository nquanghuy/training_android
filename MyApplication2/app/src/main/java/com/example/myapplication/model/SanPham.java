package com.example.myapplication.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SanPham {
    @SerializedName("MASANPHAM")
    private String MASANPHAM;
    @SerializedName("TENSANPHAM")
    private String TENSANPHAM;
    @SerializedName("NGAYMUA")
    private String NGAYMUA;
    @SerializedName("NHACUNGCAP")
    private NhaCungCap NHACUNGCAP;


    private LocalDate timeDate;
    public SanPham() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public SanPham(String MASANPHAM, String TENSANPHAM, String NGAYMUA, NhaCungCap NHACUNGCAP) {
        this.MASANPHAM = MASANPHAM;
        this.TENSANPHAM = TENSANPHAM;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDateTime dateTime = LocalDateTime.parse(NGAYMUA, formatter);
        this.NGAYMUA = NGAYMUA;
        this.NHACUNGCAP = NHACUNGCAP;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getTimeDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTime = LocalDate.parse(NGAYMUA, formatter);

        return dateTime;
    }

    public void setTimeDate(LocalDate timeDate) {
        this.timeDate = timeDate;
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

    public String getNGAYMUA() {
        return NGAYMUA;
    }

    public void setNGAYMUA(String NGAYMUA) {
        this.NGAYMUA = NGAYMUA;
    }

    public NhaCungCap getNHACUNGCAP() {
        return NHACUNGCAP;
    }

    public void setNHACUNGCAP(NhaCungCap NHACUNGCAP) {
        this.NHACUNGCAP = NHACUNGCAP;
    }
}
