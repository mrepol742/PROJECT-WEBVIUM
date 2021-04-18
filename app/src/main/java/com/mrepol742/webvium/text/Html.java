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

package com.mrepol742.webvium.text;

import android.os.Build;
import android.text.Spanned;
import android.widget.TextView;

import com.mrepol742.webvium.annotation.release.Keep;

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