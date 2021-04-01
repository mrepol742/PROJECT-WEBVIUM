/*
 *
 *
 *
 * DROID MJ Property || Confidential
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.droidmj.webvium;

import android.app.ActionBar;
import android.app.AlertDialog;
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

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.download.DownloadAdapter;
import com.droidmj.webvium.download.DownloadArrayDataModel;
import com.droidmj.webvium.download.DownloadDatabase;
import com.droidmj.webvium.download.DownloadHelper;
import com.droidmj.webvium.view.Animation;
import com.droidmj.webvium.widget.Toast;

import java.util.ArrayList;

// @Class Downloads
public class DOWN extends BaseActivity {

    private ListView a3;
    private ArrayList<DownloadArrayDataModel> downloadArrayDataModel;
    private RelativeLayout f2;
    private ImageView iv;
    private DownloadHelper d10;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.c3);
        Toolbar a1 = findViewById(R.id.b7);
        TextView a2 = findViewById(R.id.b8);
        setActionBar(a1);

        d10 = DownloadHelper.getInstance(getApplicationContext());
        a1.setElevation(5);
        a1.setBackgroundResource(R.drawable.p);
        ActionBar ab = getActionBar();
        int f = Resources.b(this, R.color.c);
        int g = Resources.b(this, R.color.b);
        f2 = findViewById(R.id.l16);
        f2.setBackgroundResource(R.drawable.b17);
        f2.setClickable(true);
        TextView f4 = findViewById(R.id.l17);
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        if (a221().getBoolean("blockSV", true)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        f4.setCompoundDrawablesWithIntrinsicBounds(null, Resources.a(this, R.drawable.f9), null, null);
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
        a1.setNavigationOnClickListener(view -> finish());
        a3 = findViewById(R.id.a3);
        Cursor res = d10.getReadableDatabase().rawQuery("SELECT * FROM " +
                DownloadDatabase.TABLE_DOWNLOAD +
                " ORDER BY " +
                BuildConfiguration.DB_ID +
                " DESC", null);
        if (res.getCount() == 0) {
            runOnUiThread(() -> {
                f2.setVisibility(View.VISIBLE);
                a3.setVisibility(View.GONE);
            });
        } else {
            downloadArrayDataModel = new ArrayList<>();
            while (res.moveToNext()) {
                downloadArrayDataModel.add(new DownloadArrayDataModel(res.getString(1),
                        res.getString(2),
                        res.getInt(3),
                        res.getString(4),
                        res.getString(5)));
            }
            runOnUiThread(() -> {
                iv.setVisibility(View.VISIBLE);
                Animation.animate(this, R.anim.i, iv);
            });
        }
        res.close();
        iv = findViewById(R.id.l7);
        iv.setBackgroundResource(R.drawable.c6);
        iv.setImageResource(R.drawable.a23);
        iv.setOnClickListener(view -> i());
        iv.setVisibility(View.VISIBLE);
        ImageView iv1 = findViewById(R.id.f3);
        iv1.setBackgroundResource(R.drawable.c6);
        iv1.setImageResource(R.drawable.c16);
        //  iv1.setOnClickListener(view -> c("",""));
        if (downloadArrayDataModel != null) {
            DownloadAdapter w12 = new DownloadAdapter(this, downloadArrayDataModel);
            a3.setAdapter(w12);
        }
    }

    private void i() {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);


        a.setCancelable(true);
        a.setTitle(getString(R.string.f));
        a.setMessage(getString(R.string.v24));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            d10.delete();
            f(getString(R.string.v23));
            f2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void f(String a) {
        Toast.b(this, a);

    }

}
