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

package com.mrepol742.webvium.setting.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.Back0;
import com.mrepol742.webvium.Lock;
import com.mrepol742.webvium.Webv;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.Swit;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.base.BasePreferenceFragment;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.download.DownloadHelper;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.util.FileUtil;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.AwesomeToast;
import com.mrepol742.webvium.util.JSON;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DatabaseFragment extends BasePreferenceFragment {
    private final IntentFilter is = new IntentFilter();
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
                is.addAction(Intent.ACTION_SCREEN_ON);
                r7 = new R7();
                getActivity().registerReceiver(r7, is);
            }
            a5(R.xml.x);
            Preference n = findPreference("res");
            n.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    DatabaseFragment.this.a6();
                    return true;
                }
            });
            Preference l = findPreference("ets");
            l.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    DatabaseFragment.this.a13();
                    return true;
                }
            });

            Preference l1 = findPreference("se");
            l1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    DatabaseFragment.this.a17();
                    return true;
                }
            });

            Preference l11 = findPreference("he");
            l11.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    DatabaseFragment.this.b1();
                    return true;
                }
            });

            Preference l111 = findPreference("be");
            l111.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    DatabaseFragment.this.b5();
                    return true;
                }
            });

            Preference l111444 = findPreference("do");
            l111444.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    DatabaseFragment.this.b9();
                    return true;
                }
            });
            Swit SWIT = (Swit) findPreference("bcP");
            SWIT.setSummary(getString(R.string.z39));

            Preference l114 = findPreference("se23");
            l114.setSummary(String.format(getActivity().getString(R.string.x48), StorageDirectory.getWebviumDir() + "/Backup"));
            l114.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference a) {
                    Intent it = new Intent(DatabaseFragment.this.getActivity(), Back0.class);
                    it.putExtra("a", "a");
                    DatabaseFragment.this.startActivity(it);
                    return true;
                }
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                SharedPreferences.Editor b = DatabaseFragment.this.a221().edit();
                b.clear();
                b.apply();
                DatabaseFragment.this.t();
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
        AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        a.setMessage(Html.b(String.format(getString(R.string.b38), StorageDirectory.a() + "Settings_" + format() + ".bac")));
        a.setPositiveButton(getString(R.string.q14), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                createFolder();
                write(PreferenceManager.getDefaultSharedPreferences(DatabaseFragment.this.getActivity()).getAll());
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

    private void a17() {
        AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        a.setMessage(Html.b(String.format(getString(R.string.b40), location(3))));
        a.setPositiveButton(getString(R.string.q14), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                createFolder();
                SearchHelper d1 = SearchHelper.getInstance(DatabaseFragment.this.getActivity().getApplicationContext());
                Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_SEARCH + " ORDER BY " + "_id" + " DESC", null);
                if (res.getCount() == 0) {
                    g(DatabaseFragment.this.getString(R.string.z31));
                } else {
                    write(res, 3);
                }
                res.close();
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

    private void b1() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        a.setMessage(Html.b(String.format(getString(R.string.c22), location(2))));
        a.setPositiveButton(getString(R.string.q14), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                createFolder();
                HistoryHelper d1 = HistoryHelper.getInstance(DatabaseFragment.this.getActivity().getApplicationContext());
                Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_HISTORY + " ORDER BY " + "_id" + " DESC", null);
                if (res.getCount() == 0) {
                    g(DatabaseFragment.this.getString(R.string.z31));
                } else {
                    write(res, 2);
                }
                res.close();
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

    private void b5() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        a.setMessage(Html.b(String.format(getString(R.string.c24), location(0))));
        a.setPositiveButton(getString(R.string.q14), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                createFolder();
                BookmarkHelper d1 = BookmarkHelper.getInstance(DatabaseFragment.this.getActivity().getApplicationContext());
                Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_BOOKMARK + " ORDER BY " + "_id" + " DESC", null);
                if (res.getCount() == 0) {
                    g(DatabaseFragment.this.getString(R.string.z31));
                } else {
                    write(res, 0);
                }
                res.close();
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

    private void b9() {
        final AlertDialog.Builder a = new AlertDialog.Builder(getActivity());
        a.setCancelable(true);
        a.setTitle(getString(R.string.t6));
        a.setMessage(Html.b(String.format(getString(R.string.z34), location(1))));
        a.setPositiveButton(getString(R.string.q14), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                createFolder();
                DownloadHelper d1 = DownloadHelper.getInstance(DatabaseFragment.this.getActivity().getApplicationContext());
                Cursor res = d1.getReadableDatabase().rawQuery("SELECT * FROM " + Sqlite.TABLE_DOWNLOAD + " ORDER BY " + "_id" + " DESC", null);
                if (res.getCount() == 0) {
                   g(DatabaseFragment.this.getString(R.string.z31));
                } else {
                    write(res, 1);
                }
                res.close();
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

    private void d(String a) {
        AwesomeToast.c(getActivity(), a);
    }

    private void g(String a) {
        AwesomeToast.b(getActivity(), a);
    }

    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyy_HHmm", Locale.US);
        return sdf.format(new Date());
    }

    private String getName(int id) {
        if (id == 0) {
            return "Bookmark_" + format() + ".bac";
        } else if (id == 1) {
            return "Download_" + format() + ".bac";
        } else if (id == 2) {
            return "History_" + format() + ".bac";
        }
        return "Search_" + format() + ".bac";
    }

    private String location(int id) {
        if (Build.VERSION.SDK_INT >= 30) {
            return StorageDirectory.a() + "/Documents/Webvium/Backup/" + DatabaseFragment.this.getName(id);
        }
        return StorageDirectory.getWebviumDir() + "/Backup/" + getName(id);
    }

    private void write(Map<String, ?> map) {
        final String sg = JSON.toString(map);
        Runnable re = new Runnable() {

            @Override
            public void run() {
                try {
                    if (Build.VERSION.SDK_INT >= 30 && FileUtil.write(getActivity(), Environment.DIRECTORY_DOCUMENTS + "/Webvium/Setting_" + format() + ".bac", "", Environment.DIRECTORY_DOCUMENTS + "/Webvium/Backup/", sg)) {
                        DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b25));
                            }
                        });
                    } else if (FileUtil.write(new File(StorageDirectory.getWebviumDir() + "/Backup/Setting_" + format() + ".bac"), sg, false)) {
                        DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b25));
                            }
                        });
                    } else {
                        DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b26));
                            }
                        });
                    }
                } catch (Exception en) {
                    en.printStackTrace();
                    DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b26));
                        }
                    });
                }
            }
        };
        new Thread(re).start();
    }

    private void write(Cursor cr, final int id) {
        final String sg = JSON.toString(cr, id);
        Runnable re = new Runnable() {

            @Override
            public void run() {
                try {
                    if (Build.VERSION.SDK_INT >= 30 && FileUtil.write(getActivity(),Environment.DIRECTORY_DOCUMENTS + "/Webvium/" + DatabaseFragment.this.getName(id), "", Environment.DIRECTORY_DOCUMENTS + "/Webvium/Backup/", sg)) {
                        DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b25));
                            }
                        });
                    } else if (FileUtil.write(new File(location(id)), sg, false)) {
                        DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b25));
                            }
                        });
                    } else {
                        DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b26));
                            }
                        });
                    }
                } catch (Exception en) {
                    en.printStackTrace();
                    DatabaseFragment.this.getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            DatabaseFragment.this.d(DatabaseFragment.this.getString(R.string.b26));
                        }
                    });
                }
            }
        };
        new Thread(re).start();
        cr.close();
    }

    private void createFolder() {
        if (Build.VERSION.SDK_INT < 30) {
            FileUtil.createNewFolder(StorageDirectory.getWebviumDir() + "/Backup");
        }
    }

    private class R7 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false)) {
                    Intent it = new Intent(a, Lock.class);
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
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            a5.dismiss();
            a5 = null;
            getActivity().finish();
            SharedPreferences c56 = getActivity().getSharedPreferences("wv", 0);
            Intent it = new Intent(getActivity(), Webv.class);
            it.putExtra("value", c56.getString("MyURL", ""));
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(it);
        }
    }
}