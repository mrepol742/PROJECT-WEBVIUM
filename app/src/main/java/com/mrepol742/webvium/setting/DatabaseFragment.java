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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.BACK0;
import com.mrepol742.webvium.BDMS;
import com.mrepol742.webvium.DDMS;
import com.mrepol742.webvium.HDMS;
import com.mrepol742.webvium.LOCK;
import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.PDMS;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.SDMS;
import com.mrepol742.webvium.SWIT;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.content.IntentsFilter;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.download.DownloadHelper;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.permission.PermissionHelper;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.util.Format;
import com.mrepol742.webvium.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseFragment extends BasePreferenceFragment implements Format {
    private final IntentsFilter is = new IntentsFilter();
    private R7 r7;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345 && resultCode != Activity.RESULT_OK) {
            getActivity().getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onCreate(Bundle b1) {
        super.onCreate(b1);
        try {
            if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
                is.act(Intent.ACTION_SCREEN_ON);
                r7 = new R7();
                getActivity().registerReceiver(r7, is);
            }
            if (Build.VERSION.SDK_INT < 29) {
                a5(R.xml.x);
            } else {
                a5(R.xml.a6);
            }

            Preference n = findPreference("res");
            n.setOnPreferenceClickListener(a -> {
                a6();
                return true;
            });
            Preference l = findPreference("ets");
            l.setOnPreferenceClickListener(a -> {
                a13();
                return true;
            });

            Preference l1 = findPreference("se");
            l1.setOnPreferenceClickListener(a -> {
                a17();
                return true;
            });

            Preference l11 = findPreference("he");
            l11.setOnPreferenceClickListener(a -> {
                b1();
                return true;
            });

            Preference l111 = findPreference("be");
            l111.setOnPreferenceClickListener(a -> {
                b5();
                return true;
            });

            Preference l111444 = findPreference("do");
            l111444.setOnPreferenceClickListener(a -> {
                b9();
                return true;
            });

            Preference l111444a = findPreference("pe");
            l111444a.setOnPreferenceClickListener(a -> {
                b13();
                return true;
            });

            SWIT SWIT = (SWIT) findPreference("bcP");
            SWIT.setSummary(getString(R.string.z39));

            Preference l114 = findPreference("se23");
            l114.setSummary(String.format(getActivity().getString(R.string.x48), StorageDirectory.getWebviumDir() + "/Backup"));
            l114.setOnPreferenceClickListener(a -> {
                Intent it = new Intent(getActivity(), BACK0.class);
                it.putExtra("a", "a");
                startActivity(it);
                return true;
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (r7 != null) {
            getActivity().unregisterReceiver(r7);
        }
    }

    private void a6() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.b24));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            SharedPreferences.Editor b = a221().edit();
            b.clear();
            b.apply();
            t();
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }


    private void t() {
        AlertDialog.Builder c = new AlertDialog.Builder(getActivity());
        LayoutInflater d = LayoutInflater.from(getActivity());
        View e = d.inflate(R.layout.a12, null);
        c.setCancelable(false);
        c.setView(e);
        TextView f = e.findViewById(R.id.g1);
        f.setText(getString(R.string.o1));
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.getColor(getActivity(), R.color.c));
        } else {
            f.setTextColor(Resources.getColor(getActivity(), R.color.b));
        }
        AlertDialog j5 = c.create();
        O5 timer = new O5(2000, 2000, j5);
        timer.start();
        j5.show();
    }

    private void a13() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        String file = StorageDirectory.getWebviumDir() + "/Backup/Databases/Settings_" + format() + ".bac";
        a.setMessage(Html.b(String.format(getString(R.string.b38), file)));
        a.setPositiveButton(getString(R.string.q14), (a12, intetg) -> {
            Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
            Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Databases");
            write(file, PreferenceManager.getDefaultSharedPreferences(getActivity()).getAll());
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void a16(String file) {
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Databases");
        Runnable p15 = () -> {
            ArrayList<SDMS> al = new ArrayList<>();
            SearchHelper d1 = SearchHelper.getInstance(getActivity().getApplicationContext());
            Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + "A" + " ORDER BY " + "_id" + " DESC", null);
            if (res.getCount() == 0) {
                getActivity().runOnUiThread(() -> g(getString(R.string.z31)));
            } else {
                while (res.moveToNext()) {
                    al.add(new SDMS(res.getString(1)));
                }
                write(file, al);
            }
            res.close();
        };
        new Thread(p15).start();
    }

    private void a17() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        String file = StorageDirectory.getWebviumDir() + "/Backup/Databases/Search_" + format() + ".bac";
        a.setMessage(Html.b(String.format(getString(R.string.b40), file)));
        a.setPositiveButton(getString(R.string.q14), (a12, intetg) -> {
            a16(file);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void a20(String file) {
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Databases");
        Runnable p15 = () -> {
            ArrayList<HDMS> al = new ArrayList<>();
            HistoryHelper d1 = HistoryHelper.getInstance(getActivity().getApplicationContext());
            Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + "A" + " ORDER BY " + "_id" + " DESC", null);
            if (res.getCount() == 0) {
                getActivity().runOnUiThread(() -> g(getString(R.string.z31)));
            } else {
                while (res.moveToNext()) {
                    al.add(new HDMS(res.getString(1), res.getString(2), res.getString(3)));
                }
                write(file, al);
            }
            res.close();
        };
        new Thread(p15).start();
    }

    private void b1() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        String file = StorageDirectory.getWebviumDir() + "/Backup/Databases/History_" + format() + ".bac";
        a.setMessage(Html.b(String.format(getString(R.string.c22), file)));
        a.setPositiveButton(getString(R.string.q14), (a12, intetg) -> {
            a20(file);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void b4(String file) {
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Databases");
        Runnable p15 = () -> {
            BookmarkHelper d1 = BookmarkHelper.getInstance(getActivity().getApplicationContext());
            Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + "A" + " ORDER BY " + "_id" + " DESC", null);
            if (res.getCount() == 0) {
                getActivity().runOnUiThread(() -> g(getString(R.string.z31)));
            } else {
                ArrayList<BDMS> al = new ArrayList<>();
                while (res.moveToNext()) {
                    al.add(new BDMS(res.getString(1), res.getString(2)));
                }
                write(file, al);
            }
            res.close();
        };
        new Thread(p15).start();
    }

    private void b5() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        String file = StorageDirectory.getWebviumDir() + "/Backup/Databases/Bookmarks_" + format() + ".bac";
        a.setMessage(Html.b(String.format(getString(R.string.c24), file)));
        a.setPositiveButton(getString(R.string.q14), (a12, intetg) -> {
            b4(file);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void b8(String file) {
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Databases");
        Runnable p15 = () -> {
            DownloadHelper d1 = DownloadHelper.getInstance(getActivity().getApplicationContext());
            Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + "A" + " ORDER BY " + "_id" + " DESC", null);
            if (res.getCount() == 0) {
                getActivity().runOnUiThread(() -> g(getString(R.string.z31)));
            } else {
                ArrayList<DDMS> al = new ArrayList<>();
                while (res.moveToNext()) {
                    al.add(new DDMS(res.getString(1), res.getString(2), res.getInt(3), res.getString(4), res.getString(5)));
                }
                write(file, al);
            }
            res.close();
        };
        new Thread(p15).start();
    }

    private void b9() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        String file = StorageDirectory.getWebviumDir() + "/Backup/Databases/Downloads_" + format() + ".bac";
        a.setMessage(Html.b(String.format(getString(R.string.z34), file)));
        a.setPositiveButton(getString(R.string.q14), (a12, intetg) -> {
            b8(file);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void b12(String file) {
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup/Databases");
        Runnable p15 = () -> {
            PermissionHelper d1 = PermissionHelper.getInstance(getActivity().getApplicationContext());
            Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + "A" + " ORDER BY " + "_id" + " DESC", null);
            if (res.getCount() == 0) {
                getActivity().runOnUiThread(() -> g(getString(R.string.z31)));
            } else {
                ArrayList<PDMS> al = new ArrayList<>();
                while (res.moveToNext()) {
                    al.add(new PDMS(res.getString(1), res.getString(2), res.getString(3), res.getString(4)));
                }
                write(file, al);
            }
            res.close();
        };
        new Thread(p15).start();
    }

    private void b13() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        String file = StorageDirectory.getWebviumDir() + "/Backup/Databases/Permissions_" + format() + ".bac";
        a.setMessage(Html.b(String.format(getString(R.string.z36), file)));
        a.setPositiveButton(getString(R.string.q14), (a12, intetg) -> {
            b12(file);
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void d(String a) {
        Toast.c(getActivity(), a);
    }

    private void g(String a) {
        Toast.b(getActivity(), a);
    }

    @Override
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyy_HHmm", Locale.US);
        return sdf.format(new Date());
    }

    private void write(String sg, Object al) {
        Runnable re = () -> {
            try {
                if (Build.VERSION.SDK_INT <= 29) {
                    FileOutputStream fos = new FileOutputStream(sg);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(al);
                    oos.close();
                    fos.close();
                    getActivity().runOnUiThread(() -> g(getString(R.string.b25)));
                }
            } catch (Exception en) {
                en.printStackTrace();
                getActivity().runOnUiThread(() -> d(getString(R.string.b26)));
            }
        };
        new Thread(re).start();
    }

    private class R7 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, LOCK.class);
                    startActivityForResult(it, 345);
                    getActivity().overridePendingTransition(R.anim.f, R.anim.b);
                }
            }
        }
    }

    class O5 extends CountDownTimer {
        AlertDialog a5;

        public O5(long a, long b, AlertDialog gj) {
            super(a, b);
            a5 = gj;
        }

        @Override
        public void onFinish() {
            a5.dismiss();
            a5 = null;
            getActivity().finish();
            SharedPreferences c56 = getActivity().getSharedPreferences("wv", 0);
            Intent it = new Intent(getActivity(), MAIN.class);
            it.putExtra("value", c56.getString("MyURL", ""));
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(it);
        }
    }


}