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
 *
 * At the time i wrote this only me and god knows,
 * now only God knows.
 */

package com.mrepol742.webvium;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
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
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Message;
import android.preference.PreferenceManager;
import android.print.PrintManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mrepol742.webvium.app.GeolocationDataModel;
import com.mrepol742.webvium.app.Notifications;
import com.mrepol742.webvium.app.PendingDownloadDataModel;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.WebViews;
import com.mrepol742.webvium.app.main.MainBaseActivity;
import com.mrepol742.webvium.app.main.MainNotification;
import com.mrepol742.webvium.app.main.MainWebViewClient;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.app.Clipboard;
import com.mrepol742.webvium.app.Intents;
import com.mrepol742.webvium.app.Package;
import com.mrepol742.webvium.app.Resources;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.util.FileUtil;
import com.mrepol742.webvium.app.StorageDirectory;
import com.mrepol742.webvium.app.Permission;
import com.mrepol742.webvium.net.Connectivity;
import com.mrepol742.webvium.net.IPAddress;
import com.mrepol742.webvium.net.Ping;
import com.mrepol742.webvium.app.Vibrator;
import com.mrepol742.webvium.permission.PermissionDataModel;
import com.mrepol742.webvium.permission.PermissionHelper;
import com.mrepol742.webvium.permission.PermissionObjectDataModel;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.security.SHA;
import com.mrepol742.webvium.security.XOR;
import com.mrepol742.webvium.security.Caesar;
import com.mrepol742.webvium.tab.NewTabAdapter;
import com.mrepol742.webvium.tab.NewTabDataModel;
import com.mrepol742.webvium.util.Html;
import com.mrepol742.webvium.util.AppID;
import com.mrepol742.webvium.security.Base64;
import com.mrepol742.webvium.util.Domain;
import com.mrepol742.webvium.app.Format;
import com.mrepol742.webvium.util.IdentityGenerator;
import com.mrepol742.webvium.net.Stream;
import com.mrepol742.webvium.util.PassGen;
import com.mrepol742.webvium.util.U3;
import com.mrepol742.webvium.util.BitmapCache;
import com.mrepol742.webvium.util.Animation;
import com.mrepol742.webvium.app.SoftKeyboard;
import com.mrepol742.webvium.util.AwesomeToast;
import com.mrepol742.webvium.download.DownloadHelper;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.reflect.*;

/*
 * @WebviumActivity
 */

public class Webv extends MainBaseActivity implements Format {
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
    public static final int POPUPMENU_WEBV_COPY = 4;
    public static final int POPUPMENU_WEBV_SHARE = 3;
    public static final int POPUPMENU_WEBV_ADD_TO_BOOKMARKS = 6;
    public static final int POPUPMENU_WEBV_ADD_TO_HOMESCREEN = 7;
    public static final int POPUPMENU_WEBV_WEB_OSINT_HEADERS = 19;
    public static final int POPUPMENU_WEBV_WEB_OSINT_LINKS = 15;
    public static final int POPUPMENU_WEBV_WEB_OSINT_META_TAGS = 20;
    public static final int POPUPMENU_WEBV_WEB_OSINT_ROBOTSTXT = 21;
    public static final int POPUPMENU_WEBV_WEB_OSINT_TRANCEROUTE = 16;
    public static final int POPUPMENU_WEBV_WEB_OSINT_NPING = 17;
    public static final int POPUPMENU_WEBV_WEB_OSINT_WHOIS = 18;
    public static final int POPUPMENU_WEBV_WEB_OSINT_NSLookup = 13;
    public static final int POPUPMENU_WEBV_WEB_OSINT_SOURCE_CODE = 5;
    public static final int POPUPMENU_WEBV_WEB_OSINT_SAVE_LINK = 12;
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
    final int WEBVIUM_SEARCH = 0;
    final int WEBVIUM_HISTORY = 2;
    final int WEBVIUM_BOOKMARKS = 4;
    private final R6 br = new R6();
    private final IntentFilter ift = new IntentFilter();
    private final IntentFilter ift1 = new IntentFilter();
    private final IntentFilter ift3 = new IntentFilter();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final HashMap<String, String> eh = new HashMap<>(1);
    private final StringBuilder cm = new StringBuilder();
    private final StringBuilder cm0 = new StringBuilder();
    private final StringBuilder cm2 = new StringBuilder();
    private final ArrayList<WebViews> tabs = new ArrayList<>();
    public WebViews h;
    public int it742;
    public ProgressBar g;
    public Toolbar o;
    public TextView u;
    public int a7;
    public int a8;
    public RelativeLayout cd;
    public RelativeLayout back23;
    public Timer timer;
    public int it7422;
    public Timer cdt;
    public ImageView tv, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    private CookieManager cm1;
    private HistoryHelper d1;
    private SearchHelper d2;
    private BookmarkHelper d3;
    private GeolocationDataModel w6;
    private PermissionDataModel w8;
    private R36 br2;
    private R8 r8;
    private ca149 br4;
    private ImageView iw;
    private ValueCallback<Uri[]> b;
    private View cv;
    private WebChromeClient.CustomViewCallback cvc;
    private TextView tv10;
    private LinearLayout llt;
    private ActionBar ab;
    private WebViewDatabase wd;
    private PendingDownloadDataModel pend;
    private ForegroundColorSpan A, E, S, I, B;
    private PermissionHelper d12;
    private R7 r7;
    private FrameLayout fl;
    private PopupMenu pm0, pm1, pm2, pm3, pm4, pm5, pm6, pm7, pm8;
    private TextView inf;
    private String sg;
    private BroadcastReceiver ipH;
    private int ct;
    private boolean pageF;
    public static final String INIT = "init_17";
    public static boolean bl = false;
    public static boolean bl2 = false;
    private boolean bl3, bl6, err, ua, set, inE, dsM, isSh = false;
    public static boolean bl4 = false;
    private String des;

    final MenuItem.OnMenuItemClickListener mio = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            switch (a1.getItemId()) {
                case POPUPMENU_TOOLBAR_PASTE_AND_SEARCH:
                    try {
                        String c = Clipboard.b(Webv.this);
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
                    return true;
                case POPUPMENU_TOOLBAR_PASTE:
                    try {
                        String c = Clipboard.b(Webv.this);
                        if (c != null) {
                            Intents.h(Webv.this, Sear.class, 911, "value", c);
                            overridePendingTransition(R.anim.a, R.anim.f);
                        } else {
                            c7(getString(R.string.t20));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        c7(getString(R.string.t20));
                    }
                    return true;
                case POPUPMENU_TOOLBAR_COPY_LINK:
                    Clipboard.a(Webv.this, Webv.this.currentUrl());
                    Webv.this.c8(Webv.this.getString(R.string.k9));
                    return true;
                case POPUPMENU_TOOLBAR_SHARE_LINK:
                    Webv.this.c16(Webv.this.currentUrl(), 0);
                    return true;
                case POPUPMENU_TOOLBAR_ADD_TO_HOMESCREEN:
                    Webv.this.c58(Webv.this.currentUrl(), Webv.this.currentTitle());
                    return true;
                case POPUPMENU_TOOLBAR_ADD_TO_BOOKMARKS:
                    Webv.this.c14(Webv.this.currentTitle(), Webv.this.currentUrl());
                    return true;
                case POPUPMENU_TOOLBAR_COPY_TITLE:
                    Clipboard.a(Webv.this, Webv.this.currentTitle());
                    Webv.this.c8(Webv.this.getString(R.string.k9));
                    return true;
                case POPUPMENU_TOOLBAR_SHARE_TITLE:
                    Webv.this.c16(Webv.this.currentTitle(), 0);
                    return true;
            }
            return false;
        }
    };

