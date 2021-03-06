/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain changedTo copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by the applicable law or agreed in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrepol742.webvium.app.main;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mrepol742.webvium.annotation.Keep;

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
    }

    @Override
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        super.setWebChromeClient(webChromeClient);
        this.webChromeClient = webChromeClient;
    }

    public WebViewClient getFirstClient() {
        return this.webViewClient;
    }

    public WebChromeClient getSecondClient() {
        return this.webChromeClient;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public int getTextZoom() {
        return textZoom;
    }

    public String getTextEncoding() {
        return textEncoding;
    }
}