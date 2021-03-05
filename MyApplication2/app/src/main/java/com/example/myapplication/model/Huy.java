package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Huy {

    @Expose(serialize = false)
    @SerializedName("MASANPHAM")
    private String MASANPHAM;

    @Expose(serialize = false)
    @SerializedName("TENSANPHAM")
    private String TENSANPHAM;

    @Expose(serialize = false)
    @SerializedName("NHACUNGCAP")
    private List<NhaCungCap> NHACUNGCAP;

    @Expose(serialize = false)
    @SerializedName("NGAYMUA")
    private LocalDate timeDate;

    @Expose(serialize = false)
    @SerializedName("NGAYBAN")
    private LocalDate ngayBan;
}
