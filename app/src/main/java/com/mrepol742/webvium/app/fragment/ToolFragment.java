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

package com.mrepol742.webvium.app.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.util.FileUtil;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.app.Permissions;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.AwesomeToast;

public class ToolFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        if (Build.VERSION.SDK_INT >= 29) {
            a5(R.xml.a5);
        } else {
            a5(R.xml.j);
            Preference u1 = findPreference("clearScreen");
            u1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    if (Permissions.check(getActivity(), Permissions.STORAGE, 2)) {
                        f();
                    }
                    return true;
                }
            });
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a1, String[] b, int[] c) {
        super.onRequestPermissionsResult(a1, b, c);
        if (a1 == 2) {
            if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                f();
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AwesomeToast.c(getActivity(), getString(R.string.u15));
                } else {
                    AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
                    a.setCancelable(true);
                    a.setTitle(getString(R.string.s26));
                    a.setMessage(Html.b(getString(R.string.u16)));
                    a.setPositiveButton(getString(R.string.u14), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            Intents.l(ToolFragment.this.getActivity(), Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", Package.b(), null));
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
            }
        }
    }

    private void f() {
        AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.h10));
        a.setMessage(getString(R.string.u9));
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                Runnable p15 = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            FileUtil.deleteAll(new java.io.File(StorageDirectory.getWebviumDir() + "/Screenshot/"));
                        } catch (Exception en) {
                            en.printStackTrace();
                        }
                    }
                };
                new Thread(p15).start();
                AwesomeToast.b(getActivity(), getString(R.string.l16));
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
}