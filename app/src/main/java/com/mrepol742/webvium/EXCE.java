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
import android.os.Bundle;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.util.Log;

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
        String errMsg = "";
        String madeErrMsg = "";
        if (intent != null) {
            errMsg = intent.getStringExtra("error");
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
                Log.a(e);
            }
        }
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setTitle(getString(R.string.y65));
        bld.setMessage(madeErrMsg);
        String temp = madeErrMsg;
        bld.setPositiveButton(getString(R.string.y67), (dialog, which) -> {
            Intent it = new Intent(this, FEED.class);
            it.putExtra("webvium", temp);
            startActivity(it);
            dialog.dismiss();
        });
        bld.setNeutralButton(getString(R.string.y66), (dialog, which) -> {
            finish();
            dialog.dismiss();
        });
        bld.create().show();
    }
}
