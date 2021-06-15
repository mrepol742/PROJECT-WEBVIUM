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

package com.mrepol742.webvium.setting;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.widget.Toast;

public class ToolFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
if (Build.VERSION.SDK_INT >= 29) {
    a5(R.xml.a5);
} else {
    a5(R.xml.j);
    Preference u1 = findPreference("clearScreen");
    u1.setOnPreferenceClickListener(a -> {
        j();
        return true;
    });
}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a, String[] b, int[] c) {
        super.onRequestPermissionsResult(a, b, c);
        if (a == 2) {
            if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                f();
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    d(getString(R.string.u15));
                } else {
                    a(getString(R.string.u16));
                }
            }
        }
    }

    private void a(String jk) {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.s26));
        a.setMessage(Html.b(jk));
        a.setPositiveButton(getString(R.string.u14), (a12, intetg) -> {
            Intents.l(getActivity(), Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", Package.b(), null));
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void d(String a) {
        Toast.c(getActivity(), a);
    }

    private void j() {
        if (Permission.check(getActivity(), Permission.STORAGE, 2)) {
            f();
        }
    }

    private void g(String a) {
        Toast.b(getActivity(), a);
    }

    private void h(final String a) {
        Runnable p15 = () -> {
            try {
                Files.deleteAll(new java.io.File(a));
            } catch (Exception en) {
                en.printStackTrace();
            }
        };
        new Thread(p15).start();
    }

    private void f() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.h10));
        a.setMessage(getString(R.string.u9));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            h(StorageDirectory.getWebviumDir() + "/Screenshot/");
            g(getString(R.string.l16));

        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }
}