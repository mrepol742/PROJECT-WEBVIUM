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

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Resources;

public class EXCE extends BaseActivity {
    final String[] exceptionType = {
            "StringIndexOutOfBoundsException",
            "IndexOutOfBoundsException",
            "ArithmeticException",
            "NumberFormatException",
            "ActivityNotFoundException"
    };

    final String[] errMessage = {
            "Invalid string operation\n",
            "Invalid list operation\n",
            "Invalid arithmetical operation\n",
            "Invalid toNumber block operation\n",
            "Invalid intent operation"
    };

    @Override
    protected void onCreate(Bundle be) {
        theme(T_ASSISTANT);
        super.onCreate(be);
        Intent intent = getIntent();
        String madeErrMsg = "";
        if (intent != null) {
            String errMsg = intent.getStringExtra("error");
            String[] spilt = errMsg.split("\n");
            try {
                int le = exceptionType.length;
                for (int j = 0; j < le; j++) {
                    if (spilt[0].contains(exceptionType[j])) {
                        madeErrMsg = errMessage[j];
                        int addIndex = spilt[0].indexOf(exceptionType[j]) + exceptionType[j].length();
                        madeErrMsg += spilt[0].substring(addIndex);
                        break;
                    }
                }
                if(madeErrMsg.isEmpty()) madeErrMsg = errMsg;
            } catch (Exception e){
                e.printStackTrace();
            }
            a(madeErrMsg);
        } else {
            finish();
        }

    }

    private void a(String madeErrMsg) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        LayoutInflater d = getLayoutInflater();
        View e = d.inflate(R.layout.a1, null);
        bld.setView(e);
        TextView tv = e.findViewById(R.id.o30);
        TextView tv1 = e.findViewById(R.id.o31);
        Button bn = e.findViewById(R.id.o33);
        Button bn1 = e.findViewById(R.id.o32);
        tv.setTypeface(type(Typeface.BOLD));
        tv1.setTypeface(type(Typeface.NORMAL));
        bn.setTypeface(type(Typeface.NORMAL));
        bn1.setTypeface(type(Typeface.NORMAL));
        tv.setText(getString(R.string.y79));
        tv1.setText(madeErrMsg);
        tv.setTextColor(Resources.getColor(this, R.color.e));
        if (!a221().getBoolean("autoUpdate", false)) {
            tv1.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            tv1.setTextColor(Resources.getColor(this, R.color.b));
        }
        bn.setText(getString(R.string.y67));
        bn1.setText(getString(R.string.y66));
        bn1.setBackgroundResource(R.drawable.g4);
        bn.setBackgroundResource(R.drawable.c10);
        final AlertDialog dd = bld.create();
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "Test");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                EXCE.this.startActivity(Intent.createChooser(intent, "Send Email"));
                EXCE.this.finish();
            }
        });
        bn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EXCE.this.finish();
                dd.dismiss();
            }
        });
        dd.show();
    }
}
