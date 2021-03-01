package com.example.myapplication.ui.home.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapterListView;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.home.detail_product.DetailProductFragment;
import com.example.myapplication.utils.control_volley.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    ListView listViewProduct;
    ProgressBar progressBarLoading;
    List<Product> listProduct;
    ProductAdapterListView adapterListView;
    OnClickItemListener onClickItemListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        listViewProduct  = view.findViewById(R.id.listViewProductCart);
        progressBarLoading = view.findViewById(R.id.progress_loading_cart);
        listProduct = new ArrayList<>();
        adapterListView = new ProductAdapterListView(onClickItemListener,listProduct,getContext());
        listViewProduct.setAdapter(adapterListView);
        fetchData();
        CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                progressBarLoading.setVisibility(View.GONE);
                listViewProduct.setVisibility(View.VISIBLE);
                adapterListView.notifyDataSetChanged();
            }
        }.start();
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailProductFragment fragment = new DetailProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString("HUY",listProduct.get(position).getTENSANPHAM());
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    private void fetchData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://mylifemrrobot.000webhostapp.com/getdata.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TAG",response.toString());
                Gson gson = new Gson();
                Type ProductList = new TypeToken<ArrayList<Product>>(){}.getType();
                List<Product> listItem = gson.fromJson(String.valueOf(response), ProductList);
                listProduct.addAll(listItem);
                Log.d("TAG",listProduct.size()+ " item");
                adapterListView.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "JsonArrayRequest onErrorResponse: " + error.getMessage());
            }
        });

        VolleySingleton.getInstance(getContext()).getRequestQueue().add(jsonArrayRequest);
    }
}