package com.example.myapplication.model;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class User{
    @SerializedName("SODIENTHOAI")
    private String numberPhone;
    @SerializedName("MATKHAU")
    private String passWord;
}
