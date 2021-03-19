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

package com.droidmj.webvium.app;

import com.droidmj.webvium.APPL;
import com.droidmj.webvium.ASSI;
import com.droidmj.webvium.BACK;
import com.droidmj.webvium.BACK0;
import com.droidmj.webvium.BDMS;
import com.droidmj.webvium.BOOK;
import com.droidmj.webvium.BOOT;
import com.droidmj.webvium.CLIP;
import com.droidmj.webvium.CRED;
import com.droidmj.webvium.DDMS;
import com.droidmj.webvium.DEVI;
import com.droidmj.webvium.DIAG;
import com.droidmj.webvium.DOWN;
import com.droidmj.webvium.DOWN0;
import com.droidmj.webvium.EDIT;
import com.droidmj.webvium.EDIT0;
import com.droidmj.webvium.EULA;
import com.droidmj.webvium.FEED;
import com.droidmj.webvium.FLOA;
import com.droidmj.webvium.HDMS;
import com.droidmj.webvium.HIST;
import com.droidmj.webvium.LIST;
import com.droidmj.webvium.LOCK;
import com.droidmj.webvium.LOGC;
import com.droidmj.webvium.MAIN;
import com.droidmj.webvium.MANG;
import com.droidmj.webvium.MYPA;
import com.droidmj.webvium.NOTI;
import com.droidmj.webvium.PDMS;
import com.droidmj.webvium.PREF;
import com.droidmj.webvium.PREF0;
import com.droidmj.webvium.QUIC;
import com.droidmj.webvium.SAVE;
import com.droidmj.webvium.SCRE;
import com.droidmj.webvium.SDMS;
import com.droidmj.webvium.SEAR;
import com.droidmj.webvium.SETT;
import com.droidmj.webvium.SETT0;
import com.droidmj.webvium.SWIT;
import com.droidmj.webvium.TEXT;
import com.droidmj.webvium.TOOL;
import com.droidmj.webvium.UPDA;
import com.droidmj.webvium.UPDA0;
import com.droidmj.webvium.VIDE;
import com.droidmj.webvium.VOIC;
import com.droidmj.webvium.WELC;
import com.droidmj.webvium.annotation.release.Remove;

@Remove
public class BuildConfiguration {

    @Remove
    private BuildConfiguration() {
    }

    @Remove
    public static final class Classes {
        public static final Class<?> APPLICATION = APPL.class;
        public static final Class<?> ASSISTANT = ASSI.class;
        public static final Class<?> BACKUP_SERVICE = BACK.class;
        public static final Class<?> BACKUP_IMPORT = BACK0.class;
        public static final Class<?> BDMS = BDMS.class;
        public static final Class<?> BOOK = BOOK.class;
        public static final Class<?> BOOT_COMPLETED = BOOT.class;
        public static final Class<?> CLIPBOARD_SERVICE = CLIP.class;
        public static final Class<?> CREDITS = CRED.class;
        public static final Class<?> DDMS = com.droidmj.webvium.DDMS.class;
        public static final Class<?> DEVICE_ADMIN_RECEIVER = DEVI.class;
        public static final Class<?> DIAGNOSTIC_SERVICE = DIAG.class;
        public static final Class<?> DOWNLOADS = DOWN.class;
        public static final Class<?> DOWNLOAD_SERVICE = DOWN0.class;
        public static final Class<?> EDIT_TEXT = EDIT.class;
        public static final Class<?> EDIT_TEXT_PREF = EDIT0.class;
        public static final Class<?> END_USER_LICENSE_AGREEMENT = EULA.class;
        public static final Class<?> FEEDBACK = FEED.class;
        public static final Class<?> FLOATING_WEBVIUM = FLOA.class;
        public static final Class<?> HDMS = HDMS.class;
        public static final Class<?> HISTORY = HIST.class;
        public static final Class<?> LIST_PREF = LIST.class;
        public static final Class<?> LOCK = LOCK.class;
        public static final Class<?> LOGCAT = LOGC.class;
        public static final Class<?> MAIN_ACTIVITY = MAIN.class;
        public static final Class<?> MANAGE_SPACE = MANG.class;
        public static final Class<?> MY_PACKAGED_REPLACED = MYPA.class;
        public static final Class<?> NOTIFICATION_SERVICE = NOTI.class;
        public static final Class<?> PDMS = PDMS.class;
        public static final Class<?> PREFERENCE = PREF.class;
        public static final Class<?> PREFERENCE_CATEGORY = PREF0.class;
        public static final Class<?> QUICK_SEARCH = QUIC.class;
        public static final Class<?> SAVE_LINK_SERVICE = SAVE.class;
        public static final Class<?> SCREENSHOT = SCRE.class;
        public static final Class<?> SDMS = SDMS.class;
        public static final Class<?> SEARCH = SEAR.class;
        public static final Class<?> SETTING_LAUNCHER = SETT.class;
        public static final Class<?> SETTINGS = SETT0.class;
        public static final Class<?> SWITCH_PREF = SWIT.class;
        public static final Class<?> TEXT_SIZE = TEXT.class;
        public static final Class<?> TOOLS = TOOL.class;
        public static final Class<?> UPDATE_SERVICE = UPDA.class;
        public static final Class<?> UPDATE_SERVICE_0 = UPDA0.class;
        public static final Class<?> VIDEO_POSTER_DEMO = VIDE.class;
        public static final Class<?> VOICE_SEARCH = VOIC.class;
        public static final Class<?> WELCOME_SCREEN = WELC.class;

