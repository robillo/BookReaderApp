package com.robillo.bookreaderapp.view;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
public class MainActivity extends AppCompatActivity implements MainMvpView, View.OnClickListener {

    private int NUM_PAGES = 0;

    private ViewPager mContentPager;
    private ProgressBar mContentProgress;
    private LinearLayout mCustomizeLinearLayout;
    private ImageButton mTextviewIncrease, mTextviewDecrease, mTextviewFont, mContentTheme, mLineSpacing, mContentPadding;

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
        mCustomizeLinearLayout = findViewById(R.id.customize_content_layout);
        mTextviewIncrease = findViewById(R.id.text_plus);
        mTextviewDecrease = findViewById(R.id.text_minus);
        mTextviewFont = findViewById(R.id.text_font);
        mContentTheme = findViewById(R.id.content_theme);
        mLineSpacing = findViewById(R.id.line_spacing);
        mContentPadding = findViewById(R.id.content_padding);

        mLineSpacing.setOnClickListener(this);
        mContentPadding.setOnClickListener(this);
        mTextviewFont.setOnClickListener(this);
        mContentTheme.setOnClickListener(this);
        mTextviewIncrease.setOnClickListener(this);
        mTextviewDecrease.setOnClickListener(this);

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
                    mContentProgress.setProgress(1);
                    //noinspection ConstantConditions
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
        mContentPager.addOnPageChangeListener(viewPagerPageChangeListener);
        mContentPager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void hideShowCustomizeLayout() {
        if(mCustomizeLinearLayout.getVisibility()==View.VISIBLE){
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mCustomizeLinearLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mCustomizeLinearLayout.startAnimation(animation);
        }
        else {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mCustomizeLinearLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mCustomizeLinearLayout.startAnimation(animation);
        }
    }

    @Override
    public void increaseTextSize() {

    }

    @Override
    public void decreaseTextSize() {

    }

    @Override
    public void changeFont() {

    }

    @Override
    public void changeTheme() {

    }

    @Override
    public void changeLineSpacing() {

    }

    @Override
    public void changeContentPadding() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_plus:{
                increaseTextSize();
                break;
            }
            case R.id.text_minus:{
                decreaseTextSize();
                break;
            }
            case R.id.text_font:{
                changeFont();
                break;
            }
            case R.id.content_theme:{
                changeTheme();
                break;
            }
            case R.id.line_spacing:{
                changeLineSpacing();
                break;
            }
            case R.id.content_padding:{
                changeContentPadding();
                break;
            }
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
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

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            mContentProgress.setProgress(position+1);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
}
