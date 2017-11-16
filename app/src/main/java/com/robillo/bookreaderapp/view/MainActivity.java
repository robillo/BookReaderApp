package com.robillo.bookreaderapp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements MainMvpView {

    private int NUM_PAGES = 5;

    private ViewPager mContentPager;
    private ProgressBar mContentProgress;

    private ApiInterface mApiService;
    private List<Content> mContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
    }

    @Override
    public void setUp() {
        mContentPager = findViewById(R.id.content_pager);
        mContentProgress = findViewById(R.id.content_progress);
        getContent();
    }

    @Override
    public void getContent() {
        mApiService = ApiClient.INSTANCE.getClient().create(ApiInterface.class);
        Call<List<Content>> call = mApiService.getRushContent(1);
        if(call!=null){
            call.enqueue(new Callback<List<Content>>() {
                @Override
                public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                    mContents = response.body();
                    //noinspection ConstantConditions
                    mContentProgress.setMax(response.body().size());
                    setFragmentsForContents(mContents);
                }

                @Override
                public void onFailure(Call<List<Content>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void setFragmentsForContents(List<Content> contents) {
        NUM_PAGES = contents.size();
        mContentPager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager()));
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            mContentProgress.setProgress(position+1);
            return ContentFragment.newInstance(mContents.get(position).getContent());
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public float getPageWidth(int position) {
            return 1.0f;
        }

    }
}
