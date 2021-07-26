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

package com.mrepol742.webvium.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mrepol742.webvium.annotation.Keep;

public class Intents {

    public static final String ACTION_INVALIDATE = "com.mrepol742.webvium.intent.action.INVALIDATE";
    public static final String ACTION_IP = "com.mrepol742.webvium.intent.action.IP";
    public static final String ACTION_LAUNCH = "com.mrepol742.webvium.intent.action.LAUNCH";
    public static final String ACTION_PASTE_SEARCH = "com.mrepol742.webvium.action.PASTE_SEARCH";
    public static final String ACTION_PASTE = "com.mrepol742.webvium.action.PASTE";
    public static final String CATEGORY_GENIUS = "com.mrepol742.webvium.intent.category.GENIUS";

    @Keep
    private Intents() {

    }

    public static void a(Context a, Class<?> b) {
        try {
            Intent c = new Intent(a, b);
            c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void b(Context a, Class<?> b) {
        try {
            Intent c = new Intent(a, b);
            a.startService(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void d(String b, String c, Activity e) {
        try {
            Intent d = new Intent();
            d.putExtra(b, c);
            e.setResult(Activity.RESULT_OK, d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void e(Context a, String b, String c, Class<?> e) {
        try {
            Intent d = new Intent(a, e);
            d.putExtra(b, c);
            d.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void e(Context a, String b, int c, Class<?> e) {
        try {
            Intent d = new Intent(a, e);
            d.putExtra(b, c);
            d.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void f(Activity b, Class<?> c, int d) {
        try {
            Intent e = new Intent(b, c);
            b.startActivityForResult(e, d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void h(Activity b, Class<?> c, int d, String h, String i) {
        try {
            Intent e = new Intent(b, c);
            e.putExtra(h, i);
            b.startActivityForResult(e, d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void i(Context a, Class<?> b) {
        try {
            Intent c = new Intent(a, b);
            c.setPackage(Package.b());
            a.stopService(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void k(Context a, String b) {
        try {
            Intent c = new Intent(b);
            c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.startActivity(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void l(Context a, String action, Uri data) {
        try {
            Intent c = new Intent();
            c.setAction(action);
            c.setData(data);
            a.startActivity(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}