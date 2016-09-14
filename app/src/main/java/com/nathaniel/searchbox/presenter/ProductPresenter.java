package com.nathaniel.searchbox.presenter;

import com.nathaniel.searchbox.constant.AppConstant;
import com.nathaniel.searchbox.model.data.Product;
import com.nathaniel.searchbox.net.ApiClient;
import com.nathaniel.searchbox.net.ApiInterface;
import com.nathaniel.searchbox.net.respond.ProductsResponse;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by USER on 9/13/2016.
 */
public class ProductPresenter {

    public interface Callback<T> {
        void onSuccess(T t);

        void onFailure(Throwable t);
    }

    public void searchProduct(String query, int start, final Callback<List<Product>> callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<ProductsResponse> observable = apiService.searchProducts(query, AppConstant.ROWS, start);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ProductsResponse>() {
                    @Override
                    public void onCompleted() {
                        // handle completed
                    }

                    @Override
                    public void onError(Throwable t) {
                        Timber.d(t.toString());
                        callback.onFailure(t);
                    }

                    @Override
                    public void onNext(ProductsResponse productsResponse) {
                        List<Product> productList = productsResponse.getProductList();
                        Timber.d("productList %s", productList.size());
                        callback.onSuccess(productList);
                    }
                });
    }
}