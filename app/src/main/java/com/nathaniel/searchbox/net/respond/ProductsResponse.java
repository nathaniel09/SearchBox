package com.nathaniel.searchbox.net.respond;

import com.google.gson.annotations.SerializedName;
import com.nathaniel.searchbox.model.data.Product;

import java.util.List;

/**
 * Created by USER on 9/13/2016.
 */
public class ProductsResponse {

    @SerializedName("data")
    private List<Product> mProductList;

    public List<Product> getProductList() {
        return mProductList;
    }
}
