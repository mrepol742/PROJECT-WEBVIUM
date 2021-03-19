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

package com.droidmj.webvium.app;

import android.content.Context;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.app.main.MainJavascriptInterface;

public class JavascriptInterfaces implements MainJavascriptInterface {
    public Context a;

    public JavascriptInterfaces(Context c) {
        a = c;
    }

    @Keep
    private JavascriptInterfaces() {
    }

    @android.webkit.JavascriptInterface
    public void showToast(String c) {
        mainShowToast(a, c, 0, 0);
    }

    @android.webkit.JavascriptInterface
    public void showToastError(String c) {
        mainShowToast(a, c, 1, 0);
    }

    @android.webkit.JavascriptInterface
    public void showToastSuccess(String c) {
        mainShowToast(a, c, 2, 0);
    }

    @android.webkit.JavascriptInterface
    public void showToast(String c, int b) {
        mainShowToast(a, c, 0, b);
    }

    @android.webkit.JavascriptInterface
    public void showToastError(String c, int b) {
        mainShowToast(a, c, 1, b);
    }

    @android.webkit.JavascriptInterface
    public void showToastSuccess(String c, int b) {
        mainShowToast(a, c, 2, b);
    }

    @android.webkit.JavascriptInterface
    public void copyToClipboard(String c) {
        mainCopyToClipboard(a, c);
    }

    @android.webkit.JavascriptInterface
    public void vibrate(int c) {
        mainVibrate(a, c);
    }

    @android.webkit.JavascriptInterface
    public void enableWifi(boolean c) {
        mainEnableWifi(a, c);
    }


    @android.webkit.JavascriptInterface
    public void exit() {
        mainExit();
    }

    @android.webkit.JavascriptInterface
    public void showNotification(String b, String c, String d) {
        mainShowNotification(a, b, c, d);
    }

    @android.webkit.JavascriptInterface
    public void enableFlashlight(boolean c) {
        mainEnableFlashlight(a, c);
    }

}