package com.nathaniel.searchbox.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nathaniel.searchbox.database.table.SearchProductTable;
import com.nathaniel.searchbox.model.converter.ProductConverter;
import com.nathaniel.searchbox.model.data.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 9/14/2016.
 */
public class ProductModel extends BaseModel {

    public ProductModel(Context context) {
        super(context);
    }

    public List<Product> getSearchProductList(String query, int start, int row) {
        List<Product> productList = new ArrayList<>();
        Cursor cursor = getContext().getContentResolver().query(SearchProductTable.CONTENT_URI, null,
                SearchProductTable.QUERY + " = ? AND " + SearchProductTable.NO + " >= ?",
                new String[]{query, String.valueOf(start)},
                SearchProductTable.NO + " ASC LIMIT " + row);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                productList.add(ProductConverter.convert(cursor));
            } while (cursor.moveToNext());
        }
        return productList;
    }

    public void insertSearchProduct(List<Product> productList, String query, int start) {
        int i = 0;
        for (Product product : productList) {
            ContentValues contentValues = ProductConverter.convertSearchProduct(product, query, i + start);
            getContext().getContentResolver().insert(SearchProductTable.CONTENT_URI, contentValues);
            i++;
        }
    }

    public void deleteAllSearchProduct() {
        getContext().getContentResolver().delete(SearchProductTable.CONTENT_URI, null, null);
    }
}
