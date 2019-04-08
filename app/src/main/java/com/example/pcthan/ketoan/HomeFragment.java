package com.example.pcthan.ketoan;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Tainted;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    private RecyclerView categoryRecycleView;
    private Category_Adapter category_adapter;
    private   RecyclerView testing;
    private List<Category_Model> category_modelList;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecycleView=view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecycleView.setLayoutManager(layoutManager);

       category_modelList=new ArrayList<Category_Model>();

        category_adapter=new Category_Adapter(category_modelList);
        categoryRecycleView.setAdapter(category_adapter);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        category_modelList.add(new Category_Model(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString()));
                    }
                    category_adapter.notifyDataSetChanged();
                }else{
                    String err=task.getException().getMessage();
                    Toast.makeText(getContext(),err,Toast.LENGTH_SHORT).show();
                }
            }
        });

        ///Banner slide
            List<SliderModel> sliderModelList=new ArrayList<SliderModel>();
            sliderModelList.add(new SliderModel(R.mipmap.banner,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner1,"#077AE4"));

            sliderModelList.add(new SliderModel(R.mipmap.banner2,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner2,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner4,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner5,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner6,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner7,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner8,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner9,"#077AE4"));

            sliderModelList.add(new SliderModel(R.mipmap.banner5,"#077AE4"));
            sliderModelList.add(new SliderModel(R.mipmap.banner8,"#077AE4"));

        ///end banner

        //horizontalproduct

        List<HorizontalProductScrollModel> horizontalProductScrollModelList=new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ssa8,"SamSung A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.soni,"Sony","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.iphone10,"Iphone 10","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.iphone7,"Iphone 7","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.bphone,"Bphone A8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ssj7,"SamSung J8","Ram 4GB","6000000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ssa9,"SamSung A9","Ram 4GB","6000000"));

        //horizontalproduct

        //testing

        testing=view.findViewById(R.id.home_page_recycleview);
        LinearLayoutManager testingLayoutManager=new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList=new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Sản phẩm bán chạy",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Sản phẩm phố biến",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#000000"));
      //  homePageModelList.add(new HomePageModel(2,"Ban chay nhat",horizontalProductScrollModelList));
        //homePageModelList.add(new HomePageModel(3,"Ban chay nhat",horizontalProductScrollModelList));
     //   homePageModelList.add(new HomePageModel(1,R.drawable.banner,"#ffff00"));
      //  homePageModelList.add(new HomePageModel(0,sliderModelList));

        HomePageAdapter adapter=new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}
