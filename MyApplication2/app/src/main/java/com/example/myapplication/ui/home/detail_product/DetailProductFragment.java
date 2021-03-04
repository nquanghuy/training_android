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
import com.example.myapplication.model.Product;
import com.example.myapplication.model.SanPham;
import com.example.myapplication.ui.home.DemoUIFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
                String jsonResponse="[{\"MASANPHAM\":\"SP1\",\"TENSANPHAM\":\"DEMO1\",\"NGAYMUA\":\"22-01-2020\",\"NHACUNGCAP\":{\"TENNHACUNGCAP\":\"Công ty 01\",\"DIACHI\":\"HA TINH\"}}]";
                Log.d("TAG","huyhuyhuy"+jsonResponse);
                Gson gson = new Gson();
                Type ProductList = new TypeToken<ArrayList<SanPham>>(){}.getType();
                List<SanPham> listItem = gson.fromJson(String.valueOf(jsonResponse), ProductList);
                List<SanPham> lst = listItem;

                Toast.makeText(getContext(), "Show "+listItem.size()+"Ngày Local date"+" Nhà cung cấp"+listItem.get(0).getTimeDate().getDayOfMonth()+"Nhà cung cấp :"+ listItem.get(0).getNHACUNGCAP().getTENNHACUNGCAP(), Toast.LENGTH_SHORT).show();
            }
        });
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