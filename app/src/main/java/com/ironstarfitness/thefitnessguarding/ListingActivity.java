package com.ironstarfitness.thefitnessguarding;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity implements LoaderCallbacks<List<Article>> {

    private static final int ARTICLE_LOADER_ID = 1;

    private ArticleAdapter mAdapter;
    private TextView mEmptyStateView;
    private String FITNESS_GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?q=fitness&api-key=test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ListView articleListView = (ListView) findViewById(R.id.article_view);
        mEmptyStateView = (TextView) findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateView);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.load_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateView.setText(R.string.no_internet);
        }
        mAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        articleListView.setAdapter(mAdapter);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Article currentArticle = mAdapter.getItem(position);
                Uri webUri =Uri.parse(currentArticle.getURI());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, webUri);
                startActivity(websiteIntent);

            }
        });


    }


    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle bundle) {
        return new ArticleLoader(this, FITNESS_GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        mAdapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        View loadingIndicator = findViewById(R.id.load_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateView.setText(R.string.no_articles);
        mAdapter.clear();
        if (articles != null && !articles.isEmpty()) {
            mAdapter.addAll(articles);
        }
    }

    public void refresh(View view){
        Intent articleListing = new Intent(ListingActivity.this, ListingActivity.class);
        startActivity(articleListing);
    }
}
