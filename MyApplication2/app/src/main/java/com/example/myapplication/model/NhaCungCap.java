package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NhaCungCap {
    @SerializedName("TENNHACUNGCAP")
    private String TENNHACUNGCAP;
    @SerializedName("DIACHI")
    private String DIACHI;

    public NhaCungCap() {
    }

    public String getTENNHACUNGCAP() {
        return TENNHACUNGCAP;
    }

    public void setTENNHACUNGCAP(String TENNHACUNGCAP) {
        this.TENNHACUNGCAP = TENNHACUNGCAP;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }
}
