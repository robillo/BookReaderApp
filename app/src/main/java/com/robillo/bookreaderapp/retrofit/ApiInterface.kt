package com.robillo.bookreaderapp.retrofit

import com.robillo.bookreaderapp.retrofit.model.Content

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by robinkamboj on 15/11/17.
 */

interface ApiInterface {

    @GET("new_api/rush_content/{rush_id}")
    fun getRushContent(
            @Path("email") rush_id: Int
    ): Call<List<Content>>

}
