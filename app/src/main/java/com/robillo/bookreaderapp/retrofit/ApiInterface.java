package com.robillo.bookreaderapp.retrofit;

import com.robillo.bookreaderapp.retrofit.model.Content;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by robinkamboj on 15/11/17.
 */

public interface ApiInterface {

    @GET("new_api/rush_content/{rush_id}")
    Call<List<Content>> getRushContent(
            @Path("rush_id") int rush_id
    );

}
