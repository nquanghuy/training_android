package com.example.myapplication.ui.home.shop;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.data.local.db.AppDatabase;
import com.example.myapplication.data.local.db.ProductLocalDao;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.home.detail_product.DetailProductFragment;
import com.example.myapplication.ui.home.detail_product.ExampleFragment;
import com.example.myapplication.utils.control_volley.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment{
    String TAG = "LOG_VOLLEY";
    RequestQueue requestQueue;
    ProductAdapter productAdapter;
    List<Product> listProduct;
    RecyclerView recyclerViewListProductShop;
    ProgressBar progressBarLoading;
    OnClickItemListener onClickItemListener;

    public ShopFragment() {
    }
    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public static ShopFragment getInstance(){
        return new ShopFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewListProductShop = view.findViewById(R.id.recycleViewListProduct);
        progressBarLoading = view.findViewById(R.id.progress_loading);
        listProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(onClickItemListener,listProduct,getContext());
        recyclerViewListProductShop.setHasFixedSize(true);
        recyclerViewListProductShop.setAdapter(productAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewListProductShop.setLayoutManager(layoutManager);
        fetchDataFromAPIWithVolley();
        productAdapter.notifyDataSetChanged();
        //Check Data
        AppDatabase database = Room.databaseBuilder(getContext(), AppDatabase.class, "myDatabaseRoom")
                .allowMainThreadQueries()
                .build();
        ProductLocalDao itemDAO = database.getProductLocalDao();
        Log.d("TAG",itemDAO.getAll().size()+"Đây là kích thước của file db Offline");
        CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                progressBarLoading.setVisibility(View.GONE);
                recyclerViewListProductShop.setVisibility(View.VISIBLE);
                productAdapter.notifyDataSetChanged();
            }
        }.start();

        productAdapter.setOnItemClickListenner(new OnClickItemListener() {
            @Override
            public void onClickItem(int content) {
                DetailProductFragment fragment = new DetailProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString("HUY",listProduct.get(content).getTENSANPHAM());
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void fetchDataFromAPIWithVolley() {
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://mylifemrrobot.000webhostapp.com/getdata.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TAG",response.toString());
                Gson gson = new Gson();
                Type ProductList = new TypeToken<ArrayList<Product>>(){}.getType();
                List<Product> listItem = gson.fromJson(String.valueOf(response), ProductList);
                listProduct.addAll(listItem);
                Log.d(TAG,listProduct.size()+ " item");
                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "JsonArrayRequest onErrorResponse: " + error.getMessage());
            }
        });

        VolleySingleton.getInstance(getContext()).getRequestQueue().add(jsonArrayRequest);

    }

}