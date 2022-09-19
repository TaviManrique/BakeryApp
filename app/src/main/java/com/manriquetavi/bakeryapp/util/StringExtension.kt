package com.manriquetavi.bakeryapp.util

fun removeLastNchars(str: String, n: Int): String {
    return str.substring(0, str.length - n)
}