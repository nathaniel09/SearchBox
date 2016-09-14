package com.nathaniel.searchbox.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.nathaniel.searchbox.database.table.SearchProductTable;

/**
 * Created by USER on 9/14/2016.
 */
public class SearchBoxProvider extends ContentProvider {

    public static final String AUTHORITY = "com.nathaniel.searchbox.database.SearchBoxProvider";

    private static final int SEARCH_PRODUCT = 1;
    private static final int SEARCH_PRODUCT_ID = 2;

    private static final UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, SearchProductTable.TABLE_NAME, SEARCH_PRODUCT);
        sUriMatcher.addURI(AUTHORITY, SearchProductTable.TABLE_NAME + "/#", SEARCH_PRODUCT_ID);
    }

    private DatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType = sUriMatcher.match(uri);
        switch (uriType) {
            case SEARCH_PRODUCT_ID:
                break;
            case SEARCH_PRODUCT:
                queryBuilder.setTables(SearchProductTable.TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(mDatabaseHelper.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case SEARCH_PRODUCT:
                return SearchProductTable.CONTENT_TYPE;
            case SEARCH_PRODUCT_ID:
                return SearchProductTable.CONTENT_ITEM_TYPE;

        }
        return "";
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri resultUri;
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case SEARCH_PRODUCT:
                long id = sqLiteDatabase.insert(SearchProductTable.TABLE_NAME, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                resultUri = Uri.parse(SearchProductTable.CONTENT_URI + "/" + id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case SEARCH_PRODUCT:
                rowsDeleted = sqLiteDatabase.delete(SearchProductTable.TABLE_NAME, selection, selectionArgs);
                break;

            case SEARCH_PRODUCT_ID:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
