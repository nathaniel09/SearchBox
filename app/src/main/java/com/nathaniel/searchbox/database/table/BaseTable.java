package com.nathaniel.searchbox.database.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 9/14/2016.
 */
public abstract class BaseTable {

    public abstract void onCreate(SQLiteDatabase db);

    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
