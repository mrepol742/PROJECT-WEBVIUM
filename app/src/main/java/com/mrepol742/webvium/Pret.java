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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mrepol742.webvium.app.main.MainBaseActivity;
import com.mrepol742.webvium.app.main.MainWebView;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.security.SHA;
import com.mrepol742.webvium.util.AwesomeToast;
import com.mrepol742.webvium.util.WindowObscured;

/*
 * @PretendModeActivity
 */
public class Pret extends MainBaseActivity implements View.OnClickListener, View.OnLongClickListener {

    private static final String[] expre = {"-", "+", "/", "*", "."};
    private static final int[] ids = {
            R.id.n2,
            R.id.n3,
            R.id.n4,
            R.id.n5,
            R.id.n6,
            R.id.n7,
            R.id.n8,
            R.id.n9,
            R.id.n10,
            R.id.n11,
            R.id.n12,
            R.id.n13,
            R.id.n14,
            R.id.n15,
            R.id.n16,
            R.id.n17,
            R.id.n18,
            R.id.n19
    };
    private TextView tv;
    private MainWebView w4;

    @Override
    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility", "SourceLockedOrientationActivity"})
    protected void onCreate(Bundle be) {
        super.onCreate(be);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            a225(R.layout.b11);
        } else {
            a225(R.layout.b20);
        }
        tv = findViewById(R.id.n1);
        int f = Resources.getColor(this, R.color.c);
        int g = Resources.getColor(this, R.color.b);
        LinearLayout ll = findViewById(R.id.l10);
        tv.setTextColor(f);
        w4 = new MainWebView(this);
        if (!a221().getBoolean("autoUpdate", false)) {
            ll.setBackgroundColor(Resources.getColor(this, R.color.b));
        } else {
            ll.setBackgroundColor(Resources.getColor(this, R.color.n));
        }
        for (int i : ids) {
            TextView tv4 = findViewById(i);
            tv4.setTextColor(g);
            tv4.setTypeface(type(Typeface.BOLD));
            tv4.setText(n(i));
            if (i == R.id.n2) {
                tv4.setBackgroundResource(R.drawable.a27);
            } else if (i == R.id.n3) {
                tv4.setBackgroundResource(R.drawable.a29);
            } else {
                tv4.setBackgroundResource(R.drawable.a26);
            }
            tv4.setOnClickListener(this);
            tv4.setOnLongClickListener(this);
            tv4.setOnTouchListener(new WindowObscured());
        }
        w4.getSettings().setJavaScriptEnabled(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public boolean onKeyDown(int a5, KeyEvent b) {
        if (b.getAction() == KeyEvent.ACTION_DOWN && a5 == KeyEvent.KEYCODE_BACK) {
            System.exit(0);
            return true;
        }
        return false;
    }

    private void a(String sg) {
        AwesomeToast.a(this, sg);
        w4.evaluateJavascript("(function() { return eval(" + sg + "); })();", new ValueCallback<String>() {

            @Override
            public void onReceiveValue(String s) {
                Pret.this.b(s);
                tv.setText("s");
            }
        });
    }

    public void b(String url) {
        if (a221().getBoolean("ptm", false)) {
            SharedPreferences b = getSharedPreferences("a", 0);
            String c = b.getString("gsJsGsKSIgPes", "");
            if (c != null) {
                if (c.equals(SHA.a("SHA-512", url))) {
                    Intent it = new Intent(this, Webv.class);
                    startActivity(it);
                    finish();
                }
            }
        }
    }

    private String n(int i) {
        if (i == R.id.n2) {
            return getString(R.string.r23);
        } else if (i == R.id.n3) {
            return getString(R.string.r24);
        } else if (i == R.id.n4) {
            return getString(R.string.m11);
        } else if (i == R.id.n5) {
            return getString(R.string.m12);
        } else if (i == R.id.n6) {
            return getString(R.string.m13);
        } else if (i == R.id.n7) {
            return getString(R.string.r25);
        } else if (i == R.id.n8) {
            return getString(R.string.m14);
        } else if (i == R.id.n9) {
            return getString(R.string.m15);
        } else if (i == R.id.n10) {
            return getString(R.string.m16);
        } else if (i == R.id.n11) {
            return getString(R.string.r26);
        } else if (i == R.id.n12) {
            return getString(R.string.m17);
        } else if (i == R.id.n13) {
            return getString(R.string.m18);
        } else if (i == R.id.n14) {
            return getString(R.string.m19);
        } else if (i == R.id.n15) {
            return getString(R.string.r27);
        } else if (i == R.id.n16) {
            return getString(R.string.r29);
        } else if (i == R.id.n17) {
            return getString(R.string.m20);
        } else if (i == R.id.n18) {
            return getString(R.string.r30);
        }
        return getString(R.string.r28);
    }

    private String o(String sg) {
        if (sg.trim().isEmpty()) {
            return "";
        }
        return sg.substring(0, sg.length() - 1);
    }

    private String o(String sg, int i) {
        if (sg.trim().isEmpty()) {
            return "";
        }
        return sg.substring(0, sg.length() - i);
    }

    private boolean p(String sg4) {
        if (sg4.trim().isEmpty()) {
            return false;
        }
        for (String sg : expre) {
            if (sg4.endsWith(sg)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.n2) {
            String sg = tv.getText().toString();
            tv.setText(o(sg));
        } else if (id == R.id.n3) {
            tv.setText("");
        } else if (id == R.id.n4) {
            tv.append(getString(R.string.m11));
        } else if (id == R.id.n5) {
            tv.append(getString(R.string.m12));
        } else if (id == R.id.n6) {
            tv.append(getString(R.string.m13));
        } else if (id == R.id.n7) {
            if (p(tv.getText().toString())) {
                //   i +="/";
                tv.append("/");
            }
        } else if (id == R.id.n8) {
            tv.append(getString(R.string.m14));
        } else if (id == R.id.n9) {
            tv.append(getString(R.string.m15));
        } else if (id == R.id.n10) {
            tv.append(getString(R.string.m16));
        } else if (id == R.id.n11) {
            if (p(tv.getText().toString())) {
                tv.append("*");
            }
        } else if (id == R.id.n12) {
            tv.append(getString(R.string.m17));
        } else if (id == R.id.n13) {
            tv.append(getString(R.string.m18));
        } else if (id == R.id.n14) {
            tv.append(getString(R.string.m19));
        } else if (id == R.id.n15) {
            if (p(tv.getText().toString())) {
                tv.append(getString(R.string.r27));
            }
        } else if (id == R.id.n16) {
            if (p(tv.getText().toString())) {
                tv.append(getString(R.string.r29));
            }
        } else if (id == R.id.n17) {
            tv.append(getString(R.string.m20));
        } else if (id == R.id.n18) {
            String sg1 = tv.getText().toString();
            a(sg1);
            //    tv.setText(sgg);
        } else if (id == R.id.n19) {
            if (p(tv.getText().toString())) {
                tv.append(getString(R.string.r28));
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == R.id.n2) {
            String sg = tv.getText().toString();
            tv.setText(o(sg, 2));
            return true;
        } else if (id == R.id.n3) {
            tv.setText("");
            return true;
        } else if (id == R.id.n4) {
            tv.append(getString(R.string.m11));
            return true;
        } else if (id == R.id.n5) {
            tv.append(getString(R.string.m12));
            return true;
        } else if (id == R.id.n6) {
            tv.append(getString(R.string.m13));
            return true;
        } else if (id == R.id.n7) {
            if (p(tv.getText().toString())) {
                tv.append("/");
            }
            return true;
        } else if (id == R.id.n8) {
            tv.append(getString(R.string.m14));
            return true;
        } else if (id == R.id.n9) {
            tv.append(getString(R.string.m15));
            return true;
        } else if (id == R.id.n10) {
            tv.append(getString(R.string.m16));
            return true;
        } else if (id == R.id.n11) {
            if (p(tv.getText().toString())) {
                tv.append("*");
            }
            return true;
        } else if (id == R.id.n12) {
            tv.append(getString(R.string.m17));
            return true;
        } else if (id == R.id.n13) {
            tv.append(getString(R.string.m18));
            return true;
        } else if (id == R.id.n14) {
            tv.append(getString(R.string.m19));
            return true;
        } else if (id == R.id.n15) {
            if (p(tv.getText().toString())) {
                tv.append(getString(R.string.r27));
            }
            return true;
        } else if (id == R.id.n16) {
            if (p(tv.getText().toString())) {
                tv.append(getString(R.string.r29));
            }
            return true;
        } else if (id == R.id.n17) {
            tv.append(getString(R.string.m20));
            return true;
        } else if (id == R.id.n18) {
            tv.append(getString(R.string.r30));
            return true;
        } else if (id == R.id.n19) {
            if (p(tv.getText().toString())) {
                tv.append(getString(R.string.r28));
            }
            return true;
        }
        return false;
    }
}