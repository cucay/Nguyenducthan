package com.example.pcthan.ketoan;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ViewPager productImageViewPager;
    private TabLayout viewPagerIndicator;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    //ratings layout
    private LinearLayout rateNowContainer;
    //ratings layout


    private static Boolean ALREADY_ADD_TO_WISHLIST=false;
    private FloatingActionButton addToWishlistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productImageViewPager=findViewById(R.id.product_images_viewpager);
        viewPagerIndicator=findViewById(R.id.viewpager_indicator);
        addToWishlistBtn = findViewById(R.id.add_to_wishlist_btn);

        productDetailsViewPager=findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout=findViewById(R.id.product_details_tablayout);

        List<Integer> productImages=new ArrayList<>();
        productImages.add(R.drawable.reset_pass);
        productImages.add(R.drawable.banner);
        productImages.add(R.drawable.add);
        productImages.add(R.drawable.banner2);

        ProductImagsAdapter productImagsAdapter=new ProductImagsAdapter(productImages);
        productImageViewPager.setAdapter(productImagsAdapter);
        viewPagerIndicator.setupWithViewPager(productImageViewPager,true);
        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADD_TO_WISHLIST){
                    ALREADY_ADD_TO_WISHLIST=false;
                    addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }else{
                    ALREADY_ADD_TO_WISHLIST=true;

                    addToWishlistBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });
        productDetailsViewPager.setAdapter(new ProducDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));

        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //ratings layout
        rateNowContainer=findViewById(R.id.rate_now_container);
            for (int x=0;x<rateNowContainer.getChildCount();x++){
                final int starPosition=x;
                rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                    }
                });
            }
        //ratings layout

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setRating(int starPosition){
        for (int x=0;x<rateNowContainer.getChildCount();x++){
            ImageView starBtn=(ImageView)rateNowContainer.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (x<= starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }else if(id == R.id.main_search_icon){
            return true;

        }else if(id == R.id.main_cart_icon){
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
