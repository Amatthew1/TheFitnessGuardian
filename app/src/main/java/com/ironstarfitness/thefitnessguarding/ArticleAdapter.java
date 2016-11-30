package com.ironstarfitness.thefitnessguarding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 11/28/2016.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {


    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate((R.layout.article_listing), parent, false);
        }
        final Article currentArticle = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        String titleText = currentArticle.getTitle();
        titleTextView.setText(titleText);

        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);
        String sectionText = currentArticle.getSection();
        sectionTextView.setText(sectionText);

        return listItemView;
    }
}



