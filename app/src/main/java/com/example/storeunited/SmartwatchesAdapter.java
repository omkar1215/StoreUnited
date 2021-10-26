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

public class SmartwatchesAdapter extends RecyclerView.Adapter<SmartwatchesAdapter.SmartwatchesViewHolder> {

    Context contextt;
    List<Smartwatches> mDataa;

    public SmartwatchesAdapter(Context context, List<Smartwatches> mData) {
        this.contextt = context;
        this.mDataa = mData;
    }

    @NonNull
    @Override
    public SmartwatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vieww= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_smartwatches,parent,false);

        return new SmartwatchesAdapter.SmartwatchesViewHolder(vieww);
    }

    @Override
    public void onBindViewHolder(@NonNull SmartwatchesViewHolder holder, int position) {

        Glide.with(contextt).load(mDataa.get(position).getImg()).into(holder.imageVieww);

    }

    @Override
    public int getItemCount() {
        return mDataa.size();
    }

    public class SmartwatchesViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageVieww;

        public SmartwatchesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageVieww = itemView.findViewById(R.id.imageView12);

        }
    }
}
