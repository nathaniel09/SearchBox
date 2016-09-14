package com.nathaniel.searchbox.net;

import com.nathaniel.searchbox.constant.NetConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by USER on 9/13/2016.
 */
public class ApiClient {

    private static Retrofit sRetrofit = null;

    public static Retrofit getClient() {
        if (sRetrofit ==null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(NetConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
