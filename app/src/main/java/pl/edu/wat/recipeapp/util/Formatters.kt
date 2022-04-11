package pl.edu.wat.recipeapp.util

fun Double.toPrintable() =
    if (this % 1 == 0.0) "%.0f".format(this)
    else "%.2f".format(this)