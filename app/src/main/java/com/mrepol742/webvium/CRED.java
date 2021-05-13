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

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.text.Html;

// @Class Credits
public class CRED extends BaseActivity {

    @Override
    protected void onCreate(Bundle a) {
        theme(T_WELCOME_SCREEN);
        super.onCreate(a);
        a225(R.layout.c20);
        TextView tv = findViewById(R.id.c10);
        ScrollView rl = findViewById(R.id.c3);
        tv.setTextColor(Resources.getColor(this, R.color.c));
        rl.setBackgroundResource(R.drawable.f10);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        if (Build.VERSION.SDK_INT >= 23) {
            if (!a221().getBoolean("autoUpdate", false)) {
                if (!a221().getBoolean("webviumB", false)) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            }
        }
        tv.setText(Html.b(getString(R.string.y64)));
        tv.setTypeface(type(Typeface.NORMAL));
    }
}