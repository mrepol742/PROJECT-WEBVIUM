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

package com.mrepol742.webvium.app.main;

import android.annotation.TargetApi;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mrepol742.webvium.annotation.release.Keep;

public class MainWebViewClient extends WebViewClient {

    @Keep
    public MainWebViewClient() {
    }

    @Override
    public void onReceivedError(WebView a, int b, String c, String d) {
        receivedError(b, c, d, false, false);
    }

    @Override
    @TargetApi(23)
    public void onReceivedError(WebView a, WebResourceRequest b, WebResourceError c) {
        receivedError(c.getErrorCode(), b.getUrl().toString(),
                c.getDescription().toString(), b.isForMainFrame(), b.hasGesture());
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView a, String b) {
        return url(a, b);
    }

    @Override
    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView a, WebResourceRequest b) {
        if (!b.hasGesture()) {
            return false;
        }
        return url(a, b.getUrl().toString());
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest url5) {
        WebResourceResponse wrs = r(url5);
        if (wrs == null) {
            return super.shouldInterceptRequest(view, url5);
        }
        return wrs;
    }

    public void receivedError(int b, String c, String d, boolean bn, boolean bn1) {
    }

    //on page load
    public boolean url(WebView a, String b) {
        return false;
    }


    public WebResourceResponse r(WebResourceRequest wr) {
        return null;
    }
/*
private WebResourceResponse getImageWebResource(InputStream data) {
    return new WebResourceResponse("image/jpg", "UTF-8", data);
}
@Nullable
@Override
public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
    if(url.contains("google")||url.contains("facebook")){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture_to_load);
ByteArrayOutputStream stream = new ByteArrayOutputStream();
bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
byte[] imageInByte = stream.toByteArray();
ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
return getImageWebResource(bis);
}
    return super.shouldInterceptRequest(view, url);
}
 */
}