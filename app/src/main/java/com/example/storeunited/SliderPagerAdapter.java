package com.example.storeunited;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Slide> mmList;

    public SliderPagerAdapter(Context context, List<Slide> mmList) {
        this.context = context;
        this.mmList = mmList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slidelayout=inflater.inflate(R.layout.slide_item,null);

        ImageView slideImg=slidelayout.findViewById(R.id.imageView15);
        Glide.with(context).load(mmList.get(position).getIimmgg()).into(slideImg);

        container.addView(slidelayout);
        return slidelayout;

    }

    @Override
    public int getCount() {
        return mmList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
