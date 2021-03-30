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
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.text.Html;

// @Class EULA
public class EULA extends BaseActivity {

    private TextView d;
    private TextView e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        theme(T_DEFAULT);
        super.onCreate(savedInstanceState);

        a225(R.layout.o);
        Toolbar c = findViewById(R.id.b7);
        d = findViewById(R.id.b8);
        e = findViewById(R.id.u);
        d.setTypeface(type(Typeface.BOLD));
        e.setTypeface(type(Typeface.NORMAL));
        setActionBar(c);
        c.setElevation(5);
        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(true);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        c.setNavigationIcon(R.drawable.a14);
        if (!a221().getBoolean("autoUpdate", false)) {
            d.setTextColor(Resources.b(this, R.color.c));
            e.setTextColor(Resources.b(this, R.color.c));
            findViewById(R.id.l11).setBackgroundColor(Resources.b(this, R.color.p));
        } else {
            d.setTextColor(Resources.b(this, R.color.b));
            e.setTextColor(Resources.b(this, R.color.b));
            findViewById(R.id.l11).setBackgroundColor(Resources.b(this, R.color.m));
        }
        c.setBackgroundResource(R.drawable.p);
        c.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent a) {
        try {
            String val = a.getStringExtra("a");
            if (val != null) {
                if (val.equals("a")) {
                    d.setText(getString(R.string.f12));
                    e.setText(Html.b(getString(R.string.n23)));
                } else {
                    d.setText(getString(R.string.f13));
                    e.setText(Html.b(getString(R.string.n24)));
                }
                a.removeExtra("a");
            } else {
                finish();
            }
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
        }
    }
}