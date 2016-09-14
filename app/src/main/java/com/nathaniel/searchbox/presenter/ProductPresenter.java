package com.nathaniel.searchbox.presenter;

import android.content.Context;

import com.nathaniel.searchbox.constant.AppConstant;
import com.nathaniel.searchbox.model.ProductModel;
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
public class ProductPresenter extends BasePresenter {

    public interface Callback<T> {
        void onSuccess(T t);

        void onFailure(Throwable t);
    }

    private ProductModel mProductModel;

    public ProductPresenter(Context context) {
        super(context);
        mProductModel = new ProductModel(getContext());
    }

    public void searchProduct(final String query, final int start, final Callback<List<Product>> callback) {
        Timber.d("Search product query:%s, start:%s", query, start);
        List<Product> productList = getSearchProductFromCache(query, start);
        if (productList.size() > 0) {
            callback.onSuccess(productList);
            Timber.d("Search product from database, size: %s", productList.size());
            return;
        }
        getSearchProductFromApi(query, start, callback);
    }

    private List<Product> getSearchProductFromCache(final String query, final int start) {
        List<Product> productList = mProductModel.getSearchProductList(query, start, AppConstant.ROWS);
        return productList;
    }

    private void getSearchProductFromApi(final String query, final int start, final Callback<List<Product>> callback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Observable<ProductsResponse> observable = apiService.searchProducts(query, start, AppConstant.ROWS);
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
                        insertProductToTable(productList);
                        Timber.d("Search product from API, size: %s", productList.size());
                        callback.onSuccess(productList);
                    }

                    /**
                     * Insert search product to table (cache)
                     * @param productList
                     */
                    private void insertProductToTable(List<Product> productList) {
                        mProductModel.insertSearchProduct(productList, query, start);
                    }
                });
    }
}