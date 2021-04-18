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

package com.mrepol742.webvium;

import android.content.Context;
import android.os.Build;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;

import com.mrepol742.webvium.annotation.release.Keep;

// @Class PrefCategory
public class PREF0 extends PreferenceCategory {
    @Keep
    public PREF0(Context context) {
        super(context);
        a();
    }

    @Keep
    public PREF0(Context context, AttributeSet attrs) {
        super(context, attrs);
        a();
    }

    @Keep
    public PREF0(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        a();
    }

    protected void a() {
        if (Build.VERSION.SDK_INT < 26) {
            setIcon(R.drawable.t);
        } else {
            setIconSpaceReserved(true);
        }
    }

}
