package com.betsson.interviewtest.data.util

import android.content.Context

object JsonUtils {
    fun loadJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}