package com.recyclerv;

public class ExampleItem {
    private String mImageResource;
    private String mText1;
    private int mText2;

    public ExampleItem(String imageResource, String text1, int text2) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public String  getImageResource() {
        return mImageResource;
    }
    public String getText1() {
        return mText1;
    }
    public int getText2() {
        return mText2;
    }
}
