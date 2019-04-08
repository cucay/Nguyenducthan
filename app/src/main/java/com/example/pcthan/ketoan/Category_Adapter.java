package com.example.pcthan.ketoan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {
    private List<Category_Model> categoryModelList;

    public Category_Adapter(List<Category_Model> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override   
    public void onBindViewHolder(@NonNull Category_Adapter.ViewHolder viewHolder, int position) {
        String icon=categoryModelList.get(position).getCategory_icon_link();
        String name=categoryModelList.get(position).getCategoryName();
        viewHolder.setCategory(name,position);
        viewHolder.setCategoryIcon(icon);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon=itemView.findViewById(R.id.category_icon);
            categoryName=itemView.findViewById(R.id.category_name);

        }
        private void setCategoryIcon(String iconUrl) {
            if (!iconUrl.equals("null")) {
                Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.mipmap.profile)).into(categoryIcon);
            }
        }
        private void setCategory(final String name, final int position){
            categoryName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position !=0 ) {
                        Intent catagoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                        catagoryIntent.putExtra("CategoryName", name);
                        itemView.getContext().startActivity(catagoryIntent);
                    }
                }
            });
        }
    }
}
