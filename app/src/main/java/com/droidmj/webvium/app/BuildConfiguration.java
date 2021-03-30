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
    public static final class Application {
        public static final boolean isDevelopment = true;
        public static final String downloadSize = "600KB";
        public static final int databaseVersion = 0;
        public static final int NOTIFICATION_DEFAULT_ID_MULTIPLIER = 5000;

        @Remove
        private Application() {

        }
    }


}
