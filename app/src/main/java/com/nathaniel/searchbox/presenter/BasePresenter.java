package com.nathaniel.searchbox.presenter;

import android.content.Context;

/**
 * Created by USER on 9/14/2016.
 */
public class BasePresenter {

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    BasePresenter(Context context) {
        mContext = context;
    }
}
