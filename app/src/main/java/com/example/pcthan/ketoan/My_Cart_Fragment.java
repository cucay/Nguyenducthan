package com.example.pcthan.ketoan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class My_Cart_Fragment extends Fragment {


    public My_Cart_Fragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my__cart_, container, false);

        cartItemsRecyclerView =view.findViewById(R.id.cart_items_recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);
        List<CartItemModel> cartItemModelList=new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.add,"SamSung S8",2,"49999","59999",1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.add,"SamSung S8",0,"49999","59999",1,1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.add,"SamSung S8",2,"49999","59999",1,2,0));
        cartItemModelList.add(new CartItemModel(1,"Tiền (3)","169999","Miễn Phí","169999","5999"));

        CartAdapter cartAdapter=new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        return view;
    }

}
