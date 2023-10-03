package com.example.network_db.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).fitCenter().centerCrop().apply(
        RequestOptions().transform(
            RoundedCorners(50)
        )
    )
        .into(this)
}