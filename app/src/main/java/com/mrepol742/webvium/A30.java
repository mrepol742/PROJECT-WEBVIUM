/*
 *
 * Created by Melvin Jones Repol on 4/17/21 10:27 AM
 * Copyright (c) 2021 . All rights reserved. Melvin Jones Repol(mrepol742.github.io)
 * Last modified 4/17/21 10:26 AM
 *
 *  License under the GNU General Public License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *  https://www.gnu.org/licenses/gpl-3.0.en.html
 *  Unless required by the applicable law or agreed in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.mrepol742.webvium;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import com.mrepol742.webvium.annotation.Test;
import com.mrepol742.webvium.app.base.BaseActivity;

@Test
public class A30 extends BaseActivity {
    private static final int[] cl = {
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.DKGRAY,
            Color.GRAY,
            Color.GREEN,
            Color.LTGRAY,
            Color.MAGENTA,
            Color.RED,
            Color.YELLOW
    };

    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.test);
       /* Toolbar tb = findViewById(R.id.c3);
        TextView tv = findViewById(R.id.c4);
        TextView tv0 = findViewById(R.id.c5);
        tv.setText("Analytics");
        tv0.setText("BuildConfiguration ArrayList<TEST>");
        setActionBar(tb);
        int f = Resources.b(this,R.color.c);
        int g = Resources.b(this,R.color.b);
        tv.setTypeface(type(Typeface.BOLD));
        tv0.setTypeface(type(Typeface.BOLD));
        tb.setBackgroundResource(R.drawable.p);
        if (!a221().getBoolean("autoUpdate", false)) {
            tv.setTextColor(f); tv0.setTextColor(f);
        } else {
            tv.setTextColor(g); tv0.setTextColor(g);
        }
        tb.setNavigationIcon(R.drawable.a2);
        tb.setNavigationOnClickListener(view -> finish());
        ActionBar ab = getActionBar();
        if (ab!=null) {
            ab.setDisplayShowTitleEnabled(false);
        }
        LinearLayout fl = findViewById(R.id.test_005_a);

        Runnable re = () -> {
            ArrayList<String[]> al = new ArrayList<>();
            String sg = "ULeOVzsxZRJQWxSSlrbZzHYuPMXPmQVUOtxOrEyZcOqlQqQvUyJZVIRgkLHMqMHyUgBvRJFguvgxCVrGDpifxDxYATOegxZnkUvKzxxEXvZmPtMqZepuRsRxcbQbpsEiUeVEqJlRsnoScnCOzynRikOLMjVJTqdriSKcmUuMbibLsCeysdkJglSWAenSqzTRHCPCHchgscWvdBOIYVUsqCYhfqVYFOtcISLiTEMGxxAmMOCYBLoHYeklWffGNUfXANYBNnoiNABWzWXIozRIZqyWwQZgNvEPYyUtruRfpUBlDybGklQDuBNLfHEyVbtUefsYqFUgqJqrqvEFTCBDqJroGtlCdWKUdhULeOVzsxZRJQWxSSlrbZzHYuPMXPmQVUOtxOrEyZcOqlQqQvUyJZVIRgkLHMqMHyUgBvRJFguvgxCVrGDpifxDxYATOegxZnkUvKzxxEXvZmPtMqZepuRsRxcbQbpsEiUeVEqJlRsnoScnCOzynRikOLMjVJTqdriSKcmUuMbibLsCeysdkJglSWAenSqzTRHCPCHchgscWvdBOIYVUsqCYhfqVYFOtcISLiTEMGxxAmMOCYBLoHYeklWffGNUfXANYBNnoiNABWzWXIozRIZqyWwQZgNvEPYyUtruRfpUBlDybGklQDuBNLfHEyVbtUefsYqFUgqJqrqvEFTCBDqJroGtlCdWKUdhULeOVzsxZRJQWxSSlrbZzHYuPMXPmQVUOtxOrEyZcOqlQqQvUyJZVIRgkLHMqMHyUgBvRJFguvgxCVrGDpifxDxYATOegxZnkUvKzxxEXvZmPtMqZepuRsRxcbQbpsEiUeVEqJlRsnoScnCOzynRikOLMjVJTqdriSKcmUuMbibLsCeysdkJglSWAenSqzTRHCPCHchgscWvdBOIYVUsqCYhfqVYFOtcISLiTEMGxxAmMOCYBLoHYeklWffGNUfXANYBNnoiNABWzWXIozRIZqyWwQZgNvEPYyUtruRfpUBlDybGklQDuBNLfHEyVbtUefsYqFUgqJqrqvEFTCBDqJroGtlCdWKUdhULeOVzsxZRJQWxSSlrbZzHYuPMXPmQVUOtxOrEyZcOqlQqQvUyJZVIRgkLHMqMHyUgBvRJFguvgxCVrGDpifxDxYATOegxZnkUvKzxxEXvZmPtMqZepuRsRxcbQbpsEiUeVEqJlRsnoScnCOzynRikOLMjVJTqdriSKcmUuMbibLsCeysdkJglSWAenSqzTRHCPCHchgscWvdBOIYVUsqCYhfqVYFOtcISLiTEMGxxAmMOCYBLoHYeklWffGNUfXANYBNnoiNABWzWXIozRIZqyWwQZgNvEPYyUtruRfpUBlDybGklQDuBNLfHEyVbtUefsYqFUgqJqrqvEFTCBDqJroGtlCdWKUdhULeOVzsxZRJQWxSSlrbZzHYuPMXPmQVUOtxOrEyZcOqlQqQvUyJZVIRgkLHMqMHyUgBvRJFguvgxCVrGDpifxDxYATOegxZnkUvKzxxEXvZmPtMqZepuRsRxcbQbpsEiUeVEqJlRsnoScnCOzynRikOLMjVJTqdriSKcmUuMbibLsCeysdkJglSWAenSqzTRHCPCHchgscWvdBOIYVUsqCYhfqVYFOtcISLiTEMGxxAmMOCYBLoHYeklWffGNUfXANYBNnoiNABWzWXIozRIZqyWwQZgNvEPYyUtruRfpUBlDybGklQDuBNLfHEyVbtUefsYqFUgqJqrqvEFTCBDqJroGtlCdWKUdhULeOVzsxZRJQWxSSlrbZzHYuPMXPmQVUOtxOrEyZcOqlQqQvUyJZVIRgkLHMqMHyUgBvRJFguvgxCVrGDpifxDxYATOegxZnkUvKzxxEXvZmPtMqZepuRsRxcbQbpsEiUeVEqJlRsnoScnCOzynRikOLMjVJTqdriSKcmUuMbibLsCeysdkJglSWAenSqzTRHCPCHchgscWvdBOIYVUsqCYhfqVYFOtcISLiTEMGxxAmMOCYBLoHYeklWffGNUfXANYBNnoiNABWzWXIozRIZqyWwQZgNvEPYyUtruRfpUBlDybGklQDuBNLfHEyVbtUefsYqFUgqJqrqvEFTCBDqJroGtlCdWKUdh";
            char[] ch = sg.toCharArray();
            String sg0 = U8.c(ch, ch.length);
            char[] char2 = sg0.toCharArray();
            for (char temp : char2) {
                al.add(new String[]{String.valueOf(temp), String.valueOf(U8.b(sg, temp))});
            }
            Random rm = new Random();
            for (String[] it : al) {

                TextView tv1 = new TextView(A30.this);
                tv1.setTextIsSelectable(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Integer.parseInt(it[1])*50, 30);
                tv1.setLayoutParams(layoutParams);


                tv1.setBackground(Resources.f(GradientDrawable.RECTANGLE, new float[] { 0, 0, 50, 50, 50, 50, 0, 0 }, cl[rm.nextInt(cl.length)]));
tv1.setElevation(5);
tv1.setTypeface(type(Typeface.BOLD));

                    tv1.setTextColor(g);
                A30.this.runOnUiThread(() -> {
                    fl.addView(tv1);
                    tv1.setText(it[0]);
                });
            }

        };
        new Thread(re).start();
*/

    }


}
