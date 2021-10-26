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


public class SunglassesAdapter extends RecyclerView.Adapter<SunglassesAdapter.SunglassesViewHolder> {

    Context contextttt;
    List<Sunglasses> mDataaaa;

    public SunglassesAdapter(Context context, List<Sunglasses> mData) {
        this.contextttt = context;
        this.mDataaaa = mData;
    }

    @NonNull
    @Override
    public SunglassesAdapter.SunglassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vieww= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sunglasses,parent,false);

        return new SunglassesAdapter.SunglassesViewHolder(vieww);
    }

    @Override
    public void onBindViewHolder(@NonNull SunglassesAdapter.SunglassesViewHolder holder, int position) {

        Glide.with(contextttt).load(mDataaaa.get(position).getImg()).into(holder.imageViewwww);

    }

    @Override
    public int getItemCount() {
        return mDataaaa.size();
    }

    public class SunglassesViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewwww;

        public SunglassesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewwww = itemView.findViewById(R.id.imageView9);

        }
    }
}

