package com.example.storeunited;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class SportshoesAdapter extends RecyclerView.Adapter<SportshoesAdapter.SportshoesViewHolder> {

    Context contexttt;
    List<Sportshoes> mDataaa;

    public SportshoesAdapter(Context context, List<Sportshoes> mData) {
        this.contexttt = context;
        this.mDataaa = mData;
    }

    @NonNull
    @Override
    public SportshoesAdapter.SportshoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vieww= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sportshoes,parent,false);

        return new SportshoesAdapter.SportshoesViewHolder(vieww);
    }

    @Override
    public void onBindViewHolder(@NonNull SportshoesAdapter.SportshoesViewHolder holder, int position) {

        Glide.with(contexttt).load(mDataaa.get(position).getImg()).into(holder.imageViewww);

    }

    @Override
    public int getItemCount() {
        return mDataaa.size();
    }

    public class SportshoesViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewww;

        public SportshoesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewww = itemView.findViewById(R.id.imageView10);

        }
    }
}
