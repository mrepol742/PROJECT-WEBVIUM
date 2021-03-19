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

package com.droidmj.webvium.text;

import android.os.Build;
import android.text.Spanned;
import android.widget.TextView;

import com.droidmj.webvium.annotation.release.Keep;

@SuppressWarnings("deprecation")
public class Html {
    @Keep
    private Html() {
    }

    public static void a(TextView tv, String sg) {
        if (Build.VERSION.SDK_INT >= 24) {
            tv.setText(android.text.Html.fromHtml(sg, android.text.Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv.setText(android.text.Html.fromHtml(sg));
        }
    }

    public static Spanned b(String sg) {
        if (Build.VERSION.SDK_INT >= 24) {
            return android.text.Html.fromHtml(sg, android.text.Html.FROM_HTML_MODE_LEGACY);
        }
        return android.text.Html.fromHtml(sg);
    }
}