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

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrepol742.webvium.A.AppName;
import com.mrepol742.webvium.A.AppNameChecker;
import com.mrepol742.webvium.A.CheckerSequence;
import com.mrepol742.webvium.A.MainSecurity;
import com.mrepol742.webvium.A.PackageName;
import com.mrepol742.webvium.A.PackageNameChecker;
import com.mrepol742.webvium.A.Security.Responder;
import com.mrepol742.webvium.A.Signature;
import com.mrepol742.webvium.A.SignatureChecker;
import com.mrepol742.webvium.annotation.Development;
import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Stream;
import com.mrepol742.webvium.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

@Development
public class EXPI extends BaseActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle be) {
        theme(T_DEFAULT);
        super.onCreate(be);
        int k5 = getSharedPreferences("ddnrr2", 0).getInt("noid", 0);
        if (k5 != 275) {
            Intent it = new Intent(this, WELC.class);
            it.putExtra("welc", true);
            startActivity(it);
            overridePendingTransition(R.anim.f, R.anim.b);
            finish();
            Toast.b(this, "Initiating Webvium...");
        }
        ScrollView scrollView = new ScrollView(this);
        tv = new TextView(this);
        scrollView.addView(tv);
        setContentView(scrollView);
        tv.setTypeface(type(Typeface.NORMAL));
        tv.setTextIsSelectable(true);
        tv.setPadding(10, 0, 10, 0);
       //TODO: this line is meeant only for testing
        // and to be deleted upon building
       append("CurrentThreadName: " + Thread.currentThread().getName());
       try {
           MainSecurity mainSecurity = new MainSecurity(this);
           mainSecurity.addListener(new Responder() {
               @Override
               public boolean isValid(boolean bn) {
                   if (!bn) {
                       append("The Webvium installed on your phone was invalid.");
                   } else {
                       append("The Webvium installed on your phone is original");
                   }
                   return bn;
               }
           });
       } catch (Exception en) {
           append(Log.getStackTraceString(en));
       }
       try {
           ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
           Bundle bundle = ai.metaData;
           append("API_KEY: " + bundle.getString("com.mrepol742.webvium.FEED.API_KEY"));
           append("AUTH_DOMAIN: " + bundle.getString("com.mrepol742.webvium.FEED.AUTH_DOMAIN"));
           append("PROJECT_ID: " + bundle.getString("com.mrepol742.webvium.FEED.PROJECT_ID"));
           append("STORAGE_BUCKET: " + bundle.getString("com.mrepol742.webvium.FEED.STORAGE_BUCKET"));
           append("MESSAGING_SENDER_ID: " + bundle.getString("com.mrepol742.webvium.FEED.MESSAGING_SENDER_ID"));
           append("APP_ID: " + bundle.getString("com.mrepol742.webvium.FEED.APP_ID"));
           append("MEASUREMENT_ID: " + bundle.getString("com.mrepol742.webvium.FEED.MEASUREMENT_ID"));
           // rules
//?a=AIzaSyAmuYgXpDNti7SXnq_T3yNqJvVP1dJDo_8&b=mrepol-e2ed6.firebaseapp.com&c=mrepol-e2ed6&d=&d=mrepol-e2ed6.appspot.com&e=541029821490&f=1:541029821490:web:3713defdb0f0d8a939e02b&g=G-YYV1BV578J&h=Test%20Mj&i=SubjectMj

       } catch (Exception en) {
           append(Log.getStackTraceString(en));
       }

