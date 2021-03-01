package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.interface_app.OnClickItemListener;
import com.example.myapplication.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHodel> {
    OnClickItemListener mListener;
    List<Product> listProduct;
    Context context;

    public ProductAdapter(OnClickItemListener mListener, List<Product> listProduct, Context context) {
        this.mListener = mListener;
        this.listProduct = listProduct;
        this.context = context;
    }



    public  void setOnItemClickListenner(OnClickItemListener listenner){
        mListener = listenner;
    }
    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview  = layoutInflater.inflate(R.layout.custom_item_product,parent,false);
        return new ViewHodel(itemview,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        holder.txtTenSP.setText(listProduct.get(position).getTENSANPHAM());
        holder.txtGia.setText(listProduct.get(position).getGIA()+" VNĐ");
        Log.d("AAA",listProduct.get(position).getGIA()+" VNĐ");
        Product product =listProduct.get(position);
        
        Picasso.get()
                .load(product.getHINHANH())
                .placeholder(R.drawable.ic_load_image_false)
                .error(R.drawable.ic_load_image_false)
                .into(holder.imgHinh);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickItem(position);
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public static class ViewHodel extends RecyclerView.ViewHolder{
        TextView txtTenSP,txtGia;
        ImageView imgHinh;
        public ViewHodel(@NonNull View itemView,final OnClickItemListener listenner) {
            super(itemView);
            txtTenSP= itemView.findViewById(R.id.textViewTenSanPham);
            txtGia= itemView.findViewById(R.id.textViewGiaSanPham);
            imgHinh= itemView.findViewById(R.id.imageViewHinhSanPham);
            itemView.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();
                @Override
                public void onClick(View v) {
                    listenner.onClickItem(position);
                }
            });
        }
    }
}
