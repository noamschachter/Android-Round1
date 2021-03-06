package com.il.tech.hqtask;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Noam Schachter on 3/22/2016.
 */
public class WebViewClientEX extends WebViewClient {

    private ProgressDialogHelper m_ProgressDialogHelper;
    private Context m_Context;
    private boolean m_IsLoadingFromCache = false;
    private String m_url;

    public WebViewClientEX(Context context, String url, boolean isLoadingFromCache) {
        m_Context = context;
        m_url = url;
        m_IsLoadingFromCache = isLoadingFromCache;
        m_ProgressDialogHelper = new ProgressDialogHelper(context);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (!m_IsLoadingFromCache)
            m_ProgressDialogHelper.showProgressDialog(m_Context.getString(R.string.loading));
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        m_ProgressDialogHelper.hideProgressDialog();
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (view.getSettings().getCacheMode() == WebSettings.LOAD_CACHE_ONLY && m_url.equals(view.getUrl())) {
            view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            view.loadUrl(m_url);
            m_IsLoadingFromCache = false;
            return;
        }
        super.onReceivedError(view, request, error);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
