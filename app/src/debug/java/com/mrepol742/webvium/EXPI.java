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
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.download.DownloadHelper;

public class EXPI extends BaseActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle be) {
        theme(T_DEFAULT);
        super.onCreate(be);
        ScrollView scrollView = new ScrollView(this);
        tv = new TextView(this);
        scrollView.addView(tv);
        scrollView.setFitsSystemWindows(true);
        setContentView(scrollView);
        tv.setTypeface(type(Typeface.NORMAL));
        tv.setTextIsSelectable(true);
        tv.setPadding(10, 0, 10, 0);
        DownloadHelper dh = DownloadHelper.getInstance(this);
        for (int i = 0; i < 25; i++)
        dh.c("File " + 1, "https://something", "25KB");
        append("append ");
    }

    public void append(String sg) {
        tv.append(sg + "\n\n");
    }

}
