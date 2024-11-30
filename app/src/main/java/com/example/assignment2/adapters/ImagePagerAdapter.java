package com.example.assignment2.adapters;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImagePagerAdapter extends RecyclerView.Adapter<ImagePagerAdapter.ViewHolder> {
    private final List<Integer> images;

    public ImagePagerAdapter(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // DisplayMetrics를 사용해 화면 크기 계산
        DisplayMetrics metrics = parent.getContext().getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 1.0);
        int height = (int) (metrics.heightPixels * 1.0);

        // FrameLayout 생성: 여백 추가를 위해 사용
        FrameLayout frameLayout = new FrameLayout(parent.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        // ImageView 생성
        ImageView imageView = new ImageView(parent.getContext());
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(width, height);
        imageParams.gravity = Gravity.CENTER;
        imageView.setLayoutParams(imageParams);

        // 이미지를 잘리지 않고 표시
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        // FrameLayout에 ImageView 추가
        frameLayout.addView(imageView);

        return new ViewHolder(frameLayout, imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(@NonNull View itemView, ImageView imageView) {
            super(itemView);
            this.imageView = imageView;
        }
    }
}