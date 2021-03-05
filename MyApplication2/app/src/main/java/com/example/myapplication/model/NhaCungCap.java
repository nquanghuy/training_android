package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NhaCungCap {

    @SerializedName("TENNHACUNGCAP")
    private String TENNHACUNGCAP;

    @Expose(serialize = false)
    @SerializedName("DIACHI")
    private String DIACHI;

    @SerializedName("NGAYTHANHLAP")
    @Expose(serialize = false)
    private LocalDateTime NGAYTHANHLAP;

    @SerializedName("LOAI")
    @Expose(serialize = false)
    private Loai xepLoai;

    @SerializedName("SOLUONGCHINHANH")
    @Expose(serialize = false)
    private Integer soLuongChiNhanh;

}
