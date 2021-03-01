package com.example.myapplication.ui.home.detail_product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interface_app.OnClickItemListener;

/**
 *
 */
public class DetailProductFragment extends Fragment  {

    TextView txtContent;
    String iDProduct;

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
        try{
        String data = getArguments().getString("HUY");
        txtContent.setText(data);
        }
        catch (Exception e){
        }
        return v;
    }
}