/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

import android.content.Context;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.app.main.MainJavascriptInterface;

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