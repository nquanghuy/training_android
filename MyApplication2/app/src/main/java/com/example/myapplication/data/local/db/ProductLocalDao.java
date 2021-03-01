package com.example.myapplication.data.local.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;
@Dao
public interface ProductLocalDao {
    @Query("SELECT * FROM product_local")
    List<ProductLocal> getAll();

    @Query("SELECT * FROM product_local WHERE MASANPHAM IN (:maSanPham)")
    List<ProductLocal> loadAllByIds(String maSanPham);


    @Insert
    void insertAll(ProductLocal... productLocal);

    @Delete
    void delete(ProductLocal productLocal);
}
