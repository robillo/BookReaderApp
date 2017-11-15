package com.robillo.bookreaderapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by robinkamboj on 15/11/17.
 */

object ApiClient {

    val BASE_URL = "http://readrush.in/index.php/"
    private var mRetrofitInstance: Retrofit? = null

    val client: Retrofit
        get() {
            if (mRetrofitInstance == null) {
                mRetrofitInstance = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            }
            return this.mRetrofitInstance!!
        }
}

