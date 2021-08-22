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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.download.DownloadAdapter;
import com.mrepol742.webvium.download.DownloadDataModel;
import com.mrepol742.webvium.download.DownloadHelper;
import com.mrepol742.webvium.util.Animation;
import com.mrepol742.webvium.util.AwesomeToast;

import java.util.ArrayList;
import java.util.List;

/*
 * @DownloadActivity
 */
public class Down extends BaseActivity {

    private ListView a3;
    private final List<DownloadDataModel> al = new ArrayList<>();
    private RelativeLayout f2;
    private ImageView iv;
    private Sqlite sql;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.c3);
        Toolbar a1 = findViewById(R.id.b7);
        TextView a2 = findViewById(R.id.b8);
        iv = findViewById(R.id.l7);
        ImageView iv1 = findViewById(R.id.f3);
        setActionBar(a1);
        sql = Sqlite.getInstance(getApplicationContext());
        a1.setElevation(5);
        a1.setBackgroundResource(R.drawable.p);
        ActionBar ab = getActionBar();
        int f = Resources.getColor(this, R.color.c);
        int g = Resources.getColor(this, R.color.b);
        f2 = findViewById(R.id.l16);
        f2.setBackgroundResource(R.drawable.b17);
        f2.setClickable(true);
        TextView f4 = findViewById(R.id.l17);
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        if (a221().getBoolean("blocksv", false)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        f4.setCompoundDrawablesWithIntrinsicBounds(null, Resources.getDrawable(this, R.drawable.f9), null, null);
        f4.setCompoundDrawablePadding(40);
        f4.setText(getString(R.string.v22));
        if (!a221().getBoolean("autoUpdate", false)) {
            a2.setTextColor(f);
            f4.setTextColor(f);
        } else {
            a2.setTextColor(g);
            f4.setTextColor(g);
        }
        a2.setTypeface(type(Typeface.BOLD));
        f4.setTypeface(type(Typeface.BOLD));
        a1.setNavigationIcon(R.drawable.a2);
        a2.setText(getString(R.string.f));
        a1.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Down.this.finish();
            }
        });
        a3 = findViewById(R.id.a3);
        Cursor res = sql.getReadableDatabase().rawQuery("SELECT * FROM " +
                Sqlite.TABLE_DOWNLOAD +
                " ORDER BY " +
                "_id" +
                " DESC", null);
        if (res.getCount() == 0) {
            f2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.GONE);
        } else {
            while (res.moveToNext()) {
                al.add(new DownloadDataModel(res.getString(1),
                        res.getString(2),
                        res.getLong(3),
                        res.getLong(4)));
            }
            iv.setVisibility(View.VISIBLE);
            Animation.animate(Down.this, R.anim.i, iv);
        }
        res.close();
        iv.setBackgroundResource(R.drawable.c6);
        iv.setImageResource(R.drawable.a23);
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                i();
            }
        });
        iv.setVisibility(View.VISIBLE);
        iv1.setBackgroundResource(R.drawable.c6);
        iv1.setImageResource(R.drawable.c16);
        //  iv1.setOnClickListener(view -> c("",""));
        DownloadAdapter w12 = new DownloadAdapter(this, al);
        a3.setAdapter(w12);
    }

    private void i() {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.f));
        a.setMessage(getString(R.string.v24));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                sql.getWritableDatabase().delete(Sqlite.TABLE_SEARCH, null, null);
                Down.this.f(Down.this.getString(R.string.v23));
                f2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.GONE);
                iv.setVisibility(View.GONE);
                a12.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
            }
        });
        a.create().show();
    }

    private void f(String a) {
        AwesomeToast.b(this, a);
    }

}
