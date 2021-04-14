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

package com.mrepol742.webvium;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mrepol742.webvium.app.base.BaseActivity;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.content.C10;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.download.DownloadHelper;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.permission.PermissionHelper;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.telemetry.DiagnosticData;
import com.mrepol742.webvium.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

// @Class BackupImport
@SuppressWarnings("unchecked")
public class BACK0 extends BaseActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 742 && resultCode == RESULT_OK && data.getData() != null) {
            try {
                a(getContentResolver().openInputStream(data.getData()));
            } catch (Exception en) {
                Toast.c(BACK0.this, getString(R.string.z21));
                DiagnosticData.a(en);
                finishAndRemoveTask();
            }
        } else {
            finishAndRemoveTask();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onNewIntent(getIntent());
    }

    protected void a(InputStream is) {
        try {
            ObjectInputStream ois = new ObjectInputStream(is);
            Object ot = ois.readObject();
            ois.close();
            is.close();
            if (ot instanceof ArrayList) {
                ArrayList<?> al = (ArrayList) ot;
                Object ot1 = al.get(0);
                if (ot1 instanceof HDMS) {
                    j(ot);
                } else if (ot1 instanceof BDMS) {
                    i(ot);
                } else if (ot1 instanceof PDMS) {
                    h(ot);
                } else if (ot1 instanceof DDMS) {
                    f(ot);
                } else if (ot1 instanceof SDMS) {
                    e(ot);
                } else {
                    Toast.c(BACK0.this, getString(R.string.z21));
                    finishAndRemoveTask();
                }

            } else if (ot instanceof Map) {
                c(ot);
            } else {
                Toast.c(BACK0.this, getString(R.string.z21));
                finishAndRemoveTask();
            }
        } catch (Exception en) {
            Toast.c(BACK0.this, getString(R.string.z21));
            DiagnosticData.a(en);
            finishAndRemoveTask();
        }
    }

    private void c(Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.b39));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            Map<String, ?> al;
            try {
                al = (Map) mp;
                SharedPreferences.Editor spe = sp.edit();
                spe.clear();
                for (Map.Entry<String, ?> sg : al.entrySet()) {
                    Object ob = sg.getValue();
                    if (ob instanceof String) {
                        spe.putString(sg.getKey(), (String) ob);
                    } else if (ob instanceof Boolean) {
                        String ky = sg.getKey();
                        if (ky.equals("ptm") || ky.equals("lockWn99")) {
                            spe.putBoolean(ky, false);
                        } else {
                            spe.putBoolean(ky, (Boolean) ob);
                        }
                    } else if (ob instanceof Integer) {
                        spe.putInt(sg.getKey(), (Integer) ob);
                    }
                }
                spe.apply();
                C10.a(this, "com.mrepol742.webvium.activity.alias.PRE", PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
                C10.a(this, "com.mrepol742.webvium.activity.alias.MAY", PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
                getSharedPreferences("a", 0).edit().putString("ajGjbduTwibdi", "").putString("gsJsGsKSIgPes", "").apply();
                g(getString(R.string.b27));
                t();
            } catch (Exception en) {
                DiagnosticData.a(en);
                d(getString(R.string.b28));
            }
            a12.dismiss();
            finishAndRemoveTask();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> {
            a1.dismiss();
            finishAndRemoveTask();
        });
        a.create().show();
    }

    private void d(String a) {
        Toast.c(this, a);
    }

    private void e(Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.c21));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            SearchHelper d1 = SearchHelper.getInstance(getApplicationContext());
            ArrayList<SDMS> al;
            try {
                al = (ArrayList) mp;
                d1.delete();
                for (SDMS w13 : al) {
                    d1.c(w13.sg);
                }
                g(getString(R.string.b27));
            } catch (Exception en) {
                DiagnosticData.a(en);
                d(getString(R.string.b28));
            }
            a12.dismiss();
            finishAndRemoveTask();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> {
            a1.dismiss();
            finishAndRemoveTask();
        });
        a.create().show();
    }

    private void f(Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.z35));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            DownloadHelper d1 = DownloadHelper.getInstance(getApplicationContext());
            ArrayList<DDMS> al;
            try {
                al = (ArrayList) mp;
                d1.delete();
                for (DDMS w13 : al) {
                    d1.d(w13);
                }
                g(getString(R.string.b27));
            } catch (Exception en) {
                DiagnosticData.a(en);
                d(getString(R.string.b28));
            }
            a12.dismiss();
            finishAndRemoveTask();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> {
            a1.dismiss();
            finishAndRemoveTask();
        });
        a.create().show();
    }

    private void g(String a) {
        Toast.b(this, a);
    }

    private void h(Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.z37));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            PermissionHelper d1 = PermissionHelper.getInstance(getApplicationContext());
            ArrayList<PDMS> al;
            try {
                al = (ArrayList) mp;
                d1.delete();
                for (PDMS w13 : al) {
                    d1.d(w13);
                }
                g(getString(R.string.b27));
            } catch (Exception en) {
                DiagnosticData.a(en);
                d(getString(R.string.b28));
            }

            a12.dismiss();
            finishAndRemoveTask();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> {
            a1.dismiss();
            finishAndRemoveTask();
        });
        a.create().show();
    }

    private void i(Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.d34));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            BookmarkHelper d1 = BookmarkHelper.getInstance(getApplicationContext());
            ArrayList<BDMS> al;
            try {
                al = (ArrayList) mp;
                d1.delete();
                for (BDMS w13 : al) {
                    d1.d(w13);
                }
                g(getString(R.string.b27));
            } catch (Exception en) {
                DiagnosticData.a(en);
                d(getString(R.string.b28));
            }
            a12.dismiss();
            finishAndRemoveTask();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> {
            a1.dismiss();
            finishAndRemoveTask();
        });
        a.create().show();
    }

    private void j(Object mp) {
        final AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.t6));
        a.setMessage(getString(R.string.d33));
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> {
            HistoryHelper d1 = HistoryHelper.getInstance(getApplicationContext());
            ArrayList<HDMS> al;
            try {
                al = (ArrayList) mp;
                d1.delete();
                for (HDMS w13 : al) {
                    d1.d(w13);
                }
                g(getString(R.string.b27));
            } catch (Exception en) {
                d(getString(R.string.b28));
            }
            a12.dismiss();
            finishAndRemoveTask();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> {
            a1.dismiss();
            finishAndRemoveTask();
        });
        a.create().show();
    }


    private void t() {
        AlertDialog.Builder c = new AlertDialog.Builder(this);
        LayoutInflater d = LayoutInflater.from(this);
        View e = d.inflate(R.layout.a12, null);
        c.setCancelable(false);
        c.setView(e);
        TextView f = e.findViewById(R.id.g1);
        f.setText(getString(R.string.o1));
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.b(this, R.color.c));
        } else {
            f.setTextColor(Resources.b(this, R.color.b));
        }
        AlertDialog j5 = c.create();
        O5 timer = new O5(2000, 2000, j5);
        timer.start();
        j5.show();
    }

    protected void onNewIntent(Intent a) {
        try {
            String action = a.getAction();
            String data = a.getDataString();
            String as = a.getStringExtra("a");
            if (as != null) {
                a.removeExtra("a");
                Intent d = new Intent(Intent.ACTION_GET_CONTENT);
                d.setType("*/*");
                d.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 742);
            } else if (action.equals(Intent.ACTION_VIEW) && data != null) {
                try {
                    a(new FileInputStream(URLDecoder.decode(data.replace("file://", ""), "UTF-8")));
                } catch (Exception en) {
                    Toast.c(BACK0.this, getString(R.string.z21));
                    DiagnosticData.a(en);
                    finishAndRemoveTask();
                }
            }
            a.replaceExtras(new Bundle());
            a.setAction("");
            a.setData(null);
            a.setFlags(0);
        } catch (Exception ex) {
            DiagnosticData.a(ex);
            finishAndRemoveTask();
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
            finishAndRemoveTask();
            SharedPreferences c56 = getSharedPreferences("wv,", 0);
            Intent it = new Intent(BACK0.this, MAIN.class);
            it.putExtra("value", c56.getString("MyURL", ""));
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(it);
        }
    }
}