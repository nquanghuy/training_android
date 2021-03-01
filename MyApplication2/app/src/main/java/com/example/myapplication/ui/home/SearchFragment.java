package com.example.myapplication.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.home.detail_product.DetailProductFragment;
import com.example.myapplication.utils.control_volley.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFragment extends Fragment {
    // TODO: Rename and change types and number of parameters

    SearchView searchViewProduct;
    ImageView imageViewNullSearch;
    RecyclerView recyclerViewListProductSearch;
    ProductAdapter productAdapter;
    List<Product> listResultProduct;
    OnClickItemListener onClickItemListener;
    RequestQueue requestQueue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_search, container, false);
        searchViewProduct = view.findViewById(R.id.searchViewProduct);
        imageViewNullSearch = view.findViewById(R.id.imageViewNullSearch);
        recyclerViewListProductSearch = view.findViewById(R.id.recycleViewListProductSearch);
        listResultProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(onClickItemListener,listResultProduct,getContext());
        recyclerViewListProductSearch.setHasFixedSize(true);
        recyclerViewListProductSearch.setAdapter(productAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewListProductSearch.setLayoutManager(layoutManager);
        productAdapter.notifyDataSetChanged();

        searchViewProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listResultProduct.clear();
                searchProduct(newText);
                return false;
            }
        });

        productAdapter.setOnItemClickListenner(new OnClickItemListener() {
            @Override
            public void onClickItem(int content) {
                DetailProductFragment fragment = new DetailProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString("HUY",content+"");
                fragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    private void searchProduct(String text){
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://mylifemrrobot.000webhostapp.com/timkiem.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("TAG", "onResponse STRINGREQUEST: " + response);
                Log.d("TAG",response.toString());
                Gson gson = new Gson();
                Type List = new TypeToken<ArrayList<Product>>(){}.getType();
                List<Product> listItem = gson.fromJson(response, List);
                listResultProduct.addAll(listItem);
                if(listResultProduct.size()!=0){
                    recyclerViewListProductSearch.setVisibility(View.VISIBLE);
                    imageViewNullSearch.setVisibility(View.GONE);
                }
                else
                {
                    recyclerViewListProductSearch.setVisibility(View.GONE);
                    imageViewNullSearch.setVisibility(View.VISIBLE);
                }
                Log.d("TAG",listResultProduct.size()+ " itemmm");
                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse STRINGREQUEST: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("TEXT", text);
                return params;
            }

            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }
        };
        VolleySingleton.getInstance(getContext()).getRequestQueue().add(stringRequest);
    }
}