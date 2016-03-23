package com.il.tech.hqtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Noam Schachter on 3/22/2016.
 */
public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    private WebView m_WebView;
    private Data m_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_WebView = new WebView(this);
        setContentView(m_WebView);
        setTitle(getIntent().getStringExtra(Constants.TITLE));
        m_Data = (Data) getIntent().getSerializableExtra(Constants.DATA);

        setupWebView();
        String modifiedUrl = getModifiedUrl(m_Data.getUrl());
        m_WebView.setWebViewClient(new WebViewClientEX(this, modifiedUrl, m_Data.isCache()));
        m_WebView.loadUrl(modifiedUrl);
    }

    private String getModifiedUrl(String url) {
        url = url.replace(Constants.USER_ID_KEY, Constants.USER_ID_VALUE);
        url = url.replace(Constants.APP_SECRET_KEY, Constants.APP_SECRET_VALUE);
        url = url.replace(Constants.CURRENCY_CODE_KEY, Constants.CURRENCY_CODE_VALUE);
        url = url.replace(Constants.OFFER_ID_KEY, Constants.OFFER_ID_VALUE);
        url = url.replace(Constants.SELECTED_VOUCHERS_KEY, Constants.SELECTED_VOUCHERS_VALUE);
        return url;
    }

    private void setupWebView() {
            m_WebView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
            m_WebView.getSettings().setAllowFileAccess(true);
            m_WebView.getSettings().setAppCacheEnabled(true);
            m_WebView.getSettings().setJavaScriptEnabled(true);
            m_WebView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );

        if(m_Data.isCache())
            m_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
