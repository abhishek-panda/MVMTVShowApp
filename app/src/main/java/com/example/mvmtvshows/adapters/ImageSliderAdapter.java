package com.example.mvmtvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvmtvshows.R;
import com.example.mvmtvshows.databinding.ItemContainerSliderImageBinding;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>{

    private String[] sliderImages;
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(String[] sliderImages) {
        this.sliderImages = sliderImages;
    }

    static class ImageSliderViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerSliderImageBinding itemContainerSliderImageBinding;

        public ImageSliderViewHolder(ItemContainerSliderImageBinding itemContainerSliderImageBinding) {
            super(itemContainerSliderImageBinding.getRoot());
            this.itemContainerSliderImageBinding = itemContainerSliderImageBinding;
        }

        public void bindSliderImage(String imageURL) {
            itemContainerSliderImageBinding.setImageURL(imageURL);
        }

    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerSliderImageBinding sliderImageBinding = DataBindingUtil.inflate(
          layoutInflater, R.layout.item_container_slider_image, parent, false
        );
        return new ImageSliderViewHolder(sliderImageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderAdapter.ImageSliderViewHolder holder, int position) {
        holder.bindSliderImage(sliderImages[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImages.length;
    }

}
