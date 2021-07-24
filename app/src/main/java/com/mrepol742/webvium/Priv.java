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

package com.mrepol742.webvium;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.util.Html;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * @PrivacyPolicyActivity
 */
public class Priv extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        theme(T_DEFAULT);
        super.onCreate(savedInstanceState);

        a225(R.layout.o);
        Toolbar c = findViewById(R.id.b7);
        TextView d = findViewById(R.id.b8);
        TextView e = findViewById(R.id.u);
        TextView tv = findViewById(R.id.l1);
        d.setTypeface(type(Typeface.BOLD));
        e.setTypeface(type(Typeface.NORMAL));
        tv.setTypeface(type(Typeface.NORMAL));
        setActionBar(c);
        c.setElevation(5);
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        c.setNavigationIcon(R.drawable.a14);
        if (!a221().getBoolean("autoUpdate", false)) {
            d.setTextColor(Resources.getColor(this, R.color.c));
            e.setTextColor(Resources.getColor(this, R.color.c));
            tv.setTextColor(Resources.getColor(this, R.color.c));
            findViewById(R.id.l11).setBackgroundColor(Resources.getColor(this, R.color.p));
        } else {
            d.setTextColor(Resources.getColor(this, R.color.b));
            e.setTextColor(Resources.getColor(this, R.color.b));
            tv.setTextColor(Resources.getColor(this, R.color.b));
            findViewById(R.id.l11).setBackgroundColor(Resources.getColor(this, R.color.m));
        }
        c.setBackgroundResource(R.drawable.p);
        c.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Priv.this.finish();
            }
        });
        d.setText(getString(R.string.x44));
        e.setText(Html.b(getString(R.string.n24)));
        SharedPreferences sp = getSharedPreferences("ag233", 0);
        long data = sp.getLong("ag466", 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy | hh:mm:ss", Locale.US);
        String newDate = simpleDateFormat.format(new Date(data));
        tv.setText(String.format(getString(R.string.f13), newDate));
    }
}