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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.mrepol742.webvium.R;
import com.mrepol742.webvium.VIDE;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.util.Log;
import com.mrepol742.webvium.util.cache.BitmapCache;
import com.mrepol742.webvium.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class VideoFragment extends BasePreferenceFragment {

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            a5(R.xml.h);
            Preference o = findPreference("captions");
            o.setOnPreferenceClickListener(a -> {
                Intents.k(getActivity(), Settings.ACTION_CAPTIONING_SETTINGS);

                return true;
            });

            Preference o5 = findPreference("shDCu");
            o5.setOnPreferenceClickListener(a -> {
                Intents.a(getActivity(), VIDE.class);
                return true;
            });
            Preference a4 = findPreference("cusP");
            a4.setOnPreferenceClickListener(a -> {
                b24();
                return true;
            });
        } catch (Exception ex) {
            Log.a(ex);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        if (requestCode == 7423 && resultCode == RESULT_OK && uri != null) {
            Runnable p15 = () -> {
                try {
                    InputStream c5 = getActivity().getContentResolver().openInputStream(uri);
                    OutputStream d5 = new FileOutputStream(StorageDirectory.getVideoPoster(getActivity()));
                    byte[] e5 = new byte[1024];
                    int f5;
                    if (c5 != null) {
                        while ((f5 = c5.read(e5)) != -1) {
                            d5.write(e5, 0, f5);
                        }
                    }
                    Objects.requireNonNull(c5).close();
                    d5.flush();
                    d5.close();
                    try {
                        File fe = new File(StorageDirectory.getVideoPoster(getActivity()));
                        fe.setReadOnly();
                        Process p;
                        p = Runtime.getRuntime().exec("attrib +h " + fe.getPath());
                        p.waitFor();
                    } catch (Exception en) {
                        Log.a(en);
                    }
                    BitmapCache.getInstance().b(StorageDirectory.getVideoPoster(getActivity()));
                    getActivity().runOnUiThread(() -> Toast.b(getActivity(), getString(R.string.h21)));
                } catch (Exception en) {
                    Log.a(en);
                    getActivity().runOnUiThread(() -> Toast.b(getActivity(), getString(R.string.p30)));
                }
            };
            new Thread(p15).start();
        } else {
            Toast.b(getActivity(), getString(R.string.p30));
        }
    }

    private void b24() {
        Intent d = new Intent(Intent.ACTION_GET_CONTENT);
        d.setType("image/*");
        d.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 7423);
    }
}