    final MenuItem.OnMenuItemClickListener e4 = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            switch (a1.getItemId()) {
                case POPUPMENU_PHONE_CALL:
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(sg)));
                    return true;
                case POPUPMENU_PHONE_SEND_SMS:
                    Webv.this.c105(sg);
                    return true;
                case POPUPMENU_PHONE_SHARE:
                    Webv.this.c16(sg, 1);
                    return true;
                case POPUPMENU_PHONE_COPY:
                    Clipboard.a(Webv.this, sg);
                    Webv.this.c8(Webv.this.getString(R.string.k9));
                    return true;
                case POPUPMENU_PHONE_ADD_TO_CONTACTS:
                    Webv.this.c110(sg);
                    return true;
            }
            return false;
        }
    };

    final MenuItem.OnMenuItemClickListener e3 = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            if (a1.getItemId() == POPUPMENU_GEO_SHARE) {
                Webv.this.c16(sg, 1);
                return true;
            }
            return false;
        }
    };

    final MenuItem.OnMenuItemClickListener e2 = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            switch (a1.getItemId()) {
                case POPUPMENU_MAIL_COPY:
                    Clipboard.a(Webv.this, sg);
                    Webv.this.c8(Webv.this.getString(R.string.k9));
                    return true;
                case POPUPMENU_MAIL_SEND_EMAIL:
                    Intent a = new Intent(Intent.ACTION_SENDTO, Uri.parse(sg));
                    a.putExtra(Intent.EXTRA_SUBJECT, "");
                    a.putExtra(Intent.EXTRA_TEXT, "");
                    if (a.resolveActivity(getPackageManager()) != null) {
                        startActivity(Intent.createChooser(a, getString(R.string.a26)));
                    } else {
                        AwesomeToast.c(Webv.this, getString(R.string.f34));
                    }
                    return true;
                case POPUPMENU_MAIL_SHARE:
                    Webv.this.c16(sg, 1);
                    return true;
            }
            return false;
        }
    };

    final MenuItem.OnMenuItemClickListener e1 = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            switch (a1.getItemId()) {
                case 3:
                    Webv.this.c16(sg, 0);
                    return true;
                case 4:
                    Clipboard.a(Webv.this, sg);
                    Webv.this.c8(Webv.this.getString(R.string.k9));
                    return true;

                case 6:
                    Webv.this.c14(null, sg);
                    return true;
                case 7:
                    Webv.this.c58(sg, null);
                    return true;
                case 12:
                    if (Permission.check(Webv.this, Permission.STORAGE, 4)) {
                        Webv.this.c55(sg, null);
                    }
                    return true;
                case 13:
                    Webv.this.c43(sg);
                    return true;
                case 5:
                    Webv.this.c112(sg, 7);
                    return true;
                case 15:
                    Webv.this.c112(sg, 0);
                    return true;
                case 16:
                    Webv.this.c112(sg, 1);
                    return true;
                case 17:
                    Webv.this.c112(sg, 2);
                    return true;
                case 18:
                    Webv.this.c112(sg, 3);
                    return true;
                case 19:
                    Webv.this.c112(sg, 5);
                    return true;
                case 20:
                    Webv.this.c112(sg, 4);
                    return true;
                case 21:
                    Webv.this.c112(sg, 6);
                    return true;
                case 25:
                    Webv.this.c112(sg, ASSETLINKS);
                    return true;
                case 26:
                    Webv.this.c112(sg, SITEMAPS);
                    return true;
            }
            return false;
        }
    };

    final MenuItem.OnMenuItemClickListener e23 = new MenuItem.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem a1) {
            switch (a1.getItemId()) {
                case POPUPMENU_IMAGE_VIEW_IMAGE:
                    Webv.this.c3(sg);
                    return true;
                case POPUPMENU_IMAGE_COPY:
                    Clipboard.a(Webv.this, sg);
                    Webv.this.c8(Webv.this.getString(R.string.k9));
                    return true;
                case POPUPMENU_IMAGE_SHARE:
                    Webv.this.c16(sg, 0);
                    return true;
                case POPUPMENU_IMAGE_SAVE_IMAGE:
                    Webv.this.c74(new PendingDownloadDataModel(sg, sg, sg, 0L, sg));
                    return true;
            }
            return false;
        }
    };

    public static void forceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getWebviumCurrentView(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    @Override
    protected void onActivityResult(int a, int b, Intent c) {
        super.onActivityResult(a, b, c);
        if (a == 742) {
            if (b == Activity.RESULT_OK && null != c) {
                String query = c.getStringExtra("changedTo");
                c8(String.format(getString(R.string.d39), Objects.requireNonNull(query)));
                c49(query);
                d2.c(query);
                c.removeExtra("changedTo");
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
        int k5 = getSharedPreferences(INIT, 0).getInt("noid", 0);
        if (k5 != 275) {
            SharedPreferences a5 = getSharedPreferences(INIT, 0);
            SharedPreferences.Editor b5 = a5.edit();
            b5.putInt("noid", 275);
            b5.apply();
            SharedPreferences a52 = getSharedPreferences("di", 0);
            SharedPreferences.Editor b52 = a52.edit();
            b52.putString("di", SHA.a("SHA-1", String.valueOf(System.currentTimeMillis())));
            b52.putString("di1", AppID.getAppID(this));
            b52.apply();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 6);
            AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            PendingIntent it = PendingIntent.getService(this, 0, new Intent(this, Upda.class), PendingIntent.FLAG_UPDATE_CURRENT);
            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it);
            PendingIntent it1 = PendingIntent.getService(this, 0, new Intent(this, Noti.class), PendingIntent.FLAG_UPDATE_CURRENT);
            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, it1);
            Intents.b(this, Upda.class);
            Intents.b(this, Noti.class);
        }
        if (k5 == 275 && a221().getBoolean("lockWn99", false)) {
            Intent it = new Intent(this, Lock.class);
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
        tabs.add(h);
        ct = 0;
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
        setActionBar(this.o);
        this.ab = getActionBar();
        if (this.ab != null) {
            this.ab.setDisplayShowTitleEnabled(false);
        }
        this.o.setElevation(5);
        this.iw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Webv.this.c75();
            }
        });
        this.iw.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                Webv.this.c140();
                return true;
            }
        });
        this.a7 = Resources.getColor(this, R.color.c);
        this.a8 = Resources.getColor(this, R.color.b);
        this.cd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Webv.this.c22();
            }
        });
        this.cd.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                Webv.this.c12();
                return true;
            }
        });
        this.u.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Webv.this.c22();
            }
        });
        hsv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Webv.this.c22();
            }
        });
        this.u.setTypeface(type(Typeface.NORMAL));
        this.g.setMax(100);
        tv7.setImageResource(R.drawable.d7);
        tv7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Webv.this.c144();
            }
        });
        tv7.setBackgroundResource(R.drawable.b17);
        tv8.setImageResource(R.drawable.b29);
        tv8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // if (BuildConfig.DEBUG) {
                //    Webv.this.c10();
                // } else {
                    Webv.this.c18();
                // }
            }
        });
        tv9.setImageResource(R.drawable.d9);
        tv9.setBackgroundResource(R.drawable.b17);
        tv9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
        try {
            this.d1 = HistoryHelper.getInstance(getApplicationContext());
            this.d2 = SearchHelper.getInstance(getApplicationContext());
            this.d3 = BookmarkHelper.getInstance(getApplicationContext());
            this.d12 = PermissionHelper.getInstance(getApplicationContext());
            c134();
            if (!a221().getBoolean("autoUpdate", false)) {
                this.u.setTextColor(this.a7);
            } else {
                this.u.setTextColor(this.a8);
            }
            cd.setBackgroundResource(R.drawable.w);
            tv.setBackgroundResource(R.drawable.f2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.cm1 = CookieManager.getInstance();
        if (Objects.requireNonNull(a221().getString("screen", "")).equals("30j")) {
            this.br2 = new R36();
        }
        c34(currentTab());
        eh.put("DNT", "1");
        this.ift.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.ift.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        this.ift.addAction("android.intent.action.AIRPLANE_MODE");
        this.it742 = getRequestedOrientation();
        if (Objects.requireNonNull(a221().getString("screen", "")).equals("30j")) {
            this.ift1.addAction("android.intent.action.BATTERY_CHANGED");
        }
        if (a221().getBoolean("webviumB", false)) {
            br4 = new ca149();
            ift3.addAction(Intents.ACTION_INVALIDATE);
            registerReceiver(br4, ift3);
        }
        tv5.setImageResource(R.drawable.a18);
        tv5.setBackgroundResource(R.drawable.b17);
        this.iw.setImageResource(R.drawable.a15);
        this.cd.setBackgroundResource(R.drawable.w);
        c15(h);
        c50(h);
        tv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Webv.this.currentTab().getProgress() == 100) {
                    Webv.this.currentTab().reload();
                } else {
                    Webv.this.currentTab().stopLoading();
                    Webv.this.currentTab().getFirstClient().onPageFinished(Webv.this.currentTab(), Webv.this.currentUrl());
                }
            }
        });
        tv3.setBackgroundResource(R.drawable.b17);
        if (a221().getBoolean("home", true)) {
            tv1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Webv.this.c51(Webv.this.currentTab());
                }
            });
        }
        if (a221().getBoolean("voice", true) && !spr()) {
            tv2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent a12 = new Intent(Webv.this, Voic.class);
                    Webv.this.startActivityForResult(a12, 742);
                }
            });
        }
        tv4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Webv.this.currentTab().canGoForward()) {
                    Webv.this.currentTab().goForward();
                }
            }
        });
        final MenuItem.OnMenuItemClickListener e = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem a1) {
                Webv.this.c3(a1.getTitle().toString());
                return true;
            }
        };
        tv4.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (Webv.this.currentTab().w4.size() == 0) {
                    return false;
                }
                if (pm0 == null) {
                    pm0 = new PopupMenu(Webv.this, view);
                    pm0.setOnDismissListener(new PopupMenu.OnDismissListener() {

                        @Override
                        public void onDismiss(PopupMenu popupMenu) {
                            popupMenu.getMenu().clear();
                        }
                    });
                }
                Menu me = pm0.getMenu();
                int size = Webv.this.currentTab().w4.size();
                for (int i = 0; i < size; i++) {
                    me.add(0, i, 0, Webv.this.currentTab().w4.get(i).sg).setOnMenuItemClickListener(e);
                }
                pm0.show();
                return true;
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (currentSettings().getJavaScriptEnabled()) {
                    if (Webv.this.des != null) {
                        AlertDialog.Builder a = new AlertDialog.Builder(Webv.this);
                        a.setTitle(currentTitle());
                        a.setCancelable(true);
                        a.setMessage(Webv.this.des);
                        a.setPositiveButton(getString(R.string.y89), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface a2, int i) {
                                a2.dismiss();
                            }
                        });
                        final AlertDialog g = a.create();
                        g.show();
                    } else {
                        c7(getString(R.string.z82));
                    }
                } else {
                    c7(getString(R.string.u13));
                }
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Webv.this.currentTab().canGoBack()) {
                    Webv.this.currentTab().goBack();
                }
            }
        });
        tv6.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (Webv.this.currentTab().w4.size() == 0) {
                    return false;
                }
                if (pm0 == null) {
                    pm0 = new PopupMenu(Webv.this, view);
                    pm0.setOnDismissListener(new PopupMenu.OnDismissListener() {

                        @Override
                        public void onDismiss(PopupMenu popupMenu) {
                            popupMenu.getMenu().clear();
                        }
                    });
                }
                Menu me = pm0.getMenu();
                int size = Webv.this.currentTab().w4.size();
                for (int i = 0; i < size; i++) {
                    me.add(0, i, 0, Webv.this.currentTab().w4.get(i).sg0).setOnMenuItemClickListener(e);
                }
                pm0.show();
                return true;
            }
        });
        tv.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (Connectivity.isAirplaneMode(Webv.this)) {
                    tv.setVisibility(View.GONE);
                    Animation.animate(Webv.this, R.anim.b, tv);
                    AwesomeToast.a(Webv.this, Webv.this.getString(R.string.m24));
                } else {
                    tv.setVisibility(View.GONE);
                    Animation.animate(Webv.this, R.anim.b, tv);
                    AwesomeToast.a(Webv.this, Webv.this.getString(R.string.m25));
                }
                return true;
            }
        });
        currentTab().setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                return Webv.this.c152();
            }
        });
        tv.setVisibility(View.GONE);
        c149(h);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Animation.animate(this, R.anim.a, llt);
            if (!a221().getBoolean("textST", true)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    currentSettings().setDisabledActionModeMenuItems(WebSettings.MENU_ITEM_NONE);
                }
            }
            c52();
            if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
                if (r7 == null) {
                    r7 = new R7();
                    registerReceiver(r7, new IntentFilter(Intent.ACTION_SCREEN_ON));
                } else {
                    boolean bn = a224("aso", false);
                    if (bn) {
                        Intent it = new Intent(this, Lock.class);
                        startActivityForResult(it, 345);
                        overridePendingTransition(R.anim.f, R.anim.b);
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 24) {
                boolean bn1 = isInMultiWindowMode() || isInPictureInPictureMode();
                if (bn1) {
                    c176(true);
                }
            }
            currentTab().resumeTimers();
            currentTab().onResume();
            c15(currentTab());
            bl = true;
            bl2 = true;
            registerReceiver(br, ift);
            if (Objects.requireNonNull(a221().getString("screen", "")).equals("30j")) {
                registerReceiver(br2, ift1);
            }
            if (a221().getBoolean("home", true)) {
                tv1.setImageResource(R.drawable.a12);
                tv1.setBackgroundResource(R.drawable.b17);
                tv1.setVisibility(View.VISIBLE);
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
            currentTab().pauseTimers();
            currentTab().onPause();
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
        try {
            for (WebViews web : tabs) {
                web.removeAllViews();
                web.destroy();
                fl.removeAllViews();
            }
        } catch (Exception en) {
            en.printStackTrace();
        }
        bl2 = false;
        if (r7 != null) {
            unregisterReceiver(r7);
        }
        if (br4 != null) {
            unregisterReceiver(br4);
        }
    }

    private void c3(String a) {
        c33(a, "");
        WebViews as = currentTab();
        as.loadUrl(a);
    }

    private void c3(WebViews web, String a) {
        c33(a, "");
        web.loadUrl(a);
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
        AwesomeToast.c(this, a);
    }

    public void c8(String a) {
        AwesomeToast.b(this, a);
    }

    public void c9(final PermissionRequest pr) {
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage(Html.b(String.format(getString(R.string.i38), c12(pr.getOrigin()), c11(Arrays.toString(pr.getResources())))));
        d.setCancelable(false);
        d.setPositiveButton(getString(R.string.v17), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int i) {
                if (Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE) && Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
                    if (!Permission.check(Webv.this, Permission.CAMERA, 9) && !Permission.check(Webv.this, Permission.MICROPHONE, 10)) {
                        w8 = new PermissionDataModel(pr);
                    } else {
                        pr.grant(pr.getResources());
                        d12.c(new PermissionObjectDataModel(SHA.a("SHA-1", pr.getOrigin().toString()),
                                SHA.a("SHA-1", Arrays.toString(pr.getResources())),
                                "true",
                                "false"));
                        Webv.this.c8(String.format(Webv.this.getString(R.string.i40), pr.getOrigin(), Arrays.toString(pr.getResources())));
                    }
                } else if (Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE)) {
                    if (!Permission.check(Webv.this, Permission.CAMERA, 6)) {
                        w8 = new PermissionDataModel(pr);
                    } else {
                        pr.grant(pr.getResources());
                        d12.c(new PermissionObjectDataModel(SHA.a("SHA-1", pr.getOrigin().toString()),
                                SHA.a("SHA-1", Arrays.toString(pr.getResources())),
                                "true",
                                "false"));
                        Webv.this.c8(String.format(Webv.this.getString(R.string.i40), pr.getOrigin(), Arrays.toString(pr.getResources())));
                    }
                } else if (Arrays.toString(pr.getResources()).contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
                    if (!Permission.check(Webv.this, Permission.MICROPHONE, 7)) {
                        w8 = new PermissionDataModel(pr);
                    } else {
                        pr.grant(pr.getResources());
                        d12.c(new PermissionObjectDataModel(SHA.a("SHA-1", pr.getOrigin().toString()),
                                SHA.a("SHA-1", Arrays.toString(pr.getResources())),
                                "true",
                                "false"));
                        Webv.this.c8(String.format(Webv.this.getString(R.string.i40), pr.getOrigin(), Arrays.toString(pr.getResources())));
                    }
                }
                a1.dismiss();
            }
        });
        d.setNegativeButton(getString(R.string.i39), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int i) {
                pr.deny();
                Webv.this.c7(String.format(Webv.this.getString(R.string.j21), pr.getOrigin().getHost(), Arrays.toString(pr.getResources())));
                a1.dismiss();
            }
        });
        AlertDialog e = d.create();
        e.show();
    }

    private void c10() {
        ListView lv = new ListView(this);
        final ArrayList<NewTabDataModel> ws = new ArrayList<>();
        int size = tabs.size();
        for (int i = 0; i < size; i++) {
            ws.add(new NewTabDataModel(getFavicon(i), getTitle(i), getUrl(i)));
        }
        lv.setPadding(10, 10, 10, 10);
        ws.add(new NewTabDataModel(null, "webvium://newtab", null));
        ws.add(new NewTabDataModel(null, "webvium://closealltab", null));
        NewTabAdapter nta = new NewTabAdapter(this, ws);
        lv.setAdapter(nta);
        // fl.addView(lv);

        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setView(lv);
        bld.setCancelable(true);
        final AlertDialog dd = bld.create();
        dd.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a4, View b, int c, long d) {
                AwesomeToast.b(Webv.this, (ws.size() - 1) + " " + c);
                try {
                    if (ws.size() == c) {
                        for (WebViews tab : tabs) {
                            tab.destroy();
                        }
                        tabs.clear();
                        WebViews web = new WebViews(Webv.this);
                        tabs.add(web);
                        fl.removeAllViews();
                        Webv.this.c50(web);
                        Webv.this.c34(web);
                        Webv.this.c15(web);
                        Webv.this.c149(web);
                        fl.addView(web);
                        ct = 0;
                        Webv.this.c8("Tabs Cleared.");
                        dd.dismiss();
                    } else if (ws.size() - 1 == c) {
                        Webv.this.currentTab().pauseTimers();
                        Webv.this.currentTab().onPause();
                        fl.removeAllViews();
                        WebViews web = new WebViews(Webv.this);
                        tabs.add(web);
                        Webv.this.c50(web);
                        Webv.this.c34(web);
                        Webv.this.c15(web);
                        Webv.this.c149(web);
                        fl.addView(web);
                        ct = tabs.size() - 1;
                        dd.dismiss();
                    } else {
                        Webv.this.currentTab().pauseTimers();
                        Webv.this.currentTab().onPause();
                        fl.removeAllViews();
                        WebViews webb = tabs.get(c);
                        webb.resumeTimers();
                        webb.onResume();
                        Webv.this.c149(webb);
                        fl.addView(webb);
                        ct = c - 2;
                        dd.dismiss();
                    }
                } catch (Exception ignored) {

                }
            }
        });
    }

    private void c18() {
        if (pm8 == null) {
            pm8 = new PopupMenu(this, tv8);
            pm8.setOnDismissListener(new PopupMenu.OnDismissListener() {

                @Override
                public void onDismiss(PopupMenu popupMenu) {
                    popupMenu.getMenu().clear();
                }
            });
        }
        MenuItem.OnMenuItemClickListener e = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem a1) {
                if (a1.getItemId() == 742) {
                    Webv.this.currentTab().pauseTimers();
                    Webv.this.currentTab().onPause();
                    fl.removeAllViews();
                    WebViews web = new WebViews(Webv.this);
                    tabs.add(web);
                    Webv.this.c50(web);
                    Webv.this.c34(web);
                    Webv.this.c15(web);
                    Webv.this.c149(web);
                    fl.addView(web);
                    ct = tabs.size() - 1;
                } else if (a1.getItemId() == 743) {
                    Webv.this.currentTab().pauseTimers();
                    Webv.this.currentTab().onPause();
                    fl.removeAllViews();
                 
                    tabs.clear();
WebViews web = new WebViews(Webv.this);
                    tabs.add(web);
ct = 0;
                    Webv.this.c50(web);
                    Webv.this.c34(web);
                    Webv.this.c15(web);
                    Webv.this.c149(web);
                    fl.addView(web);
                   
                } else {
                    Webv.this.currentTab().pauseTimers();
                    Webv.this.currentTab().onPause();
                    fl.removeAllViews();
                    WebViews webb = tabs.get(a1.getItemId());
                    webb.resumeTimers();
                    webb.onResume();
                    Webv.this.c149(webb);
                    fl.addView(webb);
                    ct = a1.getItemId();
                }
                if (Webv.this.currentUrl() != null && Webv.this.currentTitle() != null) {
                    Webv.this.c33(Webv.this.currentUrl(), Webv.this.currentTitle());
                }
                if (Webv.this.currentFavicon() != null) {
                    tv5.setImageBitmap(Webv.this.currentFavicon());
                    tv5.setContentDescription(Webv.this.currentTitle());
                }
                c134();
                return true;
            }
        };
        Menu me = pm8.getMenu();
        int len = tabs.size();
        for (int i = 0; i < len; i++) {
            me.add(0, i, 0, getTitleNonNull(i)).setOnMenuItemClickListener(e).setIcon(getFaviconNonNull(i));
        }
        me.add(0, 742, 0, "New Tab").setOnMenuItemClickListener(e).setIcon(Resources.getDrawable(this, R.drawable.a22));
        me.add(0, 743, 0, "Close All").setOnMenuItemClickListener(e).setIcon(Resources.getDrawable(this, R.drawable.a25));
        forceShowIcon(pm8);
        pm8.show();
    }

    private String getTitleNonNull(int loc) {
        if (tabs.get(loc).getTitle() != null) {
            return tabs.get(loc).getTitle();
        }
        return "Tab " + (loc + 1);
    }

    private Drawable getFaviconNonNull(int loc) {
        if (tabs.get(loc).getFavicon() != null) {
            return new BitmapDrawable(getResources(), tabs.get(loc).getFavicon());
        }
        return Resources.getDrawable(this, R.drawable.a18);
    }

    private Bitmap getFavicon(int loc) {
        if (tabs.get(loc).getFavicon() != null) {
            return tabs.get(loc).getFavicon();
        }
        return BitmapFactory.decodeResource(getResources(), R.drawable.a18);
    }

    private String getTitle(int loc) {
        if (tabs.get(loc).getTitle() != null) {
            return tabs.get(loc).getTitle();
        }
        return "Tab " + (loc + 1);
    }

    private String getUrl(int loc) {
        return tabs.get(loc).getUrl();
    }

    private String c11(String sg) {
        if (sg.contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE) && sg.contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
            return getString(R.string.z59);
        } else if (sg.contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE)) {
            return getString(R.string.z60);
        } else if (sg.contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
            return getString(R.string.z61);
        } else if (sg.contains(PermissionRequest.RESOURCE_PROTECTED_MEDIA_ID)) {
            return getString(R.string.z62);
        } else if (sg.contains(PermissionRequest.RESOURCE_MIDI_SYSEX)) {
            return getString(R.string.z63);
        }
        return null;
    }

    private String c12(Uri sg) {
        if (sg != null) {
            if (sg.getHost().length() != 0) {
                return sg.getHost();
            }
        }
        return Uri.parse(currentUrl()).getHost();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void c34(WebViews h) {
        WebSettings ws = h.getSettings();
        if (Build.VERSION.SDK_INT < 30) {
            ws.setAllowFileAccess(true);
        }
        ws.setBuiltInZoomControls(true);
        ws.setSupportMultipleWindows(false);
        ws.setJavaScriptCanOpenWindowsAutomatically(false);
        if (a221().getBoolean("maUU", false) && (a221().getBoolean("hst", false) || a221().getBoolean("hste", false))) {
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
                            if (a221().getBoolean("maUU", false) && a221().getBoolean("hst", false)) {
                                if (findViewById(R.id.m4).getVisibility() != View.VISIBLE) {
                                    ab.show();
                                    Animation.animate(Webv.this, R.anim.d, o);
                                }
                            }
                            if (a221().getBoolean("maUU", false) && a221().getBoolean("hste", false)) {
                                o.setElevation(5);
                            }
                            if (a221().getBoolean("maUU", false) && a221().getBoolean("hste0", false)) {
                                findViewById(R.id.m4).setVisibility(View.VISIBLE);
                                Animation.animate(Webv.this, R.anim.a, findViewById(R.id.m4));
                            }
                        } else {
                            if (a221().getBoolean("maUU", false) && a221().getBoolean("hst", false)) {
                                if (findViewById(R.id.m4).getVisibility() != View.GONE) {
                                    ab.hide();
                                    Animation.animate(Webv.this, R.anim.a, o);
                                }
                            }
                            if (a221().getBoolean("maUU", false) && a221().getBoolean("hste", false)) {
                                o.setElevation(0);
                            }
                            if (a221().getBoolean("maUU", false) && a221().getBoolean("hste0", false)) {
                                findViewById(R.id.m4).setVisibility(View.GONE);
                                Animation.animate(Webv.this, R.anim.d, findViewById(R.id.m4));
                            }
                        }
                    }
                    return false;
                }
            });
        }
        h.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                PendingDownloadDataModel w18 = new PendingDownloadDataModel(str, str3, str4, j, str2);
                try {
                    WifiManager b = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    if (!a221().getBoolean("autoD", false)) {
                        if (a221().getBoolean("wifi", false)) {
                            if (b.isWifiEnabled()) {
                                if (Permission.check(Webv.this, Permission.STORAGE, 1)) {
                                    c11(w18);
                                } else {
                                    pend = w18;
                                }
                            } else {
                                c7(getString(R.string.v12));
                            }
                        } else {
                            if (Permission.check(Webv.this, Permission.STORAGE, 1)) {
                                c11(w18);
                            } else {
                                pend = w18;
                            }
                        }
                    } else {
                        if (a221().getBoolean("wifi", false)) {
                            if (b.isWifiEnabled()) {
                                if (Permission.check(Webv.this, Permission.STORAGE, 1)) {
                                    c182(w18, URLUtil.guessFileName(w18.a1, w18.a2, w18.a3));
                                } else {
                                    pend = w18;
                                }
                            } else {
                                c7(getString(R.string.v12));
                            }
                        } else {
                            if (Permission.check(Webv.this, Permission.STORAGE, 1)) {
                                c182(w18, URLUtil.guessFileName(w18.a1, w18.a2, w18.a3));
                            } else {
                                pend = w18;
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        h.setWebChromeClient(new WebChromeClient() {

            @Override
            public Bitmap getDefaultVideoPoster() {
                if (a221().getBoolean("webviumP", false)) {
                    if (new java.io.File(StorageDirectory.getVideoPoster(Webv.this)).exists()) {
                        return BitmapCache.getInstance().a(StorageDirectory.getVideoPoster(Webv.this));
                    }
                }
                return null;
            }

            @Override
            public void onReceivedTitle(WebView a, String b) {
                c33(a.getUrl(), b);
            }

            @Override
            public void onHideCustomView() {
                try {
                    ab.show();
                    currentTab().invalidate();
                    bl3 = false;
                    c98();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    c99();
                    ((FrameLayout) getWindow().getDecorView()).removeView(Webv.this.cv);
                    Webv.this.cv = null;
                    getWindow().getDecorView().setSystemUiVisibility(Webv.this.it7422);
                    Webv.this.setRequestedOrientation(Webv.this.it742);
                    Webv.this.cvc.onCustomViewHidden();
                    Webv.this.cvc = null;
                } catch (Exception asd) {
                    asd.printStackTrace();
                }
            }

            @SuppressLint("SourceLockedOrientationActivity")
            @Override
            public void onShowCustomView(View a, WebChromeClient.CustomViewCallback b) {
                try {
                    ab.hide();
                    bl3 = true;
                    currentTab().invalidate();
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("1a1")) {
                        Webv.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                    if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("7a1")) {
                        Webv.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                    }
                    if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("30a1")) {
                        Webv.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }
                    if (Objects.requireNonNull(a221().getString("horiVD", "60a1")).equals("60a1")) {
                        Webv.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                    }
                    if (Webv.this.cv != null) {
                        return;
                    }
                    Webv.this.cv = a;
                    Webv.this.it7422 = getWindow().getDecorView().getSystemUiVisibility();
                    Webv.this.cvc = b;
                    ((FrameLayout) getWindow().getDecorView()).addView(Webv.this.cv, new FrameLayout.LayoutParams(-1, -1));
                    getWindow().getDecorView().setSystemUiVisibility(3846);
                } catch (Exception asd) {
                    asd.printStackTrace();
                }
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> b, FileChooserParams fileChooserParams) {
                Webv.this.b = b;
                Intent d = new Intent();
                d.setAction(Intent.ACTION_GET_CONTENT);
                d.addCategory(Intent.CATEGORY_OPENABLE);
                d.setType("*/*");
                Webv.this.startActivityForResult(Intent.createChooser(d, getString(R.string.a26)), 2);
                return true;
            }

            @Override
            public void onProgressChanged(WebView a, int b) {
                g.setProgress(b);
                if (b == 100 && g.getVisibility() != View.GONE) {
                    tv3.setImageResource(R.drawable.b11);
                    Animation.animate(Webv.this, R.anim.c, tv3);
                    g.setVisibility(View.GONE);
                    Animation.animate(Webv.this, R.anim.b, g);
                    Webv.this.cm1.flush();
                    cdt.cancel();
                    cdt.purge();
                    if (a224("a10", false) && bl6) {
                        // if (HDMS.b(Objects.requireNonNull(changedTo.getTitle()).toLowerCase()) || HDMS.b(Objects.requireNonNull(changedTo.getUrl()).toLowerCase())) {
                        c142(a.getTitle(), a.getUrl());
                    /* } else {
                     c142(getString(R.string.g29), getString(R.string.g30));
                     }*/
                    } else if (a224("a10", false) && !bl6) {
                        //if (HDMS.b(Objects.requireNonNull(changedTo.getTitle()).toLowerCase()) || HDMS.b(Objects.requireNonNull(changedTo.getUrl()).toLowerCase())) {
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
                if (b != 100 && g.getVisibility() != View.VISIBLE) {
                    g.setVisibility(View.VISIBLE);
                    Animation.animate(Webv.this, R.anim.c, g);
                    tv3.setImageResource(R.drawable.a14);
                    Animation.animate(Webv.this, R.anim.c, tv3);
                }
            }

            @Override
            public boolean onJsAlert(WebView a, String b, String c, final JsResult d) {
                if (a221().getBoolean("Java10", true)) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(Webv.this);
                    LayoutInflater d1 = getLayoutInflater();
                    View e5 = d1.inflate(R.layout.a13, null);
                    bld.setView(e5);
                    bld.setCancelable(true);
                    TextView tv = e5.findViewById(R.id.o37);
                    bld.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    tv.setText(c);
                    if (!a221().getBoolean("autoUpdate", false)) {
                        tv.setTextColor(Resources.getColor(Webv.this, R.color.c));
                    } else {
                        tv.setTextColor(Resources.getColor(Webv.this, R.color.b));
                    }
                    bld.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a13, int intetg) {
                            d.confirm();
                            a13.dismiss();
                        }
                    });
                    bld.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface a1) {
                            d.cancel();
                            a1.dismiss();
                        }
                    });
                    AlertDialog dd = bld.create();
                    dd.show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onJsPrompt(WebView a, String b, String c, String d, final JsPromptResult e) {
                if (c.equals(getSharedPreferences("di", 0).getString("di", ""))) {
                    c181(d, e);
                    return true;
                } else if (a221().getBoolean("Java9", true)) {
                    AlertDialog.Builder a89 = new AlertDialog.Builder(Webv.this);
                    LayoutInflater b54 = getLayoutInflater();
                    View c34 = b54.inflate(R.layout.x, null);
                    a89.setCancelable(true);
                    a89.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    a89.setView(c34);
                    TextView sjs1 = c34.findViewById(R.id.e1);
                    final Edit sjs = c34.findViewById(R.id.e3);
                    int e78 = Resources.getColor(Webv.this, R.color.c);
                    int f = Resources.getColor(Webv.this, R.color.b);
                    int f1 = Resources.getColor(Webv.this, R.color.j);
                    int g1 = Resources.getColor(Webv.this, R.color.k);
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
                    sjs.setText(d);
                    a89.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a13, int intetg) {
                            String uwe = sjs.getText().toString();
                            e.confirm(uwe);
                            a13.dismiss();
                        }
                    });
                    a89.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            e.cancel();
                            a12.dismiss();
                        }
                    }).setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface a1) {
                            e.cancel();
                            a1.dismiss();
                        }
                    });
                    final AlertDialog g = a89.create();
                    g.show();
                    final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
                    sjs.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            okButton.setEnabled(sjs.getText().toString().length() != 0);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onJsConfirm(WebView a, String b, String c, final JsResult e) {
                if (a221().getBoolean("Java11", true)) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(Webv.this);
                    LayoutInflater d1 = getLayoutInflater();
                    View e5 = d1.inflate(R.layout.a13, null);
                    bld.setView(e5);
                    bld.setCancelable(true);
                    TextView tv = e5.findViewById(R.id.o37);
                    bld.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    tv.setText(c);
                    if (!a221().getBoolean("autoUpdate", false)) {
                        tv.setTextColor(Resources.getColor(Webv.this, R.color.c));
                    } else {
                        tv.setTextColor(Resources.getColor(Webv.this, R.color.b));
                    }
                    bld.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a13, int intetg) {
                            e.confirm();
                            a13.dismiss();
                        }
                    });
                    bld.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            e.cancel();
                            a12.dismiss();
                        }
                    });
                    bld.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface a1) {
                            e.cancel();
                            a1.dismiss();
                        }
                    });
                    AlertDialog dd = bld.create();
                    dd.show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onJsBeforeUnload(WebView a, String b, String c, final JsResult e) {
                if (a221().getBoolean("Java12", true)) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(Webv.this);
                    LayoutInflater d1 = getLayoutInflater();
                    View e5 = d1.inflate(R.layout.a13, null);
                    bld.setView(e5);
                    bld.setCancelable(false);
                    TextView tv = e5.findViewById(R.id.o37);
                    bld.setTitle(String.format(getString(R.string.f25), Objects.requireNonNull(a.getTitle())));
                    tv.setText(c);
                    if (!a221().getBoolean("autoUpdate", false)) {
                        tv.setTextColor(Resources.getColor(Webv.this, R.color.c));
                    } else {
                        tv.setTextColor(Resources.getColor(Webv.this, R.color.b));
                    }
                    bld.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a13, int intetg) {
                            e.confirm();
                            a13.dismiss();
                        }
                    });
                    bld.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a12, int intetg) {
                            e.cancel();
                            a12.dismiss();
                        }
                    });
                    AlertDialog dd = bld.create();
                    dd.show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage cm1) {
                cm.append(String.format(getString(R.string.v188).replaceAll("742", c6(cm1.messageLevel())), cm1.messageLevel().toString(), cm1.message(), cm1.lineNumber(), cm1.sourceId()))
                        .append("\n");
                return true;
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(final String a, final GeolocationPermissions.Callback b) {
                final boolean c = false;
                AlertDialog.Builder d = new AlertDialog.Builder(Webv.this);
                d.setMessage(String.format(getString(R.string.v14), a));
                d.setCancelable(false);
                d.setPositiveButton(getString(R.string.v17), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a1, int i) {
                        if (Permission.check(Webv.this, Permission.LOCATION, 5)) {
                            b.invoke(a, true, c);
                            c8(String.format(getString(R.string.v15), a));
                        } else {
                            w6 = new GeolocationDataModel(a, b);
                        }
                        a1.dismiss();
                    }
                });
                d.setNegativeButton(getString(R.string.i39), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a1, int i) {
                        b.invoke(a, false, c);
                        c7(String.format(getString(R.string.v16), a));
                        a1.dismiss();
                    }
                });
                AlertDialog e = d.create();
                e.show();
            }

            @Override
            public View getVideoLoadingProgressView() {
                View v = View.inflate(Webv.this, R.layout.a21, null);
                ImageView v4 = v.findViewById(R.id.n28);
                v4.setImageResource(R.drawable.a21);
                return v;
            }

            @Override
            public void onPermissionRequest(final PermissionRequest pr) {
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
                            if (sg1.equals(SHA.a("SHA-1", pr.getOrigin().toString())) && SHA.a("SHA-1", Arrays.toString(pr.getResources())).equals(sg)) {
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

            @Override
            public void onReceivedIcon(WebView a, Bitmap b) {
                tv5.setImageBitmap(b);
                tv5.setContentDescription(a.getTitle());
                Animation.animate(Webv.this, R.anim.c, tv5);
            }
        });
        h.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        h.setWebViewClient(new MainWebViewClient() {

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                String a = null;
                String b = null;
                boolean c = handler.useHttpAuthUsernamePassword();
                if (c) {
                    String[] cre = currentTab().getHttpAuthUsernamePassword(host, realm);
                    if (cre != null && cre.length == 2) {
                        a = cre[0];
                        b = cre[1];
                    }
                }
                if (a != null && b != null) {
                    handler.proceed(a, b);
                }
            }

            @Override
            public void receivedError(WebView a, int b, String c, String d, boolean bn, boolean bn1) {
                try {
                    c180();
                    bl6 = false;
                    if (r8 == null) {
                        r8 = new R8();
                    }
                    registerReceiver(r8, ift);
                    err = bn;
                    if (Build.VERSION.SDK_INT >= 23 && c.startsWith("file://")) {
                        Permission.check(Webv.this, Permission.STORAGE, 2);
                    }
                    String sg = String.format(getString(R.string.v20), c, b, d);
                    cm0.append(sg);
                    if (Build.VERSION.SDK_INT >= 23) {
                        cm0.append(String.format(getString(R.string.r22),
                                bn,
                                bn1,
                                c175()));
                    }
                    cm0.append("<br><br>");
                    if (bn) {
                        a.loadUrl("about:blank");
                        String html = "<!DOCTYPE html>\n" +
                                "<html>\n" +
                                "    <head>\n" +
                                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"/>\n" +
                                "        <title>Webpage not available</title>\n" +
                                "        <style type=\"text/css\">\n" +
                                "            @font-face {\n" +
                                "            font-family: changedTo;\n" +
                                "            src: url(\"file:///android_asset/classes\")\n" +
                                "            }\n" +
                                "            *{\n" +
                                "            color: #484848;\n" +
                                "            font-family: changedTo;\n" +
                                "            }\n" +
                                "            body {\n" +
                                "            margin-top: 0px; \n" +
                                "            padding-top: 0px;\n" +
                                "            }\n" +
                                "            h2 { \n" +
                                "            margin-top: 5px; \n" +
                                "            padding-top: 0px; \n" +
                                "            }\n" +
                                "        </style>\n" +
                                "        <body>\n" +
                                "            <img src=\"file:///android_asset/webkit/android-weberror.png\" align=\"top\" />\n" +
                                "            <h3>Webpage not available</h3>\n" +
                                "            <p>The webpage at <changedTo href=\"" + c + "\">" + c + "</changedTo> could not be loaded because:</p>\n" +
                                "            <p>" + d + "</p>\n" +
                                "        </body>\n" +
                                "    </head>\n" +
                                "</html>";
                        a.loadDataWithBaseURL(c, html, "text/html", "UTF-8", c);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReceivedHttpError(WebView a, WebResourceRequest b, WebResourceResponse c) {
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

            @Override
            public void onReceivedSslError(WebView a, final SslErrorHandler b, SslError c) {
                Webv.this.iw.setImageResource(R.drawable.a16);
                Animation.animate(Webv.this, R.anim.c, iw);
                String sg = String.format(getString(R.string.g19),
                        c.getUrl(),
                        c.getPrimaryError());
                cm2.append(sg);
                cm2.append("<br><br>");
                SpannableString xjendndj = new SpannableString(c.getUrl());
                xjendndj.setSpan(Webv.this.E, 0, 8, 0);
                Webv.this.u.setText(xjendndj);
                cd.setBackgroundResource(R.drawable.f4);
                if (a221().getBoolean("showSO", true)) {
                    AlertDialog.Builder e = new AlertDialog.Builder(Webv.this);
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
                    e.setPositiveButton(getString(R.string.i14), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a1, int i) {
                            b.proceed();
                            a1.dismiss();
                        }
                    });
                    e.setNegativeButton(getString(R.string.j4), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a1, int i) {
                            b.cancel();
                            a1.dismiss();
                        }
                    });
                    e.setNeutralButton(getString(R.string.v11), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface a1, int i) {
                            Webv.this.c75();
                        }
                    });
                    AlertDialog g = e.create();
                    g.show();
                } else {
                    b.proceed();
                }
            }

            @Override
            public void onPageFinished(WebView a, String b) {
                try {
                    c134();
                    if (a.getSettings().getJavaScriptEnabled()) {
                        String js = "javascript:window." + getSharedPreferences("di", 0).getString("di1", "") + ".a((function (){\n" +
                                "    const metas = document.getElementsByTagName('meta');\n" +
                                "    for (let i = 0; i < metas.length; i++) {\n" +
                                "        if (metas[i].getAttribute('name') === 'theme-color') {\n" +
                                "            return metas[i].getAttribute('content');\n" +
                                "        }\n" +
                                "    }\n" +
                                "    return '';\n" +
                                "})());";
                        if (a221().getBoolean("maUU", false) && a221().getBoolean("wthj", false)) {
                            a.evaluateJavascript(js, null);
                        }
                        if (!pageF) {
                            String js1 = "let sc = document.createElement('script');" +
                                    "sc.innerHTML = `" +
                                    "function myfun(e) {" +
                                    "    mangaid = e.target;" +
                                    "    var getit = prompt('"+ getSharedPreferences("di", 0).getString("di", "") +"', e.target.outerHTML);" +
                                    "    if (getit != null) {" +
                                    "        e.target.outerHTML = getit;" +
                                    "    }" +
                                    "    e.stopPropagation();" +
                                    "    e.preventDefault();" +
                                    "}`;" +
                                    "document.body.appendChild(sc);";
                            a.evaluateJavascript(js1, null);
                            pageF = true;
                            return;
                        }
                        pageF = false;
                        String js2 = "javascript:window." + getSharedPreferences("di", 0).getString("di1", "") + ".c((function (){\n" +
                                "    const metas = document.getElementsByTagName('meta');\n" +
                                "    for (let i = 0; i < metas.length; i++) {\n" +
                                "        if (metas[i].getAttribute('name') === 'description') {\n" +
                                "            return metas[i].getAttribute('content');\n" +
                                "        }\n" +
                                "    }\n" +
                                "    return '';\n" +
                                "})());";
                        a.evaluateJavascript(js2, null);
                    }
                    if (a221().getBoolean("maUU", false) && a221().getBoolean("tow2", false)) {
                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifiManager.setWifiEnabled(false);
                    }
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

            @Override
            public void onPageStarted(WebView a, String b, Bitmap b5) {
                try {
                    if (r8 != null) {
                        unregisterReceiver(r8);
                    }
                    err = false;
            /* if (BuildConfig.DEBUG) {
                if (b.startsWith(WEBVIUM_HOME)) {
                    ab.hide();
                } else {
                    ab.show();
                }
            } */
                    if (a221().getBoolean("maUU", false) && a221().getBoolean("tow", false)) {
                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        wifiManager.setWifiEnabled(true);
                    }
                    tv5.setImageResource(R.drawable.a18);
                    tv5.setBackgroundResource(R.drawable.b17);
                    Animation.animate(Webv.this, R.anim.c, tv5);
                    c38(b);
                    c33(b, getString(R.string.v13));
                    Webv.this.iw.setImageResource(R.drawable.a15);
                    Animation.animate(Webv.this, R.anim.c, Webv.this.iw);
                    Webv.this.cd.setBackgroundResource(R.drawable.w);
                    dsM = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean url(WebView a, String b) {
                try {
                    c38(b);
                    if (b.startsWith("mailto:")) {
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
                    } else if (b.startsWith("smsto:")) {
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

            @TargetApi(Build.VERSION_CODES.O_MR1)
            @Override
            public void onSafeBrowsingHit(WebView view, WebResourceRequest ess, int type5, final SafeBrowsingResponse sbh) {
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
                        AlertDialog.Builder a = new AlertDialog.Builder(Webv.this);
                        a.setCancelable(false);
                        a.setTitle(getString(R.string.h40));
                        a.setMessage(Html.b(String.format(getString(R.string.h39), Objects.requireNonNull(Uri.parse(view.getUrl()).getHost()), wde, type)));
                        a.setPositiveButton(getString(R.string.i21), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface a2, int i) {
                                sbh.backToSafety(true);
                                a2.dismiss();
                            }
                        });
                        a.setNegativeButton(getString(R.string.i22), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface a2, int i) {
                                sbh.proceed(true);
                                a2.dismiss();
                            }
                        });
                        a.create().show();
                    } else {
                        sbh.backToSafety(true);
                    }
                } else {
                    sbh.proceed(true);
                }
            }

            @Override
            public void doUpdateVisitedHistory(WebView a, String b, boolean c) {
                if (!c && (b.startsWith("http://") || b.startsWith("https://") || b.startsWith("file://") || b.startsWith("content://") || IPAddress.isValidIpAddress(b))) {
                    d1.c(a.getTitle(), b);
                    SharedPreferences c56 = getSharedPreferences("wv", 0);
                    SharedPreferences.Editor d56 = c56.edit();
                    d56.putString("MyURL", b);
                    d56.apply();
                }
            }

            @Override
            public void onFormResubmission(WebView a5, final Message b, final Message c) {
                AlertDialog.Builder a = new AlertDialog.Builder(Webv.this);
                a.setCancelable(false);
                a.setTitle(getString(R.string.g36));
                a.setMessage(getString(R.string.g34));
                a.setPositiveButton(getString(R.string.g36), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a12, int intetg) {
                        c.sendToTarget();
                        a12.dismiss();
                    }
                });
                a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface a1, int intetg) {
                        b.sendToTarget();
                        a1.dismiss();
                    }
                });
                a.create().show();
            }

            @Override
            public WebResourceResponse r(WebResourceRequest wr) {
                if (a221().getBoolean("maUU", false) && a221().getBoolean("wthj123", false)) {
                    String url = Uri.parse(wr.getUrl().toString()).getHost();
                    for (String sg : ads) {
                        if (url.contains(sg)) {
                            return new WebResourceResponse("text/plain",
                                    "UTF-8",
                                    new ByteArrayInputStream(String.format(getString(R.string.g20), url).getBytes()));
                        }
                    }
                    return null;
                }
                return null;
            }
        });
        h.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void saveQuery(String sg) {
                d2.c(sg);
            }

            @JavascriptInterface
            public String query() {
                ArrayList<String> ls = new ArrayList<>();
                Cursor res = d2.getReadableDatabase().rawQuery("SELECT * FROM " +
                        Sqlite.TABLE_SEARCH +
                        " ORDER BY " +
                        "_id" +
                        " DESC ", null);
                if (res.getCount() != 0) {
                    while (res.moveToNext()) {
                        ls.add(Base64.encode(res.getString(1)));
                    }
                    res.close();
                }
                if (a221().getBoolean("showHTT", false)) {
                    Cursor rest = d1.getReadableDatabase().rawQuery("SELECT * FROM " +
                            Sqlite.TABLE_HISTORY +
                            " ORDER BY " +
                            "_id" +
                            " DESC ", null);
                    if (rest.getCount() != 0) {
                        boolean bn = a221().getBoolean("showLKS", false);
                        while (rest.moveToNext()) {
                            if (bn) {
                                ls.add(Base64.encode(rest.getString(1)));
                            }
                            ls.add(Base64.encode(rest.getString(2)));
                        }
                    }
                    rest.close();
                }
                if (a221().getBoolean("showBKM", false)) {
                    Cursor rest1 = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                            Sqlite.TABLE_BOOKMARK +
                            " ORDER BY " +
                            "_id" +
                            " DESC ", null);
                    if (rest1.getCount() != 0) {
                        boolean bn = a221().getBoolean("showLKS", false);
                        while (rest1.moveToNext()) {
                            if (bn) {
                                ls.add(Base64.encode(rest1.getString(1)));
                            }
                            ls.add(Base64.encode(rest1.getString(2)));
                        }
                    }
                    rest1.close();
                }
                return ls.toString().replaceAll("\\[", "")
                        .replaceAll("]", "")
                        .replaceAll(", ", ":");
            }

            @JavascriptInterface
            public boolean isValidDomain(String sg) {
                return Domain.isValidDomain(sg);
            }

            @JavascriptInterface
            public String getSearchEngine() {
                switch (Objects.requireNonNull(a221().getString("searchP", ""))) {
                    case Webv.SE_DUCKDUCKGO:
                        return searchEngine[1];
                    default:
                    case Webv.SE_GOOGLE:
                        return searchEngine[0] + searchPath[0];
                    case Webv.SE_BING:
                        return searchEngine[2] + searchPath[0];
                    case Webv.SE_YAHOO:
                        return searchPath[1];
                    case Webv.SE_ASK:
                        return searchPath[2];
                    case Webv.SE_AOL:
                        return searchPath[3];
                    case Webv.SE_BAIDU:
                        return searchEngine[6] + searchPath[4];
                    case Webv.SE_WOLFRAMALPHA:
                        return searchEngine[7] + searchPath[5];
                    case Webv.SE_DISCOVERAPP:
                        return searchEngine[8] + searchPath[6];
                    case Webv.SE_ECOSIA:
                        return searchEngine[9] + searchPath[0];
                    case Webv.SE_STACKOVERFLOW:
                        return searchEngine[10] + searchPath[0];
                    case Webv.SE_YOUTUBE:
                        return searchEngine[11] + searchPath[7];
                    case Webv.SE_GITHUB:
                        return searchEngine[12] + searchPath[0];
                    case Webv.SE_FACEBOOK:
                        return searchEngine[13] + searchPath[8];
                }
            }

            @JavascriptInterface
            public void voice() {
                Intents.a(Webv.this, Voic.class);
            }
        }, Package.c() + "SearchHelper");
        h.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public boolean isDarkModeEnabled() {
                return a221().getBoolean("autoUpdate", false);
            }

            @JavascriptInterface
            public String getQuality() {
                String bgQa9 = Objects.requireNonNull(a221().getString("bgQa9", "7z"));
                switch (bgQa9) {
                    case "7z":
                        return "640x480";
                    case "30z":
                        return "1280x720";
                    case "60z":
                        return "1920×1080";
                }
                return "480×360";
            }
        }, Package.c() + "ThemeHelper");
        h.addJavascriptInterface(new Object() {

            @JavascriptInterface
            public void a(final String a) {
                if (a221().getBoolean("maUU", false) && a221().getBoolean("wthj", false)) {
                    if (!a.isEmpty()) {
                        Runnable re = new Runnable() {

                            @Override
                            public void run() {
                                File fe = new File(StorageDirectory.getBackground(Webv.this));
                                if (!Webv.this.a221().getBoolean("webviumB", false) && !fe.exists()) {
                                    Webv.this.runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            int co = Color.parseColor(sg);
                                            InsetDrawable inset = new InsetDrawable(Resources.toDrawable(GradientDrawable.RECTANGLE,
                                                    new float[]{0f, 0f, 0f, 0f, 10f, 10f, 10f, 10f}, co), 10, 0, 10, 0);
                                            Webv.this.o.setBackground(inset);
                                            InsetDrawable inset1 = new InsetDrawable(Resources.toDrawable(GradientDrawable.RECTANGLE,
                                                    new float[]{10f, 10f, 10f, 10f, 0f, 0f, 0f, 0f}, co), 10, 0, 10, 0);
                                            Webv.this.llt.setBackground(inset1);
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
                                            Webv.this.cd.setBackgroundResource(R.drawable.a10);
                                        }
                                    });
                                }
                            }
                        };
                        new Thread(re).start();
                    } else {
                        Runnable re1 = new Runnable() {

                            @Override
                            public void run() {
                                File fe = new File(StorageDirectory.getBackground(Webv.this));
                                if (!Webv.this.a221().getBoolean("webviumB", false) && !fe.exists()) {
                                    Webv.this.runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            Webv.this.llt.setBackgroundResource(R.drawable.f1);
                                            Webv.this.o.setBackgroundResource(R.drawable.p);
                                            if (!Webv.this.a221().getBoolean("autoUpdate", false)) {
                                                if (Build.VERSION.SDK_INT >= 23) {
                                                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                                }
                                                getWindow().setStatusBarColor(Resources.getColor(Webv.this, R.color.b));
                                                getWindow().setNavigationBarColor(Resources.getColor(Webv.this, R.color.m));
                                            } else {
                                                getWindow().setStatusBarColor(Resources.getColor(Webv.this, R.color.n));
                                                getWindow().setNavigationBarColor(Resources.getColor(Webv.this, R.color.n));
                                            }
                                            if (Webv.this.currentUrl().startsWith("http://")) {
                                                Webv.this.cd.setBackgroundResource(R.drawable.f4);
                                            } else {
                                                Webv.this.cd.setBackgroundResource(R.drawable.w);
                                            }
                                        }
                                    });
                                }
                            }
                        };
                        new Thread(re1).start();
                    }
                }
            }

            @JavascriptInterface
            public void b(String sg) {
                Intent it = new Intent(Intents.ACTION_IP);
                it.putExtra("ip", sg);
                sendBroadcast(it);
            }

            @JavascriptInterface
            public void c(String sg) {
                Webv.this.des = sg;
            }
        }, getSharedPreferences("di", 0).getString("di1", ""));
        if (Build.VERSION.SDK_INT >= 29) {
            h.setWebViewRenderProcessClient(new WebViewRenderProcessClient() {

                @Override
                public void onRenderProcessUnresponsive(WebView webView, WebViewRenderProcess webViewRenderProcess) {
                    if (a221().getBoolean("maUU", false) && a221().getBoolean("wthj56", false)) {
                        webViewRenderProcess.terminate();
                    }
                }

                @Override
                public void onRenderProcessResponsive(WebView webView, WebViewRenderProcess webViewRenderProcess) {

                }
            });
        }
    }

    private void c11(final PendingDownloadDataModel w18) {
        try {
            final String b = URLUtil.guessFileName(w18.a1, w18.a2, w18.a3);
            AlertDialog.Builder c = new AlertDialog.Builder(this);
            LayoutInflater d = getLayoutInflater();
            View e = d.inflate(R.layout.j, null);
            c.setCancelable(true);
            c.setTitle(getString(R.string.l5));
            c.setView(e);
            TextView f = e.findViewById(R.id.a5);
            final Edit s = e.findViewById(R.id.d1);
            final TextView h = e.findViewById(R.id.d2);
            TextView i = e.findViewById(R.id.d3);
            final TextView j = e.findViewById(R.id.d4);
            TextView k = e.findViewById(R.id.b9);
            TextView l = e.findViewById(R.id.c11);
            TextView m = e.findViewById(R.id.c12);
            TextView j2 = e.findViewById(R.id.e8);
            final TextView j3 = e.findViewById(R.id.e9);
            TextView j4 = e.findViewById(R.id.e10);
            final TextView j5 = e.findViewById(R.id.e11);
            TextView j6 = e.findViewById(R.id.e12);
            final TextView j7 = e.findViewById(R.id.e13);
            f.setText(getString(R.string.c30));
            s.setText(b);
            k.setText(getString(R.string.d40));
            if (w18.a4 != 0) {
                j4.setText(getString(R.string.e23));
                Runnable re = new Runnable() {

                    @Override
                    public void run() {
                        final String fn = Formatter.formatFileSize(Webv.this, w18.a4);
                        final String fn1 = Formatter.formatFileSize(Webv.this, new File(Objects.requireNonNull(Webv.this.getExternalFilesDir(null)).toString()).getFreeSpace() + w18.a4);
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                h.setText(fn);
                                j5.setText(fn1);
                            }
                        });
                    }
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
            Runnable re = new Runnable() {

                @Override
                public void run() {
                    final String j3a = Formatter.formatFileSize(Webv.this, new File(Objects.requireNonNull(Webv.this.getExternalFilesDir(null)).toString()).getFreeSpace());
                    final String j7a = Formatter.formatFileSize(Webv.this, new File(Objects.requireNonNull(Webv.this.getExternalFilesDir(null)).toString()).getTotalSpace());
                    Webv.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            j3.setText(j3a);
                            j7.setText(j7a);
                        }
                    });
                }
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
            c.setPositiveButton(getString(R.string.e25), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface a34, int m1) {
                    c182(w18, s.getText().toString());
                    a34.dismiss();
                }
            });
            c.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface a, int intetg) {
                    a.dismiss();
                }
            });
            final AlertDialog g = c.create();
            g.show();
            final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
            s.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

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

                @Override
                public void afterTextChanged(Editable s) {

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
        pm6.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu popupMenu) {
                popupMenu.getMenu().clear();
            }
        });
        pm6.show();
    }

    public void c14(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h11));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final Edit ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final Edit ed1 = c.findViewById(R.id.f12);
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                d3.c(ed.getText().toString(), ed1.getText().toString());
                Webv.this.c8(Webv.this.getString(R.string.t2));
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed) && U3.a(ed1));
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void c15(final WebViews h) {
        final WebSettings ws = h.getSettings();
        if (a221().getBoolean("Javaweb", true)) {
            h.addJavascriptInterface(new Object() {

                @JavascriptInterface
                public void showToast(String c) {
                    mainShowToast(Webv.this, c, 0, 0);
                }

                @JavascriptInterface
                public void showToastError(String c) {
                    mainShowToast(Webv.this, c, 1, 0);
                }

                @JavascriptInterface
                public void showToastSuccess(String c) {
                    mainShowToast(Webv.this, c, 2, 0);
                }

                @JavascriptInterface
                public void showToast(String c, int b) {
                    mainShowToast(Webv.this, c, 0, b);
                }

                @JavascriptInterface
                public void showToastError(String c, int b) {
                    mainShowToast(Webv.this, c, 1, b);
                }

                @JavascriptInterface
                public void showToastSuccess(String c, int b) {
                    mainShowToast(Webv.this, c, 2, b);
                }

                @JavascriptInterface
                public void copyToClipboard(String c) {
                    Clipboard.a(Webv.this, c);
                }

                @JavascriptInterface
                public void vibrate(int c) {
                    Vibrator.vibrate(Webv.this, c);
                }

                @JavascriptInterface
                public void enableWifi(boolean c) {
                    WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(c);
                }

                @JavascriptInterface
                public int currentVersion() throws PackageManager.NameNotFoundException {
                    return Integer.parseInt(Package.e(Webv.this).replaceAll("\\.", ""));
                }

                @JavascriptInterface
                public void showNotification(String b, String c, String d) {
                    MainNotification.b(Webv.this, Uri.parse(d).getHost(), getResources().getString(R.string.y20));
                    android.app.Notification.Builder m =
                            Notifications.a(Webv.this, Uri.parse(d).getHost());
                    m.setSmallIcon(R.drawable.c);
                    android.app.Notification.BigTextStyle bigText = new android.app.Notification.BigTextStyle();
                    if (d.length() != 0) {
                        bigText.setSummaryText(Uri.parse(d).getHost());
                    } else {
                        bigText.setSummaryText(getResources().getString(R.string.g31));
                    }
                    // if (HDMS.b(c)) {
                    m.setContentText(c);
                    bigText.bigText(c);
     /*   } else {
            m.setContentText(changedTo.getResources().getString(R.string.g29));
bigText.bigText(changedTo.getResources().getString(R.string.g29));
        }*/
                    // if (HDMS.b(b)) {
                    m.setContentTitle(b);
                    bigText.setBigContentTitle(b);
       /* } else {
            m.setContentTitle(changedTo.getResources().getString(R.string.g30));
            bigText.setBigContentTitle(changedTo.getResources().getString(R.string.g30));
        }*/
                    m.setStyle(bigText);
                    m.setColor(Resources.getColor(Webv.this, R.color.a));
                    SharedPreferences sq = PreferenceManager.getDefaultSharedPreferences(Webv.this);
                    m.setAutoCancel(sq.getBoolean("eac", true));
                    m.setDefaults(android.app.Notification.DEFAULT_ALL);
                    if (Build.VERSION.SDK_INT < 26) {
                        if (sq.getString("py", "") == null) {
                            m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
                        }
                        if (Objects.requireNonNull(sq.getString("py", "")).equals("1x")) {
                            m.setPriority(android.app.Notification.PRIORITY_DEFAULT);
                        }
                        if (Objects.requireNonNull(sq.getString("py", "")).equals("7x")) {
                            m.setPriority(android.app.Notification.PRIORITY_HIGH);
                        }
                        if (Objects.requireNonNull(sq.getString("py", "")).equals("30x")) {
                            m.setPriority(android.app.Notification.PRIORITY_LOW);
                        }
                        if (Objects.requireNonNull(sq.getString("py", "")).equals("60x")) {
                            m.setPriority(android.app.Notification.PRIORITY_MAX);
                        }
                        if (Objects.requireNonNull(sq.getString("py", "")).equals("120x")) {
                            m.setPriority(android.app.Notification.PRIORITY_MIN);
                        }
                    }
                    if (sq.getString("vy", "") == null) {
                        m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
                    }
                    if (Objects.requireNonNull(sq.getString("vy", "")).equals("1y")) {
                        m.setVisibility(android.app.Notification.VISIBILITY_PRIVATE);
                    }
                    if (Objects.requireNonNull(sq.getString("vy", "")).equals("7y")) {
                        m.setVisibility(android.app.Notification.VISIBILITY_PUBLIC);
                    }
                    if (Objects.requireNonNull(sq.getString("vy", "")).equals("30y")) {
                        m.setVisibility(android.app.Notification.VISIBILITY_SECRET);
                    }
                    m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c));
                    if (d.length() != 0) {
                        Intent it = new Intent(Webv.this, Webv.class);
                        it.putExtra("value", d);
                        PendingIntent contentIntent = PendingIntent.getActivity(Webv.this, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
                        m.setContentIntent(contentIntent);
                        Intent j = new Intent(Webv.this, Webv.class);
                        j.putExtra("webvium", d);
                        PendingIntent pi23 = PendingIntent.getActivity(Webv.this, 1, j, PendingIntent.FLAG_UPDATE_CURRENT);
                        m.addAction(new android.app.Notification.Action(R.drawable.q, String.format(getResources().getString(R.string.g28), Objects.requireNonNull(Uri.parse(d).getHost())), pi23));
                    }
                    NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    nmc.notify(Notifications.getRandomizeNotificationId(Notifications.DEFAULT), m.build());
                }

                private void mainShowToast(Context a, String b, int c, int d) {
                    switch (c) {
                        default:
                        case 0:
                            AwesomeToast.a(a, b, d);
                            break;
                        case 1:
                            AwesomeToast.c(a, b);
                            break;
                        case 2:
                            AwesomeToast.b(a, b);
                            break;
                    }
                }
            }, Package.c());
            h.addJavascriptInterface(new Object() {

                @JavascriptInterface
                public void print() {
                    c100(h);
                }
            }, Package.c() + "PrintHelper");
            h.addJavascriptInterface(new Object() {
                private final String[] algos = {"MD5",
                        "SHA-1",
                        "SHA-224",
                        "SHA-256",
                        "SHA-384",
                        "SHA-512"
                };

                @JavascriptInterface
                public String encodeMD5(String text, boolean ran) {
                    try {
                        return SHA.a(algos[0], text, ran);
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                    return "";
                }
                
                @JavascriptInterface
                public String encodeSHA(String algo, String text, boolean ran) {
                    try {
                        if (Arrays.toString(algos).contains(algo) && !text.isEmpty()) {
                            if (algo.equals(algos[2]) && (Build.VERSION.SDK_INT < 22)) {
                                return "";
                            }
                            return SHA.a(algo, text, ran);
                        }
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                    return "";
                }

                @JavascriptInterface
                public String encodeXOR(String sg, String key) {
                    if (!sg.isEmpty() && !key.isEmpty()) {
                        return XOR.encode(sg, key.charAt(0));
                    }
                    return "";
                }

                @JavascriptInterface
                public String encodeCaesar(String text, int pad) {
                    if (!sg.isEmpty() && pad >= 0) {
                        return Caesar.encode(text, pad);
                    }
                    return "";
                }
            }, Package.c() + "HashHelper");
        } else {
            h.removeJavascriptInterface(Package.c());
            h.removeJavascriptInterface(Package.c() + "PrintHelper");
            h.removeJavascriptInterface(Package.c() + "HashHelper");
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
            ws.setJavaScriptEnabled(true);
        }
        if (Objects.requireNonNull(a221().getString("java", "1f")).equals("7f")) {
            ws.setJavaScriptEnabled(false);
        }
        if (!ws.getJavaScriptEnabled()) {
            if (Uri.parse(currentUrl()).getHost().equals("mrepol742.github.io") && currentUrl().contains("/Search")) {
                c50(currentTab());
            }
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
            Runnable re = new Runnable() {

                @Override
                public void run() {
                    final String sg = StorageDirectory.getCacheDir(Webv.this).toString();
                    Webv.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ws.setAppCachePath(sg);
                        }
                    });
                }
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
            c45 = String.format(c, "\"" + currentTitle() + "\"");
        }
        startActivity(Intent.createChooser(b, c45));
    }

    public void c20(boolean a) {
        if (a) {
            currentSettings().setUserAgentString(currentSettings().getUserAgentString().replace("Mobile", "eliboM").replace("Android", "diordnA"));
        } else {
            currentSettings().setUserAgentString(currentSettings().getUserAgentString().replace("eliboM", "Mobile").replace("diordnA", "Android"));
        }
        currentTab().reload();
    }

    public void c22() {
        try {
            Intents.h(this, Sear.class, 911, "value", currentUrl());
            overridePendingTransition(R.anim.a, R.anim.f);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void c25() {
        if (a221().getBoolean("clearP", false)) {
            for (WebViews web : tabs) {
                web.clearCache(false);
            }
        }
        if (a221().getBoolean("clearH", false)) {
            bl4 = true;
            d1.delete();
        }
        if (a221().getBoolean("clearC", false)) {
            Clipboard.a(this, " ");
        }
        if (a221().getBoolean("clearPr", false)) {
            for (WebViews web : tabs) {
                web.clearSslPreferences();
            }
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

    @SuppressLint("SetJavaScriptEnabled")
    public void c38(String a) {
        try {
            for (WebViews web : tabs) {
                if (Objects.requireNonNull(a221().getString("cookies", "")).equals("120")) {
                    if (a.startsWith("https://")) {
                        cm1.setAcceptCookie(true);
                        cm1.setAcceptThirdPartyCookies(web, true);
                    } else {
                        cm1.setAcceptCookie(false);
                        cm1.setAcceptThirdPartyCookies(web, false);
                    }
                }
                if (Objects.requireNonNull(a221().getString("java", "")).equals("30f")) {
                    currentSettings(web).setJavaScriptEnabled(a.startsWith("https://"));
                }
                if (Build.VERSION.SDK_INT < 26) {
                    if (Objects.requireNonNull(a221().getString("form", "")).equals("7g")) {
                        currentSettings(web).setSaveFormData(a.startsWith("https://"));
                    }
                }
                if (Objects.requireNonNull(a221().getString("location", "")).equals("7h")) {
                    if (a.startsWith("https://")) {
                        currentSettings(web).setGeolocationEnabled(true);
                        if (Build.VERSION.SDK_INT <= 24) {
                            currentSettings(web).setGeolocationDatabasePath(getFilesDir().toString());
                        }
                    } else {
                        currentSettings(web).setGeolocationEnabled(false);
                    }
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
                    AlertDialog.Builder a = new AlertDialog.Builder(Webv.this);
                    a.setCancelable(true);
                    a.setTitle(getString(R.string.w2));
                    int i = timeSet() / 60000;
                    a.setMessage(String.format(getResources().getQuantityString(R.plurals.w1, i), i));
                    a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int i1) {
                            Webv.this.c41();
                            dialog.dismiss();
                        }
                    });
                    a.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface a1) {
                            Webv.this.c41();
                            a1.dismiss();
                        }
                    });
                    a.create().show();
                    timer.cancel();
                    timer.purge();
                }
            }, timeSet());
        }
    }

    private WebViews currentTab() {
        return tabs.get(ct);
    }

    private WebSettings currentSettings(WebViews ws) {
        return ws.getSettings();
    }

    private WebSettings currentSettings() {
        return currentTab().getSettings();
    }

    private String currentTitle() {
        return currentTab().getTitle();
    }

    private String currentUrl() {
        return currentTab().getUrl();
    }

    private Bitmap currentFavicon() {
        return currentTab().getFavicon();
    }

    private int timeSet() {
        if (Objects.requireNonNull(a221().getString("remind", "")).equals("7k")) {
            return 900000;
        } else if (Objects.requireNonNull(a221().getString("remind", "")).equals("30k")) {
            return 1800000;
        }
        return 3600000; // for 60k
    }


    public void c43(final String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
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
                final String sg = Stream.a(url, Webv.this.getString(R.string.c33), Webv.this.getString(R.string.g25));
                Webv.this.runOnUiThread(new Runnable() {

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
                ti.setText(Webv.this.getString(R.string.v13));

                if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
                    Runnable p151 = new Runnable() {

                        @Override
                        public void run() {
                            final String sg = Stream.a(ab, Webv.this.getString(R.string.c33), Webv.this.getString(R.string.g25));
                            Webv.this.runOnUiThread(new Runnable() {

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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

            @Override
            public void afterTextChanged(Editable s) {

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
        final String sg = Uri.parse(url).getHost();
        Runnable re = new Runnable() {

            @Override
            public void run() {
                try {
                    final boolean st = Ping.isHostReachable(sg, 2500);
                    final boolean nd = Ping.isHostReachable(sg, 5000);
                    final boolean rd = Ping.isHostReachable(sg, 10000);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ti.setText(Html.b(String.format(getString(R.string.y85), sg, st).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81))));
                                }
                            });
                        }
                    }, 1000);
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ti.append(Html.b(String.format(getString(R.string.y86), nd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81))));
                                }
                            });
                        }
                    }, 2000);
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ti.append(Html.b(String.format(getString(R.string.y87), rd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81))));
                                }
                            });
                        }
                    }, 3000);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        new Thread(re).start();
        ed.setText(sg);
        bn.setText(getString(R.string.i6));

        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String ab = Uri.parse(ed.getText().toString()).getHost();
                ti.setText(Webv.this.getString(R.string.v13));
                Runnable re2 = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            final boolean st = Ping.isHostReachable(ab, 2500);
                            final boolean nd = Ping.isHostReachable(ab, 5000);
                            final boolean rd = Ping.isHostReachable(ab, 10000);
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {

                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            ti.setText(Html.b(String.format(getString(R.string.y85), ab, st).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81))));
                                        }
                                    });
                                }
                            }, 1000);
                            timer.schedule(new TimerTask() {

                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            ti.append(Html.b(String.format(getString(R.string.y86), nd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81))));
                                        }
                                    });
                                }
                            }, 2000);
                            timer.schedule(new TimerTask() {

                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            ti.append(Html.b(String.format(getString(R.string.y87), rd).replaceAll("true", getString(R.string.y80)).replaceAll("false", getString(R.string.y81))));
                                        }
                                    });
                                }
                            }, 3000);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                };
                new Thread(re2).start();

            }
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
        a.setPositiveButton(getString(R.string.y89), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
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
        }
    }

    public void c49(String a) {
        c49(currentTab(), a);
    }

    public void c49(WebViews as, String a) {
        String a5 = a.trim().toLowerCase();
        if (a5.equals("webvium://rickroll")) {
            as.loadUrl(Base64.decode("aHR0cHM6Ly93d3cueW91dHViZS5jb20vd2F0Y2g/dj1kUXc0dzlXZ1hjUQ"));
        } else if (a5.equals("webvium://history")) {
            c146(WEBVIUM_HISTORY);
        } else if (a5.equals("webvium://search")) {
            c146(WEBVIUM_SEARCH);
        } else if (a5.equals("webvium://bookmarks")) {
            c146(WEBVIUM_BOOKMARKS);
        } else if (a.startsWith("view-source:") && (a.contains("https://") || a.contains("http://") || a.contains("file://") || a.contains("content://"))) {
            Intent it = new Intent(this, Tool.class);
            it.putExtra("id", Tool.TOOL_SOURCE_CODE);
            it.putExtra("dat", a5);
            startActivity(it);
        } else if (a.equals("about:blank")) {
            as.loadUrl("about:blank");
        } else if (IPAddress.isValidIpAddress(a)) {
            as.loadUrl(a);
        } else if (URLUtil.isValidUrl(a5)) {
            if (a5.startsWith("file://") || a5.startsWith("https://") || a5.startsWith("http://") || a5.startsWith("content://")) {
                as.loadUrl(c138(a));
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

    private void c50(WebViews web) {
        if (a221().getBoolean("tab", false)) {
            String sg0 = a222("MyURL");
            if (sg0 != null) {
                c3(web, sg0);
            } else {
                c51(web);
            }
        } else {
            c51(web);
        }
    }

    private void c51(WebViews web) {
        switch (Objects.requireNonNull(a221().getString("general", "x57"))) {
            default:
            case "x57":
                if (currentSettings().getJavaScriptEnabled()) {
                    if (BuildConfig.DEBUG) {
                        String[] key = {"SamiunNafis0", "mrepol742", "webvium", "webvium mrepol742", "Melvin Jones Repol", "Samiun Nafis"};
                        c3(web, c46() + key[new Random().nextInt(key.length)]);
                        O4 o4 = new O4(web, 3000, 3000);
                        o4.start();
                    } else {
                        c3(web, WEBVIUM_HOME);
                    }
                } else {
                    c3(web, c48());
                }
                break;
            case "1o":
                c3(web, c48());
                break;
            case "7o":
                String sg0 = a222("MyURL");
                if (sg0 == null) {
                    sg0 = c48();
                }
                c3(web, sg0);
                break;
            case "30o":
                c3(web, "about:blank");
                break;
            case "60o":
                String sg1 = a221().getString("cGeneral", "");
                if (sg1 != null && U3.b(sg1)) {
                    c49(web, sg1);
                } else {
                    c3(web, c48());
                }
                break;
        }
    }

    private void c52() {
        if (a221().getBoolean("triMM", true)) {
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).getMemoryInfo(mi);
            if (mi.availMem < mi.threshold) {
                android.util.Log.d("Webvium", "Webv is freeing memory now because: available ="
                        + (mi.availMem / 1024) + " Keep threshold ="
                        + (mi.threshold / 1024) + " Keep");
                SQLiteDatabase.releaseMemory();
                currentTab().clearCache(false);
            }
        }
    }

    private void c53(String jk) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(getString(R.string.s26));
        a.setMessage(Html.b(jk));
        a.setPositiveButton(getString(R.string.u14), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", Package.b(), null);
                intent.setData(uri);
                Webv.this.startActivity(intent);
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
        Animation.animate(Webv.this, R.anim.c, this.iw);
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
        final Edit ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final Edit ed1 = c.findViewById(R.id.f12);
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
        a.setPositiveButton(getString(R.string.e25), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                Intent it = new Intent(Webv.this, SHA.class);
                it.putExtra("changedTo", ed.getText().toString());
                it.putExtra("b", ed1.getText().toString());
                Notifications.b(Webv.this, it);
                a2.dismiss();

            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    /*
     * ANIMEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
     */
    public void c58(String a23, String asd) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.z, null);
        a.setCancelable(true);
        a.setTitle(getString(R.string.h12));
        a.setView(c);
        final TextView ti = c.findViewById(R.id.f9);
        final Edit ed = c.findViewById(R.id.f10);
        final TextView ti1 = c.findViewById(R.id.f11);
        final Edit ed1 = c.findViewById(R.id.f12);
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                try {
                    Intent e = new Intent(Intents.ACTION_LAUNCH);
                    e.addCategory(Intents.CATEGORY_GENIUS);
                    e.putExtra("duplicate", false);
                    e.putExtra("webvium", ed1.getText().toString());
                    /*if (Build.VERSION.SDK_INT < 25) {

                     */
                        Intent f = new Intent();
                        f.putExtra("android.intent.extra.shortcut.INTENT", e);
                        f.putExtra("android.intent.extra.shortcut.NAME", ed.getText().toString());
                        if (currentTab().getFavicon() != null) {
                            f.putExtra("android.intent.extra.shortcut.ICON", currentTab().getFavicon());
                        } else {
                            f.putExtra("android.intent.extra.shortcut.ICON_RESOURCE",
                                    Intent.ShortcutIconResource.fromContext(Webv.this, R.mipmap.c));
                        }
                        f.putExtra("duplicate", false);
                        f.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
                        sendBroadcast(f);
                    /* } else {
                        ShortcutInfo shortcut = new ShortcutInfo.Builder(Webv.this, "id1").build();
                        shortcut.setShortLabel(ed.getText().toString());
                        shortcut.setLongLabel("Open the website");
                        if (currentTab().getFavicon() != null) {
                            shortcut.setIcon(Icon.createWithAdaptiveBitmap(currentTab().getFavicon()));
                        } else {
                            shortcut.setIcon(Icon.createWithResource(Webv.this, R.mipmap.c));
                        }
                        shortcut.setIntent(e);
                        shortcut.build();
                        ShortcutManager sm = (ShortcutManager) getSystemService("shortcut");
                        sm.
                        sm.pushDynamicShortcut(shortcut);
                    }

                     */
                    AwesomeToast.b(Webv.this, getString(R.string.q26));
                } catch (Exception et) {
                    AwesomeToast.e(Webv.this, getString(R.string.a36), 2);
                }
                a2.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ed1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (U3.a(ed)) {
                    okButton.setEnabled(U3.a(ed1));
                } else {
                    okButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        g.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(U3.a(ed) && U3.a(ed1));
    }

    private void c68(View a) {
        FileUtil.createNewFolder(StorageDirectory.getWebviumDir() + "/Screenshot");
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
        if (Objects.requireNonNull(a221().getString("shotQq", "1w")).equals("30w")) {
            return 80;
        }
        return 100;
    }

    private void c74(Bitmap.CompressFormat format, int quality, View ll, final String st) {
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
        Uri contentUri = Uri.fromFile(new java.io.File(st));
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
        Runnable p15 = new Runnable() {

            @Override
            public void run() {
                Webv.this.c70(st);
            }
        };
        new Thread(p15).start();
        final FrameLayout k = findViewById(R.id.i);
        View c = View.inflate(this, R.layout.a8, null);
        final LinearLayout ll1 = c.findViewById(R.id.h20);
        final ImageView iv = c.findViewById(R.id.c19);
        ll1.setBackgroundColor(Resources.getColor(this, android.R.color.transparent));
        Runnable p151 = new Runnable() {

            @Override
            public void run() {
                final Bitmap bp = BitmapFactory.decodeFile(st);
                Webv.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        iv.setImageBitmap(bp);
                    }
                });
            }
        };
        new Thread(p151).start();
        k.addView(c);
        Animation.animate(this, R.anim.e, iv);
        O3 timer = new O3(1000, 1000, k, c);
        timer.start();
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
            Intent j = new Intent(this, Scre.class);
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a6, int o) {
                Intent a4 = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                Webv.this.startActivity(a4);
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
                    c182(w18, URLUtil.guessFileName(w18.a1, w18.a2, w18.a3));
                } else {
                    pend = w18;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void c75() {
        String url = currentUrl();
        if (currentTab().getCertificate() == null) {
            return;
        }
        SslCertificate ce = currentTab().getCertificate();
        SslCertificate.DName ssl = ce.getIssuedTo();
        SslCertificate.DName ssl0 = ce.getIssuedBy();
        String ag9 = String.format(getString(R.string.b7),
                Objects.requireNonNull(currentTitle()),
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
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                a12.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
    }

    private void c98() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
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

    public void c105(String b) {
        Intent d = new Intent(Intent.ACTION_VIEW);
        d.setData(Uri.parse(c109(b)));
        if (d.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(d, getString(R.string.a26)));
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
            for (WebViews web : tabs) {
                switch (Objects.requireNonNull(a221().getString("userA", ""))) {
                    default:
                    case UA_DEFAULT:
                        currentSettings(web).setUserAgentString(String.format(userAgents[0],
                                Package.c(),
                                Package.e(this)));
                        break;
                    case UA_ANDROID_STOCK:
                        currentSettings(web).setUserAgentString(this.currentTab().getUserAgent());
                        break;
                    case UA_INTERNET_EXPLORER:
                        currentSettings(web).setUserAgentString(userAgents[1]);
                        break;
                    case UA_GOOGLE_CHROME:
                        currentSettings(web).setUserAgentString(userAgents[2]);
                        break;
                    case UA_MOZILA_FIREFOX:
                        currentSettings(web).setUserAgentString(userAgents[3]);
                        break;
                    case UA_OPERA:
                        currentSettings(web).setUserAgentString(userAgents[4]);
                        break;
                    case UA_SAFARI:
                        currentSettings(web).setUserAgentString(userAgents[5]);
                        break;
                    case UA_MICROSOFT_EDGE:
                        currentSettings(web).setUserAgentString(userAgents[6]);
                        break;
                    case UA_GOOGLE_CHROMIUM:
                        currentSettings(web).setUserAgentString(userAgents[7]);
                        break;
                    case UA_MOZILA_BRAVE:
                        currentSettings(web).setUserAgentString(userAgents[8]);
                        break;
                    case UA_CUSTOM:
                        currentSettings(web).setUserAgentString(a221().getString("CustomuserA", String.format(userAgents[0],
                                Package.c(),
                                Package.e(this))));
                        break;
                }
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
        final Edit ed = c.findViewById(R.id.l4);
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
        String sg11 = Uri.parse(currentUrl()).getHost() + ".png";
        String sg = StorageDirectory.getDownloadDir() + "/" + sg11;
        ti3.setText(sg);
        ed.setText(sg11);
        a.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                Webv.this.c113(ed.getText().toString(), dt);
                a2.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.i7), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
        final Button okButton = g.getButton(AlertDialog.BUTTON_POSITIVE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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

            @Override
            public void afterTextChanged(Editable s) {

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

    public void c112(String url, final int type) {
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
                    Intent it = new Intent(Webv.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_SOURCE_CODE);
                    Webv.this.startActivity(it);
                } else if (type == HEADERS) {
                    Webv.this.c126(a1);
                } else if (type == ROBOTS) {
                    Intent it = new Intent(Webv.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_ROBOTS);
                    Webv.this.startActivity(it);
                } else if (type == ASSETLINKS) {
                    Intent it = new Intent(Webv.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_ASSET_LINKS);
                    Webv.this.startActivity(it);
                } else if (type == SITEMAPS) {
                    Intent it = new Intent(Webv.this, Tool.class);
                    it.putExtra("dat", a1);
                    it.putExtra("id", Tool.TOOL_SITEMAPS);
                    Webv.this.startActivity(it);
                }
                g.dismiss();
            }
        });
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        g.show();
    }

    private void c113(final String a, final String b) {
        Runnable p15 = new Runnable() {

            @Override
            public void run() {
                try {
                    File a2 = new File(StorageDirectory.getWebviumDir() + "/Downloads/" + a);
                    if (a2.createNewFile()) {
                        FileOutputStream a3 = new FileOutputStream(a2);
                        OutputStreamWriter a4 = new OutputStreamWriter(a3);
                        a4.append(Base64.decode(b.split(",")[1]));
                        a4.close();
                        a3.close();
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Webv.this.c8(Webv.this.getString(R.string.f38));
                            }
                        });
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Webv.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Webv.this.c7(Webv.this.getString(R.string.w14));
                    }
                });
            }
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
        f.setText(currentSettings().getUserAgentString());
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
        final Edit ed = c.findViewById(R.id.k9);
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
        ed.setTransformationMethod(new PasswordTransformationMethod() {

            @Override
            public CharSequence getTransformation(CharSequence source, View view) {
                return source;
            }
        });
        bn.setText(getString(R.string.e28));

        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Runnable p15 = new Runnable() {

                    @Override
                    public void run() {
                        String str = ed.getText().toString();
                        try {
                            final String sg = PassGen.a(Integer.parseInt(str));
                            Webv.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ti.setText(sg);
                                }
                            });
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                        }
                    }
                };
                new Thread(p15).start();
            }
        });
        ed.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Runnable p15 = new Runnable() {

                    @Override
                    public void run() {
                        String str = ed.getText().toString();
                        try {
                            final String sg = PassGen.a(Integer.parseInt(str));
                            Webv.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    ti.setText(sg);
                                }
                            });
                        } catch (NumberFormatException nfe) {
                            nfe.printStackTrace();
                        }
                    }
                };
                new Thread(p15).start();
                return true;
            }
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
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Html.a(ti, IdentityGenerator.a(Webv.this.getResources().getQuantityString(R.plurals.m23, 23)));
            }
        });
        final AlertDialog g = a.create();
        Objects.requireNonNull(g.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        g.show();
    }

    public void c124(String tg) {
        c3(c46() + tg);
    }

    private void c125(int type) {
        final AudioManager mAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (type == 1) {
            mAudio.setStreamVolume(AudioManager.STREAM_MUSIC, mAudio.getStreamVolume(AudioManager.STREAM_MUSIC) + 1, AudioManager.FLAG_PLAY_SOUND);
        } else if (type == 0) {
            mAudio.setStreamVolume(AudioManager.STREAM_MUSIC, mAudio.getStreamVolume(AudioManager.STREAM_MUSIC) - 1, AudioManager.FLAG_PLAY_SOUND);
        }
        if (!isSh) {
            final FrameLayout k = findViewById(R.id.i);
            final View c = View.inflate(this, R.layout.c6, null);
            LinearLayout ll = c.findViewById(R.id.b6);
            final SeekBar c2 = c.findViewById(R.id.c18);
            c2.setElevation(5);
            c2.setBackgroundResource(R.drawable.a19);
            c2.setProgress(mAudio.getStreamVolume(AudioManager.STREAM_MUSIC));
            Runnable p15 = new Runnable() {

                @Override
                public void run() {
                    final Bitmap pp = Resources.getBitmapFromResource(Webv.this, R.drawable.a6);
                    Webv.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            c2.setThumb(new BitmapDrawable(Webv.this.getResources(), pp));
                        }
                    });
                }
            };
            new Thread(p15).start();
            c2.setMax(mAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            c2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    mAudio.setStreamVolume(AudioManager.STREAM_MUSIC, i, AudioManager.FLAG_PLAY_SOUND);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            ll.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Animation.animate(Webv.this, R.anim.d, c2);
                    k.removeView(c);
                    isSh = false;
                }
            });
            k.addView(c);
            Animation.animate(this, R.anim.a, c2);
            isSh = true;
        } else {
            final FrameLayout k123 = findViewById(R.id.i);
            final SeekBar c55 = k123.findViewById(R.id.c18);
            c55.setProgress(mAudio.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
    }

    public void c126(final String url) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.b8, null);
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
                final String sg = Stream.d(url, Webv.this.getString(R.string.c33));
                Webv.this.runOnUiThread(new Runnable() {

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
                ti.setText(Webv.this.getString(R.string.v13));
                if ((url.startsWith("https://") || url.startsWith("http://")) && (!url.startsWith("file://") || !url.startsWith("content://")) && Domain.isValidDomain(url)) {
                    Runnable p151 = new Runnable() {

                        @Override
                        public void run() {
                            final String sg = Stream.d(ab, Webv.this.getString(R.string.c33));
                            Webv.this.runOnUiThread(new Runnable() {

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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

            @Override
            public void afterTextChanged(Editable s) {

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
                Package.c() + ".showToast(var message)",
                Package.c() + ".showToastError(var error)",
                Package.c() + ".vibrate(integer effect)",
                Package.c() + ".showToastSuccess(var message)",
                Package.c() + ".showNotification(var title, var content, var url)",
                "document.getAttribute()",
                "document.getAttributeNode()",
                "document.getBoundingClientRect()",
                "document.getClientRects()",
                "document.setAttribute()",
                "document.setAttributeNode()",
                "document.addEventListener",
                Package.c() + ".copyToClipboard(var text)",
                Package.c() + ".enableWifi(boolean)",
                Package.c() + "PrintHelper.print()",
                Package.c() + "SearchHelper.voice()",
                Package.c() + "HashHelper.encodeMD5(var text, boolean randomBytes)",
                Package.c() + "HashHelper.encodeSHA(var algorithm, var text , boolean randomBytes)",
                Package.c() + "HashHelper.encodeXOR(var text, var key)",
                Package.c() + "HashHelper.encodeCaesar(var text, int padding)"};
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
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String str = ed.getText().toString();
                currentTab().evaluateJavascript(str, null);
            }
        });
        a.create().show();
    }

    public void c130(final int type) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.a5, null);
        a.setCancelable(true);
        if (type == BASE64_ENCODE) {
            a.setTitle(getString(R.string.y73));
        } else if (type == URL_ENCODE) {
            a.setTitle(getString(R.string.y74));
        }
        a.setView(c);
        final Edit ed = c.findViewById(R.id.o34);
        final Edit ed1 = c.findViewById(R.id.o35);
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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
        if (currentTab().canGoForward()) {
            tv4.setImageResource(R.drawable.b13);
            tv4.setBackgroundResource(R.drawable.b17);
            Animation.animate(Webv.this, R.anim.c, tv4);
        } else {
            tv4.setImageResource(R.drawable.b30);
        }
        if (currentTab().canGoBack()) {
            tv6.setImageResource(R.drawable.c13);
            tv6.setBackgroundResource(R.drawable.b17);
            Animation.animate(Webv.this, R.anim.c, tv6);
        } else {
            tv6.setImageResource(R.drawable.c14);
        }
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
        c.setPositiveButton(getString(R.string.i6), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a, int intetg) {
                a.dismiss();
            }
        });
        c.create().show();
    }

    public void c141() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setCancelable(true);
        a.setTitle(Package.c());
        a.setMessage(getString(R.string.l37));
        a.setPositiveButton(getString(R.string.l39), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                Webv.this.c150();
                a12.dismiss();
            }
        });
        a.setNegativeButton(getString(R.string.l15), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a1, int intetg) {
                a1.dismiss();
            }
        });
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
        Intent j11 = new Intent(this, Webv.class);
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
        Intent j11 = new Intent(this, Webv.class);
        PendingIntent k567 = PendingIntent.getActivity(this, 0, j11, PendingIntent.FLAG_UPDATE_CURRENT);
        m.setContentIntent(k567);
        m.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.r));
        NotificationManager nmc = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nmc.notify(Notifications.i, m.build());
    }

    public void c144() {
        if (pm7 == null) {
            pm7 = new PopupMenu(this, tv7);
            MenuItem.OnMenuItemClickListener e = new MenuItem.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem a1) {
                    switch (a1.getItemId()) {
                        case 4:
                            Webv.this.c25();
                            Webv.this.finishAndRemoveTask();
                            return true;
                        case 5:
                            Webv.this.currentTab().clearCache(false);
                            Webv.this.c8(Webv.this.getString(R.string.a27));
                            return true;
                        case 0:
                            Intents.a(Webv.this, Down.class);
                            return true;
                        case 3:
                            Intents.a(Webv.this, Sett0.class);
                            return true;
                        case 1:
                            Webv.this.currentTab().pageUp(true);
                            return true;
                        case 2:
                            Webv.this.currentTab().pageDown(true);
                            return true;
                        case 6:
                            Intents.f(Webv.this, Hist.class, 211);
                            return true;
                        case 7:
                            Intents.f(Webv.this, Book.class, 2115);
                            return true;

                    }
                    return false;
                }
            };
            Menu me = pm7.getMenu();
            me.add(0, 0, 0, getString(R.string.h16)).setOnMenuItemClickListener(e);
            me.add(0, 6, 0, getString(R.string.h18)).setOnMenuItemClickListener(e);
            me.add(0, 7, 0, getString(R.string.h11)).setOnMenuItemClickListener(e);
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
            Runnable p15 = new Runnable() {

                @Override
                public void run() {
                    Cursor cs = d2.getReadableDatabase().rawQuery("SELECT * FROM " +
                            Sqlite.TABLE_SEARCH +
                            " ORDER BY " +
                            "_id" +
                            " DESC", null);
                    try {
                        if (cs.getCount() == 0) {
                            File fe = new File(StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "Search") + ".htm");
                            if (fe.createNewFile()) {
                                FileWriter fw = new FileWriter(fe, false);
                                BufferedWriter br = new BufferedWriter(fw);
                                br.write("<!DOCTYPE html><html><head><title>" + Webv.this.getString(R.string.i9) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file:///android_asset/classes\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><h1><b>" + Webv.this.getString(R.string.f39) + "</b></h1></center></body></html>");
                                br.close();
                                fw.close();
                            }
                        } else {
                            StringBuilder sg = new StringBuilder("<!DOCTYPE html><html><head><title>" + Webv.this.getString(R.string.i9) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file:///android_asset/classes\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><table><tr><th>" + Webv.this.getString(R.string.x49) + "</th></tr>");
                            while (cs.moveToNext()) {
                                sg.append("<tr><td>")
                                        .append(cs.getString(1))
                                        .append("</td></tr>");
                            }
                            sg.append("</table></center></body></html>");
                            File fe1 = new File(StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "Search") + ".htm");
                            if (fe1.createNewFile()) {
                                FileWriter fw1 = new FileWriter(fe1, false);
                                BufferedWriter br1 = new BufferedWriter(fw1);
                                br1.write(sg.toString());
                                br1.close();
                                fw1.close();
                            }
                        }
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Webv.this.currentTab().loadUrl("file://" + StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "Search") + ".htm");
                            }
                        });
                        cs.close();
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                }
            };
            new Thread(p15).start();
        } else if (data == WEBVIUM_HISTORY) {
            Runnable p15 = new Runnable() {

                @Override
                public void run() {
                    Cursor cs5 = d1.getReadableDatabase().rawQuery("SELECT * FROM " +
                            Sqlite.TABLE_HISTORY +
                            " ORDER BY " +
                            "_id" +
                            " DESC", null);
                    try {
                        if (cs5.getCount() == 0) {
                            File fe = new File(StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "History") + ".htm");
                            if (fe.createNewFile()) {
                                FileWriter fw = new FileWriter(fe, false);
                                BufferedWriter br = new BufferedWriter(fw);
                                br.write("<!DOCTYPE html><html><head><title>" + Webv.this.getString(R.string.i16) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file:///android_asset/classes\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><h1><b>" + Webv.this.getString(R.string.i15) + "</b></h1></center></body></html>");
                                br.close();
                                fw.close();
                            }
                        } else {
                            StringBuilder sg = new StringBuilder("<!DOCTYPE html><html><head><title>" + Webv.this.getString(R.string.i16) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file:///android_asset/classes\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><table><tr><th>" + Webv.this.getString(R.string.q17) + "</th></tr>");
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
                            File fe1 = new File(StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "History") + ".htm");
                            if (fe1.createNewFile()) {
                                FileWriter fw1 = new FileWriter(fe1, false);
                                BufferedWriter br1 = new BufferedWriter(fw1);
                                br1.write(sg.toString());
                                br1.close();
                                fw1.close();
                            }
                        }
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Webv.this.currentTab().loadUrl("file://" + StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "History") + ".htm");
                            }
                        });
                        cs5.close();
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                }
            };
            new Thread(p15).start();
        } else if (data == WEBVIUM_BOOKMARKS) {
            Runnable p15 = new Runnable() {

                @Override
                public void run() {
                    Cursor cs6 = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                            Sqlite.TABLE_BOOKMARK +
                            " ORDER BY " +
                            "_id" +
                            " DESC", null);
                    try {
                        if (cs6.getCount() == 0) {
                            File fe = new File(StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "Bookmarks") + ".htm");
                            if (fe.createNewFile()) {
                                FileWriter fw = new FileWriter(fe, false);
                                BufferedWriter br = new BufferedWriter(fw);
                                br.write("<!DOCTYPE html><html><head><title>" + Webv.this.getString(R.string.h11) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file:///android_asset/classes\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><h1><b>" + Webv.this.getString(R.string.g21) + "</b></h1></center></body></html>");
                                br.close();
                                fw.close();
                            }
                        } else {
                            StringBuilder sg = new StringBuilder("<!DOCTYPE html><html><head><title>" + Webv.this.getString(R.string.h11) + "</title><style type=\"text/css\">@font-face { font-family: b; src: url(\"file:///android_asset/classes\"); } html, body {background-color: #ffffff; color: #212121; font-family: b; } ::selection { background-color: #4285f4; color: #ffffff }</style></head><body><center><table><tr><th>" + Webv.this.getString(R.string.y88) + "</th></tr>");
                            while (cs6.moveToNext()) {
                                sg.append("<tr><td>")
                                        .append(cs6.getString(1))
                                        .append(" - ")
                                        .append(cs6.getString(2))
                                        .append("</td></tr>");
                            }
                            sg.append("</table></center></body></html>");
                            File fe1 = new File(StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "Bookmarks") + ".htm");
                            if (fe1.createNewFile()) {
                                FileWriter fw1 = new FileWriter(fe1, false);
                                BufferedWriter br1 = new BufferedWriter(fw1);
                                br1.write(sg.toString());
                                br1.close();
                                fw1.close();
                            }
                        }
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Webv.this.currentTab().loadUrl("file://" + StorageDirectory.getFileDir(Webv.this) + "/" + SHA.a("SHA-1", "Bookmarks") + ".htm");
                            }
                        });
                        cs6.close();
                    } catch (Exception en) {
                        en.printStackTrace();
                    }
                }
            };
            new Thread(p15).start();
        }
    }

    public void c149(final WebViews h) {
        if (a221().getBoolean("webviumB", false) && !this.set) {
            Runnable re = new Runnable() {

                @Override
                public void run() {
                    File fe = new File(StorageDirectory.getBackground(Webv.this));
                    if (fe.exists()) {
                        final Bitmap bp = BitmapCache.getInstance().a(StorageDirectory.getBackground(Webv.this));
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Webv.this.set = true;
                                h.setBackgroundColor(Resources.getColor(Webv.this, android.R.color.transparent));
                                Webv.this.o.setBackgroundColor(Resources.getColor(Webv.this, android.R.color.transparent));
                                Webv.this.back23.setBackground(new BitmapDrawable(Webv.this.getResources(), bp));
                                Webv.this.llt.setBackgroundColor(Resources.getColor(Webv.this, android.R.color.transparent));
                            }
                        });
                    } else {
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!Webv.this.a221().getBoolean("autoUpdate", false)) {
                                    h.setBackgroundColor(Resources.getColor(Webv.this, R.color.p));
                                    Webv.this.back23.setBackgroundColor(Resources.getColor(Webv.this, R.color.p));
                                } else {
                                    h.setBackgroundColor(Resources.getColor(Webv.this, R.color.m));
                                    Webv.this.back23.setBackgroundColor(Resources.getColor(Webv.this, R.color.m));
                                }
                                Webv.this.o.setBackgroundResource(R.drawable.p);
                                Webv.this.llt.setBackgroundResource(R.drawable.f1);
                            }
                        });
                    }
                }
            };
            new Thread(re).start();
        } else {
            this.o.setBackgroundResource(R.drawable.p);
            this.llt.setBackgroundResource(R.drawable.f1);
            if (!a221().getBoolean("autoUpdate", false)) {
                h.setBackgroundColor(Resources.getColor(this, R.color.p));
                Webv.this.back23.setBackgroundColor(Resources.getColor(this, R.color.p));
            } else {
                h.setBackgroundColor(Resources.getColor(this, R.color.m));
                Webv.this.back23.setBackgroundColor(Resources.getColor(this, R.color.m));
            }
        }
    }

    public void c150() {
        SharedPreferences sp = getSharedPreferences("wv", 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putBoolean("a10", true);
        spe.apply();
    }

    private boolean c152() {
        WebView.HitTestResult d = currentTab().getHitTestResult();
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
            pm1.setOnDismissListener(new PopupMenu.OnDismissListener() {

                @Override
                public void onDismiss(PopupMenu popupMenu) {
                    popupMenu.getMenu().clear();
                    if (Webv.this.sg != null) {
                        Webv.this.sg = null;
                    }
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
            pm2.setOnDismissListener(new PopupMenu.OnDismissListener() {

                @Override
                public void onDismiss(PopupMenu popupMenu) {
                    if (Webv.this.sg != null) {
                        Webv.this.sg = null;
                    }
                }
            });
        }
        pm2.show();
    }

    private void c155() {
        if (pm3 == null) {
            pm3 = new PopupMenu(this, inf);
            pm3.setOnDismissListener(new PopupMenu.OnDismissListener() {

                @Override
                public void onDismiss(PopupMenu popupMenu) {
                    if (Webv.this.sg != null) {
                        Webv.this.sg = null;
                    }
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
            pm4.setOnDismissListener(new PopupMenu.OnDismissListener() {

                @Override
                public void onDismiss(PopupMenu popupMenu) {
                    if (Webv.this.sg != null) {
                        Webv.this.sg = null;
                    }
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
            pm5.setOnDismissListener(new PopupMenu.OnDismissListener() {

                @Override
                public void onDismiss(PopupMenu popupMenu) {
                    if (Webv.this.sg != null) {
                        Webv.this.sg = null;
                    }
                }
            });
            Menu a1 = pm5.getMenu();
            a1.add(0, POPUPMENU_PHONE_SEND_SMS, 0, getString(R.string.w20)).setOnMenuItemClickListener(e4);
            a1.add(0, POPUPMENU_PHONE_CALL, 0, getString(R.string.x1)).setOnMenuItemClickListener(e4);
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
        final TextView f5 = e.findViewById(R.id.c20);
        Button bn = e.findViewById(R.id.k12);
        f.setText(getString(R.string.v13));
        f5.setText(getString(R.string.v13));
        Runnable p15 = new Runnable() {

            @Override
            public void run() {
                final String sg = Stream.h(Webv.this.getString(R.string.c33));
                final String sg0 = Stream.b(Webv.this.getString(R.string.c33));
                Webv.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        f5.setText(sg);
                        f.setText(sg0);
                    }
                });
            }
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
        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Runnable p151 = new Runnable() {

                    @Override
                    public void run() {
                        final String sg = Stream.b(Webv.this.getString(R.string.c33));
                        Webv.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                f.setText(sg);
                            }
                        });
                    }
                };
                new Thread(p151).start();
            }
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
        a.setPositiveButton(getString(R.string.y89), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
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
        a.setPositiveButton(getString(R.string.y89), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a2, int i) {
                a2.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
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
                for (WebViews web : tabs) {
                    web.setNetworkAvailable(false);
                }
                Animation.animate(this, R.anim.i, tv);
            } else {
                tv.setVisibility(View.GONE);
                for (WebViews web : tabs) {
                    web.setNetworkAvailable(true);
                }
                Animation.animate(this, R.anim.b, tv);
            }
        } catch (Exception rx) {
            rx.printStackTrace();
        }
    }

    public void c181(Object msg, final JsPromptResult result) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        LayoutInflater b = getLayoutInflater();
        View c = b.inflate(R.layout.s, null);
        a.setTitle(getString(R.string.s31));
        a.setCancelable(true);
        a.setView(c);
        final EditText ed = c.findViewById(R.id.o46);
        ed.setText(msg.toString());
        if (Build.VERSION.SDK_INT >= 26) {
            ed.setImportantForAutofill(EditText.IMPORTANT_FOR_AUTOFILL_NO);
        }
        ed.setImeOptions(EditorInfo.IME_FLAG_NO_FULLSCREEN);
        ed.setTypeface(type(Typeface.NORMAL));
        if (!a221().getBoolean("autoUpdate", false)) {
            ed.setTextColor(Resources.getColor(this, R.color.c));
        } else {
            ed.setTextColor(Resources.getColor(this, R.color.b));
        }
        a.setPositiveButton(getString(R.string.z76), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface a12, int intetg) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String data = ed.getText().toString();
                        result.confirm(data);
                    }
                });
                a12.dismiss();
            }
        });
        a.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface p1) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        result.cancel();
                    }
                });
                p1.dismiss();
            }
        });
        final AlertDialog g = a.create();
        g.show();
    }

    private void c182(PendingDownloadDataModel w18, String b) {
        if (a221().getBoolean("launch", false)) {
            Intents.a(this, Down.class);
        }
        DownloadHelper dh = DownloadHelper.getInstance(getApplicationContext());
        dh.c(b, w18.a1, Formatter.formatFileSize(this, w18.a4));
        DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request f = new DownloadManager.Request(Uri.parse(w18.a1));
        f.setTitle(b);
        if (a221().getBoolean("drh1", true)) {
            f.setMimeType(w18.a3);
        }
        f.setAllowedOverMetered(a221().getBoolean("drh2", true));
        f.setAllowedOverRoaming(a221().getBoolean("drh3", false));
        if (Build.VERSION.SDK_INT >= 24) {
            f.setRequiresCharging(a221().getBoolean("drh4", false));
            f.setRequiresDeviceIdle(a221().getBoolean("drh5", false));
        }
        if (a221().getBoolean("drh", true)) {
            //f.addRequestHeader("cookie", cm.getCookie(sg4));
        }
        if (a221().getBoolean("drh0", true)) {
            // f.addRequestHeader("User-Agent", sg5);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            f.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, b);
        } else if (Build.VERSION.SDK_INT >= 23 && Permission.checkOnly(Webv.this, Permission.STORAGE)) {
            f.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, b );
        } else {
            f.setDestinationInExternalPublicDir(Package.c() + "/Downloads", b);
        }
        f.allowScanningByMediaScanner();
        f.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        dm.enqueue(f);
        SharedPreferences sp9 = getSharedPreferences("wv", 0);
        if (Build.VERSION.SDK_INT >= 23 && !Objects.requireNonNull(sp9.getString("downAlert", "")).equals("changedTo")) {
            c71();
            SharedPreferences.Editor spe = sp9.edit();
            spe.putString("downAlert", "changedTo");
            spe.apply();
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
                        currentTab().reload();
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
                    currentTab().reload();
                } else {
                    if (Objects.requireNonNull(currentUrl()).startsWith("file://")) {
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
                        c68(currentTab());
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

                    c55(currentUrl(), currentTitle());

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
                        c8(String.format(getString(R.string.v15), w6.a));
                        w6 = null;
                    } else {
                        currentTab().reload();
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
                        currentTab().reload();
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
                        currentTab().reload();
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
        }
    }

    @Override
    public boolean onKeyDown(int a, KeyEvent b) {
        if (a == KeyEvent.KEYCODE_VOLUME_UP) {
            if (Objects.equals(a221().getString("VU", "1u"), "1u")) {
                c125(1);
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "7u")) {
                currentTab().pageUp(true);
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "30u")) {
                currentTab().zoomIn();
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "60u") && currentTab().canGoBack()) {
                currentTab().goBack();
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "120u")) {
                currentTab().reload();
                return true;
            } else if (Objects.equals(a221().getString("VU", ""), "140u")) {
                Intents.f(this, Book.class, 2115);
                return true;
            }
            return true;
        } else if (a == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (Objects.equals(a221().getString("VD", "1v"), "1v")) {
                c125(0);
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "7v")) {
                currentTab().pageDown(true);
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "30v")) {
                currentTab().zoomOut();
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "60v") && currentTab().canGoForward()) {
                currentTab().goForward();
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "120v")) {
                currentTab().stopLoading();
                currentTab().getFirstClient().onPageFinished(currentTab(), currentUrl());
                return true;
            } else if (Objects.equals(a221().getString("VD", ""), "140v")) {
                Intents.f(this, Hist.class, 211);
                return true;
            }
            return true;
        }
        return super.onKeyDown(a, b);
    }

    @Override
    public boolean onKeyUp(int a, KeyEvent b) {
        if (a == 4 && b.isTracking() && !b.isCanceled()) {
            if (!currentTab().canGoBack()) {
                c25();
                moveTaskToBack(true);
            } else {
                currentTab().goBack();
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
                currentTab().reload();
                return true;
            } else if (Objects.equals(a221().getString("longP", ""), "60p")) {
                if (!currentTab().canGoBack()) {
                    c25();
                    moveTaskToBack(true);
                } else {
                    currentTab().goBack();
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
        if (BuildConfig.DEBUG) {
            a.add(0, 31, 0, getString(R.string.s31)).setCheckable(true);
            a.add(0, 32, 0, getString(R.string.s32)).setCheckable(true);
        }
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
        if (Build.VERSION.SDK_INT < 29) {
            a.add(0, 14, 0, getString(R.string.k1));
        }
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
        if (inE) {
            a.findItem(31).setChecked(true);
        }
        if (dsM) {
            a.findItem(32).setChecked(true);
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
                c112(currentUrl(), ASSETLINKS);
                return true;
            case 29:
                c112(currentUrl(), SITEMAPS);
                return true;
            case 30:
                c44(currentUrl());
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
            case 12:
                if (currentSettings().getJavaScriptEnabled()) {
                    c127();
                } else {
                    c7(getString(R.string.u13));
                }
                return true;
            case 1:
                c112(currentUrl(), 5);
                return true;
            case 3:
                c112(currentUrl(), 4);
                return true;
            case 25:
                c112(currentUrl(), 6);
                return true;
            case 9:
                c112(currentUrl(), 8);
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
                c100(currentTab());
                return true;
            case 8:
                c112(currentUrl(), 7);
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
            case 31:
                // if you read this message,
                // means you can read
                if (currentSettings().getJavaScriptEnabled()) {
                    if (!dsM) {
                        if (a.isChecked()) {
                            a.setChecked(false);
                            inE = false;
                            currentTab().evaluateJavascript("javascript:document.removeEventListener('click', myfun, true);", null);
                        } else {
                            a.setChecked(true);
                            inE = true;
                            currentTab().evaluateJavascript("javascript:document.addEventListener('click', myfun, true);", null);
                        }
                    } else {
                        c7(getString(R.string.s34));
                    }
                } else {
                    a.setChecked(false);
                    c7(getString(R.string.u13));
                }
                return true;
            case 32:
                if (currentSettings().getJavaScriptEnabled()) {
                    if (!inE) {
                        if (dsM) {
                            a.setChecked(false);
                            dsM = false;
                            currentTab().evaluateJavascript("javascript:document.designMode=\"off\";", null);
                        } else {
                            a.setChecked(true);
                            dsM = true;
                            currentTab().evaluateJavascript("javascript:document.designMode=\"on\";", null);
                        }
                    } else {
                        c7(getString(R.string.s33));
                    }
                } else {
                    a.setChecked(false);
                    c7(getString(R.string.u13));
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
                    c55(currentUrl(), currentTitle());
                }
                return true;
            case 15:
                if (currentSettings().getJavaScriptEnabled()) {
                    final String js = "javascript: a();\n" +
                            "async function a() {\n" +
                            "    var myRequest = new Request('https://api.ipify.org');\n" +
                            "    fetch(myRequest).then(function(response) {\n" +
                            "        response.text().then(function(text) {\n" +
                            "            " + getSharedPreferences("di", 0).getString("di1", "") + ".a(text);\n" +
                            "        });\n" +
                            "    });\n" +
                            "}";
                    currentTab().evaluateJavascript(js, null);
                    AlertDialog.Builder a12 = new AlertDialog.Builder(Webv.this);
                    a12.setCancelable(true);
                    a12.setTitle(getString(R.string.h7));
                    LayoutInflater d = getLayoutInflater();
                    View e = d.inflate(R.layout.b4, null);
                    a12.setView(e);
                    final TextView f = e.findViewById(R.id.x);
                    f.setText(getString(R.string.v13));
                    TextView f5 = e.findViewById(R.id.c20);
                    Button bn = e.findViewById(R.id.k12);
                    f5.setText(getString(R.string.o7));
                    this.ipH = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            f.setText(intent.getStringExtra("ip"));
                        }
                    };
                    IntentFilter af = new IntentFilter();
                    af.addAction(Intents.ACTION_IP);
                    registerReceiver(this.ipH, af);
                    bn.setText(getString(R.string.h14));
                    if (!a221().getBoolean("autoUpdate", false)) {
                        f.setTextColor(Resources.getColor(this, R.color.c));
                        f5.setTextColor(Resources.getColor(this, R.color.c));
                    } else {
                        f.setTextColor(Resources.getColor(this, R.color.b));
                        f5.setTextColor(Resources.getColor(this, R.color.b));
                    }
                    bn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            currentTab().evaluateJavascript(js, null);
                        }
                    });
                    a12.setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            Webv.this.unregisterReceiver(Webv.this.ipH);
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dd = a12.create();
                    Objects.requireNonNull(dd.getWindow()).setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
                    dd.show();
                } else {
                    c7(getString(R.string.u13));
                }
                return true;
            case 7:
                c43(currentUrl());
                return true;
            case 23:
                Intent d12 = new Intent(Intent.ACTION_GET_CONTENT);
                d12.addCategory(Intent.CATEGORY_OPENABLE);
                d12.setType("*/*");
                if (d12.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(d12, getString(R.string.a26)), 3);
                }
                return true;
            case 14:
                if (Permission.check(this, Permission.STORAGE, 3)) {
                    SoftKeyboard.hide(this, back23);
                    if (Objects.requireNonNull(a221().getString("SVCTb", "1a2")).equals("1a2")) {
                        c68(back23);
                    } else {
                        c68(currentTab());
                    }
                }
                return true;
            case 2:
                return true;
            case 4:
                c112(currentUrl(), 1);
                return true;
            case 5:
                c112(currentUrl(), 2);
                return true;
            case 6:
                c112(currentUrl(), 3);
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
                    AwesomeToast.c(this, getString(R.string.t20));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                AwesomeToast.c(this, getString(R.string.t20));
            }
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
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN);
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

    static class O3 extends CountDownTimer {
        private final FrameLayout fl;
        private final View w;

        public O3(long a, long b, FrameLayout fl4, View c) {
            super(a, b);
            fl = fl4;
            w = c;
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            fl.removeView(w);
        }
    }

    static class O4 extends CountDownTimer {
        private final WebViews web;

        public O4(WebViews web, long a, long b) {
            super(a, b);
            this.web = web;
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            web.loadUrl(WEBVIUM_HOME);
        }
    }

    private class R7 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals(Intent.ACTION_SCREEN_ON)) {
                if (a221().getBoolean("lockWn99", false) && a221().getBoolean("scrON", false)) {
                    a225("aso", true);
                }
            }
        }
    }

    private class R6 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals("android.net.conn.CONNECTIVITY_CHANGE") || sg.equals("android.net.wifi.WIFI_STATE_CHANGED") || sg.equals("android.intent.action.AIRPLANE_MODE")) {
                c180();
                if (err) {
                    currentTab().reload();
                }
            }
        }
    }

    private class R8 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals("android.net.conn.CONNECTIVITY_CHANGE") || sg.equals("android.net.wifi.WIFI_STATE_CHANGED") || sg.equals("android.intent.action.AIRPLANE_MODE")) {
                if (!Connectivity.isAirplaneMode(a) && !Connectivity.isThereAnyInternetConnection(a)) {
                    currentTab().reload();
                }
            }
        }
    }

    private class R36 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
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

    private class ca149 extends BroadcastReceiver {

        @Override
        public void onReceive(Context a, Intent b) {
            String sg = b.getAction();
            if (sg.equals(Intents.ACTION_INVALIDATE)) {
                c149(currentTab());
                cd.setBackgroundResource(R.drawable.w);
                tv.setBackgroundResource(R.drawable.f2);
            }
        }
    }
}
