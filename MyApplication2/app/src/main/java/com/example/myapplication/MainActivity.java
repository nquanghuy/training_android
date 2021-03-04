package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.data.local.db.AppDatabase;
import com.example.myapplication.data.local.db.ProductLocal;
import com.example.myapplication.data.local.db.ProductLocalDao;
import com.example.myapplication.ui.home.HomeActivity;
import com.example.myapplication.ui.login.LoginActivity;
import com.example.myapplication.utils.AppConstant;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intentGoToScreenLogin = new Intent(this, LoginActivity.class);
        Intent intentGoToScreenHome = new Intent(this, HomeActivity.class);
        intentGoToScreenLogin.putExtra(AppConstant.TIME_FROM_WELCOME,getTimeNow());



        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "myDatabaseRoom")
                .allowMainThreadQueries()
                .build();
        ProductLocalDao itemDAO = database.getProductLocalDao();
        //ProductLocal productLocal = new ProductLocal("01","Đồ Đẹp","ĐẸp nhất rồi","1","560000","http://akhoai.net/wp-content/uploads/2021/02/1.jpg");
        //itemDAO.insertAll(productLocal);

        List<ProductLocal> items = itemDAO.loadAllByIds("01");
        //Create SharePreferences
        SharedPreferences sharedPref = getSharedPreferences("StatusLogin", Context.MODE_PRIVATE);
        boolean active = sharedPref.getBoolean("status", false);
        CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(active){
                    startActivity(intentGoToScreenHome);
                    finish();
                }
                else {
                    startActivity(intentGoToScreenLogin);
                    finish();
                }
            }
        }.start();
    }

    String getTimeNow(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat timeCurrent= new SimpleDateFormat("dd-MM-yyyy");
        String timeNow = timeCurrent.format(date);
        return timeNow;
    }
}