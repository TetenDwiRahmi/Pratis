package com.mobile.pratice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mobile.pratice.Model.BannerSlider;
import com.mobile.pratice.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BannerSliderAdapter extends SliderViewAdapter<BannerSliderAdapter.SliderAdapterVH> {
    private Context context;
    private List<BannerSlider> mBannerSliders = new ArrayList<>();

    public BannerSliderAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<BannerSlider> sliderItems) {
        this.mBannerSliders = sliderItems;
        notifyDataSetChanged();
    }

    @Override
    public BannerSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(BannerSliderAdapter.SliderAdapterVH viewHolder, final int position) {
        final BannerSlider sliderItem = mBannerSliders.get(position);

        Picasso.get().load(sliderItem.getGambar()).into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        return mBannerSliders.size();
    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image_banner);
            this.itemView = itemView;
        }
    }
}
