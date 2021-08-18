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

 package com.mrepol742.webvium.app.base;

 import android.app.Activity;
 import android.app.AlertDialog;
 import android.app.Fragment;
 import android.app.FragmentManager;
 import android.app.FragmentTransaction;
 import android.content.Context;
 import android.content.DialogInterface;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.graphics.Typeface;
 import android.net.Uri;
 import android.os.Build;
 import android.os.Bundle;
 import android.preference.PreferenceManager;
 import android.speech.SpeechRecognizer;
 import android.text.TextUtils;
 import android.util.LruCache;
 import android.view.LayoutInflater;
 import android.view.Menu;
 import android.view.SubMenu;
 import android.view.View;
 import android.view.ViewGroup;
 import android.view.Window;
 import android.webkit.URLUtil;
 import android.widget.Button;
 import android.widget.PopupMenu;
 import android.widget.TextView;

 import com.mrepol742.webvium.Edit;
 import com.mrepol742.webvium.Hist;
 import com.mrepol742.webvium.R;
 import com.mrepol742.webvium.Tool;
 import com.mrepol742.webvium.app.Intents;
 import com.mrepol742.webvium.app.Resources;
 import com.mrepol742.webvium.app.StorageDirectory;
 import com.mrepol742.webvium.net.Stream;
 import com.mrepol742.webvium.util.AwesomeToast;
 import com.mrepol742.webvium.util.Domain;
 import com.mrepol742.webvium.util.Html;
 import com.mrepol742.webvium.util.TextWatcher;

 import java.io.BufferedReader;
 import java.io.File;
 import java.io.InputStreamReader;
 import java.util.ArrayList;

 public class BaseActivity extends Activity {

     public static final String MAVEN_PRO = "classes";
     public static final int T_MAIN = 0;
     public static final int T_MANAGE_SPACE = 1;
     public static final int T_DEFAULT = 2;
     public static final int T_WELCOME_SCREEN = 3;
     public static final int T_ASSISTANT = 4;
     private static final int PRIMARY_CACHE = 99;
     private static final int SECONDARY_CACHE = 100;
     public LruCache<Integer, Typeface> cac;
     private Typeface main;
     private SharedPreferences sharedPreferences;
     private SharedPreferences exclusive;
     public static final int LINKS = 0;
     public static final int TRANCEROUTE = 1;
     public static final int NPING = 2;
     public static final int WHOIS = 3;
     public static final int META_TAGS = 4;
     public static final int HEADERS = 5;
     public static final int ROBOTS = 6;
     public static final int SOURCE_CODE = 7;
     public static final int IP_GEO = 8;
     public static final int ASSETLINKS = 9;
     public static final int SITEMAPS = 10;

     @Override
     protected void onCreate(Bundle be) {
         super.onCreate(be);
         cac = new LruCache<>(32);
         main = Typeface.createFromAsset(getAssets(), MAVEN_PRO);
     }

     @Override
     protected void onResume() {
         super.onResume();
         if (a221().getBoolean("maUU", false) && a221().getBoolean("qwe73", false)) {
             System.gc();
         }
     }

     public SharedPreferences a221() {
         if (sharedPreferences == null) {
             sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         }
         return sharedPreferences;
     }

     public String a222(String sg) {
         if (exclusive == null) {
             exclusive = getSharedPreferences("wv", 0);
         }
         return exclusive.getString(sg, "");
     }

     public int a223(String sg) {
         if (exclusive == null) {
             exclusive = getSharedPreferences("wv", 0);
         }
         return exclusive.getInt(sg, 0);
     }

     public boolean a224(String sg, boolean def) {
         if (exclusive == null) {
             exclusive = getSharedPreferences("wv", 0);
         }
         return exclusive.getBoolean(sg, def);
     }

     public void a225(String sg, boolean def) {
         if (exclusive == null) {
             exclusive = getSharedPreferences("wv", 0);
         }
         SharedPreferences.Editor e = exclusive.edit();
         e.putBoolean(sg, def);
         e.apply();
     }

     public void a225(int i) {
         setContentView(i);
         if ((Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) || a221().getBoolean("webviumB", false)) {
             ViewGroup vg = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
             vg.setFitsSystemWindows(true);
         }

     }

     public void shrt(String name, String data, int icon) {
         try {
             Intent e = new Intent(Intents.ACTION_LAUNCH);
             e.addCategory(Intents.CATEGORY_GENIUS);
             e.putExtra("duplicate", false);
             e.putExtra("webvium", data);
             Intent f = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
             f.putExtra("android.intent.extra.shortcut.INTENT", e);
             f.putExtra("android.intent.extra.shortcut.NAME", name);
             f.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this, icon));
             f.putExtra("duplicate", false);
             sendBroadcast(f);
             AwesomeToast.b(this, getString(R.string.q26));
         } catch (Exception et) {
             et.printStackTrace();
         }
     }

     public void as(int i, Fragment s) {
         String a = s.getClass().getName();
         FragmentManager fm = getFragmentManager();
         boolean bn = fm.popBackStackImmediate(a, 0);
         if (!bn) {
             FragmentTransaction ft = fm.beginTransaction();
             ft.replace(i, s);
             ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
             ft.addToBackStack(a);
             ft.commit();
         }
     }

     public void as1() {
         int count = getFragmentManager().getBackStackEntryCount();
         if (count == 1) {
             finishAndRemoveTask();
         } else {
             getFragmentManager().popBackStack();
         }
     }


     public void theme(int i) {
         if (i == T_MAIN) {
             if (!a221().getBoolean("autoUpdate", false)) {
                 if (!a221().getBoolean("webviumB", false)) {
                     if (!a221().getBoolean("autoUpdate742", false)) {
                         setTheme(R.style.d);
                     } else {
                         setTheme(R.style.b15);
                     }
                 } else {
                     if (!a221().getBoolean("autoUpdate742", false)) {
                         setTheme(R.style.a);
                     } else {
                         setTheme(R.style.b19);
                     }
                 }
             } else {
                 if (!a221().getBoolean("webviumB", false)) {
                     if (!a221().getBoolean("autoUpdate742", false)) {
                         setTheme(R.style.e);
                     } else {
                         setTheme(R.style.b16);
                     }
                 } else {
                     if (!a221().getBoolean("autoUpdate742", false)) {
                         setTheme(R.style.g);
                     } else {
                         setTheme(R.style.c2);
                     }
                 }
             }
         } else if (i == T_MANAGE_SPACE) {
             if (!a221().getBoolean("autoUpdate", false)) {
                 if (!a221().getBoolean("autoUpdate742", false)) {
                     setTheme(R.style.d3);
                 } else {
                     setTheme(R.style.d5);
                 }
             } else {
                 if (!a221().getBoolean("autoUpdate742", false)) {
                     setTheme(R.style.d4);
                 } else {
                     setTheme(R.style.d6);
                 }
             }
         } else if (i == T_DEFAULT) {
             if (!a221().getBoolean("autoUpdate", false)) {
                 if (!a221().getBoolean("autoUpdate742", false)) {
                     setTheme(R.style.d);
                 } else {
                     setTheme(R.style.b15);
                 }
             } else {
                 if (!a221().getBoolean("autoUpdate742", false)) {
                     setTheme(R.style.e);
                 } else {
                     setTheme(R.style.b16);
                 }
             }
         } else if (i == T_WELCOME_SCREEN) {
             if (!a221().getBoolean("autoUpdate742", false)) {
                 setTheme(R.style.a);
             } else {
                 setTheme(R.style.g);
             }
         } else if (i == T_ASSISTANT) {
             if (!a221().getBoolean("autoUpdate", false)) {
                 setTheme(R.style.m);
             } else {
                 setTheme(R.style.n);
             }
         }
     }

     public Typeface type(int sg) {
         if (a221().getBoolean("cFNT", false)) {
             File fe = new File(StorageDirectory.Fonts.getPrimaryFont(this));
             File fe1 = new File(StorageDirectory.Fonts.getSecondaryFont(this));
             if (fe.exists()) {
                 Typeface bp0 = cac.get(PRIMARY_CACHE);
                 if (bp0 == null) {
                     bp0 = Typeface.createFromFile(fe);
                     cac.put(PRIMARY_CACHE, bp0);
                 }
                 return bp0;
             }
             if (fe1.exists()) {
                 Typeface bp0 = cac.get(SECONDARY_CACHE);
                 if (bp0 == null) {
                     bp0 = Typeface.createFromFile(fe);
                     cac.put(SECONDARY_CACHE, bp0);
                 }
                 return bp0;
             }
         }
         Typeface bp = cac.get(sg);
         if (bp == null) {
             bp = Typeface.create(main, sg);
             cac.put(sg, bp);
         }
         return bp;
     }

     public boolean spr() {
         return !SpeechRecognizer.isRecognitionAvailable(this);
     }

     public void pro(final TextView tv, final String url) {
         Runnable re = new Runnable() {

             @Override
             public void run() {
                 try {
                     ArrayList<String> cmd = new ArrayList<>();
                     cmd.add("ping");
                     cmd.add(Uri.parse(url).getHost());
                     ProcessBuilder pb = new ProcessBuilder(cmd);
                     Process process = pb.start();
                     BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                     BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                     final StringBuilder sb = new StringBuilder();
                     String s;
                     while ((s = input.readLine()) != null) {
                         sb.append(s);
                         sb.append("\n");
                     }
                     while ((s = error.readLine()) != null) {
                         sb.append(s);
                         sb.append("\n");
                     }
                     input.close();
                     error.close();
                     runOnUiThread(new Runnable() {

                         @Override
                         public void run() {
                             tv.setText(sb.toString());
                         }
                     });
                 } catch (Exception en) {
                     en.printStackTrace();
                 }
             }
         };
         new Thread(re).start();
     }

     public void Tools(final LayoutInflater inf, final Context ct, String url, final int type) {
         if (!URLUtil.isValidUrl(url)) {
             AwesomeToast.c(this, getString(R.string.c32));
             return;
         } else if (!Domain.isValidDomain(url)) {
             AwesomeToast.c(this, getString(R.string.c32));
             return;
         }
         AlertDialog.Builder a = new AlertDialog.Builder(ct);
         View c = inf.inflate(R.layout.b8, null);
         a.setCancelable(true);
         if (type == LINKS) {
             a.setTitle(getString(R.string.x9)); // LINKS
         } else if (type == TRANCEROUTE) {
             a.setTitle(getString(R.string.x16)); // TRANCEROUT
         } else if (type == NPING) {
             a.setTitle(getString(R.string.y11)); //NPing
         } else if (type == WHOIS) {
             a.setTitle(getString(R.string.z4)); //Whois
         } else if (type == META_TAGS) {
             a.setTitle(getString(R.string.z15)); //Meta Tags
         } else if (type == HEADERS) {
             a.setTitle(getString(R.string.y15)); // Headers
         } else if (type == ROBOTS) {
             a.setTitle(getString(R.string.f32)); // Robots
         } else if (type == SOURCE_CODE) {
             a.setTitle(getString(R.string.j)); // Source Code
         } else if (type == IP_GEO) {
             a.setTitle(getString(R.string.z12)); // IP GeolocationDataModel
         } else if (type == ASSETLINKS) {
             a.setTitle(getString(R.string.y76)); // assetslinks
         } else if (type == SITEMAPS) {
             a.setTitle(getString(R.string.y77)); // sitemap
         }
         a.setView(c);
         final Edit ed = c.findViewById(R.id.g8);
         final TextView ti = c.findViewById(R.id.e2);
         final Button bn = c.findViewById(R.id.k20);
         int e = Resources.getColor(this, R.color.c);
         int f = Resources.getColor(this, R.color.b);
         int e3 = Resources.getColor(this, R.color.j);
         int f3 = Resources.getColor(this, R.color.k);
         if (!a221().getBoolean("autoUpdate", false)) {
             ed.setTextColor(e);
             ti.setTextColor(e3);
         } else {
             ed.setTextColor(f);
             ti.setTextColor(f3);
         }
         ed.setText(url);
         if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
             bn.setBackgroundResource(R.drawable.c10);
         } else {
             bn.setBackgroundResource(R.drawable.c11);
         }
         bn.setText(getString(R.string.i6));
         ti.setText(String.format(getString(R.string.f31), "https://mrepol742.github.io", "http://mrepol742.github.io"));
         final AlertDialog g = a.create();
         bn.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 String a1 = ed.getText().toString();
                 if (type == SOURCE_CODE) {
                     Intent it = new Intent(ct, Tool.class);
                     it.putExtra("dat", a1);
                     it.putExtra("id", Tool.TOOL_SOURCE_CODE);
                     ct.startActivity(it);
                 } else if (type == HEADERS) {
                     Headers(inf, ct, a1);
                 } else if (type == ROBOTS) {
                     Intent it = new Intent(ct, Tool.class);
                     it.putExtra("dat", a1);
                     it.putExtra("id", Tool.TOOL_ROBOTS);
                     ct.startActivity(it);
                 } else if (type == ASSETLINKS) {
                     Intent it = new Intent(ct, Tool.class);
                     it.putExtra("dat", a1);
                     it.putExtra("id", Tool.TOOL_ASSET_LINKS);
                     ct.startActivity(it);
                 } else if (type == SITEMAPS) {
                     Intent it = new Intent(ct, Tool.class);
                     it.putExtra("dat", a1);
                     it.putExtra("id", Tool.TOOL_SITEMAPS);
                     ct.startActivity(it);
                 }
                 g.dismiss();
             }
         });
         ed.addTextChangedListener(new TextWatcher() {

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 String url = ed.getText().toString().trim();
                 if (url.startsWith("https://") || url.startsWith("http://")) {
                     if (!Domain.isValidDomain(url)) {
                         ed.setError(getString(R.string.y84));
                         bn.setBackgroundResource(R.drawable.c11);
                     } else {
                         bn.setBackgroundResource(R.drawable.c10);
                     }
                 } else if (type != SOURCE_CODE && (url.startsWith("file://") || url.startsWith("content://"))) {
                     ed.setError(getString(R.string.y83));
                     bn.setBackgroundResource(R.drawable.c11);
                 } else {
                     ed.setError(getString(R.string.y82));
                     bn.setBackgroundResource(R.drawable.c11);
                 }
             }
         });
         g.show();
     }

     public void Headers(LayoutInflater inf, Context ct, final String url) {
         AlertDialog.Builder a = new AlertDialog.Builder(ct);
         View c = inf.inflate(R.layout.b8, null);
         a.setCancelable(true);
         a.setTitle(getString(R.string.y15));
         a.setView(c);
         final Edit ed = c.findViewById(R.id.g8);
         final TextView ti = c.findViewById(R.id.e2);
         final Button bn = c.findViewById(R.id.k20);
         int e = Resources.getColor(this, R.color.c);
         int f = Resources.getColor(this, R.color.b);
         if (!a221().getBoolean("autoUpdate", false)) {
             ed.setTextColor(e);
             ti.setTextColor(e);
         } else {
             ed.setTextColor(f);
             ti.setTextColor(f);
         }
         ti.setText(getString(R.string.v13));
         ed.setText(url);
         Runnable p15 = new Runnable() {

             @Override
             public void run() {
                 final String sg = Stream.d(url, getString(R.string.c33));
                 runOnUiThread(new Runnable() {

                     @Override
                     public void run() {
                         ti.setText(Html.b(sg));
                     }
                 });
             }
         };
         new Thread(p15).start();
         if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
             bn.setBackgroundResource(R.drawable.c10);
         } else {
             bn.setBackgroundResource(R.drawable.c11);
         }
         bn.setText(getString(R.string.i6));
         bn.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 final String ab = ed.getText().toString();
                 ti.setText(getString(R.string.v13));
                 if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
                     Runnable p151 = new Runnable() {

                         @Override
                         public void run() {
                             final String sg = Stream.d(ab, getString(R.string.c33));
                             runOnUiThread(new Runnable() {

                                 @Override
                                 public void run() {
                                     ti.setText(Html.b(sg));
                                 }
                             });
                         }
                     };
                     new Thread(p151).start();
                 }
             }
         });
         ed.addTextChangedListener(new TextWatcher() {

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 String url = ed.getText().toString().trim();
                 if (url.startsWith("https://") || url.startsWith("http://")) {
                     if (!Domain.isValidDomain(url)) {
                         ed.setError(getString(R.string.y84));
                         bn.setBackgroundResource(R.drawable.c11);
                     } else {
                         bn.setBackgroundResource(R.drawable.c10);
                     }
                 } else if (url.startsWith("file://") || url.startsWith("content://")) {
                     ed.setError(getString(R.string.y83));
                     bn.setBackgroundResource(R.drawable.c11);
                 } else {
                     ed.setError(getString(R.string.y82));
                     bn.setBackgroundResource(R.drawable.c11);
                 }
             }
         });
         final AlertDialog g = a.create();
         g.show();
     }

     public void NSLookup(LayoutInflater inf, Context ct, final String url) {
         AlertDialog.Builder a = new AlertDialog.Builder(ct);
         View c = inf.inflate(R.layout.b8, null);
         a.setCancelable(true);
         a.setTitle(getString(R.string.h6));
         a.setView(c);
         final Edit ed = c.findViewById(R.id.g8);
         final TextView ti = c.findViewById(R.id.e2);
         final Button bn = c.findViewById(R.id.k20);
         int e = Resources.getColor(this, R.color.c);
         int f = Resources.getColor(this, R.color.b);
         if (!a221().getBoolean("autoUpdate", false)) {
             ed.setTextColor(e);
             ti.setTextColor(e);
         } else {
             ed.setTextColor(f);
             ti.setTextColor(f);
         }
         ti.setText(getString(R.string.v13));
         ed.setText(url);
         Runnable p15 = new Runnable() {

             @Override
             public void run() {
                 final String sg = Stream.a(url, getString(R.string.c33), getString(R.string.g25));
                 runOnUiThread(new Runnable() {

                     @Override
                     public void run() {
                         ti.setText(Html.b(sg));
                     }
                 });
             }
         };
         new Thread(p15).start();
         bn.setText(getString(R.string.i6));
         if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
             bn.setBackgroundResource(R.drawable.c10);
         } else {
             bn.setBackgroundResource(R.drawable.c11);
         }
         bn.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 final String ab = ed.getText().toString();
                 ti.setText(getString(R.string.v13));

                 if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
                     Runnable p151 = new Runnable() {

                         @Override
                         public void run() {
                             final String sg = Stream.a(ab, getString(R.string.c33), getString(R.string.g25));
                             runOnUiThread(new Runnable() {

                                 @Override
                                 public void run() {
                                     ti.setText(Html.b(sg));
                                 }
                             });
                         }
                     };
                     new Thread(p151).start();
                 }
             }
         });
         ed.addTextChangedListener(new TextWatcher() {

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 String url = ed.getText().toString().trim();
                 if (url.startsWith("https://") || url.startsWith("http://")) {
                     if (!Domain.isValidDomain(url)) {
                         ed.setError(getString(R.string.y84));
                         bn.setBackgroundResource(R.drawable.c11);
                     } else {
                         bn.setBackgroundResource(R.drawable.c10);
                     }
                 } else if (url.startsWith("file://") || url.startsWith("content://")) {
                     ed.setError(getString(R.string.y83));
                     bn.setBackgroundResource(R.drawable.c11);
                 } else {
                     ed.setError(getString(R.string.y82));
                     bn.setBackgroundResource(R.drawable.c11);
                 }
             }
         });
         final AlertDialog g = a.create();
         g.show();
     }

     public void ping(LayoutInflater inf, final Context ct, String url) {
         AlertDialog.Builder a = new AlertDialog.Builder(ct);
         View c = inf.inflate(R.layout.b8, null);
         a.setCancelable(true);
         a.setTitle(getString(R.string.y78));
         a.setView(c);
         final Edit ed = c.findViewById(R.id.g8);
         final TextView ti = c.findViewById(R.id.e2);
         final Button bn = c.findViewById(R.id.k20);
         int e = Resources.getColor(this, R.color.c);
         int f = Resources.getColor(this, R.color.b);
         if (!a221().getBoolean("autoUpdate", false)) {
             ed.setTextColor(e);
             ti.setTextColor(e);
         } else {
             ed.setTextColor(f);
             ti.setTextColor(f);
         }
         ti.setText(getString(R.string.v13));
         pro(ti, url);
         ed.setText(url);
         bn.setText(getString(R.string.i6));
         bn.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 ti.setText(getString(R.string.v13));
                 pro(ti, ed.getText().toString());
             }
         });
         final AlertDialog g = a.create();
         g.show();
     }
 }