package com.example.myapplication.ui.home.detail_product;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.model.Huy;
import com.example.myapplication.model.LocalDate2Deserializer;
import com.example.myapplication.model.LocalDate2Serializer;
import com.example.myapplication.model.LocalDateDeserializer;
import com.example.myapplication.model.LocalDateSerializer;
import com.example.myapplication.model.LocalDateTimeDeserializer;
import com.example.myapplication.model.LocalDateTimeSerializer;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.SanPham;
import com.example.myapplication.ui.home.DemoUIFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DetailProductFragment extends Fragment  {

    TextView txtContent;
    String iDProduct="";
    Button btnClickMe,btnClickMeShow;
    List<SanPham> lstSanPham;
    public DetailProductFragment() {
    }


    public static DetailProductFragment getInstance(){

        return new DetailProductFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_detail_product, container, false);

        txtContent = v.findViewById(R.id.textViewContent);
        btnClickMe = v.findViewById(R.id.buttonClickMe);
        btnClickMeShow = v.findViewById(R.id.buttonClickMeShow);

        btnClickMeShow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String jsonResponse="{\"MASANPHAM\":\"SP1\",\"TENSANPHAM\":\"DEMO1\",\"NGAYMUA\":\"22/01/2020\",\"NGAYBAN\":\"11-01-2023\",\"NHACUNGCAP\":[{\"TENNHACUNGCAP\":\"Công ty 01\",\"SOLUONGCHINHANH\":\"2\",\"DIACHI\":\"HA TINH\",\"NGAYTHANHLAP\":\"27::Oct::2018 14::35::13\"},{\"TENNHACUNGCAP\":\"Công ty 02\",\"SOLUONGCHINHANH\":\"5\",\"DIACHI\":\"HA TINH\",\"NGAYTHANHLAP\":\"27::Oct::2018 14::35::13\",\"LOAI\":\"VIP\"},{\"TENNHACUNGCAP\":\"Công ty 03\",\"SOLUONGCHINHANH\":\"20\",\"DIACHI\":\"HA TINH\",\"NGAYTHANHLAP\":\"27::Oct::2018 14::35::13\",\"LOAI\":\"VIP\"},{\"TENNHACUNGCAP\":\"Công ty 04\",\"DIACHI\":\"HA TINH\",\"NGAYTHANHLAP\":\"27::Oct::2018 14::35::13\",\"LOAI\":\"NORMAL\"}]}";
                Log.d("TAG","huyhuyhuy"+jsonResponse);
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
                gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
                gsonBuilder.registerTypeAdapter(LocalDateTime.class,new LocalDateTimeDeserializer());
                gsonBuilder.registerTypeAdapter(LocalDateTime.class,new LocalDateTimeSerializer());
//                gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDate2Serializer());
//                gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDate2Deserializer());
                Gson gsonHuy = gsonBuilder.create();

                Huy huyObject = gsonHuy.fromJson(jsonResponse, Huy.class);
                Toast.makeText(getContext(), "Hello anh huy+ "+ huyObject.getTimeDate().getDayOfMonth(), Toast.LENGTH_SHORT).show();
                Log.d("HUY",huyObject.getNHACUNGCAP().get(0).getTENNHACUNGCAP()+"");
                Log.d("HUY",huyObject.getNHACUNGCAP().size()+"");
                Log.d("HUY",huyObject.getNHACUNGCAP().get(0).getXepLoai()+" Xeeps loai");
                Log.d("HUY","To Json " + gsonHuy.toJson(huyObject));
                Log.d("HUY",huyObject.getNHACUNGCAP().get(0).getNGAYTHANHLAP().getDayOfMonth()+" LocalDateTime");
            }
        }
        );
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DemoUIFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        try{
        String data = getArguments().getString("HUY");
        txtContent.setText(data);
        }
        catch (Exception e){
        }
        return v;
    }
    public void setChangeText(String string){
        iDProduct = string;
    }
}