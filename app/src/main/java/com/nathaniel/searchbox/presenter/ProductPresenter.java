package com.nathaniel.searchbox.presenter;

import com.nathaniel.searchbox.model.data.Product;
import com.nathaniel.searchbox.net.ApiClient;
import com.nathaniel.searchbox.net.ApiInterface;
import com.nathaniel.searchbox.net.respond.ProductsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by USER on 9/13/2016.
 */
public class ProductPresenter {

    public interface Callback<T> {
        void onSuccess(T t);

        void onFailure(Throwable t);
    }

    public void searchProduct(String query, final Callback<List<Product>> callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductsResponse> call = apiService.searchProducts(query);
        call.enqueue(new retrofit2.Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse>call, Response<ProductsResponse> response) {
                List<Product> productList = response.body().getProductList();
                Timber.d("productList %s", productList.size());
                callback.onSuccess(productList);
            }

            @Override
            public void onFailure(Call<ProductsResponse>call, Throwable t) {
                Timber.d(t.toString());
                callback.onFailure(t);
            }
        });
    }
}