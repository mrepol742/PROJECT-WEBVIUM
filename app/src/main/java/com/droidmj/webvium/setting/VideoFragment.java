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

package com.droidmj.webvium.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings;

import com.droidmj.webvium.R;
import com.droidmj.webvium.VIDE;
import com.droidmj.webvium.app.BuildConfiguration;
import com.droidmj.webvium.app.base.BasePreferenceFragment;
import com.droidmj.webvium.content.Intents;
import com.droidmj.webvium.telemetry.DiagnosticData;
import com.droidmj.webvium.util.cache.BitmapCache;
import com.droidmj.webvium.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
            DiagnosticData.a(ex);
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
                    OutputStream d5 = new FileOutputStream(getActivity().getFilesDir() + BuildConfiguration.Files.videoPoster);
                    byte[] e5 = new byte[1024];
                    int f5;
                    if (c5 != null) {
                        while ((f5 = c5.read(e5)) != -1) {
                            d5.write(e5, 0, f5);
                        }
                    }
                    c5.close();
                    d5.flush();
                    d5.close();
                    try {
                        File fe = new File(getActivity().getFilesDir() + BuildConfiguration.Files.videoPoster);
                        if (BuildConfiguration.Application.isDevelopment) {
                            if (fe.setReadOnly()) {
                                DiagnosticData.a("READ ONLY = " + fe.toString());
                            }
                        } else {
                            fe.setReadOnly();
                        }
                        Process p;
                        p = Runtime.getRuntime().exec("attrib +h " + fe.getPath());
                        p.waitFor();
                    } catch (Exception en) {
                        DiagnosticData.a(en);
                    }
                    BitmapCache.getInstance().b(getActivity().getFilesDir() + BuildConfiguration.Files.videoPoster);
                    getActivity().runOnUiThread(() -> Toast.b(getActivity(), getString(R.string.h21)));
                } catch (Exception en) {
                    DiagnosticData.a(en);
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