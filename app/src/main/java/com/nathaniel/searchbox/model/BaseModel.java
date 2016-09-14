package com.nathaniel.searchbox.model;

import android.content.Context;

/**
 * Created by USER on 9/14/2016.
 */
public class BaseModel {

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    BaseModel(Context context) {
        mContext = context;
    }
}
