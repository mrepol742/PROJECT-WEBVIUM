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
import android.content.pm.PackageManager;
import android.webkit.JavascriptInterface;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.content.Package;

public class FeedJSI {
    private Context context;

    public FeedJSI(Context context) {
        this.context = context;
    }

    @Keep
    private FeedJSI() {
    }

    @JavascriptInterface
    public String appKey() {
        return context.getString(R.string.firebase_apiKey);
    }

    @JavascriptInterface
    public String authDomain() {
        return context.getString(R.string.firebase_authDomain);
    }

    @JavascriptInterface
    public String projectId() {
        return context.getString(R.string.firebase_projectId);
    }

    @JavascriptInterface
    public String storageBucket() {
        return context.getString(R.string.firebase_storageBucket);
    }

    @JavascriptInterface
    public String messagingSenderId() {
        return context.getString(R.string.firebase_measurementId);
    }

    @JavascriptInterface
    public String appId() {
        return context.getString(R.string.firebase_appId);
    }

    @JavascriptInterface
    public String measurementId() {
        return context.getString(R.string.firebase_measurementId);
    }

    @JavascriptInterface
    public String getWebviumVersion() throws PackageManager.NameNotFoundException {
        return Package.c() + " v-" + Package.e(context) + " c-" + Long.toString(Package.f(context));
    }

    @JavascriptInterface
    public String getMessage() {
        return context.getString(R.string.firebase_measurementId);
    }

    @JavascriptInterface
    public String getLog() {
        return context.getString(R.string.firebase_measurementId);
    }

    @JavascriptInterface
    public void succeed(boolean bn) {

    }
}
