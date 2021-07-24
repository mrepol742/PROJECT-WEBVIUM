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

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.mrepol742.webvium.annotation.Keep;
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
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean c(Context a) {
        String sg = b(a);
        return sg != null && U3.b(sg);
    }

}

