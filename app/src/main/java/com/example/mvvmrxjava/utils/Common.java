package com.example.mvvmrxjava.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.mvvmrxjava.network.ApiInterface;
import com.example.mvvmrxjava.network.RetrofitClient;

import static androidx.core.content.ContextCompat.getSystemService;

public class Common {

    public static final String BASE_URL = "https://api.themoviedb.org/";

    public static ApiInterface getApiInterface() {
        return RetrofitClient.getRetrofitClient(BASE_URL).create(ApiInterface.class);
    }





}
