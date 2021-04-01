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

package com.droidmj.webvium.content;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.telemetry.DiagnosticData;

public class Intents {

    public static final String ACTION_INVALIDATE = "com.droidmj.webvium.intent.action.INVALIDATE";
    public static final String CATEGORY_GENIUS = "com.droidmj.webvium.intent.category.GENIUS";
    public static final String ACTION_LAUNCH = "com.droidmj.webvium.intent.action.LAUNCH";
    public static final String ACTION_PASTE_SEARCH = "com.droidmj.webvium.action.PASTE_SEARCH";
    public static final String ACTION_PASTE = "com.droidmj.webvium.action.PASTE";

    @Keep
    private Intents() {

    }

    public static void a(Context a, Class<?> b) {
        try {
            Intent c = new Intent(a, b);
            c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(c);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static void b(Context a, Class<?> b) {
        try {
            Intent c = new Intent(a, b);
            a.startService(c);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static boolean c(Context a, String b) {
        try {
            Intent c = a.getPackageManager().getLaunchIntentForPackage(b);
            if (c == null) {
                return false;
            }
            c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (check(a, c)) {
                a.startActivity(c);
                return true;
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
        return false;
    }

    public static void d(String b, String c, Activity e) {
        try {
            Intent d = new Intent();
            d.putExtra(b, c);

            e.setResult(Activity.RESULT_OK, d);

        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static void e(Context a, String b, String c, Class<?> e) {
        try {
            Intent d = new Intent(a, e);
            d.putExtra(b, c);
            d.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(d);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static void e(Context a, String b, int c, Class<?> e) {
        try {
            Intent d = new Intent(a, e);
            d.putExtra(b, c);
            d.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(d);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static void f(Activity b, Class<?> c, int d) {
        try {
            Intent e = new Intent(b, c);
            b.startActivityForResult(e, d);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static boolean g(Context a, String b, String c) {
        try {
            Intent d = new Intent(Intent.ACTION_MAIN);
            d.setClassName(b, c);
            d.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (check(a, d)) {
                a.startActivity(d);
                return true;
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
        return false;
    }

    public static void h(Activity b, Class<?> c, int d, String h, String i) {
        try {
            Intent e = new Intent(b, c);
            e.putExtra(h, i);
            b.startActivityForResult(e, d);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static void i(Context a, Class<?> b) {
        try {
            Intent c = new Intent(a, b);
            c.setPackage(Package.b());
            a.stopService(c);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static boolean j(Context a, String b) {
        try {
            Intent c = new Intent(b);
            c.setPackage(Package.b());
            if (check(a, c)) {
                a.startService(c);
                return true;
            }
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
        return false;
    }

    public static void k(Context a, String b) {
        try {
            Intent c = new Intent(b);

            c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(c);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static void l(Context a, String action, Uri data) {
        try {
            Intent c = new Intent();
            c.setAction(action);
            c.setData(data);
            a.startActivity(c);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }

    public static boolean check(Context a, Intent c) {
        return c.resolveActivity(a.getPackageManager()) != null;
    }
}




























