package com.nathaniel.searchbox.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nathaniel.searchbox.database.table.BaseTable;
import com.nathaniel.searchbox.database.table.SearchProductTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by USER on 9/14/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SearchBox";
    private static final int DATABASE_VERSION = 1;

    private static final List<BaseTable> TABLE_LIST = Collections.unmodifiableList(
            new ArrayList<BaseTable>() {{
                add(new SearchProductTable());
            }});

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(BaseTable table: TABLE_LIST) {
            table.onCreate(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(BaseTable table: TABLE_LIST) {
            table.onUpgrade(db, oldVersion, newVersion);
        }
    }
}
