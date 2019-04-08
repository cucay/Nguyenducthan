package com.example.pcthan.ketoan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=getIntent().getStringExtra("catagoryName");
        getSupportActionBar().setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryRecycleView=findViewById(R.id.category_recyclerview);

        ///Banner slide


        List<SliderModel> sliderModelList=new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.mipmap.search,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.signout,"#077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.email,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.cart,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.bell,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.oder,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.search,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.signout,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.search,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.signout,"#077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.email,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.cart,"#077AE4"));

        ///end banner

        //horizontalproduct

        List<HorizontalProductScrollModel> horizontalProductScrollModelList=new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ssa8,"SamSung A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ssa8,"SamSung A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ssa8,"SamSung A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ssa8,"SamSung A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ssa8,"SamSung A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ssa8,"SamSung A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ssa8,"SamSung A8","Ram 4GB","6000000"));

        //horizontalproduct

        //testing


        LinearLayoutManager testingLayoutManager=new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecycleView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList=new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.add,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Ban chay nhat",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Ban chay nhat",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.add,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Ban chay nhat",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Ban chay nhat",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#ffff00"));


        HomePageAdapter adapter=new HomePageAdapter(homePageModelList);
        categoryRecycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {
            return true;
        }else if (id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
