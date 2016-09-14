package com.nathaniel.searchbox.view.searchbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nathaniel.searchbox.R;
import com.nathaniel.searchbox.model.data.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by USER on 9/13/2016.
 */
public class SearchBoxAdapter extends RecyclerView.Adapter<SearchBoxAdapter.ViewHolder> {

    private List<Product> mProductList;

    public void addProductList(List<Product> productList) {
        if (mProductList == null) {
            mProductList = new ArrayList<>();
        }
        mProductList.addAll(productList);
    }

    public SearchBoxAdapter(List<Product> productList) {
        mProductList = productList;
    }

    @Override
    public int getItemCount() {
        if (mProductList == null) {
            return 0;
        }
        return mProductList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.mNameTextView.setText(product.getName());
        holder.mPriceTextView.setText(product.getPrice());
    }

    public void clearData() {
        mProductList = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_image_view)
        ImageView mProductImageView;

        @BindView(R.id.name_text_view)
        TextView mNameTextView;

        @BindView(R.id.price_text_view)
        TextView mPriceTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}