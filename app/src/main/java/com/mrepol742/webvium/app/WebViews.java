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

package com.mrepol742.webvium.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.mrepol742.webvium.annotation.Development;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.telemetry.DiagnosticData;

import java.util.ArrayList;

public class WebViews extends MainWebView {
    @Development
    public final ArrayList<ForwardBackwardHistoryDataModel> w4 = new ArrayList<>();

    @Keep
    public WebViews(Context ct) {
        super(ct);
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a("WebView Initialize");
        }
    }

    @Keep
    public WebViews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Keep
    public WebViews(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if (visibility != View.GONE) super.onWindowVisibilityChanged(View.VISIBLE);
    }

    @Override
    @Development
    public void goBack() {
        w4.add(new ForwardBackwardHistoryDataModel("", getUrl()));
        super.goBack();
    }

    @Override
    @Development
    public void goForward() {
        w4.add(new ForwardBackwardHistoryDataModel(getUrl(), ""));
        super.goForward();
    }

    public void load(String baseUrl, String html) {
        loadDataWithBaseURL("system checked", html, "text/html", getTextEncoding(), baseUrl);
    }
}