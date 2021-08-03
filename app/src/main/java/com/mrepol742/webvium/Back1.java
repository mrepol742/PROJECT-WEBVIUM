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

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Permission;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.AwesomeToast;

/*
 * @BackupActivity
 */
public class Back1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle be) {
        super.onCreate(be);
        if (Permission.check(this, Permission.STORAGE, 1)) {
            Intents.b(this, Back.class);
            finish();
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a, String[] b, int[] c) {
        super.onRequestPermissionsResult(a, b, c);
        if (a == 1) {
            if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                Intents.b(this, Back.class);
                finish();
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AwesomeToast.c(this, getString(R.string.u15));
                    finish();
                } else {
                    c53(getString(R.string.u16));
                }
            }
        }
    }

    private void c53(String jk) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.s26));
        a.setMessage(Html.b(jk));
        a.setPositiveButton(getString(R.string.u14), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", Package.b(), null);
                intent.setData(uri);
                Back1.this.startActivity(intent);
                a12.dismiss();
                Back1.this.finish();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
                Back1.this.finish();
            }
        });
        a.create().show();
    }
}
