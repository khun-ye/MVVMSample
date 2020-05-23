package com.ytn.mvvm.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideApp(private val context: Context, private val imageView: ImageView) {

    fun loadDrawable(url: Int) {
        Glide.with(context.applicationContext)
            .load(url)
            .into(imageView)
    }
}
