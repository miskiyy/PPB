package com.example.marketsiswa.model

import androidx.annotation.DrawableRes
import com.example.marketsiswa.R

data class Product(
    val id: Long = (0..Long.MAX_VALUE).random(),
    val name: String,
    val price: String,
    val description: String,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false,
    @DrawableRes val imageRes: Int = R.drawable.product_placeholder,
)