       if (Build.VERSION.SDK_INT >= 29 ) {
            write();
        }
       Runnable runnable1 = () -> {
           try {
               String name = Thread.currentThread().getName();
               runOnUiThread(()-> append("CurrentThreadName:" + name));
               SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
               int b = Integer.parseInt(Package.e(this).replaceAll("\\.", ""));
               int newUpdate = Stream.i(Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "")) + "?raw=true");
               if (newUpdate > b) {
                   runOnUiThread(()-> append("UpdateService: Pass"));
               }
               runOnUiThread(()-> append("UpdateService: Data parsed is " + newUpdate));
           } catch (Exception w) {
               runOnUiThread(()-> append(Log.getStackTraceString(w)));
           }
           try {
               SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
                   String neTf = Stream.f(Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_DATA, "")) + "?raw=true", getString(R.string.c33));
                   String[] sp = neTf.split(";");
                   SharedPreferences j988 = getSharedPreferences("wv,", 0);
                   if (!Objects.requireNonNull(j988.getString("notif1", "")).equals(sp[0]) && !Objects.requireNonNull(j988.getString("notif2", "")).equals(sp[1])) {
                       runOnUiThread(()-> {
                           append("NotificationService: Data parsed wasn't found on sharedpreferences.");
                           append("NotificationService:\nTitle: " + sp[0] + "\nContent: " + sp[1] + "\nLink: " + sp[2]);
                       });
                       SharedPreferences.Editor gujh = j988.edit();
                       gujh.putString("notif1", sp[0]);
                       gujh.putString("notif2", sp[1]);
                       gujh.apply();
                   } else {
                       runOnUiThread(()-> {
                           append("NotificationService: Data parsed was found on sharedpreferences.");
                           append("NotificationService:\nTitle: " + j988.getString("notif1", "") + "\nContent: " + j988.getString("notif2", ""));
                       });
                   }
           } catch (Exception en) {
               runOnUiThread(()->  tv.append(Log.getStackTraceString(en)));
           }
       };
       new Thread(runnable1).start();
       try {
            SharedPreferences sharedPreferences = getSharedPreferences("b", 0);
            append("SharedPreference: \nUpdate URL: " + sharedPreferences.getString(WELC.TEMP_UPDATE_URL, "") +
                    "\nDecoded Update URL: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_URL, "")) +
                    "\n\nNotification Data: " + sharedPreferences.getString(WELC.TEMP_NOTIFICATION_DATA, "") +
                    "\nDecoded Notification Data: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_NOTIFICATION_DATA, "")) +
                    "\n\nUpdate Version: " + sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "") +
                    "\nDecoded Update Version: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_UPDATE_VERSION, "")) +
                    "\n\nWebvium Search: " + sharedPreferences.getString(WELC.TEMP_SEARCH, "") +
                    "\nDecoded Webvium Search: " + Base64.decode(sharedPreferences.getString(WELC.TEMP_SEARCH, "")));
        } catch (Exception en) {
            append(Log.getStackTraceString(en));
        }

        Runnable runnable2 = () -> {
            String name = Thread.currentThread().getName();
            runOnUiThread(()-> append("CurrentThreadName:" + name));
            try {
                String sg1 = dt();
                try {
                    int t = 10;
                    String sg = Package.b();
                    String sg0 = Base64.decode("L2Rhd  GEvYXB   wLyUx      JHMvYm FzZS5hcGs");
                    String pc = sg + "-";
                    for (int i = 0; i < t; i++) {
                        String sg2 = pc + i;
                        java.io.File fe = new java.io.File(String.format(sg0, sg2));
                        runOnUiThread(()-> append("BackupService: Data parse is " + String.format(sg0, sg2)));
                        if (fe.exists()) {
                            FileInputStream fos = new FileInputStream(fe.toString());
                            OutputStream d = new FileOutputStream(sg1);
                            byte[] e = new byte[1024];
                            int f;
                            while ((f = fos.read(e)) != -1) {
                                d.write(e, 0, f);
                            }
                            d.flush();
                            d.close();
                            fos.close();
                            runOnUiThread(() -> append("BackupService: Pass"));
                            break;
                        }
                    }
                } catch (Exception e) {
                    runOnUiThread(()-> append(Log.getStackTraceString(e)));
                }

            } catch (Exception e) {
                runOnUiThread(()-> append(Log.getStackTraceString(e)));
            }

        };
        new Thread(runnable2).start();
        try {
            Clipboard.a(this, tv.getText().toString());
        } catch (Exception en) {
            Toast.c(this, "Failed to copy to clipboard");
        }
    }

    @TargetApi(Build.VERSION_CODES.Q)
    private void write() {
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, "webvium_test.txt");
            values.put(MediaStore.MediaColumns.MIME_TYPE, "text/plain");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/Webvium");
            Uri uri = getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
            OutputStream outputStream = getContentResolver().openOutputStream(uri);
            outputStream.write("Testing write".getBytes());
            outputStream.close();
            append("Write success on DIRECTORY_DOCUMENTS/Webvium/webvium_test.txt with data \"Testing write\"");
        } catch (Exception e) {
            append(Log.getStackTraceString(e));
        }
    }


    private String dt() throws PackageManager.NameNotFoundException {
        if (!Permission.checkOnly(this, Permission.STORAGE)) {
            Files.createNewFolder(getFilesDir() + "/Backup");
            Files.createNewFolder(getFilesDir() + "/Backup/Application");
            return getFilesDir() + "/Backup/Application/Base_" + Package.e(this) + ".apk";
        }
        h();
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Application");
        return StorageDirectory.getWebviumDir() + "/Backup/Application/Base_" + Package.e(this) + ".apk";
    }

    private void h() {
        Runnable runnable = () -> {
            try {
                String sg = getFilesDir() + "/Backup/Application/";
                java.io.File fe = new java.io.File(sg);
                String[] qw = fe.list();
                if (Objects.requireNonNull(qw).length != 0) {
                    for (String sg2 : qw) {
                        FileInputStream fos = new FileInputStream(getFilesDir() + "/Backup/Application/" + sg2);
                        OutputStream d = new FileOutputStream(StorageDirectory.getWebviumDir() + "/Backup/Application/" + sg2);
                        byte[] e = new byte[1024];
                        int f;
                        while ((f = fos.read(e)) != -1) {
                            d.write(e, 0, f);
                        }
                        d.flush();
                        d.close();
                        fos.close();
                    }
                }
            } catch (Exception en) {
                runOnUiThread(()-> append(Log.getStackTraceString(en)));
            }
        };
        new Thread(runnable).start();
    }

    public void append(String sg) {
        tv.append(sg + "\n\n");
    }

}
