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

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Package;
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
        rl.setBackground(Resources.getDrawable(this, R.drawable.f11));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        SharedPreferences sharedPreferences = getSharedPreferences("di", 0);
        String name = Build.MODEL;
        try {
            ContentResolver cr=getContentResolver();
            Cursor cursor = cr.query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Profile.DISPLAY_NAME));
            }
            cursor.close();
        } catch (Exception en) {
            en.printStackTrace();
        }
        try {
            String sg = String.format(getString(R.string.y64),
                    Package.e(this),
                    sharedPreferences.getString("di", ""),
                    sharedPreferences.getString("di1", ""),
                    name);
            tv.setText(Html.b(sg));
        } catch (Exception en) {
            en.printStackTrace();
        }
        tv.setTypeface(type(Typeface.NORMAL));
    }
}