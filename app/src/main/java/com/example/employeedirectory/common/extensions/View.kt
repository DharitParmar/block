package com.example.employeedirectory.common.extensions

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

fun ImageView.loadUrl(url: String) {
    this.load(url)
    {
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}