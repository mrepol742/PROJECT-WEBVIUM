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

 package com.mrepol742.webvium.app.base;

 import android.app.Activity;
 import android.app.Fragment;
 import android.app.FragmentManager;
 import android.app.FragmentTransaction;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.graphics.Typeface;
 import android.os.Build;
 import android.os.Bundle;
 import android.preference.PreferenceManager;
 import android.speech.SpeechRecognizer;
 import android.util.LruCache;
 import android.view.ViewGroup;

 import com.mrepol742.webvium.BuildConfig;
 import com.mrepol742.webvium.R;
 import com.mrepol742.webvium.content.Intents;
 import com.mrepol742.webvium.io.StorageDirectory;
 import com.mrepol742.webvium.widget.AwesomeToast;

 import java.io.File;

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

     @Override
     protected void onCreate(Bundle be) {
         super.onCreate(be);
         cac = new LruCache<>(32);
         main = Typeface.createFromAsset(getAssets(), MAVEN_PRO);
     }

     @Override
     protected void onResume() {
         super.onResume();
         if (a221().getBoolean("maUU", BuildConfig.DEBUG) && a221().getBoolean("qwe73", false)) {
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
 }