        @Remove
        private Classes() {

        }
    }

    @Remove
    public static final class URLs {
        public static final String FILE = "file://";
        public static final String CONTENT = "content://";
        public static final String HTTP = "http://";
        public static final String HTTPS = "https://";

        @Remove
        public static final class Internal {


            @Remove
            private Internal() {

            }
        }

        @Remove
        private URLs() {

        }
    }

    @Remove
    public static final class UserAgents {
        public static final String DEFAULT = "1e";
        public static final String ANDROID_STOCK = "7e";
        public static final String INTERNET_EXPLORER = "30e";
        public static final String GOOGLE_CHROME = "60e";
        public static final String MOZILA_FIREFOX = "120e";
        public static final String OPERA = "240e";
        public static final String SAFARI = "480e";
        public static final String MICROSOFT_EDGE = "960e";
        public static final String GOOGLE_CHROMIUM = "1920e";
        public static final String MOZILA_BRAVE = "3840e";
        public static final String CUSTOM = "7680e";

        @Remove
        private UserAgents() {

        }
    }

    @Remove
    public static final class SearchEngines {
        public static final String GOOGLE = "7b";
        public static final String DUCKDUCKGO = "1b";
        public static final String BING = "30b";
        public static final String YAHOO = "60b";
        public static final String ASK = "120b";
        public static final String AOL = "240b";
        public static final String BAIDU = "480b";
        public static final String WOLFRAMALPHA = "860b";
        public static final String DISCOVERAPP = "1720b"; // updated from freebasics
        public static final String ECOSIA = "3440b";
        public static final String STACKOVERFLOW = "6880b";
        public static final String YOUTUBE = "12b";
        public static final String GITHUB = "13b";
        public static final String FACEBOOK = "14b";

        @Remove
        private SearchEngines() {

        }
    }

    @Remove
    public static final class Font {
        public static final String MAVEN_PRO = "classes";

        @Remove
        private Font() {

        }
    }

    @Remove
    public static final class Theme {
        public static final int MAIN = 0;
        public static final int MANAGE_SPACE = 1;
        public static final int DEFAULT = 2;
        public static final int WELCOME_SCREEN = 3;
        public static final int ASSISTANT = 4;

        @Remove
        private Theme() {

        }
    }

    @Remove
    public static final class Database {
        public static final String DATA_HISTORY = "qw";
        public static final String DATA_SEARCH = "as";
        public static final String DATA_DOWNLOAD = "jo";
        public static final String DATA_BOOKMARK = "zx";
        public static final String DATA_PERMISSION = "po";

