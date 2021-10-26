package com.example.storeunited;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Cart2Adapter extends RecyclerView.Adapter<Cart2Adapter.CartViewHolder>  {

    Context context10;
    List<Cart2> mData10;

    public Cart2Adapter(Context context10, List<Cart2> mData10) {
        this.context10 = context10;
        this.mData10 = mData10;
    }

    @NonNull
    @Override
    public Cart2Adapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);

        return new Cart2Adapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart2Adapter.CartViewHolder holder, int position) {

        Glide.with(context10).load(mData10.get(position).getIimgg()).into(holder.imageView177);
        holder.name177.setText(mData10.get(position).getNnamee());
        holder.price177.setText(mData10.get(position).getPpricee());


    }

    @Override
    public int getItemCount() {
        return mData10.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView177;
        private TextView name177,price177;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView177 = itemView.findViewById(R.id.imageView17);
            name177 = itemView.findViewById(R.id.textView21);
            price177 = itemView.findViewById(R.id.textView22);


        }
    }
}