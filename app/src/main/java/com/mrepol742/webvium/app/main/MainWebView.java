/*
 *
 * Created by Melvin Jones Repol on 4/17/21 10:27 AM
 * Copyright (c) 2021 . All rights reserved. Melvin Jones Repol(mrepol742.github.io)
 * Last modified 4/17/21 10:26 AM
 *
 *  License under the GNU General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *  https://www.gnu.org/licenses/gpl-3.0.en.html
 *  Unless required by the applicable law or agreed in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.mrepol742.webvium.app.main;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mrepol742.webvium.annotation.Default;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.BuildConfiguration;
import com.mrepol742.webvium.telemetry.DiagnosticData;

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
        if (BuildConfiguration.isDevelopment) {
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
        if (BuildConfiguration.isDevelopment) {
            DiagnosticData.a("WebViewClient was been set");
        }
    }

    @Override
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        super.setWebChromeClient(webChromeClient);
        this.webChromeClient = webChromeClient;
        if (BuildConfiguration.isDevelopment) {
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