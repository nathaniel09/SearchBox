package com.nathaniel.searchbox.model.converter;

import android.content.ContentValues;
import android.database.Cursor;

import com.nathaniel.searchbox.database.table.SearchProductTable;
import com.nathaniel.searchbox.model.data.Product;

/**
 * Created by USER on 9/14/2016.
 */
public class ProductConverter {

    /**
     * Convert cursor to product
     * @param cursor
     * @return
     */
    public static Product convert(Cursor cursor) {
        Product product = new Product();
        product.setId(cursor.getLong(cursor.getColumnIndex(SearchProductTable.ID)));
        product.setImageUri(cursor.getString(cursor.getColumnIndex(SearchProductTable.IMAGE_URI)));
        product.setUri(cursor.getString(cursor.getColumnIndex(SearchProductTable.URI)));
        product.setName(cursor.getString(cursor.getColumnIndex(SearchProductTable.NAME)));
        product.setPrice(cursor.getString(cursor.getColumnIndex(SearchProductTable.PRICE)));
        return product;
    }

    /**
     * Convert product to content value
     * @param product
     * @param query
     * @param no
     * @return
     */
    public static ContentValues convertSearchProduct(Product product, String query, int no) {
        ContentValues values = new ContentValues();
        values.put(SearchProductTable.ID, product.getId());
        values.put(SearchProductTable.QUERY, query);
        values.put(SearchProductTable.NO, no);
        values.put(SearchProductTable.IMAGE_URI, product.getImageUri());
        values.put(SearchProductTable.URI, product.getUri());
        values.put(SearchProductTable.NAME, product.getName());
        values.put(SearchProductTable.PRICE, product.getPrice());
        return values;
    }
}