package com.nathaniel.searchbox.net;

import com.nathaniel.searchbox.constant.NetConstant;
import com.nathaniel.searchbox.net.respond.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by USER on 9/13/2016.
 */
public interface ApiInterface {

    @GET(NetConstant.SEARCH_PRODUCT)
    Call<ProductsResponse> searchProducts(@Query("q") String query);
}
