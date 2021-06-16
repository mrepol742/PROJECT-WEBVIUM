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

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.media.AudioManager;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.print.PrintManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.format.Formatter;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewDatabase;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.GeolocationDataModel;
import com.mrepol742.webvium.app.HashJSI;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.PendingDownloadDataModel;
import com.mrepol742.webvium.app.ReceivedErrorDataModel;
import com.mrepol742.webvium.app.SearchJSI;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.WebViews;
import com.mrepol742.webvium.app.WebviumJSI;
import com.mrepol742.webvium.app.XORJSI;
import com.mrepol742.webvium.app.main.MainBaseActivity;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainReceiver;
import com.mrepol742.webvium.app.main.MainWebViewClient;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.content.C10;
import com.mrepol742.webvium.content.Clipboard;
import com.mrepol742.webvium.content.Intents;
import com.mrepol742.webvium.content.IntentsFilter;
import com.mrepol742.webvium.content.Package;
import com.mrepol742.webvium.content.Resources;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.io.Files;
import com.mrepol742.webvium.io.StorageDirectory;
import com.mrepol742.webvium.manifest.Permission;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.net.IPAddress;
import com.mrepol742.webvium.net.Ping;
import com.mrepol742.webvium.os.CountDownTimer;
import com.mrepol742.webvium.permission.PermissionDataModel;
import com.mrepol742.webvium.permission.PermissionHelper;
import com.mrepol742.webvium.permission.PermissionObjectDataModel;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.security.Hash;
import com.mrepol742.webvium.text.Html;
import com.mrepol742.webvium.text.Password;
import com.mrepol742.webvium.text.TextWatcher;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Domain;
import com.mrepol742.webvium.util.Format;
import com.mrepol742.webvium.util.IdentityGenerator;
import com.mrepol742.webvium.util.Stream;
import com.mrepol742.webvium.util.U1;
import com.mrepol742.webvium.util.U3;
import com.mrepol742.webvium.util.cache.BitmapCache;
import com.mrepol742.webvium.view.Animation;
import com.mrepol742.webvium.view.SoftKeyboard;
import com.mrepol742.webvium.widget.Toast;
import com.mrepol742.webvium.widget.W11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

// @Class MainActivity
public class MAIN extends MainBaseActivity implements Format {
    private static final String[] searchEngine = {
            "https://google.com",
            "https://duckduckgo.com",
            "https://bing.com",
            "https://yahoo.com",
            "https://ask.com",
            "https://aol.com",
            "https://baidu.com",
            "https://wolframalpha.com",
            "https://0.discoverapp.com",
            "https://ecosia.org",
            "https://stackoverflow.com",
            "https://youtube.com",
            "https://github.com",
            "https://facebook.com",
            "https://suasive.in"
    };

