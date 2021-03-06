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
public class ProductSpecificationFragment extends Fragment {


    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    private RecyclerView productSpecificationRecycleView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_specification, container, false);

        productSpecificationRecycleView=view.findViewById(R.id.product_specifition_recycleview);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productSpecificationRecycleView.setLayoutManager(linearLayoutManager);

        List<ProductSpecificationModel> productSpecificationModelList=new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel(0,"Genaral"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));

        productSpecificationModelList.add(new ProductSpecificationModel(0,"Genaral"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"Display"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));






        ProductSpecificationAdapter productSpecificationAdapter=new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationAdapter.notifyDataSetChanged();
        return view;
    }

}
