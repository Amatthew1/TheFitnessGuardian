package com.ironstarfitness.thefitnessguarding;

/**
 * Created by Admin on 11/28/2016.
 */

public class Article {
    private String mTitle;
    private String mSection;
    private String mUri;


    public Article(String title, String section, String uri) {

        this.mTitle = title;
        this.mSection = section;
        this.mUri = uri;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getURI() {
        return mUri;
    }
}
