package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.model.Product;

import java.util.List;

public class SpinnerProductAdapter extends BaseAdapter {
    private List<Product> listProduct;
    private Context context;

    public SpinnerProductAdapter(Context context,
                                 List<Product> list) {
        this.listProduct = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(this.listProduct == null)  {
            return 0;
        }
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = (Product) getItem(position);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        @SuppressLint("ViewHolder") View rowView = layoutInflater.inflate(R.layout.custom_item_spinner, parent,false);

        TextView textViewItemName = rowView.findViewById(R.id.textView_item_name);
        textViewItemName.setText(product.getTENSANPHAM()+"");
        TextView textViewItemPercent = (TextView) rowView.findViewById(R.id.textView_item_price);
        textViewItemPercent.setText(product.getGIA() + "VND");

        return rowView;
    }

}
