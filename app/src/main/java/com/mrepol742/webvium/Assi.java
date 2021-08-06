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
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.util.Animation;
import com.mrepol742.webvium.app.SoftKeyboard;
import com.mrepol742.webvium.util.TextWatcher;

/*
 * @AssistantActivity
 */
public class Assi extends BaseActivity {
    private LinearLayout gjj;
    private EditText p;
    private ImageView iv;
    private ImageView iv1;
    private SearchHelper d2;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_ASSISTANT);
        super.onCreate(a);
        a225(R.layout.a2);
        gjj = findViewById(R.id.y);
        Toolbar o = findViewById(R.id.o);
        p = findViewById(R.id.p);
        iv = findViewById(R.id.m13);
        iv1 = findViewById(R.id.m19);
        d2 = SearchHelper.getInstance(getApplicationContext());
        TextView m11 = findViewById(R.id.m11);
        m11.setText(getString(R.string.l24));
        LinearLayout f2 = findViewById(R.id.g4);
        f2.setFitsSystemWindows(true);
        f2.setBackgroundResource(R.drawable.b17);
        f2.setClickable(true);
        int d = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.j);
        setActionBar(o);
        o.setElevation(5);
        p.setTextColor(d);
        p.setHintTextColor(f);
        o.setBackgroundResource(R.drawable.b22);
        p.setTypeface(type(Typeface.NORMAL));
        m11.setTypeface(type(Typeface.BOLD));
        gjj.setBackgroundResource(R.drawable.w);
        gjj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SoftKeyboard.hide(Assi.this, gjj);
            }
        });
        p.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                e();
            }
        });
        ActionBar ab = getActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        p.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String query = p.getText().toString();
                    if (TextUtils.isEmpty(query)) {
                        SoftKeyboard.hide(Assi.this, gjj);
                        Intents.e(Assi.this, "value", query, Webv.class);
                        d2.c(query);
                        Assi.this.finish();
                        handled = true;
                    }
                }
                return handled;
            }
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
            iv1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent a = new Intent(Assi.this, Voic.class);
                    startActivityForResult(a, 100);
                }
            });
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
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Assi.this.finishAndRemoveTask();
                }
            });
        } else {
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    p.getText().clear();
                }
            });
        }
    }
}
