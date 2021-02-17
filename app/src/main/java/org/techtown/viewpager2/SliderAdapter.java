package org.techtown.viewpager2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

import org.techtown.viewpager2.databinding.SlideItemBinding;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private static final String TAG = "SliderAdapter";

    private Context mContext;
    private ViewPager2 mViewPager2;
    private List<Integer> sliderItems;

    public SliderAdapter(Context context, ViewPager2 viewPager2, List<Integer> sliderImage) {
        mContext = context;
        mViewPager2 = viewPager2;
        this.sliderItems = sliderImage;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(SlideItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.bind(sliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private SlideItemBinding mBinding;

        public SliderViewHolder(SlideItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Integer sliderItem) {
            try {
                Glide.with(mContext).load(sliderItem).into(mBinding.imageSlider);
            } catch (Exception e) {
                Log.d(TAG, "ERROR: " + e.getMessage());
            }
        }
    }

}