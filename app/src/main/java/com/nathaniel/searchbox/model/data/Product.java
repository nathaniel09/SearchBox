package com.nathaniel.searchbox.model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 9/13/2016.
 */
public class Product {

    @SerializedName("id")
    private long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("uri")
    private String mUri;

    @SerializedName("image_uri")
    private String mImageUri;

    @SerializedName("price")
    private String mPrice;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public String getImageUri() {
        return mImageUri;
    }

    public void setImageUri(String imageUri) {
        mImageUri = imageUri;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }
}
