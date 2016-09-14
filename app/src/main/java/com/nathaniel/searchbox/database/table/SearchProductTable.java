package com.nathaniel.searchbox.database.table;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.nathaniel.searchbox.database.SearchBoxProvider;

/**
 * Created by USER on 9/14/2016.
 */
public class SearchProductTable extends BaseTable {

    public static final String TABLE_NAME = "search_product";

    public static final Uri CONTENT_URI = Uri.parse("content://" + SearchBoxProvider.AUTHORITY + "/" + TABLE_NAME);

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;

    public static final String ID = "id";
    public static final String NO = "no";
    public static final String QUERY = "query";
    public static final String IMAGE_URI = "image_uri";
    public static final String URI = "uri";
    public static final String NAME = "name";
    public static final String PRICE = "price";


    private static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INT NOT NULL, " +
                    NO + " INT NOT NULL, " +
                    QUERY + " INT NOT NULL, " +
                    IMAGE_URI + " TEXT NOT NULL, " +
                    URI + " TEXT NOT NULL, " +
                    NAME + " TEXT NOT NULL, " +
                    PRICE + " TEXT NOT NULL" +
                    ");";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
