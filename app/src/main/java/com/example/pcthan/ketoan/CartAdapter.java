package com.example.pcthan.ketoan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {
    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
                default:
                    return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case CartItemModel.CART_ITEM:
                View cartItemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout,viewGroup,false);
                return new CartItemViewholder(cartItemView);
                case CartItemModel.TOTAL_AMOUNT:
                    View cartTotalView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout,viewGroup,false);
                    return new CartTotalAmount(cartTotalView);
                    default:
                        return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                int resourcr=cartItemModelList.get(position).getProductImage();
                String title=cartItemModelList.get(position).getProdcutTitle();
                int freeCoupens=cartItemModelList.get(position).getFreeCoupens();
                String productPrice=cartItemModelList.get(position).getProductPrice();
                String cuttedPrice=cartItemModelList.get(position).getCuttedPrice();
                int offersApplied=cartItemModelList.get(position).getOffersApplied();


                ((CartItemViewholder)viewHolder).setItemDetails(resourcr,title,freeCoupens,productPrice,cuttedPrice,offersApplied);
                break;
                case CartItemModel.TOTAL_AMOUNT:
                    String totalItems= cartItemModelList.get(position).getTotalItems();
                    String totalItemPrice= cartItemModelList.get(position).getTotalItemPrice();
                    String deliveryPrice= cartItemModelList.get(position).getDeliveryPrice();
                    String totalAmount= cartItemModelList.get(position).getTotalAmount();
                    String savedAmount= cartItemModelList.get(position).getSaveAmount();
                    ((CartTotalAmount)viewHolder).setTotalAmout(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);
                    break;
                    default:
                        return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }
    class CartItemViewholder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private TextView productTitle;
        private ImageView freeCoupenIcon;
        private TextView freeCoupens;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offersApplied;
        private TextView coupensApplied;
        private TextView productQuantity;

        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productTitle=itemView.findViewById(R.id.product_title);
            freeCoupenIcon=itemView.findViewById(R.id.free_coupen_icon);
            freeCoupens=itemView.findViewById(R.id.tv_free_coupen);
            productPrice=itemView.findViewById(R.id.product_price);
            cuttedPrice=itemView.findViewById(R.id.cutted_price);
            offersApplied=itemView.findViewById(R.id.offers_applied);
            coupensApplied=itemView.findViewById(R.id.coupens_applied);
            productQuantity=itemView.findViewById(R.id.product_quantity);

        }
        private void setItemDetails(int resource, String title, int freeCoupensNo,String productPriceText,String cuttedPriceText,int offersAppliedNo){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCoupensNo > 0) {
                freeCoupenIcon.setVisibility(View.VISIBLE);
                freeCoupens.setVisibility(View.VISIBLE);
                if (freeCoupensNo ==1) {
                    freeCoupens.setText("Miễn phí" + freeCoupensNo + "Phiếu giảm giá");
                }else{
                    freeCoupens.setText("Miễn phí" + freeCoupensNo + "Phiếu giảm giá");
                }
            }else {
                freeCoupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);
            }
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);
            if(offersAppliedNo>0){
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo + "Mua hang");
            }else {
                offersApplied.setVisibility(View.INVISIBLE);
            }
        }
    }
    class CartTotalAmount extends RecyclerView.ViewHolder{
        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmout;
        private TextView savedAmout;


        public CartTotalAmount(@NonNull View itemView) {
            super(itemView);
            totalItems=itemView.findViewById(R.id.total_items);
            totalItemPrice=itemView.findViewById(R.id.total_items_price);
            deliveryPrice=itemView.findViewById(R.id.delivery_price);
            totalAmout=itemView.findViewById(R.id.total_price);
            savedAmout=itemView.findViewById(R.id.saved_amount);
        }
        private void setTotalAmout(String totalItemText,String totalItemPriceText,String deliveryPriceText,String totalAmoutText,String saveAmoutText){
            totalItems.setText(totalItemText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmout.setText(totalAmoutText);
            savedAmout.setText(saveAmoutText);

        }
    }
}
