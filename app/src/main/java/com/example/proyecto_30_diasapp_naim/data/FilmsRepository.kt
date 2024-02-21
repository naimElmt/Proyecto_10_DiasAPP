package com.example.proyecto_30_diasapp_naim.data

import com.example.proyecto_30_diasapp_naim.R
import com.example.proyecto_30_diasapp_naim.model.Film

object FilmsRepository {
    val films = listOf(
        Film(1, R.string.dia1_title, R.string.dia1_desc, R.drawable.dia1),
        Film(2, R.string.dia2_title, R.string.dia2_desc, R.drawable.dia2),
        Film(3, R.string.dia3_title, R.string.dia3_desc, R.drawable.dia3),
        Film(4, R.string.dia4_title, R.string.dia4_desc, R.drawable.dia4),
        Film(5, R.string.dia5_title, R.string.dia5_desc, R.drawable.dia5),
        Film(6, R.string.dia6_title, R.string.dia6_desc, R.drawable.dia6),
        Film(7, R.string.dia7_title, R.string.dia7_desc, R.drawable.dia7),
        Film(8, R.string.dia8_title, R.string.dia8_desc, R.drawable.dia8),
        Film(9, R.string.dia9_title, R.string.dia9_desc, R.drawable.dia9),
        Film(10, R.string.dia10_title, R.string.dia10_desc, R.drawable.dia10),

    )
}