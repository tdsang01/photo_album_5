package com.framgia.photoalbum.data.model;

/**
 * Created by SANG-TRAN on 16/01/2017.
 */
public class EditItem {
    private String mTitleItem;
    private int mImageItem;

    public EditItem(String titleItem, int imageItem) {
        mTitleItem = titleItem;
        mImageItem = imageItem;
    }

    public String getTitleItem() {
        return mTitleItem;
    }

    public void setTitleItem(String titleItem) {
        mTitleItem = titleItem;
    }

    public int getImageItem() {
        return mImageItem;
    }

    public void setImageItem(int imageItem) {
        mImageItem = imageItem;
    }
}
