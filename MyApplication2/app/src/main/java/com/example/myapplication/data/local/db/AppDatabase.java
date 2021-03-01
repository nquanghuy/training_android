package com.example.myapplication.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {ProductLocal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductLocalDao getProductLocalDao();
}
