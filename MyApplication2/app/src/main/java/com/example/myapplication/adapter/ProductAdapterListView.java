package com.example.myapplication.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.data.local.db.AppDatabase;
import com.example.myapplication.data.local.db.ProductLocal;
import com.example.myapplication.data.local.db.ProductLocalDao;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapterListView extends BaseAdapter {
    OnClickItemListener mListener;
    List<Product> listProduct;
    Context context;

    public ProductAdapterListView(OnClickItemListener mListener, List<Product> listProduct, Context context) {
        this.mListener = mListener;
        this.listProduct = listProduct;
        this.context = context;
    }



    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = (Product) getItem(position);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = layoutInflater.inflate(R.layout.custom_item_listview_product, parent,false);

        TextView txtName = rowView.findViewById(R.id.textViewNameProductCart);
        txtName.setText(product.getTENSANPHAM());
        TextView txtPrice = rowView.findViewById(R.id.textViewPriceProductCart);
        txtPrice.setText(product.getGIA() + "VND");
        TextView txtDescription = rowView.findViewById(R.id.textViewDescriptionProductCart);
        txtDescription.setText(listProduct.get(position).getMOTASANPHAM());
        ImageView imgProduct = rowView.findViewById(R.id.imageViewPictureProduct);
        ImageView imgAddToLocalDB = rowView.findViewById(R.id.imageViewAddToLocalDB);
        imgAddToLocalDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Hello"+position, Toast.LENGTH_SHORT).show();
                try {
                    AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "myDatabaseRoom")
                            .allowMainThreadQueries()
                            .build();
                    ProductLocalDao itemDAO = database.getProductLocalDao();
                    ProductLocal productLocal = new ProductLocal(listProduct.get(position).getMASANPHAM(), listProduct.get(position).getTENSANPHAM(), listProduct.get(position).getMOTASANPHAM(), listProduct.get(position).getMALOAI(), listProduct.get(position).getGIA(), listProduct.get(position).getHINHANH());
                    itemDAO.insertAll(productLocal);
                }
                catch (Exception e){
                    Toast.makeText(context, "Sản phẩm đã tốn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Picasso.get()
                .load(product.getHINHANH())
                .placeholder(R.drawable.ic_load_image_false)
                .error(R.drawable.ic_load_image_false)
                .into(imgProduct);
        return rowView;
    }
}
