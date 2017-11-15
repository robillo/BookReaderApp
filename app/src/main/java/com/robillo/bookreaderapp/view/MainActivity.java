package com.robillo.bookreaderapp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.robillo.bookreaderapp.R;
import com.robillo.bookreaderapp.retrofit.ApiClient;
import com.robillo.bookreaderapp.retrofit.ApiInterface;
import com.robillo.bookreaderapp.retrofit.model.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by robinkamboj on 15/11/17.
 */

public class MainActivity extends AppCompatActivity implements MainMvpView {

    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void getContent() {
        mApiService = ApiClient.INSTANCE.getClient().create(ApiInterface.class);
        Call<List<Content>> call = mApiService.getRushContent(1);
        if(call!=null){
            call.enqueue(new Callback<List<Content>>() {
                @Override
                public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                    Toast.makeText(MainActivity.this, response.body().get(0).getContent(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<Content>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
