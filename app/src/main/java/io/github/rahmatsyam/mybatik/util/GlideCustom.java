package io.github.rahmatsyam.mybatik.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideCustom {

    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