    private static final String[] ads = {
            "adservice.google.com",
            "media.net",
            "propellerads.com",
            "popads.net",
            "infolinks.com",
            "revenuehits.com",
            "adbuff.com",
            "hilltopads.com"
    };
    private static final String[] searchPath = {
            "/search?q=",
            "https://search.yahoo.com/search?p=",
            "https://www.ask.com/web?q=",
            "https://search.aol.com/aol/search?q=",
            "/s?wd=",
            "/input/?i=",
            "/search_shim/?ref=fbs_can_rdr&query=",
            "/results?search_query=",
            "/search/top/?q="
    };
    private static final String[] userAgents = {
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) %1$s/%2$s Mobile Safari/537.36",
            "Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 635) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537",
            "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36",
            "Mozilla/5.0 (Android 8.0.0; Mobile; rv:61.0) Gecko/61.0 Firefox/68.0",
            "Mozilla/5.0 (Linux; Android 10; SM-N975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36 OPR/55.2.2719",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 13_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) CriOS/80.0.3987.95 Mobile/15E148 Safari/604.1",
            "Mozilla/5.0 (Windows Mobile 10; Android 8.0.0; Microsoft; Lumia 950XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36 Edge/80.0.361.109",
            "Mozilla/5.0 (Linux; Android 8.0.0;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 8.0.0; SM-G935F Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Brave Chrome/69.0.3497.100 Mobile Safari/537.36",
    };
    private static final String[] code = {
            "ISO-8858-1",
            "UTF-8",
            "GBK",
            "Big5",
            "ISO-2022-JP",
            "SHIFT_JIS",
            "EUC-JP",
            "EUC-KR"
    };
    public static boolean bl = false;
    public static boolean bl2 = false;
    public static boolean bl3 = false;
    public static boolean bl6 = false;
    public static boolean bl4 = false;
    private final R6 br = new R6();
    private final IntentsFilter ift = new IntentsFilter();
    private final IntentsFilter ift1 = new IntentsFilter();
    private final IntentsFilter ift3 = new IntentsFilter();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final HashMap<String, String> eh = new HashMap<>(1);
    public WebViews h;
    public int it742;
    public ProgressBar g;
    public Toolbar o;
    public TextView u;
    public int a7;
    public int a8;
    public int a9;
    public boolean bl5 = false;
    public RelativeLayout cd;
    public RelativeLayout back23;
    public Timer timer;
    public int it7422;
    public Timer cdt;
    public ImageView tv, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    private final StringBuilder cm = new StringBuilder();
    private final StringBuilder cm0 = new StringBuilder();
    private final StringBuilder cm2 = new StringBuilder();
    private CookieManager cm1;
    private HistoryHelper d1;
    private SearchHelper d2;
    private BookmarkHelper d3;
    public static final String UA_DEFAULT = "1e";
    public static final String UA_ANDROID_STOCK = "7e";
    public static final String UA_INTERNET_EXPLORER = "30e";
    public static final String UA_GOOGLE_CHROME = "60e";
    public static final String UA_MOZILA_FIREFOX = "120e";
    public static final String UA_OPERA = "240e";
    public static final String UA_SAFARI = "480e";
    public static final String UA_MICROSOFT_EDGE = "960e";
    public static final String UA_GOOGLE_CHROMIUM = "1920e";
    public static final String UA_MOZILA_BRAVE = "3840e";
    public static final String UA_CUSTOM = "7680e";
    public static final String SE_GOOGLE = "7b";
    public static final String SE_DUCKDUCKGO = "1b";
    public static final String SE_BING = "30b";
    public static final String SE_YAHOO = "60b";
    public static final String SE_ASK = "120b";
    public static final String SE_AOL = "240b";
    public static final String SE_BAIDU = "480b";
    public static final String SE_WOLFRAMALPHA = "860b";
    public static final String SE_DISCOVERAPP = "1720b";
    public static final String SE_ECOSIA = "3440b";
    public static final String SE_STACKOVERFLOW = "6880b";
    public static final String SE_YOUTUBE = "12b";
    public static final String SE_GITHUB = "13b";
    public static final String SE_FACEBOOK = "14b";
    public static final String SE_SUASIVE = "15b";
    private WebSettings ws;
    private GeolocationDataModel w6;
    private PermissionDataModel w8;
    private R36 br2;
    private ca149 br4;
    private ImageView iw;
    private ValueCallback<Uri[]> b;
    private View cv;
    private WebChromeClient.CustomViewCallback cvc;
    private boolean ua = false;
    private TextView tv10;
    private LinearLayout llt;
    private ActionBar ab;
    private WebViewDatabase wd;
    private PendingDownloadDataModel pend;
    private PowerManager.WakeLock wl;
    private PowerManager pm;
    private ForegroundColorSpan A, E, S, I, B;
    private String cll;
    private PermissionHelper d12;
    private R7 r7;
    private FrameLayout fl;
    private PopupMenu pm0, pm1, pm2, pm3, pm4, pm5, pm6, pm7;
    private TextView inf;
    private String sg;
    public static final int POPUPMENU_TOOLBAR_PASTE_AND_SEARCH = 0;
    public static final int POPUPMENU_TOOLBAR_PASTE = 1;
    public static final int POPUPMENU_TOOLBAR_COPY_LINK = 2;
    public static final int POPUPMENU_TOOLBAR_COPY_TITLE = 6;
    public static final int POPUPMENU_TOOLBAR_SHARE_LINK = 3;
    public static final int POPUPMENU_TOOLBAR_SHARE_TITLE = 7;
    public static final int POPUPMENU_TOOLBAR_ADD_TO_HOMESCREEN = 4;
    public static final int POPUPMENU_TOOLBAR_ADD_TO_BOOKMARKS = 5;
    public static final int POPUPMENU_PHONE_SEND_SMS = 10;
    public static final int POPUPMENU_PHONE_CALL = 11;
    public static final int POPUPMENU_PHONE_DIAL = 5;
    public static final int POPUPMENU_PHONE_ADD_TO_CONTACTS = 6;
    public static final int POPUPMENU_PHONE_COPY = 4;
    public static final int POPUPMENU_PHONE_SHARE = 14;
    public static final int POPUPMENU_GEO_SHARE = 14;
    public static final int POPUPMENU_MAIL_SEND_EMAIL = 9;
    public static final int POPUPMENU_MAIL_COPY = 4;
    public static final int POPUPMENU_MAIL_SHARE = 14;
    public static final int POPUPMENU_IMAGE_VIEW_IMAGE = 0;
    public static final int POPUPMENU_IMAGE_COPY = 1;
    public static final int POPUPMENU_IMAGE_SHARE = 2;
    public static final int POPUPMENU_IMAGE_SAVE_IMAGE = 3;
    public static final int POPUPMENU_MAIN_COPY = 4;
    public static final int POPUPMENU_MAIN_SHARE = 3;
    public static final int POPUPMENU_MAIN_ADD_TO_BOOKMARKS = 6;
    public static final int POPUPMENU_MAIN_ADD_TO_HOMESCREEN = 7;
    public static final int POPUPMENU_MAIN_WEB_OSINT_HEADERS = 19;
    public static final int POPUPMENU_MAIN_WEB_OSINT_LINKS = 15;
    public static final int POPUPMENU_MAIN_WEB_OSINT_META_TAGS = 20;
    public static final int POPUPMENU_MAIN_WEB_OSINT_ROBOTSTXT = 21;
    public static final int POPUPMENU_MAIN_WEB_OSINT_TRANCEROUTE = 16;
    public static final int POPUPMENU_MAIN_WEB_OSINT_NPING = 17;
    public static final int POPUPMENU_MAIN_WEB_OSINT_WHOIS = 18;
    public static final int POPUPMENU_MAIN_WEB_OSINT_NSLookup = 13;
    public static final int POPUPMENU_MAIN_WEB_OSINT_SOURCE_CODE = 5;
    public static final int POPUPMENU_MAIN_WEB_OSINT_SAVE_LINK = 12;
    public static final int LINKS = 0;
    public static final int TRANCEROUTE = 1;
    public static final int NPING = 2;
    public static final int WHOIS = 3;
    public static final int META_TAGS = 4;
    public static final int HEADERS = 5;
    public static final int ROBOTS = 6;
    public static final int SOURCE_CODE = 7;
    public static final int IP_GEO = 8;
    public static final int BASE64_ENCODE = 1;
    public static final int URL_ENCODE = 2;
    public static final int ASSETLINKS = 9;
    public static final int SITEMAPS = 10;
    public static final String WEBVIUM_HOME = "https://mrepol742.github.io/Search";
    final int WEBVIUM_SEARCH = 0;
    final int WEBVIUM_HISTORY = 2;
    final int WEBVIUM_BOOKMARKS = 4;
    private MainReceiver ipH;

    final MenuItem.OnMenuItemClickListener mio = a1 -> {
        switch (a1.getItemId()) {
            case POPUPMENU_TOOLBAR_PASTE_AND_SEARCH:
                c13();
                return true;
            case POPUPMENU_TOOLBAR_PASTE:
                c17();
                return true;
            case POPUPMENU_TOOLBAR_COPY_LINK:
                Clipboard.a(MAIN.this, h.getUrl());
                c8(getString(R.string.k9));
                return true;
            case POPUPMENU_TOOLBAR_SHARE_LINK:
                c16(h.getUrl(), 0);
                return true;
            case POPUPMENU_TOOLBAR_ADD_TO_HOMESCREEN:
                c58(h.getUrl(), h.getTitle());
                return true;
            case POPUPMENU_TOOLBAR_ADD_TO_BOOKMARKS:
                c14(h.getTitle(), h.getUrl());
                return true;
            case POPUPMENU_TOOLBAR_COPY_TITLE:
                Clipboard.a(MAIN.this, h.getTitle());
                c8(getString(R.string.k9));
                return true;
            case POPUPMENU_TOOLBAR_SHARE_TITLE:
                c16(h.getTitle(), 0);
                return true;
        }
        return false;
    };

    final MenuItem.OnMenuItemClickListener e4 = a1 -> {
        switch (a1.getItemId()) {
            case POPUPMENU_PHONE_CALL:
                c106(sg);
                return true;
            case POPUPMENU_PHONE_SEND_SMS:
                c105(sg);
                return true;
            case POPUPMENU_PHONE_SHARE:
                c16(sg, 1);
                return true;
            case POPUPMENU_PHONE_COPY:
                Clipboard.a(MAIN.this, sg);
                c8(getString(R.string.k9));
                return true;
            case POPUPMENU_PHONE_DIAL:
                c104(sg);
                return true;
            case POPUPMENU_PHONE_ADD_TO_CONTACTS:
                c110(sg);
                return true;
        }
        return false;
    };

    final MenuItem.OnMenuItemClickListener e3 = a1 -> {
        if (a1.getItemId() == POPUPMENU_GEO_SHARE) {
            c16(sg, 1);
            return true;
        }
        return false;
    };

    final MenuItem.OnMenuItemClickListener e2 = a1 -> {
        switch (a1.getItemId()) {
            case POPUPMENU_MAIL_COPY:
                Clipboard.a(MAIN.this, sg);
                c8(getString(R.string.k9));
                return true;
            case POPUPMENU_MAIL_SEND_EMAIL:
                c65(Objects.requireNonNull(sg));
                return true;
            case POPUPMENU_MAIL_SHARE:
                c16(sg, 1);
                return true;
        }
        return false;
    };

    final MenuItem.OnMenuItemClickListener e1 = a1 -> {
        switch (a1.getItemId()) {
            case 3:
                c16(sg, 0);
                return true;
            case 4:
                Clipboard.a(MAIN.this, sg);
                c8(getString(R.string.k9));
                return true;

            case 6:
                c14(null, sg);
                return true;
            case 7:
                c58(sg, null);
                return true;
            case 12:
                if (Permission.check(MAIN.this, Permission.STORAGE, 4)) {
                    c55(sg, null);
                }
                return true;
            case 13:
                c43(sg);
                return true;
            case 5:
                c112(sg, 7);
                return true;
            case 15:
                c112(sg, 0);
                return true;
            case 16:
                c112(sg, 1);
                return true;
            case 17:
                c112(sg, 2);
                return true;
            case 18:
                c112(sg, 3);
                return true;
            case 19:
                c112(sg, 5);
                return true;
            case 20:
                c112(sg, 4);
                return true;
            case 21:
                c112(sg, 6);
                return true;
            case 25:
                c112(sg, ASSETLINKS);
                return true;
            case 26:
                c112(sg, SITEMAPS);
                return true;
        }
        return false;
    };

    final MenuItem.OnMenuItemClickListener e23 = a1 -> {
        switch (a1.getItemId()) {
            case POPUPMENU_IMAGE_VIEW_IMAGE:
                c3(sg);
                return true;
            case POPUPMENU_IMAGE_COPY:
                Clipboard.a(MAIN.this, sg);
                c8(getString(R.string.k9));
                return true;
            case POPUPMENU_IMAGE_SHARE:
                c16(sg, 0);
                return true;
            case POPUPMENU_IMAGE_SAVE_IMAGE:
                c74(new PendingDownloadDataModel(sg, sg, sg, 0L, sg));
                return true;
        }
        return false;
    };

    public static void c63() {
        bl4 = true;
    }

    @Override
    protected void onActivityResult(int a, int b, Intent c) {
        super.onActivityResult(a, b, c);
        if (a == 742) {
            if (b == Activity.RESULT_OK && null != c) {
                String query = c.getStringExtra("a");
                c8(String.format(getString(R.string.d39), Objects.requireNonNull(query)));
                c49(query);
                d2.c(query);
                c.removeExtra("a");
            }
        }
        if (a == 911 || a == 211 || a == 2115) {
            if (b == Activity.RESULT_OK && null != c) {
                String z = c.getStringExtra("value");
                if (z != null) {
                    c49(Objects.requireNonNull(z));
                    c.removeExtra("value");
                    return;
                }
                String queryq = c.getStringExtra("b");
                if (queryq != null) {
                    c146(0);
                    c.removeExtra("b");
                    return;
                }
            }
        }
        if (a == 3) {
            if (b == Activity.RESULT_OK && null != c) {
                c3(c.getDataString());
            }
        }
        if (a == 345) {
            if (b != Activity.RESULT_OK) {
                finishAndRemoveTask();
            } else {
                a225("aso", false);
            }
        }
        if (a == 2) {
            if (b == Activity.RESULT_OK && null != c) {
                this.b.onReceiveValue(new Uri[]{Uri.parse(c.getDataString())});
                this.b = null;
            } else if (this.b != null) {
                this.b = null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle a) {
        super.onCreate(a);
        cdt = new Timer();
        timer = new Timer();
        int k5 = getSharedPreferences(WELC.INIT, 0).getInt("noid", 0);
        if (k5 != 275) {
            Intents.a(this, WELC.class);
        }
        if (k5 == 275 && a221().getBoolean("lockWn99", false)) {
            Intent it = new Intent(this, LOCK.class);
            startActivityForResult(it, 345);
            overridePendingTransition(R.anim.f, R.anim.b);
        }
        c41();

        a225(R.layout.c);


        this.o = findViewById(R.id.f);
        this.g = findViewById(R.id.h);
        if (a221().getBoolean("enableSWDD", false)) {
            WebView.enableSlowWholeDocumentDraw();
        }
        this.h = new com.mrepol742.webvium.app.WebViews(this);
        fl = findViewById(R.id.i);
        fl.addView(h);
        tv = findViewById(R.id.d19);
        tv1 = findViewById(R.id.b1);
        tv2 = findViewById(R.id.z);
        tv3 = findViewById(R.id.r);
        tv4 = findViewById(R.id.s);
        tv5 = findViewById(R.id.q);
        tv6 = findViewById(R.id.m5);
        tv7 = findViewById(R.id.m6);
        tv8 = findViewById(R.id.m7);
        tv9 = findViewById(R.id.m8);
        this.A = new ForegroundColorSpan(Resources.getColor(this, R.color.a));
        this.E = new ForegroundColorSpan(Resources.getColor(this, R.color.e));
        this.S = new ForegroundColorSpan(Resources.getColor(this, R.color.s));
        this.I = new ForegroundColorSpan(Resources.getColor(this, R.color.i));
        this.B = new ForegroundColorSpan(Resources.getColor(this, R.color.b));
        this.cd = findViewById(R.id.v);
        HorizontalScrollView hsv = findViewById(R.id.c13);
        this.iw = findViewById(R.id.d20);
        this.u = findViewById(R.id.g);
        this.back23 = findViewById(R.id.e);
        this.inf = findViewById(R.id.f7);
        this.tv10 = findViewById(R.id.m9);
        this.llt = findViewById(R.id.m4);
        this.llt.setBackgroundResource(R.drawable.f1);
        setActionBar(this.o);
        this.ab = getActionBar();
        if (this.ab != null) {
            this.ab.setDisplayShowTitleEnabled(false);
        }
        this.o.setElevation(5);
        this.iw.setOnClickListener(view -> c75());
        this.iw.setOnLongClickListener(view -> {
            c140();
            return true;
        });
        this.a7 = Resources.getColor(this, R.color.c);
        this.a8 = Resources.getColor(this, R.color.b);
        this.a9 = Resources.getColor(this, R.color.a);
        this.cd.setOnClickListener(view -> c22());
        this.cd.setOnLongClickListener(view -> {
            c12();
            return true;
        });
        this.u.setOnClickListener(view -> c22());
        hsv.setOnClickListener(view -> c22());
        this.u.setTypeface(type(Typeface.NORMAL));
        this.g.setMax(100);
        tv7.setImageResource(R.drawable.d7);
        tv7.setOnClickListener(view -> c144());
        tv7.setBackgroundResource(R.drawable.b17);
        tv8.setImageResource(R.drawable.d8);
        tv8.setBackgroundResource(R.drawable.b17);
        tv8.setOnClickListener(view -> Intents.f(this, HIST.class, 211));
        tv9.setImageResource(R.drawable.d9);
        tv9.setBackgroundResource(R.drawable.b17);
        tv9.setOnClickListener(view -> Intents.f(this, BOOK.class, 2115));
        try {
            this.d1 = HistoryHelper.getInstance(getApplicationContext());
            this.d2 = SearchHelper.getInstance(getApplicationContext());
            this.d3 = BookmarkHelper.getInstance(getApplicationContext());
            this.d12 = PermissionHelper.getInstance(getApplicationContext());
            this.ws = h.getSettings();
            c134();
            if (!a221().getBoolean("autoUpdate", false)) {
                this.u.setTextColor(this.a7);
            } else {
                this.u.setTextColor(this.a8);
            }
            c149();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.cm1 = CookieManager.getInstance();
        if (Objects.requireNonNull(a221().getString("screen", "")).equals("30j")) {
            this.br2 = new R36();
        }
        c34();
        eh.put("DNT", "1");
        this.ift.act("android.net.conn.CONNECTIVITY_CHANGE");
        this.ift.act("android.net.wifi.WIFI_STATE_CHANGED");
        this.ift.act("android.intent.action.AIRPLANE_MODE");
        this.it742 = getRequestedOrientation();
        if (Objects.requireNonNull(a221().getString("screen", "")).equals("30j")) {
            this.ift1.act("android.intent.action.BATTERY_CHANGED");
        }
        if (a221().getBoolean("webviumB", false)) {
            br4 = new ca149();
            ift3.act(Intents.ACTION_INVALIDATE);
            registerReceiver(br4, ift3);
        }
        pm = (PowerManager) getSystemService(POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "webvium:" + this);
        tv5.setImageResource(R.drawable.a18);
        tv5.setBackgroundResource(R.drawable.b17);
        this.iw.setImageResource(R.drawable.a15);
        this.cd.setBackgroundResource(R.drawable.w);
        c15();
        c50();
        tv3.setOnClickListener(view -> {
            if (h.getProgress() == 100) {
                h.reload();
            } else {
                h.stopLoading();
                h.getFirstClient().onPageFinished(h, h.getUrl());
            }
        });
        if (a221().getBoolean("home", true)) {
            tv1.setOnClickListener(view -> c51());
        }
        if (a221().getBoolean("voice", true) && !spr()) {
            tv2.setOnClickListener(view -> {
                Intent a12 = new Intent(this, VOIC.class);
                startActivityForResult(a12, 742);
            });
        }

        tv4.setOnClickListener(view -> {
            if (h.canGoForward()) {
                h.goForward();
            }
        });
        MenuItem.OnMenuItemClickListener e = a1 -> {
            c3(a1.getTitle().toString());
            return true;
        };
        tv4.setOnLongClickListener(view -> {
            if (h.w4.size() == 0) {
                return false;
            }
            if (pm0 == null) {
                pm0 = new PopupMenu(MAIN.this, view);
                pm0.setOnDismissListener(popupMenu -> popupMenu.getMenu().clear());
            }
            Menu me = pm0.getMenu();
            int size = h.w4.size();
            for (int i = 0; i < size; i++) {
                if (U3.b(h.w4.get(i).sg))
                    me.add(0, i, 0, h.w4.get(i).sg).setOnMenuItemClickListener(e);
            }
            pm0.show();
            return true;
        });
        tv6.setOnClickListener(view -> {
            if (h.canGoBack()) {
                h.goBack();
            }
        });
        tv6.setOnLongClickListener(view -> {
            if (h.w4.size() == 0) {
                return false;
            }
            if (pm0 == null) {
                pm0 = new PopupMenu(MAIN.this, view);
                pm0.setOnDismissListener(popupMenu -> popupMenu.getMenu().clear());
            }
            Menu me = pm0.getMenu();
            int size = h.w4.size();
            for (int i = 0; i < size; i++) {
                if (U3.b(h.w4.get(i).sg0))
                    me.add(0, i, 0, h.w4.get(i).sg0).setOnMenuItemClickListener(e);
            }
            pm0.show();
            return true;
        });
        tv.setOnLongClickListener(view -> {
            if (Connectivity.isAirplaneMode(this)) {
                tv.setVisibility(View.GONE);
                Animation.animate(this, R.anim.b, tv);
                Toast.a(this, getString(R.string.m24));
            } else {
                tv.setVisibility(View.GONE);
                Animation.animate(this, R.anim.b, tv);
                Toast.a(this, getString(R.string.m25));
            }
            return true;
        });
        h.setOnLongClickListener(view -> c152());
        tv.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Animation.animate(this, R.anim.a, llt);
            if (!a221().getBoolean("textST", true)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    ws.setDisabledActionModeMenuItems(WebSettings.MENU_ITEM_NONE);
                }
            }
            c52();
            if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
                if (r7 == null) {
                    r7 = new R7();
                    registerReceiver(r7, new IntentsFilter(Intent.ACTION_SCREEN_ON));
                } else {
                    boolean bn = a224("aso", false);
                    if (bn) {
                        Intent it = new Intent(this, LOCK.class);
                        startActivityForResult(it, 345);
                        overridePendingTransition(R.anim.f, R.anim.b);
                    }
                }
            }
            if (!pm.isPowerSaveMode()) {
                wl.acquire(10 * 60 * 1000L /*10 minutes*/);
            } else if (Build.VERSION.SDK_INT >= 23 && pm.isIgnoringBatteryOptimizations(Package.b())) {
                wl.acquire(10 * 60 * 1000L /*10 minutes*/);
            }
            if (Objects.equals(h.getTitle(), getSharedPreferences("di", 0).getString("di", "742"))) {
                h.reload();
            }
            if (Build.VERSION.SDK_INT >= 24) {
                boolean bn1 = isInMultiWindowMode() || isInPictureInPictureMode();
                if (bn1) {
                    c176(true);
                }
            }
            h.resumeTimers();
            h.onResume();
            c15();
            bl = true;
            bl2 = true;
            registerReceiver(br, ift);
            if (Objects.requireNonNull(a221().getString("screen", "")).equals("30j")) {
                registerReceiver(br2, ift1);
            }
            if (a221().getBoolean("home", true)) {
                tv1.setVisibility(View.VISIBLE);
                tv1.setImageResource(R.drawable.a12);
                tv1.setBackgroundResource(R.drawable.b17);
            } else {
                tv1.setVisibility(View.GONE);
            }
            if (a221().getBoolean("voice", true) && !spr()) {
                tv2.setVisibility(View.VISIBLE);
                tv2.setBackgroundResource(R.drawable.b17);
                tv2.setImageResource(R.drawable.f);
            } else {
                tv2.setVisibility(View.GONE);
            }
            onNewIntent(getIntent());
            WebView.setWebContentsDebuggingEnabled(a221().getBoolean("webP", false));
            if (a221().getBoolean("blocksv", false)) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
            }
            c99();
            c108();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(br);
            if (br2 != null) {
                unregisterReceiver(br2);
            }
            if (wl.isHeld()) {
                wl.release();
            }
            h.pauseTimers();
            h.onPause();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        bl = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (h != null) {
            fl.removeView(h);
            h.removeAllViews();
            h.destroy();
        }
        bl2 = false;
        if (r7 != null) {
            unregisterReceiver(r7);
        }
        if (br4 != null) {
            unregisterReceiver(br4);
        }
    }

    public View c1() {
        View v = View.inflate(this, R.layout.a21, null);
        ImageView v4 = v.findViewById(R.id.n28);
        v4.setImageResource(R.drawable.a21);
        return v;
    }

    public void c2() {
        if (h.canGoForward()) {
            h.goForward();
        }
    }

    private void c3(String a) {
        c33(a, "");
        h.loadUrl(a);
    }

    public void c4(Message b, Message c) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(false);
        a.setTitle(getString(R.string.g36));
        a.setMessage(getString(R.string.g34));
        a.setPositiveButton(getString(R.string.g36), (a12, intetg) -> {
            c.sendToTarget();
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> {
            b.sendToTarget();
            a1.dismiss();
        });
        a.create().show();
    }

    private void c5(Bitmap b) {
        tv5.setImageBitmap(b);
        Animation.animate(MAIN.this, R.anim.c, tv5);
    }

    private String c6(ConsoleMessage.MessageLevel lev) {
        if (lev == ConsoleMessage.MessageLevel.TIP) {
            return "#ff34a853";
        } else if (lev == ConsoleMessage.MessageLevel.ERROR) {
            return "#ffea4335";
        } else if (lev == ConsoleMessage.MessageLevel.WARNING) {
            return "#fffbbc05";
        } else {
            if (!a221().getBoolean("autoUpdate", false)) {
                return "#ff484848";
            } else {
                return "#ffffffff";
            }
        }
    }

    public void c7(String a) {
        Toast.c(this, a);
    }

    public void c8(String a) {
        Toast.b(this, a);
    }

    public void c9(PermissionRequest pr) {
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage(String.format(getString(R.string.i38), Objects.requireNonNull(pr.getOrigin().getHost()), Arrays.toString(pr.getResources())));
        d.setCancelable(false);
        d.setPositiveButton(getString(R.string.v17), (a1, i) -> {
            if (Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE) && Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
                if (!Permission.check(this, Permission.CAMERA, 9) && !Permission.check(this, Permission.MICROPHONE, 10)) {
                    w8 = new PermissionDataModel(pr);
                } else {
                    pr.grant(pr.getResources());
                    d12.c(new PermissionObjectDataModel(Hash.a("SHA-1", pr.getOrigin().toString()),
                            Hash.a("SHA-1", Arrays.toString(pr.getResources())),
                            "true",
                            "false"));
                    c8(String.format(getString(R.string.i40), pr.getOrigin(), Arrays.toString(pr.getResources())));
                }
            } else if (Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE)) {
                if (!Permission.check(this, Permission.CAMERA, 6)) {
                    w8 = new PermissionDataModel(pr);
                } else {
                    pr.grant(pr.getResources());
                    d12.c(new PermissionObjectDataModel(Hash.a("SHA-1", pr.getOrigin().toString()),
                            Hash.a("SHA-1", Arrays.toString(pr.getResources())),
                            "true",
                            "false"));
                    c8(String.format(getString(R.string.i40), pr.getOrigin(), Arrays.toString(pr.getResources())));
                }
            } else if (Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
                if (!Permission.check(this, Permission.MICROPHONE, 7)) {
                    w8 = new PermissionDataModel(pr);
                } else {
                    pr.grant(pr.getResources());
                    d12.c(new PermissionObjectDataModel(Hash.a("SHA-1", pr.getOrigin().toString()),
                            Hash.a("SHA-1", Arrays.toString(pr.getResources())),
                            "true",
                            "false"));
                    c8(String.format(getString(R.string.i40), pr.getOrigin(), Arrays.toString(pr.getResources())));
                }
            }
            a1.dismiss();
        });
        d.setNegativeButton(getString(R.string.i39), (a1, i) -> {
            pr.deny();
            c7(String.format(getString(R.string.j21), pr.getOrigin().getHost(), Arrays.toString(pr.getResources())));
            a1.dismiss();
        });
        AlertDialog e = d.create();
        e.show();
    }

    private void c11(PendingDownloadDataModel w18) {
        try {

            String b = URLUtil.guessFileName(w18.a1, w18.a2, w18.a3);
            AlertDialog.Builder c = new AlertDialog.Builder(this);
            LayoutInflater d = getLayoutInflater();
            View e = d.inflate(R.layout.j, null);
            c.setCancelable(true);
            c.setTitle(getString(R.string.l5));
            c.setView(e);
            TextView f = e.findViewById(R.id.a5);
            final EDIT s = e.findViewById(R.id.d1);
            TextView h = e.findViewById(R.id.d2);
            TextView i = e.findViewById(R.id.d3);
            final TextView j = e.findViewById(R.id.d4);
            TextView k = e.findViewById(R.id.b9);
            TextView l = e.findViewById(R.id.c11);
            TextView m = e.findViewById(R.id.c12);
            TextView j2 = e.findViewById(R.id.e8);
            TextView j3 = e.findViewById(R.id.e9);
            TextView j4 = e.findViewById(R.id.e10);
            TextView j5 = e.findViewById(R.id.e11);
            TextView j6 = e.findViewById(R.id.e12);
            TextView j7 = e.findViewById(R.id.e13);
            f.setText(getString(R.string.c30));
            s.setText(b);
            k.setText(getString(R.string.d40));
            if (w18.a4 != 0) {

                j4.setText(getString(R.string.e23));
                Runnable re = () -> {
                    String fn = Formatter.formatFileSize(MAIN.this, w18.a4);
                    String fn1 = Formatter.formatFileSize(MAIN.this, new java.io.File(Objects.requireNonNull(getExternalFilesDir(null)).toString()).getFreeSpace() + w18.a4);
                    runOnUiThread(() -> {
                        h.setText(fn);
                        j5.setText(fn1);
                    });
                };
                new Thread(re).start();
            } else {
                h.setText(getString(R.string.w22));
                j4.setVisibility(View.GONE);
                j5.setVisibility(View.GONE);
            }
            l.setText(getString(R.string.e21));
            i.setText(w18.a1);
            m.setText(getString(R.string.c31));
            String sg7 = StorageDirectory.getWebviumDir() + "/Downloads/" + b;
            j.setText(sg7);
            j2.setText(getString(R.string.e22));
            j6.setText(getString(R.string.e24));
            Runnable re = () -> {
                String j3a = Formatter.formatFileSize(MAIN.this, new java.io.File(Objects.requireNonNull(getExternalFilesDir(null)).toString()).getFreeSpace());
                String j7a = Formatter.formatFileSize(MAIN.this, new java.io.File(Objects.requireNonNull(getExternalFilesDir(null)).toString()).getTotalSpace());
                runOnUiThread(() -> {
                    j3.setText(j3a);
                    j7.setText(j7a);
                });
            };
            new Thread(re).start();
            if (!a221().getBoolean("autoUpdate", false)) {
                f.setTextColor(this.a7);
                k.setTextColor(this.a7);
                l.setTextColor(this.a7);
                m.setTextColor(this.a7);
                s.setTextColor(this.a7);
                h.setTextColor(this.a7);
                i.setTextColor(this.a7);
                j.setTextColor(this.a7);
                j2.setTextColor(this.a7);
                j3.setTextColor(this.a7);
                j4.setTextColor(this.a7);
                j5.setTextColor(this.a7);
                j6.setTextColor(this.a7);
                j7.setTextColor(this.a7);
            } else {
                f.setTextColor(this.a8);
                k.setTextColor(this.a8);
                l.setTextColor(this.a8);
                m.setTextColor(this.a8);
                s.setTextColor(this.a8);
                h.setTextColor(this.a8);
                i.setTextColor(this.a8);
                j.setTextColor(this.a8);
                j2.setTextColor(this.a8);
                j3.setTextColor(this.a8);
                j4.setTextColor(this.a8);
                j5.setTextColor(this.a8);
                j6.setTextColor(this.a8);
                j7.setTextColor(this.a8);
            }
            c.setPositiveButton(getString(R.string.e25), (a34, m1) -> {
                try {
                    String c1 = s.getText().toString();
                    c8(getString(R.string.e27));
                    if (a221().getBoolean("launch", false)) {
                        Intents.a(MAIN.this, DOWN.class);
                    }
                    // DownloadHelper downloadHelper = DownloadHelper.getInstance(getApplicationContext());
                    // downloadHelper.c(new DownloadNewDataModel("", "", "", ""));
                    SharedPreferences sp9 = MAIN.this.getSharedPreferences("wv", 0);
                    Intent it = new Intent(MAIN.this, DOWN0.class);
                    it.putExtra("a", c1);
                    it.putExtra("b", w18.a1);
                    it.putExtra("c", true);
                    it.putExtra("d", true);
                    it.putExtra("e", w18.a3);
                    it.putExtra("f", w18.a1);
                    it.putExtra("g", w18.a6);
                    Notifications.b(MAIN.this, it);
                    if (Build.VERSION.SDK_INT >= 23 && !Objects.requireNonNull(sp9.getString("downAlert", "")).equals("a")) {
                        c71();
                        SharedPreferences.Editor spe = sp9.edit();
                        spe.putString("downAlert", "a");
                        spe.apply();
                    }
                } catch (Exception d4) {
                    d4.printStackTrace();
                }
                a34.dismiss();
            });
            c.setNegativeButton(getString(R.string.i7), (a, intetg) -> a.dismiss());
            final AlertDialog g = c.create();
            g.show();
            final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
            s.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String jhh56 = s.getText().toString();
                    String sg78 = StorageDirectory.getWebviumDir() + "/Downloads/" + jhh56;
                    j.setText(sg78);
                    if (U3.b(jhh56)) {
                        java.io.File a90 = new java.io.File(StorageDirectory.getWebviumDir() + "/Downloads/" + jhh56 + "." + Package.c().toLowerCase());
                        if (a90.exists()) {
                            s.setError(getString(R.string.u19));
                            okButton.setEnabled(false);
                        } else {
                            okButton.setEnabled(true);
                        }
                    } else {
                        okButton.setEnabled(false);
                    }
                }


            });

            String jhh56 = s.getText().toString();
            java.io.File a90 = new java.io.File(StorageDirectory.getWebviumDir() + "/Downloads/" + jhh56 + "." + Package.c().toLowerCase());
            if (a90.exists()) {
                s.setError(getString(R.string.u19));
                g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            } else {
                g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        } catch (Exception haha) {
            haha.printStackTrace();
        }
    }

    private void c12() {
        if (pm6 == null) {
            pm6 = new PopupMenu(this, this.iw);
        }
        Menu menu = pm6.getMenu();
        if (Clipboard.c(this)) {
            menu.add(0, POPUPMENU_TOOLBAR_PASTE_AND_SEARCH, 0, getString(R.string.i2)).setOnMenuItemClickListener(mio);
            menu.add(0, POPUPMENU_TOOLBAR_PASTE, 0, getString(R.string.i3)).setOnMenuItemClickListener(mio);
        }
        SubMenu sb3 = menu.addSubMenu(getString(R.string.u));
        sb3.add(0, POPUPMENU_TOOLBAR_COPY_LINK, 0, getString(R.string.t4)).setOnMenuItemClickListener(mio);
        sb3.add(0, POPUPMENU_TOOLBAR_COPY_TITLE, 0, getString(R.string.s23)).setOnMenuItemClickListener(mio);
        SubMenu sb2 = menu.addSubMenu(getString(R.string.a8));
        sb2.add(0, POPUPMENU_TOOLBAR_SHARE_LINK, 0, getString(R.string.t4)).setOnMenuItemClickListener(mio);
        sb2.add(0, POPUPMENU_TOOLBAR_SHARE_TITLE, 0, getString(R.string.s23)).setOnMenuItemClickListener(mio);
        SubMenu sb = menu.addSubMenu(getString(R.string.h17));
        sb.add(0, POPUPMENU_TOOLBAR_ADD_TO_HOMESCREEN, 0, getString(R.string.h12)).setOnMenuItemClickListener(mio);
        sb.add(0, POPUPMENU_TOOLBAR_ADD_TO_BOOKMARKS, 0, getString(R.string.h11)).setOnMenuItemClickListener(mio);
        pm6.setOnDismissListener(popupMenu -> popupMenu.getMenu().clear());
        pm6.show();
    }

    public void c13() {
        try {
            String c = Clipboard.b(this);
            if (c != null) {
                c49(c);
                d2.c(c);
            } else {
                c7(getString(R.string.t20));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            c7(getString(R.string.t20));
        }
    }

    public void c14(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h11));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ed1.setTextColor(e);
            ti.setTextColor(e);
            ti1.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ed1.setTextColor(f);
            ti.setTextColor(f);
            ti1.setTextColor(f);
        }
        ti.setText(getString(R.string.t3));
        ti1.setText(getString(R.string.t4));
        if (a23 != null) {
            ed.setText(a23);
        } else {
            Uri hl = Uri.parse(asd);
            ed.setText(hl.getHost());
        }
        ed1.setText(asd);
        a.setPositiveButton(getString(R.string.i6), (a2, i) -> {
            d3.c(ed.getText().toString(), ed1.getText().toString());
            c8(getString(R.string.t2));
        });
        a.setNegativeButton(getString(R.string.i7), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed) && U3.a(ed1));
    }

    private void c15() {
        if (a221().getBoolean("Javaweb", true)) {
            h.addJavascriptInterface(new WebviumJSI(this), Package.c());
            h.addJavascriptInterface(new Object() {

                @JavascriptInterface
                public void print() {
                    c100(h);
                }
            }, Package.c()+"PrintHelper");
            h.addJavascriptInterface(new HashJSI(), Package.c()+"HashHelper");
            h.addJavascriptInterface(new XORJSI(), Package.c()+"XORHelper");
        } else {
            h.removeJavascriptInterface(Package.c());
            h.removeJavascriptInterface(Package.c()+"PrintHelper");
            h.removeJavascriptInterface(Package.c()+"HashHelper");
            h.removeJavascriptInterface(Package.c()+"XORHelper");
        }
        if (Objects.requireNonNull(a221().getString("setLT", "7l")).equals("1l")) {
            h.setLayerType(View.LAYER_TYPE_NONE, null);
        }
        if (Objects.requireNonNull(a221().getString("setLT", "7l")).equals("7l")) {
            h.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        if (Objects.requireNonNull(a221().getString("setLT", "7l")).equals("30l")) {
            h.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (Objects.requireNonNull(a221().getString("renderP", "1s")).equals("1s")) {
                h.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_BOUND, true);
            }
            if (Objects.requireNonNull(a221().getString("renderP", "1s")).equals("7s")) {
                h.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_IMPORTANT, true);
            }
            if (Objects.requireNonNull(a221().getString("renderP", "1s")).equals("30s")) {
                h.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_WAIVED, true);
            }
        }
        if (a223("dr") == 0) {
            ws.setTextZoom(h.getTextZoom());
        } else {
            ws.setTextZoom(a223("dr"));
        }
        if (a223("dr55") == 0) {
            ws.setDefaultFontSize(h.getFontSize());
        } else {
            ws.setDefaultFontSize(a223("dr55"));
        }
        ws.setLoadWithOverviewMode(a221().getBoolean("open", false));
        ws.setUseWideViewPort(a221().getBoolean("open1", false));
        ws.setDomStorageEnabled(a221().getBoolean("open2", true));
        ws.setDatabaseEnabled(a221().getBoolean("open3", true));
        if (Build.VERSION.SDK_INT >= 29) {
            if (a221().getBoolean("wfdM01", true)) {
                ws.setForceDark(WebSettings.FORCE_DARK_ON);
            } else {
                ws.setForceDark(WebSettings.FORCE_DARK_OFF);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            ws.setSafeBrowsingEnabled(a221().getBoolean("save", true));
        }
        ws.setBlockNetworkImage(a221().getBoolean("block", false));
        ws.setDisplayZoomControls(a221().getBoolean("zoom", false));
        ws.setMediaPlaybackRequiresUserGesture(a221().getBoolean("auto", false));
        ws.setSupportZoom(a221().getBoolean("enableZ", true));
        if (Objects.requireNonNull(a221().getString("cookies", "7")).equals("1")) {
            cm1.setAcceptCookie(true);
            cm1.setAcceptThirdPartyCookies(h, false);

        }
        if (Objects.requireNonNull(a221().getString("cookies", "7")).equals("7")) {
            cm1.setAcceptThirdPartyCookies(h, true);
            cm1.setAcceptCookie(true);
        }
        if (Objects.requireNonNull(a221().getString("cookies", "7")).equals("30")) {
            cm1.setAcceptCookie(false);
            cm1.setAcceptThirdPartyCookies(h, false);
        }
        if (Objects.requireNonNull(a221().getString("cookies", "7")).equals("60")) {
            cm1.setAcceptCookie(true);
            cm1.setAcceptThirdPartyCookies(h, true);
            CookieManager.allowFileSchemeCookies();
        }
        if (Objects.requireNonNull(a221().getString("java", "1f")).equals("1f")) {
            c166();
        }
        if (Objects.requireNonNull(a221().getString("java", "1f")).equals("7f")) {
            ws.setJavaScriptEnabled(false);
        }
        if (Objects.requireNonNull(a221().getString("mcm", "1r")).equals("1r")) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Objects.requireNonNull(a221().getString("mcm", "1r")).equals("7r")) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        if (Objects.requireNonNull(a221().getString("mcm", "1r")).equals("30r")) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("1a")) {
            ws.setDefaultTextEncodingName(h.getTextEncoding());
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("7a")) {
            ws.setDefaultTextEncodingName(code[0]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("30a")) {
            ws.setDefaultTextEncodingName(code[1]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("60a")) {
            ws.setDefaultTextEncodingName(code[2]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("120a")) {
            ws.setDefaultTextEncodingName(code[3]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("240a")) {
            ws.setDefaultTextEncodingName(code[4]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("480a")) {
            ws.setDefaultTextEncodingName(code[5]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("960a")) {
            ws.setDefaultTextEncodingName(code[6]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("1920a")) {
            ws.setDefaultTextEncodingName(code[7]);
        }
        if (Objects.requireNonNull(a221().getString("textE", "1a")).equals("3840a")) {
            ws.setDefaultTextEncodingName(a221().getString("CtextE", ""));
        }
        if (Build.VERSION.SDK_INT < 26) {
            if (Objects.requireNonNull(a221().getString("form", "1g")).equals("1g")) {
                ws.setSaveFormData(true);
            }
            if (Objects.requireNonNull(a221().getString("form", "1g")).equals("30g")) {
                ws.setSaveFormData(false);
            }
        }
        if (Objects.requireNonNull(a221().getString("location", "1h")).equals("1h")) {
            ws.setGeolocationEnabled(true);
            if (Build.VERSION.SDK_INT < 24) {
                ws.setGeolocationDatabasePath(getFilesDir().toString());
            }
        }
        if (Objects.requireNonNull(a221().getString("location", "1h")).equals("30h")) {
            ws.setGeolocationEnabled(false);
        }
        if (Build.VERSION.SDK_INT < 30) {
            ws.setAppCacheEnabled(a221().getBoolean("open4", true));
            Runnable re = () -> {
                String sg = StorageDirectory.getCacheDir(this).toString();
                runOnUiThread(() -> ws.setAppCachePath(sg));
            };
            new Thread(re).start();
        }
        if (Objects.requireNonNull(a221().getString("caches", "1m")).equals("1m")) {
            ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        if (Objects.requireNonNull(a221().getString("caches", "1m")).equals("7m")) {
            ws.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }
        if (Objects.requireNonNull(a221().getString("caches", "1m")).equals("30m")) {
            ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        if (Objects.requireNonNull(a221().getString("caches", "1m")).equals("60m")) {
            ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
    }

    public void c16(String a, int c11) {
        Intent b = new Intent("android.intent.action.SEND");
        b.setType("text/plain");
        b.putExtra("android.intent.extra.TEXT", a);
        String c = getString(R.string.l8);
        String c45 = String.format(c, "\"" + a + "\"");
        if (c11 == 0) {
            c45 = String.format(c, "\"" + h.getTitle() + "\"");
        }
        startActivity(Intent.createChooser(b, c45));
    }

    public void c17() {
        try {
            String c = Clipboard.b(this);
            if (c != null) {
                Intents.h(this, SEAR.class, 911, "value", c);
                overridePendingTransition(R.anim.a, R.anim.f);
            } else {
                c7(getString(R.string.t20));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            c7(getString(R.string.t20));
        }
    }

    public void c20(boolean a) {
        if (a) {
            ws.setUserAgentString(ws.getUserAgentString().replace("Mobile", "eliboM").replace("Android", "diordnA"));
        } else {
            ws.setUserAgentString(ws.getUserAgentString().replace("eliboM", "Mobile").replace("diordnA", "Android"));
        }
        h.reload();
    }

    public void c22() {
        try {
            Intents.h(this, SEAR.class, 911, "value", h.getUrl());
            overridePendingTransition(R.anim.a, R.anim.f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void c23(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            onKeyDown(event.getKeyCode(), event);
        } else {
            onKeyUp(event.getKeyCode(), event);
        }
    }

    public void c25() {
        if (a221().getBoolean("clearP", false)) {
            h.clearCache(false);
        }
        if (a221().getBoolean("clearH", false)) {
            c63();
            d1.delete();
        }
        if (a221().getBoolean("clearC", false)) {
            Clipboard.a(this, " ");
        }
        if (a221().getBoolean("clearPr", false)) {
            c31();
            if (wd == null) {
                wd = WebViewDatabase.getInstance(this);
            }
            if (wd.hasHttpAuthUsernamePassword()) {
                wd.clearHttpAuthUsernamePassword();
            }
            if (Build.VERSION.SDK_INT <= 26 && wd.hasFormData()) {
                wd.clearFormData();
            }
        }
    }

    public void c31() {
        h.clearSslPreferences();
    }

    private void c33(String url, String b) {
        try {
            if (!a221().getBoolean("showW", false)) {
                c35(new SpannableString(url), url, 0);
            } else {
                c35(new SpannableString(b + " | " + url), url, b.length() + 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void c34() {
        if (Build.VERSION.SDK_INT < 30) {
            ws.setAllowFileAccess(true);
        }
        ws.setBuiltInZoomControls(true);
        ws.setSupportMultipleWindows(false);
        ws.setJavaScriptCanOpenWindowsAutomatically(false);
        if (a221().getBoolean("hst", false) || a221().getBoolean("hste", false)) {
            h.setOnTouchListener(new View.OnTouchListener() {
                final int sel = 5;
                float lastY;
                ArrayList<Integer> ali;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    float diff;
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        lastY = event.getY();
                        ali = new ArrayList<>();
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        diff = event.getY() - lastY;
                        lastY = event.getY();
                        if (diff > 0)
                            ali.add(1);
                        else
                            ali.add(-1);
                        if (ali.size() == sel + 1)
                            ali.remove(0);
                        int res = 0;
                        int size = ali.size();
                        for (int i = 0; i < size; i++) {
                            res += ali.get(i);
                        }
                        if (res > 0) {
                            if (a221().getBoolean("hst", false)) {
                                if (findViewById(R.id.m4).getVisibility() != View.VISIBLE) {
                                    ab.show();
                                    Animation.animate(MAIN.this, R.anim.d, o);
                                }
                            }
                            if (a221().getBoolean("hste", false)) {
                                o.setElevation(5);
                            }
                            if (a221().getBoolean("hste0", false)) {
                                findViewById(R.id.m4).setVisibility(View.VISIBLE);
                                Animation.animate(MAIN.this, R.anim.a, findViewById(R.id.m4));
                            }
                        } else {
                            if (a221().getBoolean("hst", false)) {
                                if (findViewById(R.id.m4).getVisibility() != View.GONE) {
                                    ab.hide();
                                    Animation.animate(MAIN.this, R.anim.a, o);
                                }
                            }
                            if (a221().getBoolean("hste", false)) {
                                o.setElevation(0);
                            }
                            if (a221().getBoolean("hste0", false)) {
                                findViewById(R.id.m4).setVisibility(View.GONE);
                                Animation.animate(MAIN.this, R.anim.d, findViewById(R.id.m4));
                            }
                        }
                    }
                    return false;
                }
            });
        }
        h.setDownloadListener((str, str2, str3, str4, j) -> c83(new PendingDownloadDataModel(str, str3, str4, j, str2)));
        h.setWebChromeClient(new WebChromeClient() {

            @Override
            public Bitmap getDefaultVideoPoster() {
                return c84();
            }

            @Override
            public void onReceivedTitle(WebView a, String b) {
                c86(a, b);
            }

            @Override
            public void onHideCustomView() {
                c87();
            }

            @Override
            public void onShowCustomView(View a, WebChromeClient.CustomViewCallback b) {
                c88(a, b);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> b, FileChooserParams fileChooserParams) {
                MAIN.this.b = b;
                Intent d = fileChooserParams.createIntent();
                d.setAction(Intent.ACTION_GET_CONTENT);
                d.addCategory(Intent.CATEGORY_OPENABLE);
                String[] sg = fileChooserParams.getAcceptTypes();
                if (sg.length != 0) {
                    d.setType(sg[1]);
                } else {
                    d.setType("*/*");
                }
                if (d.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 2);
                    return true;
                }
                return false;
            }

            @Override
            public void onProgressChanged(WebView a, int b) {
                g.setProgress(b);
                if (b == 100) {
                    tv3.setImageResource(R.drawable.b11);
                    Animation.animate(MAIN.this, R.anim.c, tv3);
                    g.setVisibility(View.GONE);
                    Animation.animate(MAIN.this, R.anim.b, g);
                    MAIN.this.cm1.flush();
                    cdt.cancel();
                    cdt.purge();
                    if (a224("a10", false) && bl6) {
                        // if (HDMS.b(Objects.requireNonNull(a.getTitle()).toLowerCase()) || HDMS.b(Objects.requireNonNull(a.getUrl()).toLowerCase())) {
                        c142(a.getTitle(), a.getUrl());
                       /* } else {
                            c142(getString(R.string.g29), getString(R.string.g30));
                        }*/
                    } else if (a224("a10", false) && !bl6) {
                        //if (HDMS.b(Objects.requireNonNull(a.getTitle()).toLowerCase()) || HDMS.b(Objects.requireNonNull(a.getUrl()).toLowerCase())) {
                        c143(a.getTitle(), a.getUrl());
                      /*  } else {
                            c143(getString(R.string.g29), getString(R.string.g30));
                        }*/
                    }
                } else if (a.getUrl() != null && cdt == null && !(a.getUrl().startsWith("file://") || a.getUrl().startsWith("webvium://"))) {
                    cdt.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            c141();
                            cdt.cancel();
                            cdt.purge();
                        }
                    }, 10000);
                }
                if (b != 100) {
                    g.setVisibility(View.VISIBLE);
                    Animation.animate(MAIN.this, R.anim.c, g);
                    tv3.setImageResource(R.drawable.a14);
                    Animation.animate(MAIN.this, R.anim.c, tv3);
                }
                tv3.setBackgroundResource(R.drawable.b17);
            }

            @Override
            public boolean onJsAlert(WebView a, String b, String c, JsResult d) {
                if (a221().getBoolean("Java10", true)) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(MAIN.this);
                    LayoutInflater d1 = getLayoutInflater();
                    View e5 = d1.inflate(R.layout.a13, null);
                    bld.setView(e5);
                    bld.setCancelable(true);
                    TextView tv = e5.findViewById(R.id.o37);
                    bld.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    tv.setText(c);
                    if (!a221().getBoolean("autoUpdate", false)) {
                        tv.setTextColor(Resources.getColor(MAIN.this, R.color.c));
                    } else {
                        tv.setTextColor(Resources.getColor(MAIN.this, R.color.b));
                    }
                    bld.setPositiveButton(getString(R.string.i6), (a13, intetg) -> {
                        d.confirm();
                        a13.dismiss();
                    });
                    bld.setOnCancelListener(a1 -> {
                        d.cancel();
                        a1.dismiss();
                    });
                    AlertDialog dd = bld.create();
                    dd.show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onJsPrompt(WebView a, String b, String c, String d, JsPromptResult e) {
                if (a221().getBoolean("Java9", true)) {
                    AlertDialog.Builder a89 = new AlertDialog.Builder(MAIN.this);
                    LayoutInflater b54 = getLayoutInflater();
                    View c34 = b54.inflate(R.layout.x, null);
                    a89.setCancelable(true);
                    a89.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    a89.setView(c34);
                    TextView sjs1 = c34.findViewById(R.id.e1);
                    final EDIT sjs = c34.findViewById(R.id.e3);
                    int e78 = Resources.getColor(MAIN.this, R.color.c);
                    int f = Resources.getColor(MAIN.this, R.color.b);
                    int f1 = Resources.getColor(MAIN.this, R.color.j);
                    int g1 = Resources.getColor(MAIN.this, R.color.k);
                    if (!a221().getBoolean("autoUpdate", false)) {
                        sjs1.setTextColor(e78);
                        sjs.setTextColor(e78);
                        sjs.setHintTextColor(f1);
                    } else {
                        sjs1.setTextColor(f);
                        sjs.setTextColor(f);
                        sjs.setHintTextColor(g1);
                    }
                    sjs1.setText(c);
                    a89.setPositiveButton(getString(R.string.i6), (a13, intetg) -> {
                        String uwe = sjs.getText().toString();
                        e.confirm(uwe);
                    });
                    a89.setNegativeButton(getString(R.string.i7), (a12, intetg) -> e.cancel()).setOnCancelListener(a1 -> {
                        e.cancel();
                        a1.dismiss();
                    });
                    final AlertDialog g = a89.create();
                    g.show();
                    final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
                    sjs.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            okButton.setEnabled(sjs.getText().toString().length() != 0);
                        }
                    });
                    g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onJsConfirm(WebView a, String b, String c, JsResult e) {
                if (a221().getBoolean("Java11", true)) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(MAIN.this);
                    LayoutInflater d1 = getLayoutInflater();
                    View e5 = d1.inflate(R.layout.a13, null);
                    bld.setView(e5);
                    bld.setCancelable(true);
                    TextView tv = e5.findViewById(R.id.o37);
                    bld.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    tv.setText(c);
                    if (!a221().getBoolean("autoUpdate", false)) {
                        tv.setTextColor(Resources.getColor(MAIN.this, R.color.c));
                    } else {
                        tv.setTextColor(Resources.getColor(MAIN.this, R.color.b));
                    }
                    bld.setPositiveButton(getString(R.string.i6), (a13, intetg) -> {
                        e.confirm();
                        a13.dismiss();
                    });
                    bld.setNegativeButton(getString(R.string.i7), (a12, intetg) -> {
                        e.cancel();
                        a12.dismiss();
                    });
                    bld.setOnCancelListener(a1 -> {
                        e.cancel();
                        a1.dismiss();
                    });
                    AlertDialog dd = bld.create();
                    dd.show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onJsBeforeUnload(WebView a, String b, String c, JsResult e) {
                if (a221().getBoolean("Java12", true)) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(MAIN.this);
                    LayoutInflater d1 = getLayoutInflater();
                    View e5 = d1.inflate(R.layout.a13, null);
                    bld.setView(e5);
                    bld.setCancelable(false);
                    TextView tv = e5.findViewById(R.id.o37);
                    bld.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    tv.setText(c);
                    if (!a221().getBoolean("autoUpdate", false)) {
                        tv.setTextColor(Resources.getColor(MAIN.this, R.color.c));
                    } else {
                        tv.setTextColor(Resources.getColor(MAIN.this, R.color.b));
                    }
                    bld.setPositiveButton(getString(R.string.i6), (a13, intetg) -> {
                        e.confirm();
                        a13.dismiss();
                    });
                    bld.setNegativeButton(getString(R.string.i7), (a12, intetg) -> {
                        e.cancel();
                        a12.dismiss();
                    });
                    AlertDialog dd = bld.create();
                    dd.show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage cm1) {
                cm.append(String.format(getString(R.string.v188), cm1.messageLevel().toString(), c6(cm1.messageLevel()), cm1.message(), cm1.lineNumber(), cm1.sourceId()))
                        .append("\n\n");
                return true;
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String a, GeolocationPermissions.Callback b) {
                final boolean c = false;
                AlertDialog.Builder d = new AlertDialog.Builder(MAIN.this);
                d.setMessage(String.format(getString(R.string.v14), a));
                d.setCancelable(false);
                d.setPositiveButton(getString(R.string.v17), (a1, i) -> {
                    if (Permission.check(MAIN.this, Permission.LOCATION, 5)) {
                        b.invoke(a, true, c);
                        c8(String.format(getString(R.string.v15), a));
                    } else {
                        w6 = new GeolocationDataModel(a, b);
                    }
                    a1.dismiss();
                });
                d.setNegativeButton(getString(R.string.i39), (a1, i) -> {
                    b.invoke(a, false, c);
                    c7(String.format(getString(R.string.v16), a));
                    a1.dismiss();
                });
                AlertDialog e = d.create();
                e.show();
            }

            @Override
            public View getVideoLoadingProgressView() {
                return c1();
            }

            @Override
            public void onPermissionRequest(PermissionRequest pr) {
                c129(pr);
            }

            @Override
            public void onReceivedIcon(WebView a, Bitmap b) {
                c5(b);
            }
        });
        h.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        h.setWebViewClient(new MainWebViewClient() {

            @Override
            public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                c23(event);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                c172(handler, host, realm);
            }

            @Override
            public void receivedError(int b, String c, String d, boolean bn, boolean bn1) {
                c77(new ReceivedErrorDataModel(b, c, d, bn, bn1));
            }

            @Override
            public void onReceivedHttpError(WebView a, WebResourceRequest b, WebResourceResponse c) {
                c78(b, c);
            }

            @Override
            public void onReceivedSslError(WebView a, SslErrorHandler b, SslError c) {
                c79(b, c);
            }

            @Override
            public void onPageFinished(WebView a, String b) {
                c80(a, b);
            }

            @Override
            public void onPageStarted(WebView a, String b, Bitmap b5) {
                c81(b);
            }

            @Override
            @Keep
            public boolean url(WebView a, String b) {
                return c82(b);
            }

            @Override
            public void onSafeBrowsingHit(WebView view, WebResourceRequest ess, int type, SafeBrowsingResponse sbh) {
                c60(view, type, sbh);
            }

            @Override
            public void doUpdateVisitedHistory(WebView a, String b, boolean c) {
                c151(a, b, c);
            }

            @Override
            public void onFormResubmission(WebView a, Message b, Message c) {
                c4(b, c);
            }

            @Override
            public WebResourceResponse r(WebResourceRequest wr) {
                return c168(wr);
            }
        });
        h.addJavascriptInterface(new SearchJSI(this), Package.c()+"SearchHelper");
        h.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void setTheme(String a) {
                c36(a);
            }
        }, Package.c()+"ThemeHelper");
        h.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void launch() {
                Intents.a(MAIN.this, VOIC.class);
            }
        }, Package.c() + "VoiceHelper");
        if (Build.VERSION.SDK_INT >= 29) {
            h.setWebViewRenderProcessClient(new WebViewRenderProcessClient() {

                @Override
                public void onRenderProcessUnresponsive(WebView webView, WebViewRenderProcess webViewRenderProcess) {
                    if (a221().getBoolean("wthj56", false)) {
                        webViewRenderProcess.terminate();
                    }
                }

                @Override
                public void onRenderProcessResponsive(WebView webView, WebViewRenderProcess webViewRenderProcess) {

                }
            });
        }
    }

    private void c35(SpannableString ssb, String url, int it) {
        try {
            if (url.startsWith("https://")) {
                ssb.setSpan(this.A, it, 8, 0);
            } else if (url.startsWith("http://")) {
                ssb.setSpan(this.E, it, 7, 0);
            } else if (url.startsWith("file://")) {
                ssb.setSpan(this.S, it, 7, 0);
            } else if (url.startsWith("content://")) {
                ssb.setSpan(this.S, it, 10, 0);
            } else {
                if (!a221().getBoolean("autoUpdate", false)) {
                    ssb.setSpan(this.I, it, url.length(), 0);
                } else {
                    ssb.setSpan(this.B, it, url.length(), 0);
                }
            }
            this.u.setText(ssb, TextView.BufferType.SPANNABLE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void c36(String sg) {
        if (a221().getBoolean("wthj", false)) {
            if (!sg.isEmpty()) {
                if (BuildConfig.DEBUG) {
                    SharedPreferences sharedPreferences = getSharedPreferences("th", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("a", sg);
                    editor.apply();
                }
                Runnable re = () -> {
                    java.io.File fe = new java.io.File(StorageDirectory.getBackground(this));
                    if (!a221().getBoolean("webviumB", false) && !fe.exists()) {
                        runOnUiThread(() -> {
                            int co = Color.parseColor(sg);
                            InsetDrawable inset = new InsetDrawable(Resources.toDrawable(GradientDrawable.RECTANGLE,
                                    new float[]{0f, 0f, 0f, 0f, 10f, 10f, 10f, 10f}, co), 10, 0, 10, 0);
                            this.o.setBackground(inset);
                            InsetDrawable inset1 = new InsetDrawable(Resources.toDrawable(GradientDrawable.RECTANGLE,
                                    new float[]{10f, 10f, 10f, 10f, 0f, 0f, 0f, 0f}, co), 10, 0, 10, 0);
                            this.llt.setBackground(inset1);
                            if (Build.VERSION.SDK_INT >= 23) {
                                if (!Resources.isColorDark(co)) {
                                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                    if (Build.VERSION.SDK_INT >= 26) {
                                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
                                    }
                                }
                                getWindow().setStatusBarColor(co);
                                getWindow().setNavigationBarColor(co);
                            }
                            this.cd.setBackgroundResource(R.drawable.a10);
                        });
                    }
                };
                new Thread(re).start();
            } else {
                Runnable re1 = () -> {
                    java.io.File fe = new java.io.File(StorageDirectory.getBackground(this));
                    if (!a221().getBoolean("webviumB", false) && !fe.exists()) {
                        runOnUiThread(() -> {
                            this.llt.setBackgroundResource(R.drawable.f1);
                            this.o.setBackgroundResource(R.drawable.p);
                            if (!a221().getBoolean("autoUpdate", false)) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                }
                                getWindow().setStatusBarColor(Resources.getColor(this, R.color.b));
                                getWindow().setNavigationBarColor(Resources.getColor(this, R.color.m));
                            } else {
                                getWindow().setStatusBarColor(Resources.getColor(this, R.color.n));
                                getWindow().setNavigationBarColor(Resources.getColor(this, R.color.n));
                            }
                            if (h.getUrl().startsWith("http://")) {
                                this.cd.setBackgroundResource(R.drawable.f4);
                            } else {
                                this.cd.setBackgroundResource(R.drawable.w);
                            }
                        });
                    }
                };
                new Thread(re1).start();
            }
        }
    }

    public void c37(String a, String c) {
        try {
            Intent e = new Intent(Intents.ACTION_LAUNCH);
            e.addCategory(Intents.CATEGORY_GENIUS);
            e.putExtra("duplicate", false);
            e.putExtra("webvium", c);
            Intent f = new Intent();
            f.putExtra("android.intent.extra.shortcut.INTENT", e);
            f.putExtra("android.intent.extra.shortcut.NAME", a);
            if (h.getFavicon() != null) {
                f.putExtra("android.intent.extra.shortcut.ICON", h.getFavicon());
            } else {
                f.putExtra("android.intent.extra.shortcut.ICON_RESOURCE",
                        Intent.ShortcutIconResource.fromContext(this, R.mipmap.c));
            }
            f.putExtra("duplicate", false);
            f.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(f);
            Toast.b(this, getString(R.string.q26));
        } catch (Exception et) {
            Toast.e(this, getString(R.string.a36), 2);
        }
    }

    public void c38(String a) {
        try {
            if (Objects.requireNonNull(a221().getString("cookies", "")).equals("120")) {
                if (a.startsWith("https://")) {
                    cm1.setAcceptCookie(true);
                    cm1.setAcceptThirdPartyCookies(h, true);
                } else {
                    cm1.setAcceptCookie(false);
                    cm1.setAcceptThirdPartyCookies(h, false);
                }
            }
            if (Objects.requireNonNull(a221().getString("java", "")).equals("30f")) {
                if (a.startsWith("https://")) {
                    c166();
                } else {
                    ws.setJavaScriptEnabled(false);
                }
            }
            if (Build.VERSION.SDK_INT < 26) {
                if (Objects.requireNonNull(a221().getString("form", "")).equals("7g")) {
                    ws.setSaveFormData(a.startsWith("https://"));
                }
            }
            if (Objects.requireNonNull(a221().getString("location", "")).equals("7h")) {
                if (a.startsWith("https://")) {
                    ws.setGeolocationEnabled(true);
                    if (Build.VERSION.SDK_INT <= 24) {
                        ws.setGeolocationDatabasePath(getFilesDir().toString());
                    }
                } else {
                    ws.setGeolocationEnabled(false);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void c41() {
        if (!Objects.requireNonNull(a221().getString("remind", "")).equals("1k")) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    c42();
                    timer.cancel();
                    timer.purge();
                }
            }, timeSet());
        }
    }

    private int timeSet() {
        if (Objects.requireNonNull(a221().getString("remind", "")).equals("7k")) {
            return 900000;
        } else if (Objects.requireNonNull(a221().getString("remind", "")).equals("30k")) {
            return 1800000;
        }
        return 3600000; // for 60k
    }

    public void c42() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.w2));
        int i = timeSet() / 60000;
        a.setMessage(String.format(getResources().getQuantityString(R.plurals.w1, i), i));
        a.setPositiveButton(getString(R.string.i6), (dialog, i1) -> {
            c41();
            dialog.dismiss();
        });
        a.setOnCancelListener(a1 -> {
            c41();
            a1.dismiss();
        });
        a.create().show();
    }

    public void c43(String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h6));
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.g8);
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
        Runnable p15 = () -> {
            final String sg = Stream.a(url, getString(R.string.c33), getString(R.string.g25));
            runOnUiThread(() -> ti.setText(Html.b(sg)));
        };
        new Thread(p15).start();
        bn.setText(getString(R.string.i6));
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setOnClickListener(view -> {
            String ab = ed.getText().toString();
            ti.setText(getString(R.string.v13));

            if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
                Runnable p151 = () -> {
                    final String sg = Stream.a(ab, getString(R.string.c33), getString(R.string.g25));
                    runOnUiThread(() -> ti.setText(Html.b(sg)));
                };
                new Thread(p151).start();
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)){
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                }  else if (url.startsWith("file://") || url.startsWith("content://")){
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

    public void c44(String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.y78));
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.g8);
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
        String sg = Uri.parse(url).getHost();
        Runnable re = () -> {
            try {
                boolean st = Ping.isHostReachable(sg, 2500);
                boolean nd = Ping.isHostReachable(sg, 5000);
                boolean rd = Ping.isHostReachable(sg, 10000);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(() -> ti.setText(Html.b(String.format(getString(R.string.y85), sg, st).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81)))));
                    }
                }, 1000);
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(() -> ti.append(Html.b(String.format(getString(R.string.y86), nd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81)))));
                    }
                }, 2000);
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(() -> ti.append(Html.b(String.format(getString(R.string.y87), rd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81)))));
                    }
                }, 3000);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        new Thread(re).start();
        ed.setText(sg);
        bn.setText(getString(R.string.i6));

        bn.setOnClickListener(view -> {
            String ab = Uri.parse(ed.getText().toString()).getHost();
            ti.setText(getString(R.string.v13));
            Runnable re2 = () -> {
                try {
                    boolean st = Ping.isHostReachable(ab, 2500);
                    boolean nd = Ping.isHostReachable(ab, 5000);
                    boolean rd = Ping.isHostReachable(ab, 10000);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(() -> ti.setText(Html.b(String.format(getString(R.string.y85), ab, st).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81)))));
                        }
                    }, 1000);
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(() -> ti.append(Html.b(String.format(getString(R.string.y86), nd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81)))));
                        }
                    }, 2000);
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(() -> ti.append(Html.b(String.format(getString(R.string.y87), rd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81)))));
                        }
                    }, 3000);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            };
            new Thread(re2).start();

        });
        final AlertDialog g = a.create();
        g.show();
    }

    public String c46() {
        switch (Objects.requireNonNull(a221().getString("searchP", ""))) {
            case SE_DUCKDUCKGO:
                return searchEngine[1];
            default:
            case SE_GOOGLE:
                return searchEngine[0] + searchPath[0];
            case SE_BING:
                return searchEngine[2] + searchPath[0];
            case SE_YAHOO:
                return searchPath[1];
            case SE_ASK:
                return searchPath[2];
            case SE_AOL:
                return searchPath[3];
            case SE_BAIDU:
                return searchEngine[6] + searchPath[4];
            case SE_WOLFRAMALPHA:
                return searchEngine[7] + searchPath[5];
            case SE_DISCOVERAPP:
                return searchEngine[8] + searchPath[6];
            case SE_ECOSIA:
                return searchEngine[9] + searchPath[0];
            case SE_STACKOVERFLOW:
                return searchEngine[10] + searchPath[0];
            case SE_YOUTUBE:
                return searchEngine[11] + searchPath[7];
            case SE_GITHUB:
                return searchEngine[12] + searchPath[0];
            case SE_FACEBOOK:
                return searchEngine[13] + searchPath[8];
            case SE_SUASIVE:
                return searchEngine[14] + searchPath[0];
        }
    }

    public void c47() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b19, null);
        a.setTitle(getString(R.string.h4));
        a.setCancelable(true);
        a.setView(c);
        final TextView ed = c.findViewById(R.id.l8);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            ed.setTextColor(Resources.getColor(this, R.color.b));
        }
        ed.setText(cm.toString());
        a.setPositiveButton(getString(R.string.y89), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
    }

    public String c48() {
        switch (Objects.requireNonNull(a221().getString("searchP", "7b"))) {
            case SE_DUCKDUCKGO:
                return searchEngine[1];
            default:
            case SE_GOOGLE:
                return searchEngine[0];
            case SE_BING:
                return searchEngine[2];
            case SE_YAHOO:
                return searchEngine[3];
            case SE_ASK:
                return searchEngine[4];
            case SE_AOL:
                return searchEngine[5];
            case SE_BAIDU:
                return searchEngine[6];
            case SE_WOLFRAMALPHA:
                return searchEngine[7];
            case SE_DISCOVERAPP:
                return searchEngine[8];
            case SE_ECOSIA:
                return searchEngine[9];
            case SE_STACKOVERFLOW:
                return searchEngine[10];
            case SE_YOUTUBE:
                return searchEngine[11];
            case SE_GITHUB:
                return searchEngine[12];
            case SE_FACEBOOK:
                return searchEngine[13];
            case SE_SUASIVE:
                return searchEngine[14];
        }
    }

    public void c49(String a) {
        String a5 = a.trim().toLowerCase();
        if (a5.equals("webvium://history")) {
            c146(WEBVIUM_HISTORY);
        } else if (a5.equals("webvium://search")) {
            c146(WEBVIUM_SEARCH);
        } else if (a5.equals("webvium://bookmarks")) {
            c146(WEBVIUM_BOOKMARKS);
        } else if (a.startsWith("view-source:") && (a.contains("https://") || a.contains("http://") || a.contains("file://") || a.contains("content://"))) {
            Intent it = new Intent(this, TOOL.class);
            it.putExtra("id", TOOL.TOOL_SOURCE_CODE);
            it.putExtra("dat", a5);
            startActivity(it);
        } else if (a.equals("about:blank")) {
            h.loadUrl("about:blank");
        } else if (IPAddress.isValidIpAddress(a)) {
            h.loadUrl(a);
        } else if (URLUtil.isValidUrl(a5)) {
            if (a5.startsWith("file://") || a5.startsWith("https://") || a5.startsWith("http://") || a5.startsWith("content://")) {
                h.loadUrl(c138(a));
            } else {
                if (Domain.isValidDomain(a5)) {
                    c3(c138(a));
                } else {
                    try {
                        c124(URLEncoder.encode(a, "UTF-8"));
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                        c124(a);
                    }
                }
            }
        } else if (Domain.isValidDomain(a5)) {
            c3(c138("http://" + a));
        } else {
            try {
                c124(URLEncoder.encode(a, "UTF-8"));
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
                c124(a);
            }
        }
    }

    private void c50() {
        if (a221().getBoolean("tab", false)) {
            String sg0 = a222("MyURL");
            if (sg0 != null) {
                c3(sg0);
            } else {
                c51();
            }
        } else {
            c51();
        }
    }

    private void c51() {
        switch (Objects.requireNonNull(a221().getString("general", "x57"))) {
            default:
            case "x57":
                if (h.getSettings().getJavaScriptEnabled()) {
                    c3(WEBVIUM_HOME);
                } else {
                    c3(c48());
                }
                break;
            case "1o":
                c3(c48());
                break;
            case "7o":
                String sg0 = a222("MyURL");
                if (sg0 == null) {
                    sg0 = c48();
                }
                c3(sg0);
                break;
            case "30o":
                c3("about:blank");
                break;
            case "60o":
                String sg1 = a221().getString("cGeneral", "");
                if (sg1 != null && U3.b(sg1)) {
                    c49(sg1);
                } else {
                    c3(c48());
                }
                break;
        }
    }

    private void c52() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).getMemoryInfo(mi);
        if (mi.availMem < mi.threshold) {
            android.util.Log.d("Webvium", "MAIN is freeing memory now because: available ="
                    + (mi.availMem / 1024) + " Keep threshold ="
                    + (mi.threshold / 1024) + " Keep");
            SQLiteDatabase.releaseMemory();
            System.gc();
            h.clearCache(false);
        }
    }

    private void c53(String jk) {
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

    public void c54(String a) {
        if (this.iw.getVisibility() == View.GONE) {
            this.iw.setVisibility(View.VISIBLE);
            this.tv10.setVisibility(View.VISIBLE);
        }
        if (a.startsWith("https://")) {
            this.iw.setImageResource(R.drawable.a15);
            this.cd.setBackgroundResource(R.drawable.w);
        } else if (a.startsWith("http://")) {
            this.iw.setImageResource(R.drawable.a16);
            this.cd.setBackgroundResource(R.drawable.f4);
        } else if (a.startsWith("file://") || a.startsWith("content://")) {
            this.iw.setImageResource(R.drawable.a17);
            this.cd.setBackgroundResource(R.drawable.w);
        } else {
            this.iw.setVisibility(View.GONE);
            this.tv10.setVisibility(View.GONE);
        }
        Animation.animate(MAIN.this, R.anim.c, this.iw);
        this.iw.setBackgroundResource(R.drawable.b17);
        this.tv10.setText("|");
    }

    public void c55(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.l5));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ed1.setTextColor(e);
            ti.setTextColor(e);
            ti1.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ed1.setTextColor(f);
            ti.setTextColor(f);
            ti1.setTextColor(f);
        }
        ti.setText(getString(R.string.t3));
        ti1.setText(getString(R.string.t4));
        if (asd != null) {
            ed.setText(asd);
        } else {
            String hl = Uri.parse(a23).getHost() + ".html";
            ed.setText(hl);
        }
        ed1.setText(a23);
        a.setPositiveButton(getString(R.string.e25), (a2, i) -> {
            Intent it = new Intent(this, Hash.class);
            it.putExtra("a", ed.getText().toString());
            it.putExtra("b", ed1.getText().toString());
            Notifications.b(MAIN.this, it);
            a2.dismiss();

        });
        a.setNegativeButton(getString(R.string.i7), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    if (U3.a(ed1)) {
                        java.io.File file = new java.io.File(StorageDirectory.getWebviumDir() + "/Downloads/" + ed.getText().toString());
                        if (file.exists()) {
                            ed.setError(getString(R.string.u19));
                            okButton.setEnabled(false);
                        } else {
                            okButton.setEnabled(true);
                        }
                    } else {
                        okButton.setEnabled(false);
                    }
                } else {
                    okButton.setEnabled(false);
                }
            }
        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }
        });
        if (U3.a(ed) && U3.a(ed1)) {
            java.io.File file = new java.io.File(StorageDirectory.getWebviumDir() + "/Downloads/" + ed.getText().toString());
            if (file.exists()) {
                ed.setError(getString(R.string.u19));
                g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            } else {
                g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        } else {
            g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
    }

    public void c58(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h12));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final EDIT ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final EDIT ed1 = c.findViewById(R.id.f12);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ed1.setTextColor(e);
            ti.setTextColor(e);
            ti1.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ed1.setTextColor(f);
            ti.setTextColor(f);
            ti1.setTextColor(f);
        }
        ti.setText(getString(R.string.t3));
        ti1.setText(getString(R.string.t4));
        if (asd != null) {
            ed.setText(asd);
        } else {
            Uri hl = Uri.parse(a23);
            ed.setText(hl.getHost());
        }
        ed1.setText(a23);
        a.setPositiveButton(getString(R.string.i6), (a2, i) -> {
            c37(ed.getText().toString(), ed1.getText().toString());
            a2.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }
        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }
        });
        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed) && U3.a(ed1));
    }

    @TargetApi(Build.VERSION_CODES.O_MR1)
    public void c60(WebView view, int type5, final SafeBrowsingResponse sbh) {
        if (a221().getBoolean("safe", true)) {
            if (a221().getBoolean("safeDG", true)) {
                String type = getString(R.string.i23);
                String wde = getString(R.string.i28);
                switch (type5) {
                    case WebViewClient.SAFE_BROWSING_THREAT_UNKNOWN:
                        type = getString(R.string.i23);
                        wde = getString(R.string.i28);
                        break;
                    case WebViewClient.SAFE_BROWSING_THREAT_MALWARE:
                        type = getString(R.string.i24);
                        wde = getString(R.string.i29);
                        break;
                    case WebViewClient.SAFE_BROWSING_THREAT_PHISHING:
                        type = getString(R.string.i25);
                        wde = getString(R.string.i30);
                        break;
                    case WebViewClient.SAFE_BROWSING_THREAT_UNWANTED_SOFTWARE:
                        type = getString(R.string.i26);
                        wde = getString(R.string.i31);
                        break;
                    case WebViewClient.SAFE_BROWSING_THREAT_BILLING:
                        type = getString(R.string.i27);
                        wde = getString(R.string.i32);
                        break;
                }
                AlertDialog.Builder a = new AlertDialog.Builder(this);
                a.setCancelable(false);
                a.setTitle(getString(R.string.h40));
                a.setMessage(Html.b(String.format(getString(R.string.h39), Objects.requireNonNull(Uri.parse(view.getUrl()).getHost()), wde, type)));
                a.setPositiveButton(getString(R.string.i21), (a2, i) -> {
                    sbh.backToSafety(true);
                    a2.dismiss();
                });
                a.setNegativeButton(getString(R.string.i22), (a2, i) -> {
                    sbh.proceed(true);
                    a2.dismiss();
                });
                a.create().show();
            } else {
                sbh.backToSafety(true);
            }
        } else {
            sbh.proceed(true);
        }
    }

    public void c65(String c) {
        Intent a = new Intent(Intent.ACTION_SENDTO, Uri.parse(c));
        a.putExtra(Intent.EXTRA_SUBJECT, "");
        a.putExtra(Intent.EXTRA_TEXT, "");
        if (a.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(a, getString(R.string.a26)));
        } else {
            Toast.c(this, getString(R.string.f34));
        }
    }

    public void c67() {
        h.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void ip(String sg) {
                Intent it = new Intent("com.mrepol742.webvium.WebviumIpHelper");
                it.putExtra("ip", sg);
                sendBroadcast(it);
            }
        }, Package.c() + "IpHelper");
        h.loadUrl("javascript:a();async function a() {var myRequest = new Request('https://api.ipify.org');fetch(myRequest).then(function(response) {response.text().then(function(text) {print(text);});});}function print(dat) {"+Package.c()+"IpHelper.ip(dat);}");
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h7));
        LayoutInflater d = getLayoutInflater();
        View e = d.inflate(R.layout.b4, null);
        a.setView(e);
        final TextView f = e.findViewById(R.id.x);
        f.setText(getString(R.string.v13));
        TextView f5 = e.findViewById(R.id.c20);
        Button bn = e.findViewById(R.id.k12);
        f5.setText(getString(R.string.o7));
        this.ipH = new MainReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                f.setText(intent.getStringExtra("ip"));
            }
        };
        IntentsFilter af = new IntentsFilter();
        af.act("com.mrepol742.webvium.WebviumIpHelper");
        registerReceiver(this.ipH, af);
        bn.setText(getString(R.string.h14));
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.getColor(this, R.color.c));
            f5.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            f.setTextColor(Resources.getColor(this, R.color.b));
            f5.setTextColor(Resources.getColor(this, R.color.b));
        }
        bn.setOnClickListener(view -> h.loadUrl("javascript:a();async function a() {var myRequest = new Request('https://api.ipify.org');fetch(myRequest).then(function(response) {response.text().then(function(text) {print(text);});});}function print(dat) {"+Package.c()+"IpHelper.ip(dat);}"));
        a.setOnCancelListener(dialog -> {
            unregisterReceiver(this.ipH);
            h.removeJavascriptInterface(Package.c() + "IpHelper");
            dialog.dismiss();
        });
        AlertDialog dd = a.create();
        Objects.requireNonNull(dd.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        dd.show();
    }

    private void c68(View a) {
        Files.createNewFolder(StorageDirectory.getWebviumDir() + "/Screenshot");
        if (Objects.requireNonNull(a221().getString("shotSs", "1q")).equals("1q")) {
            c74(Bitmap.CompressFormat.PNG, c69(), a, StorageDirectory.getWebviumDir() + "/Screenshot/Screenshot_" + format() + ".png");
        }
        if (Objects.requireNonNull(a221().getString("shotSs", "1q")).equals("7q")) {
            c74(Bitmap.CompressFormat.JPEG, c69(), a, StorageDirectory.getWebviumDir() + "/Screenshot/Screenshot_" + format() + ".jpeg");
        }
        if (Objects.requireNonNull(a221().getString("shotSs", "1q")).equals("30q")) {
            c74(Bitmap.CompressFormat.WEBP, c69(), a, StorageDirectory.getWebviumDir() + "/Screenshot/Screenshot_" + format() + ".webp");
        }
    }

    private int c69() {
        if (Objects.requireNonNull(a221().getString("shotQq", "1w")).equals("7w")) {
            return 95;
        }
        if (Objects.requireNonNull(a221().getString("shotQq", "1w")).equals("30w"))

        {
            return 80;
        }
        return 100;
    }

    private void c74(Bitmap.CompressFormat format, int quality, View ll, String st) {
        try {
            FileOutputStream c = new FileOutputStream(new java.io.File(st));
            getWebviumCurrentView(ll).compress(format, quality, c);
            c.flush();
            c.close();
            c8(getString(R.string.w16));
        } catch (IOException e) {
            e.printStackTrace();
            c7(getString(R.string.w14));
        }
        c69(st);
        Runnable p15 = () -> c70(st);
        new Thread(p15).start();
        c171(st);
    }

    public static Bitmap getWebviumCurrentView(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    private void c69(String a) {
        Uri contentUri = Uri.fromFile(new java.io.File(a));
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    private void c70(String a) {
        try {
            Bitmap bit = BitmapFactory.decodeFile(a);
            MainNotification.b(this, getString(R.string.k1), getString(R.string.y17));
            android.app.Notification.Builder e = Notifications.a(this, getString(R.string.k1));
            e.setColor(Resources.getColor(this, R.color.a));
            e.setSmallIcon(R.drawable.a13);
            e.setLargeIcon(bit);
            e.setDefaults(android.app.Notification.DEFAULT_ALL);
            e.setContentTitle(getString(R.string.w15));
            e.setContentText(getString(R.string.w17));
            if (Build.VERSION.SDK_INT < 26) {

                if (Objects.equals(a221().getString("py", "1x"), "1x")) {
                    e.setPriority(android.app.Notification.PRIORITY_DEFAULT);
                }
                if (Objects.equals(a221().getString("py", "1x"), "7x")) {
                    e.setPriority(android.app.Notification.PRIORITY_HIGH);
                }
                if (Objects.equals(a221().getString("py", "1x"), "30x")) {
                    e.setPriority(android.app.Notification.PRIORITY_LOW);
                }
                if (Objects.equals(a221().getString("py", "1x"), "60x")) {
                    e.setPriority(android.app.Notification.PRIORITY_MAX);
                }
                if (Objects.equals(a221().getString("py", "1x"), "120x")) {
                    e.setPriority(android.app.Notification.PRIORITY_MIN);
                }
            }
            if (Objects.equals(a221().getString("vy", "7y"), "1y")) {
                e.setVisibility(android.app.Notification.VISIBILITY_PRIVATE);
            }
            if (Objects.equals(a221().getString("vy", "7y"), "7y")) {
                e.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
            }
            if (Objects.equals(a221().getString("vy", "7y"), "30y")) {
                e.setVisibility(android.app.Notification.VISIBILITY_SECRET);
            }
            e.setPublicVersion(e.build());
            e.setStyle(new android.app.Notification.BigPictureStyle().bigPicture(bit).bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.a13)));
            Intent f = new Intent(Intent.ACTION_SEND);
            f.setType("image/*");
            f.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + a));
            PendingIntent g = PendingIntent.getActivity(this, 0, f, PendingIntent.FLAG_UPDATE_CURRENT);
            e.setAutoCancel(a221().getBoolean("eac", true));
            Intent j = new Intent(this, SCRE.class);
            j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            j.putExtra("a56hj", a);
            PendingIntent k = PendingIntent.getActivity(this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
            e.setContentIntent(k);
            e.addAction(new android.app.Notification.Action(R.drawable.e, getString(R.string.a8), g));
            NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nmc.notify(Notifications.b, e.build());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void c71() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(Package.c());
        a.setMessage(Html.b(getString(R.string.u12)));
        a.setPositiveButton(getString(R.string.i6), (a6, o) -> {
            Intent a4 = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            startActivity(a4);
        });
        a.setNegativeButton(getString(R.string.i7), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    private void c74(PendingDownloadDataModel w18) {
        try {
            WifiManager b = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!a221().getBoolean("autoD", false)) {
                if (URLUtil.isValidUrl(w18.a1)) {
                    if (a221().getBoolean("wifi", false)) {
                        if (b.isWifiEnabled()) {

                            if (Permission.check(this, Permission.STORAGE, 1)) {
                                c11(w18);
                            } else {
                                pend = w18;
                            }
                        } else {
                            c7(getString(R.string.v12));
                        }
                    } else {
                        if (Permission.check(this, Permission.STORAGE, 1)) {
                            c11(w18);
                        } else {
                            pend = w18;
                        }
                    }
                } else {
                    c111(w18.a1);
                }
            } else {
                if (Permission.check(this, Permission.STORAGE, 1)) {
                    c11(w18);
                    // auto download unfinished system 0001
                } else {
                    pend = w18;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void c75() {
        String url = h.getUrl();
        if (h.getCertificate() == null) {
            return;
        }
        SslCertificate ce = h.getCertificate();
        SslCertificate.DName ssl = ce.getIssuedTo();
        SslCertificate.DName ssl0 = ce.getIssuedBy();
        String ag9 = String.format(getString(R.string.b7),
                Objects.requireNonNull(h.getTitle()),
                Objects.requireNonNull(url),
                ssl.getCName(),
                ssl.getOName(),
                ssl.getUName(),
                ssl0.getCName(),
                ssl0.getOName(),
                ssl0.getUName(),
                ce.getValidNotBeforeDate().toString(),
                ce.getValidNotAfterDate().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyddMMHHmmss", Locale.US);
        String vnbd = sdf.format(ce.getValidNotAfterDate());
        String ndt = sdf.format(new Date());
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b10, null);
        a.setTitle(getString(R.string.v19));
        a.setCancelable(true);
        a.setView(c);
        TextView ed = c.findViewById(R.id.b10);
        TextView ed1 = c.findViewById(R.id.m11);
        try {
            long nb0 = Long.parseLong(vnbd);
            long nb1 = Long.parseLong(ndt);
            if (nb1 <= nb0) {
                ed1.setText(getString(R.string.l22));
                ed1.setCompoundDrawablesWithIntrinsicBounds(null,
                        Resources.getDrawable(this, R.drawable.f5),
                        null,
                        null);
            } else {
                ed1.setText(getString(R.string.l23));
                ed1.setCompoundDrawablesWithIntrinsicBounds(null,
                        Resources.getDrawable(this, R.drawable.f7),
                        null,
                        null);
            }
            ed1.setCompoundDrawablePadding(40);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.getColor(this, R.color.c));
            ed1.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            ed.setTextColor(Resources.getColor(this, R.color.b));
            ed1.setTextColor(Resources.getColor(this, R.color.b));
        }
        Html.a(ed, ag9);
        a.setPositiveButton(getString(R.string.i6), (a12, intetg) -> a12.dismiss());
        final AlertDialog g = a.create();
        g.show();
    }

    public void c77(ReceivedErrorDataModel receivedErrorDataModel) {
        try {
            c180();
            bl6 = false;
            if (Build.VERSION.SDK_INT >= 23 && receivedErrorDataModel.c.startsWith("file://")) {
                Permission.check(this, Permission.STORAGE, 2);
            }
            String sg = String.format(getString(R.string.v20),
                    receivedErrorDataModel.c,
                    receivedErrorDataModel.b,
                    receivedErrorDataModel.d);
            cm0.append(sg);
            if (Build.VERSION.SDK_INT >= 23) {
                cm0.append(String.format(getString(R.string.r22),
                        receivedErrorDataModel.bn,
                        receivedErrorDataModel.bn1,
                        c175()));
            }
            cm0.append("<br><br>");
           /* if (bn || Build.VERSION.SDK_INT == 21) {
                h.load((c, Base64.a(W5.a7()).replace("%a", getString(R.string.c33)).replace("%b", getString(R.string.g23)).replace("%c", getString(R.string.r21)).replace("%d", c).replace("%e", c).replace("%f", c160(b)).replace("%g", d));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // on httpreceive error
    public void c78(WebResourceRequest b, WebResourceResponse c) {
        try {
            c180();
            if (b == null || c == null || b.getUrl() == null || c.getData() == null || c.getResponseHeaders() == null) {
                return;
            }
            String sg9 = String.format(getString(R.string.v2),
                    b.getUrl().toString(),
                    c.getData().toString(),
                    c.getEncoding(),
                    c.getMimeType(),
                    c.getReasonPhrase(),
                    c.getResponseHeaders().toString(),
                    c.getStatusCode(),
                    b.isForMainFrame(),
                    b.hasGesture(),
                    c175());
            cm0.append(sg9).append("<br><br>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // on sslreceive error
    public void c79(final SslErrorHandler b, SslError c) {
        this.iw.setImageResource(R.drawable.a16);
        Animation.animate(this, R.anim.c, iw);
        String sg = String.format(getString(R.string.g19),
                c.getUrl(),
                c.getPrimaryError());
        cm2.append(sg);
        cm2.append("<br><br>");
        SpannableString xjendndj = new SpannableString(c.getUrl());
        xjendndj.setSpan(this.E, 0, 8, 0);
        this.u.setText(xjendndj);
        cd.setBackgroundResource(R.drawable.f4);
        if (a221().getBoolean("showSO", true)) {
            AlertDialog.Builder e = new AlertDialog.Builder(this);
            String f = getString(R.string.v3);
            switch (c.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    f = getString(R.string.v4);
                    break;
                case SslError.SSL_EXPIRED:
                    f = getString(R.string.v5);
                    break;
                case SslError.SSL_IDMISMATCH:
                    f = getString(R.string.v6);
                    break;
                case SslError.SSL_NOTYETVALID:
                    f = getString(R.string.v7);
                    break;
                case SslError.SSL_DATE_INVALID:
                    f = getString(R.string.v8);
                    break;
                case SslError.SSL_INVALID:
                    f = getString(R.string.v9);
                    break;
            }
            e.setTitle(getString(R.string.v10));
            e.setMessage(f);
            e.setCancelable(false);
            e.setPositiveButton(getString(R.string.i14), (a1, i) -> {
                b.proceed();
                a1.dismiss();
            });
            e.setNegativeButton(getString(R.string.j4), (a1, i) -> {
                b.cancel();
                a1.dismiss();
            });
            e.setNeutralButton(getString(R.string.v11), (a1, i) -> c75());
            AlertDialog g = e.create();
            g.show();
        } else {
            b.proceed();
        }
    }

    // on page finished
    public void c80(WebView a, String b) {
        try {
            if (a.getSettings().getJavaScriptEnabled()) {
                if (a221().getBoolean("wthj", false)) {
                    a.loadUrl("javascript:window." + Package.c() + "ThemeHelper.setTheme( (function (){ const metas = document.getElementsByTagName('meta'); for (let i = 0; i < metas.length; i++) { if (metas[i].getAttribute('name') === 'theme-color') { return metas[i].getAttribute('content'); } } return '';  } )() );");
                }
                a.loadUrl("javascript:window." + Package.c() + "Webvium.description( (function (){ const metas = document.getElementsByTagName('meta'); for (let i = 0; i < metas.length; i++) { if (metas[i].getAttribute('name') === 'description') { return metas[i].getAttribute('content'); } } return '';  } )() );");
            }
            if (a221().getBoolean("tow2", false)) {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(false);
            }
            bl5 = true;
            bl6 = true;
            c38(b);
            if (bl4) {
                bl4 = false;
                a.clearHistory();
            }
            c54(b);
            c52();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // on page started
    public void c81(String b) {
        try {
            if (a221().getBoolean("tow", false)) {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
            }
            tv5.setImageResource(R.drawable.a18);
            tv5.setBackgroundResource(R.drawable.b17);
            Animation.animate(MAIN.this, R.anim.c, tv5);
            bl5 = false;
            c134();
            c38(b);
            c33(b, getString(R.string.v13));
            this.iw.setImageResource(R.drawable.a15);
            Animation.animate(MAIN.this, R.anim.c, this.iw);
            this.cd.setBackgroundResource(R.drawable.w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ShouldOverrideUrlLoading
    public boolean c82(String b) {
        try {
            c38(b);
            if (b.equals("webvium://history")) {
                c146(WEBVIUM_HISTORY);
                return true;
            } else if (b.equals("webvium://search")) {
                c146(WEBVIUM_SEARCH);
                return true;
            } else if (b.equals("webvium://bookmarks")) {
                c146(WEBVIUM_BOOKMARKS);
                return true;
            } else if (b.equals("webvium://launch?search")) {
                Intents.a(this, SEAR.class);
                return true;
            } else if (MailTo.isMailTo(b)) {
                return c177(b);
            } else if (b.startsWith("smsto:")) {
                return c178(b);
            } else if (b.startsWith("tel:")) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(b)));
                return true;
            } else if (b.startsWith("intent://")) {
                try {
                    Intent it = Intent.parseUri(b, Intent.URI_INTENT_SCHEME);
                    if (it.resolveActivity(getPackageManager()) != null) {
                        startActivity(it);
                    }
                    String fa = it.getStringExtra("browser_fallback_url");
                    if (!Objects.requireNonNull(fa).startsWith("market://") && !fa.startsWith("geo:") && fa.contains("/store/apps/details?id=")) {
                        return c179(b);
                    }
                    c3(fa);
                    return true;
                } catch (URISyntaxException use) {
                    use.printStackTrace();
                }
            } else if (!b.startsWith("market://") && !b.startsWith("geo:") && b.contains("/store/apps/details?id=")) {
                return c179(b);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(b));
                if (intent.resolveActivity(getPackageManager()) != null && !(b.startsWith("https://") || b.startsWith("http://") || b.startsWith("file://") || b.startsWith("content://") || b.startsWith("about:blank"))) {
                    startActivity(intent);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void c83(PendingDownloadDataModel w18) {
        try {

            WifiManager b = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!a221().getBoolean("autoD", false)) {
                if (a221().getBoolean("wifi", false)) {
                    if (b.isWifiEnabled()) {
                        if (Permission.check(this, Permission.STORAGE, 1)) {
                            c11(w18);

                        } else {
                            pend = w18;
                        }
                    } else {
                        c7(getString(R.string.v12));
                    }
                } else {
                    if (Permission.check(this, Permission.STORAGE, 1)) {
                        c11(w18);
                    } else {
                        pend = w18;
                    }
                }
            } else {
                if (a221().getBoolean("wifi", false)) {
                    if (b.isWifiEnabled()) {

                        if (Permission.check(this, Permission.STORAGE, 1)) {
// auto download 003
                            c11(w18);

                        } else {
                            pend = w18;
                        }
                    } else {
                        c7(getString(R.string.v12));
                    }
                } else {
                    if (Permission.check(this, Permission.STORAGE, 1)) {
// auto download  002
                        c11(w18);
                    } else {
                        pend = w18;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Bitmap c84() {
        if (a221().getBoolean("webviumP", false)) {
            if (new java.io.File(StorageDirectory.getVideoPoster(this)).exists()) {
                return BitmapCache.getInstance().a(StorageDirectory.getVideoPoster(this));
            }
        }
        return BitmapFactory.decodeResource(getResources(), R.drawable.e3);
    }

    // on receive title
    public void c86(WebView a, String b) {
        c33(a.getUrl(), b);
    }

    // on hide custom view
    public void c87() {
        try {
            ab.show();
            h.invalidate();
            bl3 = false;
            c98();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            c99();
            ((FrameLayout) getWindow().getDecorView()).removeView(this.cv);
            this.cv = null;
            getWindow().getDecorView().setSystemUiVisibility(this.it7422);
            setRequestedOrientation(this.it742);
            this.cvc.onCustomViewHidden();
            this.cvc = null;
        } catch (Exception asd) {
            asd.printStackTrace();
        }
    }

    // on show custom view
    public void c88(View a, WebChromeClient.CustomViewCallback b) {
        try {
            ab.hide();
            bl3 = true;
            h.invalidate();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            c128();
            if (this.cv != null) {
                return;
            }
            this.cv = a;
            this.it7422 = getWindow().getDecorView().getSystemUiVisibility();
            this.cvc = b;
            ((FrameLayout) getWindow().getDecorView()).addView(this.cv, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846);
        } catch (Exception asd) {
            asd.printStackTrace();
        }
    }

    private void c98() {
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(true);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!a221().getBoolean("autoUpdate", false)) {
                if (!a221().getBoolean("webviumB", false)) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    getWindow().setStatusBarColor(Resources.getColor(this, R.color.b));
                }
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    public void c99() {
        try {
            if (Objects.requireNonNull(a221().getString("hori", "30c")).equals("1c")) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            if (Objects.requireNonNull(a221().getString("hori", "30c")).equals("7c")) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            if (Objects.requireNonNull(a221().getString("hori", "30c")).equals("30c")) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            }
            if (Objects.requireNonNull(a221().getString("screen", "1j")).equals("1j")) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
            if (Objects.requireNonNull(a221().getString("screen", "1j")).equals("7j")) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
            if (Objects.requireNonNull(a221().getString("hide", "1d")).equals("1d")) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                c98();
            }
            if (Objects.requireNonNull(a221().getString("hide", "1d")).equals("7d")) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void c100(WebView a) {
        PrintManager aa = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        aa.print(Objects.requireNonNull(a.getTitle()), a.createPrintDocumentAdapter(a.getTitle()), null);
    }

    public void c104(String b) {
        Intent a = new Intent(Intent.ACTION_DIAL, Uri.parse(c107(b)));
        if (a.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(a, getString(R.string.a26)));
        }
    }

    public void c105(String b) {
        Intent d = new Intent(Intent.ACTION_VIEW);
        d.setData(Uri.parse(c109(b)));
        if (d.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(d, getString(R.string.a26)));
        }
    }

    public void c106(String b) {
        if (Permission.check(this, Permission.PHONE, 8)) {
            Intent a = new Intent(Intent.ACTION_CALL, Uri.parse(c107(b)));
            if (a.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(a, getString(R.string.a26)));
            }
        } else {
            cll = b;
        }
    }

    public String c107(String sg) {
        if (sg.startsWith("tel:")) {
            return sg;
        }
        return "tel:" + sg;
    }

    private void c108() throws PackageManager.NameNotFoundException {
        if (!ua) {
            switch (Objects.requireNonNull(a221().getString("userA", ""))) {
                default:
                case UA_DEFAULT:
                    ws.setUserAgentString(String.format(userAgents[0],
                            Package.c(),
                            Package.e(this)));
                    break;
                case UA_ANDROID_STOCK:
                    ws.setUserAgentString(this.h.getUserAgent());
                    break;
                case UA_INTERNET_EXPLORER:
                    ws.setUserAgentString(userAgents[1]);
                    break;
                case UA_GOOGLE_CHROME:
                    ws.setUserAgentString(userAgents[2]);
                    break;
                case UA_MOZILA_FIREFOX:
                    ws.setUserAgentString(userAgents[3]);
                    break;
                case UA_OPERA:
                    ws.setUserAgentString(userAgents[4]);
                    break;
                case UA_SAFARI:
                    ws.setUserAgentString(userAgents[5]);
                    break;
                case UA_MICROSOFT_EDGE:
                    ws.setUserAgentString(userAgents[6]);
                    break;
                case UA_GOOGLE_CHROMIUM:
                    ws.setUserAgentString(userAgents[7]);
                    break;
                case UA_MOZILA_BRAVE:
                    ws.setUserAgentString(userAgents[8]);
                    break;
                case UA_CUSTOM:
                    ws.setUserAgentString(a221().getString("CustomuserA",  String.format(userAgents[0],
                            Package.c(),
                            Package.e(this))));
                    break;
            }
        }
    }

    public String c109(String sg) {
        if (sg.startsWith("smsto:")) {
            return sg;
        }
        return "smsto:" + sg;
    }

    private void c110(String sg) {
        Intent it = new Intent();
        it.setAction(ContactsContract.Intents.Insert.ACTION);
        it.putExtra(ContactsContract.Intents.Insert.PHONE, sg);
        it.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        if (it.resolveActivity(getPackageManager()) != null) {
            startActivity(it);
        }
    }

    public void c111(final String dt) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.k, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.w18));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.l3);
        final EDIT ed = c.findViewById(R.id.l4);
        final TextView ti2 = c.findViewById(R.id.l5);
        final TextView ti3 = c.findViewById(R.id.l6);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ti.setTextColor(e);
            ti2.setTextColor(e);
            ti3.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ti.setTextColor(f);
            ti2.setTextColor(f);
            ti3.setTextColor(f);
        }
        ti.setText(getString(R.string.c30));
        ti2.setText(getString(R.string.c31));
        String sg11 = Uri.parse(h.getUrl()).getHost() + ".png";
        String sg = StorageDirectory.getDownloadDir() + "/" + sg11;
        ti3.setText(sg);
        ed.setText(sg11);
        a.setPositiveButton(getString(R.string.i6), (a2, i) -> {
            c113(ed.getText().toString(), dt);
            a2.dismiss();
        });
        a.setNegativeButton(getString(R.string.i7), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String sg = StorageDirectory.getWebviumDir() + "/Downloads/" + ed.getText().toString();
                ti3.setText(sg);
                if (U3.a(ed)) {

                    if (new java.io.File(StorageDirectory.getWebviumDir() + "/Downloads/" + ed.getText().toString()).exists()) {
                        ed.setError(getString(R.string.u19));
                        okButton.setEnabled(false);
                    } else {

                        okButton.setEnabled(true);
                    }
                } else {
                    okButton.setEnabled(false);
                }
            }


        });
        if (U3.a(ed)) {
            if (new java.io.File(StorageDirectory.getWebviumDir() + "/Downloads/" + ed.getText().toString()).exists()) {
                g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                ed.setError(getString(R.string.u19));
            } else {
                g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        } else {
            g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }
    }

    public void c112(String url, int type) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
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
        final EDIT ed = c.findViewById(R.id.g8);
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
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setText(getString(R.string.i6));
        ti.setText(String.format(getString(R.string.f31), "https://mrepol742.github.io", "http://mrepol742.github.io"));
        final AlertDialog g = a.create();
        bn.setOnClickListener(view -> {
            String a1 = ed.getText().toString();
            if (type == SOURCE_CODE) {
                Intent it = new Intent(MAIN.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_SOURCE_CODE);
                startActivity(it);
            } else if (type == HEADERS) {
                c126(a1);
            } else if (type == ROBOTS) {
                Intent it = new Intent(MAIN.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_ROBOTS);
                startActivity(it);
            } else if (type == ASSETLINKS) {
                Intent it = new Intent(MAIN.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_ASSET_LINKS);
                startActivity(it);
            } else if (type == SITEMAPS) {
                Intent it = new Intent(MAIN.this, TOOL.class);
                it.putExtra("dat", a1);
                it.putExtra("id", TOOL.TOOL_SITEMAPS);
                startActivity(it);
            }
            g.dismiss();
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)){
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                }  else if (type != SOURCE_CODE && (url.startsWith("file://") || url.startsWith("content://"))){
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

    private void c113(String a, String b) {
        Runnable p15 = () -> {
            try {
                java.io.File a2 = new java.io.File(StorageDirectory.getWebviumDir() + "/Downloads/" + a);
                if (a2.createNewFile()) {
                    FileOutputStream a3 = new FileOutputStream(a2);
                    OutputStreamWriter a4 = new OutputStreamWriter(a3);
                    a4.append(Base64.decode(b.split(",")[1]));
                    a4.close();
                    a3.close();
                    runOnUiThread(() -> c8(getString(R.string.f38)));
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> c7(getString(R.string.w14)));
        };
        new Thread(p15).start();
    }

    public void c119() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.y14));
        LayoutInflater d = getLayoutInflater();
        View e = d.inflate(R.layout.b15, null);
        a.setView(e);
        TextView f = e.findViewById(R.id.k6);
        f.setText(ws.getUserAgentString());
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            f.setTextColor(Resources.getColor(this, R.color.b));
        }
        AlertDialog dd = a.create();
        Objects.requireNonNull(dd.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        dd.show();
    }

    public void c122() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b16, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.z16));
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.k9);
        ed.setHint(getString(R.string.q21));
        final TextView ti = c.findViewById(R.id.k10);
        Button bn = c.findViewById(R.id.k11);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);

            ti.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ti.setTextColor(f);

        }
        ed.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        ed.setTransformationMethod(new Password());
        bn.setText(getString(R.string.e28));

        bn.setOnClickListener(view -> {
            Runnable p15 = () -> {
                String str = ed.getText().toString();
                try {
                    String sg = U1.a(Integer.parseInt(str));
                    runOnUiThread(() -> ti.setText(sg));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            };
            new Thread(p15).start();
        });
        ed.setOnEditorActionListener((v, actionId, event) -> {
            Runnable p15 = () -> {
                String str = ed.getText().toString();
                try {
                    String sg = U1.a(Integer.parseInt(str));
                    runOnUiThread(() -> ti.setText(sg));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            };
            new Thread(p15).start();
            return true;
        });
        final AlertDialog g = a.create();
        g.show();
    }

    public void c123() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b17, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.z17));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.k13);
        Button bn = c.findViewById(R.id.k14);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);

        if (!a221().getBoolean("autoUpdate", false)) {
            ti.setTextColor(e);

            bn.setTextColor(e);
        } else {
            ti.setTextColor(f);

            bn.setTextColor(f);
        }
        Html.a(ti, IdentityGenerator.a(getResources().getQuantityString(R.plurals.m23, 23)));
        bn.setText(getString(R.string.e28));
        bn.setOnClickListener(view -> Html.a(ti, IdentityGenerator.a(getResources().getQuantityString(R.plurals.m23, 23))));
        final AlertDialog g = a.create();
        Objects.requireNonNull(g.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        g.show();
    }

    public void c124(String tg) {
        c3(c46() + tg);
    }

    private void c125(int type) {
        final FrameLayout k = findViewById(R.id.i);
        View c = View.inflate(this, R.layout.c6, null);
        LinearLayout ll = c.findViewById(R.id.b6);
        final SeekBar c2 = c.findViewById(R.id.c18);
        final AudioManager mAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        c2.setElevation(5);
        c2.setBackgroundResource(R.drawable.a19);
        c2.setProgress(mAudio.getStreamVolume(AudioManager.STREAM_MUSIC));
        if (type == 1) {
            mAudio.setStreamVolume(AudioManager.STREAM_MUSIC, mAudio.getStreamVolume(AudioManager.STREAM_MUSIC) + 1, AudioManager.FLAG_PLAY_SOUND);
        } else if (type == 0) {
            mAudio.setStreamVolume(AudioManager.STREAM_MUSIC, mAudio.getStreamVolume(AudioManager.STREAM_MUSIC) - 1, AudioManager.FLAG_PLAY_SOUND);
        }
        Runnable p15 = () -> {
            final Bitmap pp = Resources.getBitmapFromResource(MAIN.this, R.drawable.a6);
            runOnUiThread(() -> c2.setThumb(new BitmapDrawable(getResources(), pp)));
        };
        new Thread(p15).start();
        c2.setMax(mAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        c2.setOnSeekBarChangeListener(new W11() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mAudio.setStreamVolume(AudioManager.STREAM_MUSIC, i, AudioManager.FLAG_PLAY_SOUND);

            }
        });
        ll.setOnClickListener(view -> {
            Animation.animate(MAIN.this, R.anim.d, c2);
            k.removeView(c);
        });
        k.addView(c);
        Animation.animate(this, R.anim.a, c2);
    }

    public void c126(String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.y15));
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.g8);
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
        Runnable p15 = () -> {
            final String sg = Stream.d(url, getString(R.string.c33));
            runOnUiThread(() -> ti.setText(Html.b(sg)));
        };
        new Thread(p15).start();
        if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
            bn.setBackgroundResource(R.drawable.c10);
        } else {
            bn.setBackgroundResource(R.drawable.c11);
        }
        bn.setText(getString(R.string.i6));
        bn.setOnClickListener(view -> {
            String ab = ed.getText().toString();
            ti.setText(getString(R.string.v13));
            if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)){
                Runnable p151 = () -> {
                    final String sg = Stream.d(ab, getString(R.string.c33));
                    runOnUiThread(() -> ti.setText(Html.b(sg)));
                };
                new Thread(p151).start();
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String url = ed.getText().toString().trim();
                if (url.startsWith("https://") || url.startsWith("http://")) {
                    if (!Domain.isValidDomain(url)){
                        ed.setError(getString(R.string.y84));
                        bn.setBackgroundResource(R.drawable.c11);
                    } else {
                        bn.setBackgroundResource(R.drawable.c10);
                    }
                }  else if (url.startsWith("file://") || url.startsWith("content://")){
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

    public void c127() {
        String[] suggestion = {"document.getElementById()",
                "document.getElementByTagName()",
                "document.getElementByClassName()",
                "document.getElementByName()",
                "document.body.style.backgroundColor",
                "document.body.style.color",
                "document.body.style.margin",
                "document.body.style.marginLeft",
                "document.body.style.marginBottom",
                "document.body.style.marginRight",
                "document.body.style.marginTop",
                "document.body.style.padding",
                "document.body.style.paddingTop",
                "document.body.style.paddingBottom",
                "document.body.style.paddingLeft",
                "document.body.style.paddingRight",
                "document.body.style.backgroundImage",
                "document.body.style.backgroundRepeat",
                "document.body.style.backgroundClip",
                "document.body.style.backgroundPosition",
                "document.body.style.backgroundSize",
                "document.body.style.background",
                "document.body.style.cursor",
                "document.body.style.outline",
                "document.body.style.fontFamily",
                "document.body.style.fontSize",
                "document.body.style.fontWeight",
                "document.body.style.fontStyle",
                Package.c() + ".showToast(string)",
                Package.c() + ".showToastError(string)",
                Package.c() + ".vibrate(integer)",
                Package.c() + ".showToastSuccess(string)",
                Package.c() + ".showNotification(string_title, string_content, string_url)",
                "document.getAttribute()",
                "document.getAttributeNode()",
                "document.getBoundingClientRect()",
                "document.getClientRects()",
                "document.setAttribute()",
                "document.setAttributeNode()",
                "document.addEventListener",
                Package.c() + ".copyToClipboard(string)",
                Package.c() + ".enableWifi(boolean)",
                Package.c() + "PrintHelper.print()",
                Package.c() + ".enableFlashlight(boolean)"};
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b5, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.f23));
        a.setView(c);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, suggestion);
        final AutoCompleteTextView ed = c.findViewById(R.id.d15);
        ed.setAdapter(adapter);
        ed.setDropDownBackgroundDrawable(Resources.getDrawable(this, R.drawable.c12));
        Button bn = c.findViewById(R.id.c1);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);

        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
        } else {
            ed.setTextColor(f);
        }
        bn.setText(getString(R.string.f24));
        bn.setOnClickListener(view -> {
            String str = ed.getText().toString();
            if (U3.b(str)) {
                c170(str);
            }
        });
        a.create().show();
    }

    @SuppressLint("SourceLockedOrientationActivity")
    public void c128() {
        if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("1a1")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("7a1")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
        if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("30a1")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("60a1")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
    }

    public void c129(final PermissionRequest pr) {
        if (BuildConfig.DEBUG) {
            Cursor res = d12.getReadableDatabase().rawQuery("SELECT * FROM " +
                    Sqlite.TABLE_PERMISSION +
                    " ORDER BY " +
                    "_id" +
                    " DESC", null);
            if (res.getCount() == 0) {
                c9(pr);
            } else {
                while (res.moveToNext()) {
                    String sg1 = res.getString(1);
                    String sg = res.getString(2);
                    if (sg1.equals(Hash.a("SHA-1", pr.getOrigin().toString())) && Hash.a("SHA-1", Arrays.toString(pr.getResources())).equals(sg)) {
                        pr.grant(pr.getResources());
                    } else {
                        c9(pr);
                    }
                }
            }
            res.close();
        } else {
            c9(pr);
        }
    }

    public void c130(int type) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.a5, null);
        a.setCancelable(true);
        if (type == BASE64_ENCODE) {
            a.setTitle(getString(R.string.y73));
        } else if (type == URL_ENCODE){
            a.setTitle(getString(R.string.y74));
        }
        a.setView(c);
        final EDIT ed = c.findViewById(R.id.o34);
        final EDIT ed1 = c.findViewById(R.id.o35);
        int e = Resources.getColor(this, R.color.c);
        int f = Resources.getColor(this, R.color.b);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(e);
            ed1.setTextColor(e);
        } else {
            ed.setTextColor(f);
            ed1.setTextColor(f);
        }
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ed.hasFocus()) {
                    try {
                        if (type == BASE64_ENCODE) {
                            ed1.setText(Base64.encode(ed.getText().toString()));
                        } else if (type == URL_ENCODE) {
                            ed1.setText(URLEncoder.encode(ed.getText().toString(), "UTF-8"));
                        }
                    } catch (Exception en) {

                        en.printStackTrace();
                        ed.setError(getString(R.string.y75));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ed1.hasFocus()) {
                    try {
                        if (type == BASE64_ENCODE) {
                            ed.setText(Base64.decode(ed1.getText().toString()));
                        } else if (type == URL_ENCODE) {
                            ed.setText(URLDecoder.decode(ed1.getText().toString(), "UTF-8"));
                        }
                    } catch (Exception en) {

                        en.printStackTrace();
                        ed1.setError(getString(R.string.y75));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        final AlertDialog g = a.create();
        g.show();
    }


    public void c134() {
        if (h.canGoForward()) {
            tv4.setImageResource(R.drawable.b13);
            tv4.setBackgroundResource(R.drawable.b17);
        } else {
            tv4.setImageResource(R.drawable.b30);
        }
        if (h.canGoBack()) {
            tv6.setImageResource(R.drawable.c13);
            tv4.setBackgroundResource(R.drawable.b17);
        } else {
            tv6.setImageResource(R.drawable.c14);
        }

        Animation.animate(MAIN.this, R.anim.c, tv6);
        Animation.animate(MAIN.this, R.anim.c, tv4);
    }

    public String c138(String sg) {
        if (a221().getBoolean("flvht", false)) {
            return sg.replace("http://", "https://");
        }
        return sg;
    }

    private void c140() {
        AlertDialog.Builder c = new AlertDialog.Builder(this);
        c.setTitle(getString(R.string.l35));
        c.setCancelable(true);
        c.setMessage(Html.b(getString(R.string.l36)));
        c.setPositiveButton(getString(R.string.i6), (a, intetg) -> a.dismiss());
        c.create().show();
    }

    public void c141() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(Package.c());
        a.setMessage(getString(R.string.l37));
        a.setPositiveButton(getString(R.string.l39), (a12, intetg) -> {
            c150();
            a12.dismiss();
        });
        a.setNegativeButton(getString(R.string.l15), (a1, intetg) -> a1.dismiss());
        a.create().show();
    }

    public void c142(String sg, String sg1) {
        SharedPreferences sp = getSharedPreferences("wv", 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putBoolean("a10", false);
        spe.apply();
        MainNotification.b(this, getString(R.string.l38), getString(R.string.l40));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.l38));
        m.setSmallIcon(R.drawable.f8);
        m.setContentTitle(getString(R.string.l38));
        m.setContentText(sg1);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        bigText.bigText(sg1);
        bigText.setBigContentTitle(getString(R.string.l38));
        bigText.setSummaryText(sg);
        m.setStyle(bigText);
        m.setColor(Resources.getColor(this, R.color.a));
        m.setAutoCancel(a221().getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT <= 26) {
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("1x")) {
                m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("7x")) {
                m.setPriority(android.app.Notification.PRIORITY_HIGH);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("30x")) {
                m.setPriority(android.app.Notification.PRIORITY_LOW);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("60x")) {
                m.setPriority(android.app.Notification.PRIORITY_MAX);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("120x")) {
                m.setPriority(android.app.Notification.PRIORITY_MIN);
            }
        }
        if (Objects.requireNonNull(a221().getString("vy", "7y")).equals("1y")) {

            m.setVisibility(android.app.Notification.VISIBILITY_PRIVATE);
        }
        if (Objects.requireNonNull(a221().getString("vy", "7y")).equals("7y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }
        if (Objects.requireNonNull(a221().getString("vy", "7y")).equals("30y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_SECRET);
        }
        Intent j11 = new Intent(this, MAIN.class);
        PendingIntent k567 = PendingIntent.getActivity(this, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k567);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.f8));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.h, m.build());
    }

    public void c143(String sg, String sg1) {
        SharedPreferences sp = getSharedPreferences("wv", 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putBoolean("a10", false);
        spe.apply();
        MainNotification.b(this, getString(R.string.h1), getString(R.string.m1));
        android.app.Notification.Builder m = Notifications.a(this, getString(R.string.h1));
        m.setSmallIcon(R.drawable.r);
        m.setContentTitle(getString(R.string.m22));
        m.setContentText(sg1);
        android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
        bigText.bigText(sg1);
        bigText.setBigContentTitle(getString(R.string.m22));
        bigText.setSummaryText(sg);
        m.setStyle(bigText);
        m.setColor(Resources.getColor(this, R.color.e));
        m.setAutoCancel(a221().getBoolean("eac", true));
        m.setDefaults(android.app.Notification.DEFAULT_ALL);
        if (Build.VERSION.SDK_INT <= 26) {
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("1x")) {
                m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("7x")) {
                m.setPriority(android.app.Notification.PRIORITY_HIGH);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("30x")) {
                m.setPriority(android.app.Notification.PRIORITY_LOW);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("60x")) {
                m.setPriority(android.app.Notification.PRIORITY_MAX);
            }
            if (Objects.requireNonNull(a221().getString("py", "1x")).equals("120x")) {
                m.setPriority(android.app.Notification.PRIORITY_MIN);
            }
        }
        if (Objects.requireNonNull(a221().getString("vy", "7y")).equals("1y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_PRIVATE);
        }
        if (Objects.requireNonNull(a221().getString("vy", "7y")).equals("7y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        }
        if (Objects.requireNonNull(a221().getString("vy", "7y")).equals("30y")) {
            m.setVisibility(android.app.Notification.VISIBILITY_SECRET);
        }
        Intent j11 = new Intent(this, MAIN.class);
        PendingIntent k567 = PendingIntent.getActivity(this, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k567);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.r));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.i, m.build());
    }

    public void c144() {
        if (pm7 == null) {
            pm7 = new PopupMenu(this, tv7);
            MenuItem.OnMenuItemClickListener e = a1 -> {
                switch (a1.getItemId()) {
                    case 4:
                        c25();
                        finishAndRemoveTask();
                        return true;
                    case 5:
                        h.clearCache(false);
                        c8(getString(R.string.a27));
                        return true;

                    case 0:
                        Intents.a(MAIN.this, DOWN.class);
                        return true;
                    case 3:
                        Intents.a(MAIN.this, SETT0.class);
                        return true;
                    case 1:
                        h.pageUp(true);
                        return true;
                    case 2:
                        h.pageDown(true);
                        return true;

                }
                return false;
            };
            Menu me = pm7.getMenu();
            if (BuildConfig.DEBUG) {
                me.add(0, 0, 0, getString(R.string.h16)).setOnMenuItemClickListener(e);
            }
            me.add(0, 1, 0, getString(R.string.s14)).setOnMenuItemClickListener(e);
            me.add(0, 2, 0, getString(R.string.s16)).setOnMenuItemClickListener(e);
            me.add(0, 3, 0, getString(R.string.h3)).setOnMenuItemClickListener(e);
            me.add(0, 4, 0, getString(R.string.h2)).setOnMenuItemClickListener(e);
            me.add(0, 5, 0, getString(R.string.e10)).setOnMenuItemClickListener(e);
        }
        pm7.show();
    }

    private void c146(int data) {
        if (data == WEBVIUM_SEARCH) {
            Runnable p15 = () -> {
                Cursor cs = d2.getReadableDatabase().rawQuery("SELECT * FROM " +
                        Sqlite.TABLE_SEARCH +
                        " ORDER BY " +
                        "_id" +
                        " DESC", null);
                try {
                    if (cs.getCount() == 0) {
                        java.io.File fe = new java.io.File(StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "Search") + ".htm");
                        if (fe.createNewFile()) {
                            FileWriter fw = new FileWriter(fe, false);
                            BufferedWriter br = new BufferedWriter(fw);
                            br.write("<!DOCTYPE html><html><head><title>" + getString(R.string.i9) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file://" + StorageDirectory.getClasses(this) + "\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><h1><b>" + getString(R.string.f39) + "</b></h1></center></body></html>");
                            br.close();
                            fw.close();
                        }
                    } else {
                        StringBuilder sg = new StringBuilder("<!DOCTYPE html><html><head><title>" + getString(R.string.i9) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file://" + StorageDirectory.getClasses(this) + "\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><table><tr><th>" + getString(R.string.x49) + "</th></tr>");
                        while (cs.moveToNext()) {
                            sg.append("<tr><td>")
                                    .append(cs.getString(1))
                                    .append("</td></tr>");
                        }
                        sg.append("</table></center></body></html>");
                        java.io.File fe1 = new java.io.File(StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "Search") + ".htm");
                        if (fe1.createNewFile()) {
                            FileWriter fw1 = new FileWriter(fe1, false);
                            BufferedWriter br1 = new BufferedWriter(fw1);
                            br1.write(sg.toString());
                            br1.close();
                            fw1.close();
                        }
                    }
                    runOnUiThread(() -> h.loadUrl("file://" + StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "Search") + ".htm"));
                    cs.close();
                } catch (Exception en) {
                    en.printStackTrace();
                }
            };
            new Thread(p15).start();
        } else if (data == WEBVIUM_HISTORY) {
            Runnable p15 = () -> {
                Cursor cs5 = d1.getReadableDatabase().rawQuery("SELECT * FROM " +
                        Sqlite.TABLE_HISTORY +
                        " ORDER BY " +
                        "_id" +
                        " DESC", null);
                try {
                    if (cs5.getCount() == 0) {
                        java.io.File fe = new java.io.File(StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "History") + ".htm");
                        if (fe.createNewFile()) {
                            FileWriter fw = new FileWriter(fe, false);
                            BufferedWriter br = new BufferedWriter(fw);
                            br.write("<!DOCTYPE html><html><head><title>" + getString(R.string.i16) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file://" + StorageDirectory.getClasses(this) + "\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><h1><b>" + getString(R.string.i15) + "</b></h1></center></body></html>");
                            br.close();
                            fw.close();
                        }
                    } else {
                        StringBuilder sg = new StringBuilder("<!DOCTYPE html><html><head><title>" + getString(R.string.i16) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file://" + StorageDirectory.getClasses(this) + "\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><table><tr><th>" + getString(R.string.q17) + "</th></tr>");
                        while (cs5.moveToNext()) {
                            sg.append("<tr><td>")
                                    .append(cs5.getString(1))
                                    .append(" - ")
                                    .append(cs5.getString(2))
                                    .append(" - ")
                                    .append(cs5.getString(3))
                                    .append("</td></tr>");
                        }
                        sg.append("</table></center></body></html>");
                        java.io.File fe1 = new java.io.File(StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "History") + ".htm");
                        if (fe1.createNewFile()) {
                            FileWriter fw1 = new FileWriter(fe1, false);
                            BufferedWriter br1 = new BufferedWriter(fw1);
                            br1.write(sg.toString());
                            br1.close();
                            fw1.close();
                        }
                    }
                    runOnUiThread(() -> h.loadUrl("file://" + StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "History") + ".htm"));
                    cs5.close();
                } catch (Exception en) {
                    en.printStackTrace();
                }
            };
            new Thread(p15).start();
        } else if (data == WEBVIUM_BOOKMARKS) {
            Runnable p15 = () -> {
                Cursor cs6 = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                        Sqlite.TABLE_BOOKMARK +
                        " ORDER BY " +
                        "_id" +
                        " DESC", null);
                try {
                    if (cs6.getCount() == 0) {
                        java.io.File fe = new java.io.File(StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "Bookmarks") + ".htm");
                        if (fe.createNewFile()) {
                            FileWriter fw = new FileWriter(fe, false);
                            BufferedWriter br = new BufferedWriter(fw);
                            br.write("<!DOCTYPE html><html><head><title>" + getString(R.string.h11) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file://" + StorageDirectory.getClasses(this) + "\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><h1><b>" + getString(R.string.g21) + "</b></h1></center></body></html>");
                            br.close();
                            fw.close();
                        }
                    } else {
                        StringBuilder sg = new StringBuilder("<!DOCTYPE html><html><head><title>" + getString(R.string.h11) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file://" + StorageDirectory.getClasses(this) + "\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><table><tr><th>" + getString(R.string.y88) + "</th></tr>");
                        while (cs6.moveToNext()) {
                            sg.append("<tr><td>")
                                    .append(cs6.getString(1))
                                    .append(" - ")
                                    .append(cs6.getString(2))
                                    .append("</td></tr>");
                        }
                        sg.append("</table></center></body></html>");
                        java.io.File fe1 = new java.io.File(StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "Bookmarks") + ".htm");
                        if (fe1.createNewFile()) {
                            FileWriter fw1 = new FileWriter(fe1, false);
                            BufferedWriter br1 = new BufferedWriter(fw1);
                            br1.write(sg.toString());
                            br1.close();
                            fw1.close();
                        }
                    }
                    runOnUiThread(() -> h.loadUrl("file://" + StorageDirectory.getFileDir(this) + "/" + Hash.a("SHA-1", "Bookmarks") + ".htm"));
                    cs6.close();
                } catch (Exception en) {
                    en.printStackTrace();
                }
            };
            new Thread(p15).start();
        }
    }

    public void c149() {
        cd.setBackgroundResource(R.drawable.w);
        tv.setBackgroundResource(R.drawable.f2);
        if (a221().getBoolean("webviumB", false)) {
            h.setBackgroundColor(Resources.getColor(this, android.R.color.transparent));
        } else {
            if (!a221().getBoolean("autoUpdate", false)) {
                h.setBackgroundColor(Resources.getColor(this, R.color.p));
            } else {
                h.setBackgroundColor(Resources.getColor(this, R.color.m));
            }
        }
        Runnable re = () -> {
            java.io.File fe = new java.io.File(StorageDirectory.getBackground(this));
            if (a221().getBoolean("webviumB", false) && fe.exists()) {
                Bitmap bp = BitmapCache.getInstance().a(StorageDirectory.getBackground(MAIN.this));
                runOnUiThread(() -> {
                    this.o.setBackgroundColor(Resources.getColor(this, android.R.color.transparent));
                    MAIN.this.back23.setBackground(new BitmapDrawable(MAIN.this.getResources(), bp));
                });
            } else {
                runOnUiThread(() -> this.o.setBackgroundResource(R.drawable.p));
            }
        };
        new Thread(re).start();
    }

    public void c150() {
        SharedPreferences sp = getSharedPreferences("wv", 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putBoolean("a10", true);
        spe.apply();
    }

    public void c151(WebView a, String b, Boolean c) {
        if (!c) {
            d1.c(a.getTitle(), b);
            SharedPreferences c56 = getSharedPreferences("wv", 0);
            SharedPreferences.Editor d56 = c56.edit();
            d56.putString("MyURL", b);
            d56.apply();
        }
    }

    private boolean c152() {
        WebView.HitTestResult d = h.getHitTestResult();
        int te = d.getType();
        sg = d.getExtra();
        switch (te) {
            case WebView.HitTestResult.UNKNOWN_TYPE:
                return !a221().getBoolean("textST", true);
            default:
            case WebView.HitTestResult.EDIT_TEXT_TYPE:
                return false;
            case WebView.HitTestResult.IMAGE_TYPE:
                if (Objects.requireNonNull(d.getExtra()).startsWith("http://") || d.getExtra().startsWith("https://")) {
                    c153(742);
                } else {
                    c153(12);
                }
                return true;
            case WebView.HitTestResult.SRC_ANCHOR_TYPE:
            case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                c154();
                return true;
            case WebView.HitTestResult.EMAIL_TYPE:
                c155();
                return true;
            case WebView.HitTestResult.GEO_TYPE:
                c156();
                return true;
            case WebView.HitTestResult.PHONE_TYPE:
                c157();
                return true;
        }
    }

    private void c153(int i) {
        if (pm1 == null) {
            pm1 = new PopupMenu(this, findViewById(R.id.f7));
            pm1.setOnDismissListener(popupMenu -> {
                popupMenu.getMenu().clear();
                if (this.sg != null) {
                    this.sg = null;
                }
            });
        }
        Menu m = pm1.getMenu();
        m.add(0, POPUPMENU_IMAGE_VIEW_IMAGE, 0, getString(R.string.w19)).setOnMenuItemClickListener(e23);
        m.add(0, POPUPMENU_IMAGE_COPY, 0, getString(R.string.x3)).setOnMenuItemClickListener(e23);
        m.add(0, POPUPMENU_IMAGE_SHARE, 0, getString(R.string.a8)).setOnMenuItemClickListener(e23);
        if (i != 12) {
            m.add(0, POPUPMENU_IMAGE_SAVE_IMAGE, 0, getString(R.string.w18)).setOnMenuItemClickListener(e23);
        }
        pm1.show();
    }

    private void c154() {
        if (pm2 == null) {
            pm2 = new PopupMenu(this, inf);
            Menu a1 = pm2.getMenu();
            a1.add(0, 4, 0, getString(R.string.x3)).setOnMenuItemClickListener(e1);
            a1.add(0, 3, 0, getString(R.string.a8)).setOnMenuItemClickListener(e1);
            SubMenu sm = a1.addSubMenu(getString(R.string.h17));
            sm.add(0, 6, 0, getString(R.string.h11)).setOnMenuItemClickListener(e1);
            sm.add(0, 7, 0, getString(R.string.h12)).setOnMenuItemClickListener(e1);
            SubMenu sm0 = a1.addSubMenu(getString(R.string.j36));
            sm0.add(0, 19, 0, getString(R.string.y15)).setOnMenuItemClickListener(e1);
            // sm0.add(0, 15, 0, getString(R.string.x9)).setOnMenuItemClickListener(e1);
            // sm0.add(0, 20, 0, getString(R.string.z15)).setOnMenuItemClickListener(e1);
            sm0.add(0, 21, 0, getString(R.string.f32)).setOnMenuItemClickListener(e1);
            // sm0.add(0, 16, 0, getString(R.string.x16)).setOnMenuItemClickListener(e1);
            // sm0.add(0, 17, 0, getString(R.string.y11)).setOnMenuItemClickListener(e1);
            // sm0.add(0, 18, 0, getString(R.string.z4)).setOnMenuItemClickListener(e1);
            sm0.add(0, 13, 0, getString(R.string.h6)).setOnMenuItemClickListener(e1);
            sm0.add(0, 5, 0, getString(R.string.j)).setOnMenuItemClickListener(e1);
            sm0.add(0, 25, 0, getString(R.string.y76)).setOnMenuItemClickListener(e1);
            sm0.add(0, 26, 0, getString(R.string.y77)).setOnMenuItemClickListener(e1);
            sm0.add(0, 12, 0, getString(R.string.i4)).setOnMenuItemClickListener(e1);
            pm2.setOnDismissListener(popupMenu -> {
                if (this.sg != null) {
                    this.sg = null;
                }
            });
        }
        pm2.show();
    }

    private void c155() {
        if (pm3 == null) {
            pm3 = new PopupMenu(this, inf);
            pm3.setOnDismissListener(popupMenu -> {
                if (this.sg != null) {
                    this.sg = null;
                }
            });
            Menu m = pm3.getMenu();
            m.add(0, POPUPMENU_MAIL_SEND_EMAIL, 0, getString(R.string.x2)).setOnMenuItemClickListener(e2);
            m.add(0, POPUPMENU_MAIL_COPY, 0, getString(R.string.u)).setOnMenuItemClickListener(e2);
            m.add(0, POPUPMENU_MAIL_SHARE, 0, getString(R.string.a8)).setOnMenuItemClickListener(e2);
        }
        pm3.show();
    }

    private void c156() {
        if (pm4 == null) {
            pm4 = new PopupMenu(this, inf);
            pm4.setOnDismissListener(popupMenu -> {
                if (this.sg != null) {
                    this.sg = null;
                }
            });
            Menu m = pm4.getMenu();
            m.add(0, POPUPMENU_GEO_SHARE, 0, getString(R.string.a8)).setOnMenuItemClickListener(e3);
        }
        pm4.show();
    }

    private void c157() {
        if (pm5 == null) {
            pm5 = new PopupMenu(this, inf);
            pm5.setOnDismissListener(popupMenu -> {
                if (this.sg != null) {
                    this.sg = null;
                }
            });
            Menu a1 = pm5.getMenu();
            a1.add(0, POPUPMENU_PHONE_SEND_SMS, 0, getString(R.string.w20)).setOnMenuItemClickListener(e4);
            a1.add(0, POPUPMENU_PHONE_CALL, 0, getString(R.string.x1)).setOnMenuItemClickListener(e4);
            a1.add(0, POPUPMENU_PHONE_DIAL, 0, getString(R.string.w24)).setOnMenuItemClickListener(e4);
            a1.add(0, POPUPMENU_PHONE_ADD_TO_CONTACTS, 0, getString(R.string.w25)).setOnMenuItemClickListener(e4);
            a1.add(0, POPUPMENU_PHONE_COPY, 0, getString(R.string.u)).setOnMenuItemClickListener(e4);
            a1.add(0, POPUPMENU_PHONE_SHARE, 0, getString(R.string.a8)).setOnMenuItemClickListener(e4);
        }
        pm5.show();
    }

    public void c159() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h35));
        LayoutInflater d = getLayoutInflater();
        View e = d.inflate(R.layout.b4, null);
        a.setView(e);
        final TextView f = e.findViewById(R.id.x);
        TextView f5 = e.findViewById(R.id.c20);
        Button bn = e.findViewById(R.id.k12);
        f.setText(getString(R.string.v13));
        f5.setText(getString(R.string.v13));
        Runnable p15 = () -> {
            final String sg = Stream.h(getString(R.string.c33));
            final String sg0 = Stream.b(getString(R.string.c33));
            runOnUiThread(() -> {
                f5.setText(sg);
                f.setText(sg0);
            });
        };
        new Thread(p15).start();
        bn.setText(getString(R.string.h14));
        if (!a221().getBoolean("autoUpdate", false)) {
            f.setTextColor(Resources.getColor(this, R.color.c));
            f5.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            f.setTextColor(Resources.getColor(this, R.color.b));
            f5.setTextColor(Resources.getColor(this, R.color.b));
        }
        bn.setOnClickListener(view -> {
            Runnable p151 = () -> {
                final String sg = Stream.b(getString(R.string.c33));
                runOnUiThread(() -> f.setText(sg));
            };
            new Thread(p151).start();
        });
        AlertDialog dd = a.create();
        Objects.requireNonNull(dd.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        dd.show();
    }

    public void c164() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b19, null);
        a.setTitle(getString(R.string.h36));
        a.setCancelable(true);
        a.setView(c);
        final TextView ed = c.findViewById(R.id.l8);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            ed.setTextColor(Resources.getColor(this, R.color.b));
        }
        ed.setText(Html.b(cm0.toString()));
        a.setPositiveButton(getString(R.string.y89), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
    }

    public void c165() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b19, null);
        a.setTitle(getString(R.string.e2));
        a.setCancelable(true);
        a.setView(c);
        final TextView ed = c.findViewById(R.id.l8);
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            ed.setTextColor(Resources.getColor(this, R.color.b));
        }
        ed.setText(Html.b(cm2.toString()));
        a.setPositiveButton(getString(R.string.y89), (a2, i) -> a2.dismiss());
        final AlertDialog g = a.create();
        g.show();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void c166() {
        ws.setJavaScriptEnabled(true);
    }

    public WebResourceResponse c168(WebResourceRequest wr) {
        String url = wr.getUrl().toString();
        for (String sg : ads) {
            if (url.contains(sg)) {
                return new WebResourceResponse("text/plain",
                        "UTF-8",
                        new ByteArrayInputStream(String.format(getString(R.string.g20), url).getBytes()));
            }
        }
        return null;
    }

    private void c170(String sg) {
        h.evaluateJavascript(sg, s -> android.util.Log.d("Webvium", sg));
    }

    private void c171(final String sg) {
        final FrameLayout k = findViewById(R.id.i);
        View c = View.inflate(this, R.layout.a8, null);
        final LinearLayout ll = c.findViewById(R.id.h20);
        final ImageView iv = c.findViewById(R.id.c19);
        ll.setBackgroundColor(Resources.getColor(this, android.R.color.transparent));
        Runnable p15 = () -> {
            final Bitmap bp = BitmapFactory.decodeFile(sg);
            runOnUiThread(() -> iv.setImageBitmap(bp));
        };
        new Thread(p15).start();
        k.addView(c);
        Animation.animate(this, R.anim.e, iv);
        O3 timer = new O3(1000, 1000, k, c);
        timer.start();
    }

    private void c172(HttpAuthHandler handler, String host, String realm) {
        String a = null;
        String b = null;
        boolean c = handler.useHttpAuthUsernamePassword();
        if (c) {
            String[] cre = h.getHttpAuthUsernamePassword(host, realm);
            if (cre != null && cre.length == 2) {
                a = cre[0];
                b = cre[1];
            }
        }
        if (a != null && b != null) {
            handler.proceed(a, b);
        }
    }

    public String c175() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss:SSS | aa", Locale.US);
        return sdf.format(new Date());
    }

    public void c176(boolean n) {
        if (n) {
            ab.hide();
            llt.setVisibility(View.GONE);
        } else {
            ab.show();
            llt.setVisibility(View.VISIBLE);
        }
    }

    private boolean c177(String b) {
        MailTo sg = MailTo.parse(b);
        if (sg.getTo().length() > 0) {
            Map<String, String> hd = sg.getHeaders();
            Intent it = new Intent(Intent.ACTION_SEND);
            it.setType("text/plain");
            it.putExtra("android.intent.extra.EMAIL", new String[]{sg.getTo()});
            it.putExtra("android.intent.extra.SUBJECT", sg.getSubject());
            it.putExtra("andorid.intent.extra.CC", sg.getCc());
            if (hd.containsKey("bcc")) {
                it.putExtra("android.intent.extra.BCC", hd.get("bcc"));
            }
            it.putExtra("android.intent.extra.TEXT", sg.getBody());
            if (it.resolveActivity(getPackageManager()) != null) {
                startActivity(it);
                return true;
            }
        }
        return false;
    }

    private boolean c178(String sg) {
        String[] sg5 = sg.split(":");
        Intent it = new Intent(Intent.ACTION_SENDTO);
        it.setData(Uri.parse("smsto:" + sg5[1]));
        it.putExtra("address", sg5[1]);
        it.putExtra("sms_body", sg5[2]);
        if (it.resolveActivity(getPackageManager()) != null) {
            startActivity(it);
            return true;
        }
        return false;
    }

    private boolean c179(String sg) {
        String str = sg.substring(sg.indexOf("/store/apps/details?id=") + 23);
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setData(Uri.parse("market://details?id=" + str));
        if (it.resolveActivity(getPackageManager()) != null) {
            startActivity(it);
            return true;
        }
        return false;
    }

    private void c180() {
        try {
            if (Connectivity.isThereAnyInternetConnection(this)) {
                if (Connectivity.isAirplaneMode(this)) {
                    tv.setImageResource(R.drawable.a3);
                } else {
                    tv.setImageResource(R.drawable.a4);
                }
                tv.setVisibility(View.VISIBLE);
                h.setNetworkAvailable(false);
                Animation.animate(this, R.anim.i, tv);
            } else {
                tv.setVisibility(View.GONE);
                h.setNetworkAvailable(true);
                Animation.animate(this, R.anim.b, tv);
            }
        } catch (Exception rx) {
            rx.printStackTrace();
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int a, String[] b, int[] c) {
        super.onRequestPermissionsResult(a, b, c);
        switch (a) {
            case 1:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    if (pend != null) {
                        c11(pend);
                        pend = null;
                    } else {
                        h.reload();
                    }
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        c7(getString(R.string.u15));
                    } else {
                        c53(getString(R.string.u16));
                    }
                    pend = null;
                }
                break;
            case 2:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    h.reload();
                } else {
                    if (Objects.requireNonNull(h.getUrl()).startsWith("file://")) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                            c7(getString(R.string.u17));
                        } else {
                            c53(getString(R.string.u18));
                        }
                    }

                }
                break;
            case 3:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    if (Objects.requireNonNull(a221().getString("SVCTb", "1a2")).equals("1a2")) {
                        c68(back23);
                    } else {
                        c68(h);
                    }
                    SoftKeyboard.hide(this, back23);
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        c7(getString(R.string.u15));
                    } else {
                        c53(getString(R.string.u16));
                    }
                }
                break;
            case 4:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {

                    c55(h.getUrl(), h.getTitle());

                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                        c7(getString(R.string.u15));
                    } else {
                        c53(getString(R.string.u16));
                    }
                }
                break;
            case 5:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    if (w6 != null) {
                        w6.b.invoke(w6.a, true, false);
                        c8(String.format(getString(R.string.v15),w6.a));
                        w6 = null;
                    } else {
                        h.reload();
                    }
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

                        c7(getString(R.string.w4));
                    } else {
                        c53(getString(R.string.w5));
                    }
                    w6 = null;
                }
                break;

            case 6:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    if (w8 != null) {
                        w8.pr.grant(w8.pr.getResources());
                        d12.c(new PermissionObjectDataModel(w8.pr.getOrigin().getHost(), Arrays.toString(w8.pr.getResources()), "true", "false"));

                        c8(String.format(getString(R.string.i40), Objects.requireNonNull(w8.pr.getOrigin().getHost()), Arrays.toString(w8.pr.getResources())));
                        w8 = null;
                    } else {
                        h.reload();
                    }
                } else {

                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        c7(getString(R.string.j22));
                    } else {
                        c53(getString(R.string.j23));
                    }
                    w8 = null;

                }
                break;

            case 7:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    if (w8 != null) {
                        w8.pr.grant(w8.pr.getResources());
                        d12.c(new PermissionObjectDataModel(w8.pr.getOrigin().getHost(), Arrays.toString(w8.pr.getResources()), "true", "false"));
                        c8(String.format(getString(R.string.i40), Objects.requireNonNull(w8.pr.getOrigin().getHost()), Arrays.toString(w8.pr.getResources())));
                        w8 = null;
                    } else {
                        h.reload();
                    }
                } else {

                    if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                        c7(getString(R.string.j24));
                    } else {
                        c53(getString(R.string.j25));
                    }
                    w8 = null;
                }
                break;

            case 8:
                if (c.length > 0 && c[0] == PackageManager.PERMISSION_GRANTED) {
                    if (cll != null)
                        c106(cll);
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                        c7(getString(R.string.w26));
                    } else {
                        c53(getString(R.string.w27));
                    }
                }
                cll = null;

                break;

        }
    }

    @Override
    public boolean onKeyDown(int a, KeyEvent b) {
        if (a == KeyEvent.KEYCODE_VOLUME_UP) {
            if (a221().getString("VU", "") == null) {
                c125(1);
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "7u")) {
                h.pageUp(true);
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "30u")) {
                h.zoomIn();
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "60u") && h.canGoBack()) {
                h.goBack();
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "120u")) {
                h.reload();
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "140u")) {
                Intents.f(this, BOOK.class, 2115);
                return true;
            }
            return true;
        } else if (a == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (a221().getString("VD", "") == null) {
                c125(0);
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "1v")) {
                c125(0);
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "7v")) {
                h.pageDown(true);
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "30v")) {
                h.zoomOut();
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "60v") && h.canGoForward()) {
                h.goForward();
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "120v")) {
                h.stopLoading();
                h.getFirstClient().onPageFinished(h, h.getUrl());
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "140v")) {
                Intents.f(this, HIST.class, 211);
                return true;
            }
            return true;
        }
        return super.onKeyDown(a, b);
    }

    @Override
    public boolean onKeyUp(int a, KeyEvent b) {
        if (a == 4 && b.isTracking() && !b.isCanceled()) {
            if (!h.canGoBack()) {
                c25();
                moveTaskToBack(true);
            } else {
                h.goBack();
            }
            return true;
        }
        return super.onKeyUp(a, b);
    }

    @Override
    public boolean onKeyLongPress(int a, KeyEvent b) {
        if (a == 4 && !b.isCanceled()) {
            if (a221().getString("longP", "") == null) {
                c25();
                moveTaskToBack(true);
                return true;
            } else if (Objects.equals(a221().getString("longP", ""), "1p")) {
                c25();
                moveTaskToBack(true);
                return true;
            } else if (Objects.equals(a221().getString("longP", ""), "7p")) {
                c3(c48());
                return true;
            } else if (Objects.equals(a221().getString("longP", ""), "30p")) {
                h.reload();
                return true;
            } else if (Objects.equals(a221().getString("longP", ""), "60p")) {
                if (!h.canGoBack()) {
                    c25();
                    moveTaskToBack(true);
                } else {
                    h.goBack();
                }
                return true;
            }
            return true;
        }
        return super.onKeyLongPress(a, b);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        a.add(0, 24, 0, getString(R.string.h20)).setCheckable(true);
        // web osint
        SubMenu sm = a.addSubMenu(getString(R.string.j36));
        sm.add(0, 1, 0, getString(R.string.y15));
        // sm.add(0, 2, 0, getString(R.string.x9));
        // sm.add(0, 3, 0, getString(R.string.z15));
        // sm.add(0, 4, 0, getString(R.string.x16));
        // sm.add(0, 5, 0, getString(R.string.y11));
        // sm.add(0, 6, 0, getString(R.string.z4));
        sm.add(0, 7, 0, getString(R.string.h6));
        sm.add(0, 8, 0, getString(R.string.j));
        // sm.add(0, 9, 0, getString(R.string.z12));
        sm.add(0, 25, 0, getString(R.string.f32));
        sm.add(0, 28, 0, getString(R.string.y76));
        sm.add(0, 29, 0, getString(R.string.y77));
        sm.add(0, 30, 0, getString(R.string.y78));
        // error
        SubMenu b = a.addSubMenu(getString(R.string.h30));
        b.add(0, 10, 0, getString(R.string.h36));
        b.add(0, 11, 0, getString(R.string.e2));
        // javascript
        SubMenu c = a.addSubMenu(getString(R.string.c8));
        c.add(0, 12, 0, getString(R.string.f23));
        c.add(0, 13, 0, getString(R.string.h4));
        a.add(0, 26, 0, getString(R.string.y73));
        a.add(0, 27, 0, getString(R.string.y74));
        a.add(0, 14, 0, getString(R.string.k1));
        a.add(0, 15, 0, getString(R.string.h7));
        a.add(0, 16, 0, getString(R.string.h35));
        a.add(0, 17, 0, getString(R.string.y14));
        a.add(0, 19, 0, getString(R.string.z16));
        a.add(0, 20, 0, getString(R.string.z17));
        a.add(0, 21, 0, getString(R.string.i4));
        a.add(0, 22, 0, getString(R.string.w3));
        a.add(0, 23, 0, getString(R.string.o5));
        return super.onCreateOptionsMenu(a);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu a) {
        if (ua) {
            a.findItem(24).setChecked(true);
        }
        return super.onPrepareOptionsMenu(a);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem a) {

        switch (a.getItemId()) {
            case 26:
                c130(BASE64_ENCODE);
                return true;
            case 27:
                c130(URL_ENCODE);
                return true;
            case 28:
                c112(h.getUrl(), ASSETLINKS);
                return true;
            case 29:
                c112(h.getUrl(), SITEMAPS);
                return true;
            case 30:
                c44(h.getUrl());
                return true;
            case 17:
                c119();
                return true;
            case 10:
                if (cm0.capacity() > 16) {
                    c164();
                } else {
                    c7(getString(R.string.i33));
                }
                return true;
            case 11:
                if (cm2.capacity() > 16) {
                    c165();
                } else {
                    c7(getString(R.string.i34));
                }
                return true;
            //     case R.id.l1:
            //  c112(h.getUrl(), 6);
            //    return true;
            case 12:
                if (ws.getJavaScriptEnabled()) {
                    c127();
                } else {
                    c7(getString(R.string.u13));
                }
                return true;
            case 1:
                c112(h.getUrl(), 5);
                return true;
            case 3:
                c112(h.getUrl(), 4);
                return true;
            case 25:
                c112(h.getUrl(), 6);
                return true;
            case 9:
                c112(h.getUrl(), 8);
                return true;
            case 19:
                c122();
                return true;
            case 16:
                c159();
                return true;
            case 20:
                c123();
                return true;
            case 22:
                c100(h);
                return true;
            case 8:
                c112(h.getUrl(), 7);
                return true;
            case 24:
                if (a.isChecked()) {
                    a.setChecked(false);
                    ua = false;
                    c20(false);
                } else {
                    a.setChecked(true);
                    ua = true;
                    c20(true);
                }
                return true;
            case 13:
                if (cm.capacity() > 16) {
                    c47();
                } else {
                    c7(getString(R.string.d20));
                }
                return true;
            case 21:
                if (Permission.check(this, Permission.STORAGE, 4)) {
                    c55(h.getUrl(), h.getTitle());
                }
                return true;
            case 15:
                c67();
                return true;
            case 7:
                c43(h.getUrl());
                return true;
            case 23:
                Intent d = new Intent(Intent.ACTION_GET_CONTENT);
                d.addCategory(Intent.CATEGORY_OPENABLE);
                d.setType("*/*");
                if (d.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 3);
                }
                return true;
            case 14:
                if (Permission.check(this, Permission.STORAGE, 3)) {
                    SoftKeyboard.hide(this, back23);
                    if (Objects.requireNonNull(a221().getString("SVCTb", "1a2")).equals("1a2")) {
                        c68(back23);
                    } else {
                        c68(h);
                    }
                }
                return true;
            //     case R.id.f5:
            //        c58(h.getUrl(), h.getTitle());
            //     return true;
            case 2:
                return true;
            case 4:
                c112(h.getUrl(), 1);
                return true;
            case 5:
                c112(h.getUrl(), 2);
                return true;
            case 6:
                c112(h.getUrl(), 3);
                return true;

        }
        if (a.getItemId() == R.id.g9) {
//new tab
            return true;
        }
        return super.onOptionsItemSelected(a);
    }

    @Override
    public void onConfigurationChanged(Configuration a) {
        super.onConfigurationChanged(a);
        invalidateOptionsMenu();
    }

    @Override
    protected void onNewIntent(Intent a) {
        String sg = a.getStringExtra("webvium");
        String sg0 = a.getStringExtra("value");
        String sg1 = a.getAction();
        String sg12 = a.getDataString();
        if (sg12 != null) {
            String pe;
            try {
                pe = URLDecoder.decode(sg12, "UTF-8");
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
                pe = sg12;
            }
            File fe2 = new File(pe.replace("file://", ""));
            if (sg12.startsWith("file://") && sg12.endsWith(".url") && fe2.exists() && fe2.isFile()) {
                Runnable re = () -> {
                    try {
                        String pe1;
                        try {
                            pe1 = URLDecoder.decode(sg12, "UTF-8");
                        } catch (UnsupportedEncodingException unsupportedEncodingException) {
                            unsupportedEncodingException.printStackTrace();
                            pe1 = sg12;
                        }
                        java.io.File fe = new java.io.File(pe1.replace("file://", ""));
                        FileReader fr = new FileReader(fe);
                        BufferedReader br = new BufferedReader(fr);
                        StringBuilder sb = new StringBuilder();
                        String ln;
                        while ((ln = br.readLine()) != null) {
                            sb.append(ln);
                            sb.append("<br>");
                        }
                        fr.close();
                        br.close();
                        String[] sgdd = sb.toString().split("<br>");
                        for (String dt : sgdd) {
                            if (dt.startsWith("URL=")) {
                                runOnUiThread(() -> c3(dt.replace("URL=", "")));
                                return;
                            }
                        }
                    } catch (Exception en3) {
                        en3.printStackTrace();
                    }
                };
                new Thread(re).start();
            }
        }
        String queryq = a.getStringExtra("b");
        if (queryq != null) {
            c146(0);
            a.removeExtra("b");
        } else if (sg != null) {
            c3(sg);
            a.removeExtra("webvium");
        } else if (sg0 != null) {
            c49(sg0);
            a.removeExtra("value");
        } else if (sg1.equals(Intents.ACTION_PASTE_SEARCH)) {
            try {
                String c = Clipboard.b(this);
                if (c != null && U3.b(c)) {
                    SearchHelper d2 = SearchHelper.getInstance(getApplicationContext());
                    d2.c(c);
                    c49(c);
                } else {
                    Toast.c(this, getString(R.string.t20));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.c(this, getString(R.string.t20));
            }
        } else if (sg1.equals(Intents.ACTION_LAUNCH)) {
            c3(sg);
            a.removeExtra("webvium");
        } else {
            String sg2 = a.getStringExtra(Intent.EXTRA_TEXT);
            String sg3 = a.getDataString();
            if (sg1.equals(Intent.ACTION_SEND) && sg2 != null) {
                c49(sg2);
            } else if (sg1.equals(Intent.ACTION_VIEW) && sg3 != null) {
                c3(sg3);

            } else if (sg1.equals(Intent.ACTION_SEARCH) || sg1.equals(Intent.ACTION_WEB_SEARCH) || sg1.equals("android.speech.action.VOICE_SPEECH_RESULTS")) {
                c49(SearchManager.QUERY);
            } else if (sg1.equals(MediaStore.INTENT_ACTION_MEDIA_SEARCH)) {
                c49(a.getStringExtra(MediaStore.EXTRA_MEDIA_ARTIST) + " " + a.getStringExtra(MediaStore.EXTRA_MEDIA_TITLE));
            }
        }
        a.replaceExtras(new Bundle());
        a.setAction("");
        a.setData(null);
        a.setFlags(0);
    }

    @Override
    public void onWindowFocusChanged(boolean a) {
        super.onWindowFocusChanged(a);
        if (a && bl3) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (Objects.requireNonNull(a221().getString("hide", "")).equals("30d")) {
            if (a) {
                if (Build.VERSION.SDK_INT >= 30) {
                    getWindow().setDecorFitsSystemWindows(false);
                } else {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN);
                }
            } else {
                c98();
            }
        } else {
            c98();
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        c176(isInPictureInPictureMode);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        c176(isInMultiWindowMode);
    }

    @Override
    public String format() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyy_HHmm", Locale.US);
        return sdf.format(new Date());
    }

    private static class O3 extends CountDownTimer {
        private final FrameLayout fl;
        private final View w;

        public O3(long a, long b, FrameLayout fl4, View c) {
            super(a, b);
            fl = fl4;
            w = c;
        }

        @Override
        public void onFinish() {
            fl.removeView(w);
        }
    }

    private class R7 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
                    a225("aso", true);
                }
            }
        }
    }

    private class R6 extends MainReceiver {
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            if (sg.equals("android.net.conn.CONNECTIVITY_CHANGE") || sg.equals("android.net.wifi.WIFI_STATE_CHANGED") || sg.equals("android.intent.action.AIRPLANE_MODE")) {
                c180();
                if (Objects.equals(h.getTitle(), getSharedPreferences("wv", 0).getString("di", "742"))) {
                    h.reload();
                }
            }
        }
    }

    private class R36 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            try {
                String sg = b.getAction();
                if (sg.equals("android.intent.action.BATTERY_CHANGED")) {
                    if (Objects.requireNonNull(a221().getString("screen", "")).equals("30j")) {
                        int d = b.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                        if (d == 1) {
                            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        } else {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private class ca149 extends MainReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            super.onReceive(a, b);
            String sg = b.getAction();
            if (sg.equals(Intents.ACTION_INVALIDATE)) {
                c149();
            }
        }
    }
}