        public static final int VERSION_HISTORY = 1;
        public static final int VERSION_SEARCH = 1;
        public static final int VERSION_DOWNLOAD = 1;
        public static final int VERSION_BOOKMARK = 1;
        public static final int VERSION_PERMISSION = 1;

        public static final String TABLE_HISTORY = "A";
        public static final String TABLE_SEARCH = "A";
        public static final String TABLE_DOWNLOAD = "A";
        public static final String TABLE_BOOKMARK = "A";
        public static final String TABLE_PERMISSION = "A";

        public static final String COL1_HISTORY = "B";
        public static final String COL2_HISTORY = "C";
        public static final String COL3_HISTORY = "D";

        public static final String COL1_SEARCH = "B";

        public static final String COL1_DOWNLOAD = "B";
        public static final String COL2_DOWNLOAD = "C";
        public static final String COL3_DOWNLOAD = "D";
        public static final String COL4_DOWNLOAD = "E";
        public static final String COL5_DOWNLOAD = "F";

        public static final String COL1_BOOKMARK = "B";
        public static final String COL2_BOOKMARK = "C";

        public static final String COL1_PERMISSION = "B";
        public static final String COL2_PERMISSION = "C";
        public static final String COL3_PERMISSION = "D";
        public static final String COL4_PERMISSION = "E";

        public static final String ID = "_id";

        @Remove
        private Database() {

        }
    }

    @Remove
    public static final class Setting {
        public static final int FRAGMENT_TOOL = 1;
        public static final int FRAGMENT_GENERAL = 2;
        public static final int FRAGMENT_VIDEO = 3;
        public static final int FRAGMENT_SEARCH = 4;
        public static final int FRAGMENT_DOWNLOAD = 5;
        public static final int FRAGMENT_INTERFACE = 6;
        public static final int FRAGMENT_PRIVACY = 7;
        public static final int FRAGMENT_ADVANCED = 8;
        public static final int FRAGMENT_ACCESSIBILITY = 9;
        public static final int FRAGMENT_ABOUT = 10;
        public static final int FRAGMENT_EXPERIMENTAL = 11;
        public static final int FRAGMENT_DATABASE = 12;
        public static final int FRAGMENT_SECURITY_LOCK = 13;
        public static final int FRAGMENT_PRETEND_MODE = 14;
        public static final int FRAGMENT_DEFAULT = 742;

        @Remove
        private Setting() {

        }
    }

    @Remove
    public static final class Intent {
        public static final String ACTION_INVALIDATE = "com.droidmj.webvium.intent.action.INVALIDATE";
        public static final String CATEGORY_GENIUS = "com.droidmj.webvium.intent.category.GENIUS";
        public static final String ACTION_LAUNCH = "com.droidmj.webvium.intent.action.LAUNCH";
        public static final String ACTION_VOICE = "com.droidmj.webvium.intent.action.VOICE";
        public static final String ACTION_PASTE_SEARCH = "com.droidmj.webvium.action.PASTE_SEARCH";
        public static final String ACTION_PASTE = "com.droidmj.webvium.action.PASTE";
        public static final String ACTION_ASSIST = "com.droidmj.webvium.action.ASSIST";
        public static final String ACTION_SEARCH = "com.droidmj.webvium.action.SEARCH";
        public static final String ACTION_LOGCAT = "com.droidmj.webvium.action.LOGCAT";
        public static final String ACTION_FEEDBACK = "com.droidmj.webvium.action.FEEDBACK";

        @Remove
        private Intent() {

        }
    }

    @Remove
    public static final class Manifest {

        @Remove
        private Manifest() {

        }
    }

    @Remove
    public static final class Notification {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        public static final int i = 9;

        @Remove
        private Notification() {

        }
    }

    @Remove
    public static final class Application {
        public static final boolean isDevelopment = true;
        public static final String downloadSize = "600KB";
        public static final int databaseVersion = 0;
        public static final int NOTIFICATION_DEFAULT_ID_MULTIPLIER = 5000;

        @Remove
        private Application() {

        }
    }

    @Remove
    public static final class Files {
        public static final String classes = "/b";
        public static final String videoPoster = "/e";
        public static final String background = "/a";

        @Remove
        private Files() {

        }
    }

}
