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

public class SneakersAdapter extends RecyclerView.Adapter<SneakersAdapter.SneakersViewHolder> {

    Context context;
    List<Sneakers> mData;

    public SneakersAdapter(Context context, List<Sneakers> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public SneakersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sneakers,parent,false);

        return new SneakersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SneakersViewHolder holder, int position) {

        Glide.with(context).load(mData.get(position).getImg()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sneaker=new Intent(context,SneakersActivity.class);
                sneaker.putExtra("SneakerID",mData.get(position).getID());
                sneaker.putExtra("SneakerSIZE",mData.get(position).getSize());
                sneaker.putExtra("userID",mData.get(position).getUserID());
                sneaker.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(sneaker);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class SneakersViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public SneakersViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView8);


        }
    }
}
