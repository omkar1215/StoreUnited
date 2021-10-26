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


public class WatchesAdapter extends RecyclerView.Adapter<WatchesAdapter.WatchesViewHolder> {

    Context context2;
    List<Watches> mData2;

    public WatchesAdapter(Context context, List<Watches> mData) {
        this.context2 = context;
        this.mData2 = mData;
    }

    @NonNull
    @Override
    public WatchesAdapter.WatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vieww= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_watches,parent,false);

        return new WatchesAdapter.WatchesViewHolder(vieww);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchesAdapter.WatchesViewHolder holder, int position) {

        Glide.with(context2).load(mData2.get(position).getImg()).into(holder.imageViewwwww);

    }

    @Override
    public int getItemCount() {
        return mData2.size();
    }

    public class WatchesViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewwwww;

        public WatchesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewwwww = itemView.findViewById(R.id.imageView11);

        }
    }
}

