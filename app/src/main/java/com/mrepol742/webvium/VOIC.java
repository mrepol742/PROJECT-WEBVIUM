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

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

// @Class Voice
public class VOIC extends BaseActivity {
    public static SpeechRecognizer sr;
    public TextView tv;
    private ScrollView sv;

    @Override
    protected void onCreate(Bundle a) {
        theme(T_DEFAULT);
        super.onCreate(a);
        a225(R.layout.q);
        tv = findViewById(R.id.n21);
        int f = Resources.b(this, R.color.c);
        int g = Resources.b(this, R.color.b);
        sv = findViewById(R.id.n);
        RelativeLayout rl = findViewById(R.id.w);

        tv.setTypeface(type(Typeface.NORMAL));
        if (!a221().getBoolean("autoUpdate", false)) {
            tv.setTextColor(f);
            rl.setBackgroundColor(g);
            sv.setBackgroundColor(g);
        } else {
            tv.setTextColor(g);
            rl.setBackgroundColor(Resources.b(this, R.color.n));
            sv.setBackgroundColor(Resources.b(this, R.color.n));
        }
        if (spr()) {
            Toast.c(this, getString(R.string.n36));
            finish();
        } else {
            sr = SpeechRecognizer.createSpeechRecognizer(this);
            sr.setRecognitionListener(new RecognitionListener() {

                @Override
                public void onReadyForSpeech(Bundle bundle) {
                    ArrayList<String> sg = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (sg == null) {
                        return;
                    }
                    String sg0 = Objects.requireNonNull(sg).get(0);
                    tv.append(sg0);
                    tv.append("\n");
                }

                @Override
                public void onBeginningOfSpeech() {
                    tv.append(getString(R.string.n35));
                    tv.append("\n");
                    f();
                }

                @Override
                public void onRmsChanged(float v) {
                }

                @Override
                public void onBufferReceived(byte[] bytes) {
                }

                @Override
                public void onEndOfSpeech() {
                }

                @Override
                public void onError(int i) {
                    switch (i) {
                        case SpeechRecognizer.ERROR_AUDIO:
                            tv.append(getString(R.string.n26));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_CLIENT:
                            tv.append(getString(R.string.n27));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                            tv.append(getString(R.string.n28));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_NETWORK:
                            tv.append(getString(R.string.n29));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                            tv.append(getString(R.string.n30));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_NO_MATCH:
                            tv.append(getString(R.string.n31));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                            tv.append(getString(R.string.n32));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_SERVER:
                            tv.append(getString(R.string.n33));
                            tv.append("\n");
                            break;
                        case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                            tv.append(getString(R.string.n34));
                            tv.append("\n");
                            break;
                    }
                    f();
                }

                @Override
                public void onResults(Bundle bundle) {
                    ArrayList<String> sg = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (sg == null) {
                        return;
                    }
                    String sg0 = Objects.requireNonNull(sg).get(0);
                    tv.append(sg0);
                    tv.append("\n");
                    a(sg0);
                }

                @Override
                public void onPartialResults(Bundle bundle) {
                    ArrayList<String> sg = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (sg == null) {
                        return;
                    }
                    String sg0 = Objects.requireNonNull(sg).get(0);
                    tv.append(sg0);
                    tv.append("\n");
                }

                @Override
                public void onEvent(int i, Bundle bundle) {
                    ArrayList<String> sg = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if (sg == null) {
                        return;
                    }
                    String sg0 = Objects.requireNonNull(sg).get(0);
                    tv.append(sg0);
                    tv.append("\n");
                }
            });
            b();
            c();
        }
    }

    @Override
    protected void onDestroy() {
        if (sr != null) {
            sr.destroy();
        }
        super.onDestroy();
    }

    private void a(String sg) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("a", sg);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
        overridePendingTransition(R.anim.f, R.anim.b);
    }

    private void b() {
        Intent a5 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        a5.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        a5.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        sr.startListening(a5);
    }

    private void c() {
        Button bn5 = findViewById(R.id.n22);
        bn5.setText(R.string.n37);
        bn5.setAllCaps(true);
        bn5.setTypeface(type(Typeface.BOLD));
        bn5.setOnClickListener(view -> {
            if (Permission.check(VOIC.this, Permission.MICROPHONE, 1)) {
                if (spr()) {
                    b();
                }
            }
        });
    }

    private void e(String jk) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.s26));
        a.setMessage(Html.b(jk));
        a.setPositiveButton(getString(R.string.u14), (a12, intetg) -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", Package.b(), null);
            intent.setData(uri);
            startActivity(intent);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void f() {
        sv.post(() -> sv.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a, String[] b, int[] c) {
        super.onRequestPermissionsResult(a, b, c);
        if (a == 1) {
            if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                if (spr()) {
                    b();
                }
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                    tv.append(getString(R.string.j24));
                    tv.append("\n");
                } else {
                    e(getString(R.string.j25));
                }

            }
        }
    }
}