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

package com.mrepol742.webvium.content;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.util.U3;

public class Clipboard {
    @Keep
    private Clipboard() {
    }

    public static void a(Context a, String b) {
        try {
            ClipboardManager c = (ClipboardManager) a.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData d = ClipData.newPlainText("text", b);
            c.setPrimaryClip(d);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static String b(Context a) {
        try {
            ClipboardManager b = (ClipboardManager) a.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData c = b.getPrimaryClip();
            ClipData.Item d = null;
            if (c != null) {
                d = c.getItemAt(0);
            }
            if (d != null) {
                return d.getText().toString();
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
        return null;
    }

    public static boolean c(Context a) {
        String sg = b(a);
        return sg != null && U3.b(sg);
    }

}

