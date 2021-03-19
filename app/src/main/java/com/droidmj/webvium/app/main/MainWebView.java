/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium.app.main;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.droidmj.webvium.annotation.Default;
import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class MainWebView extends WebView {

    private int fontSize;
    private String userAgent;
    private String textEncoding;
    private int textZoom;
    private WebViewClient webViewClient;
    private WebChromeClient webChromeClient;

    @Keep
    public MainWebView(Context ct) {
        super(ct);
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a(ct + " Initialize");
        }
        WebSettings ws = getSettings();
        fontSize = ws.getDefaultFontSize();
        userAgent = ws.getUserAgentString();
        textEncoding = ws.getDefaultTextEncodingName();
        textZoom = ws.getTextZoom();
    }

    @Keep
    public MainWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Keep
    public MainWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        this.webViewClient = webViewClient;
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("WebViewClient was been set");
        }
    }

    @Override
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        super.setWebChromeClient(webChromeClient);
        this.webChromeClient = webChromeClient;
        if (BuildConfiguration.Application.isDevelopment) {
            DiagnosticData.a("WebChromeClient was been set");
        }
    }

    public WebViewClient getFirstClient() {
        return this.webViewClient;
    }

    public WebChromeClient getSecondClient() {
        return this.webChromeClient;
    }

    @Default
    public int getFontSize() {
        return fontSize;
    }

    @Default
    public String getUserAgent() {
        return userAgent;
    }

    @Default
    public int getTextZoom() {
        return textZoom;
    }

    @Default
    public String getTextEncoding() {
        return textEncoding;
    }
}