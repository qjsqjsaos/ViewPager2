package org.techtown.viewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import org.techtown.viewpager2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private Handler sliderHandler = new Handler(Looper.myLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        List<Integer> sliderItems = new ArrayList<>();
        sliderItems.add(R.drawable.ic_launcher_background);
        sliderItems.add(R.drawable.ic_launcher_foreground);

        mBinding.vpImageSlider.setClipToPadding(false);// 미리보기
        mBinding.vpImageSlider.setClipChildren(false);// 미리보기
        mBinding.vpImageSlider.setOffscreenPageLimit(3); //이미지 한계를 보여주는 메서드
        mBinding.vpImageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        mBinding.vpImageSlider.setAdapter(new SliderAdapter(this, mBinding.vpImageSlider, sliderItems)); //어뎁터로 보내기기

       CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        mBinding.vpImageSlider.setPageTransformer(compositePageTransformer);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}