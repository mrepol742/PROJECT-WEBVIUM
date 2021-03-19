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

// ASSISTANT ACTIVITY


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BaseActivity;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.content.Resources;
import com.droidmj.webvium.search.SearchHelper;
import com.droidmj.webvium.text.TextWatcher;
import com.droidmj.webvium.util.U3;
import com.droidmj.webvium.view.Animation;
import com.droidmj.webvium.view.SoftKeyboard;

// @Class Assistant
public class ASSI extends BaseActivity {
    private LinearLayout gjj;
    private AutoCompleteTextView p;
    private ImageView iv;
    private ImageView iv1;
    private SearchHelper d2;

    @Override
    protected void onCreate(Bundle a) {
        theme(BuildConfiguration.Theme.ASSISTANT);
        super.onCreate(a);

        a225(R.layout.a2);


        gjj = findViewById(R.id.y);

        Toolbar o = findViewById(R.id.o);
        p = findViewById(R.id.p);
        iv = findViewById(R.id.m13);
        iv1 = findViewById(R.id.m19);
        d2 = SearchHelper.getInstance(getApplicationContext());
        TextView hau = findViewById(R.id.e7);
        TextView m11 = findViewById(R.id.m11);
        m11.setText(getString(R.string.l24));
        hau.setText("|");
        LinearLayout f2 = findViewById(R.id.g4);
        f2.setFitsSystemWindows(true);
        f2.setBackgroundResource(R.drawable.b17);
        f2.setClickable(true);
        int d = Resources.b(this, R.color.c);

        int f = Resources.b(this, R.color.j);
        setActionBar(o);
        o.setElevation(5);
        p.setTextColor(d);
        p.setHintTextColor(f);
        o.setBackgroundResource(R.drawable.b22);
        p.setTypeface(type(Typeface.NORMAL));
        m11.setTypeface(type(Typeface.BOLD));
        gjj.setBackgroundResource(R.drawable.w);
        gjj.setOnClickListener(view -> SoftKeyboard.hide(ASSI.this, gjj));
        p.addTextChangedListener(new TextWatcher() {


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                e();
            }


        });

        ActionBar ab = getActionBar();
        if (ab != null) {
            // ab.setDisplayHomeAsUpEnabled(false);
            // ab.setDisplayShowHomeEnabled(false);
            ab.setDisplayShowTitleEnabled(false);
        }
        p.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                SoftKeyboard.hide(ASSI.this, gjj);
                String query = p.getText().toString();
                if (U3.b(query)) {
                    Intents.e(ASSI.this, "value", query, MAIN.class);

                    d2.c(query);
                    finish();

                }
                handled = true;
            }
            return handled;
        });
        e();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SoftKeyboard.hide(this, gjj);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (a221().getBoolean("voice", true) && !spr()) {
            iv1.setImageResource(R.drawable.c9);
            iv1.setBackgroundResource(R.drawable.c6);
            iv1.setOnClickListener(view -> b());
            Animation.animate(this, R.anim.i, iv1);
            iv1.setVisibility(View.VISIBLE);
        } else {
            iv1.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK && null != data) {
                String speechText = data.getStringExtra("a");
                d2.c(speechText);
                p.setText(speechText);

            }
        }

    }

    private void e() {
        iv.setImageResource(R.drawable.a14);
        iv.setBackgroundResource(R.drawable.b17);
        if (p.getText().toString().length() == 0) {
            iv.setOnClickListener(view -> finishAndRemoveTask());
        } else {
            iv.setOnClickListener(view -> p.getText().clear());
        }
    }

    private void b() {

        Intent a = new Intent(this, VOIC.class);
        if (a.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(a, 100);
        }

    }
}
