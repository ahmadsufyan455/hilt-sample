package com.zerodev.hiltexample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.getConnectivity(): NetworkCapabilities? {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm.getNetworkCapabilities(cm.activeNetwork)
    } else {
        TODO("VERSION.SDK_INT < M")
    }
}