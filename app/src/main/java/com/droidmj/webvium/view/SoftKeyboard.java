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

package com.droidmj.webvium.view;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.droidmj.webvium.annotation.release.Keep;

public class SoftKeyboard {
    @Keep
    private SoftKeyboard() {
    }

    public static void show(Context a, View b) {
        InputMethodManager c = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        c.showSoftInput(b, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hide(Context a, View b) {
        InputMethodManager c = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
        c.hideSoftInputFromWindow(b.getWindowToken(), 0);
    }
}