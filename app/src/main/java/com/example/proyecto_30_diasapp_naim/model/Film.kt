package com.example.proyecto_30_diasapp_naim.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Film(
    val dayRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descRes: Int,
    @DrawableRes val imageRes: Int
)
