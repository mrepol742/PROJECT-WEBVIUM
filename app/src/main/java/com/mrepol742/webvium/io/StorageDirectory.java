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

package com.mrepol742.webvium.io;

import android.content.Context;
import android.os.Environment;

import com.mrepol742.webvium.annotation.Keep;

import java.io.File;

public class StorageDirectory {

    @Keep
    private StorageDirectory() {

    }

    public static String a() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getWebviumDir() {
        return a() + "/Webvium";
    }

    public static String getDownloadDir() {
        return getWebviumDir() + "/Downloads";
    }

    public static String getScreenshotDir() {
        return getWebviumDir() + "/Screenshots";
    }

    public static String getToolsDir() {
        return getWebviumDir() + "/Tools";
    }

    public static String getClasses(Context context) {
        return getFileDir(context) + "/a";
    }

    public static String getVideoPoster(Context context) {
        return getFileDir(context) + "/b";
    }

    public static String getBackground(Context context) {
        return getFileDir(context) + "/c";
    }

    public static String getSharedPref(Context context) {
        return "//data/data/" + context.getPackageName() + "/shared_prefs/";
    }

    public static String getBaseApk() {
        return "/data/app/%1$s/base.apk";
    }

    public static File getCacheDir(Context context) {
        return context.getCacheDir();
    }

    public static File getFileDir(Context context) {
        return  context.getFilesDir();
    }

    public static class Backup {

        public static String getBackupDir() {
            return getWebviumDir() + "/Backup";
        }

        public static String getApplicationDir() {
            return getBackupDir() + "/Application";
        }

        public static String getDatabasesDir() {
            return getBackupDir() + "/Databases";
        }
    }

    public static class Fonts {

        public static String getPrimaryFont(Context co) {
            return getFileDir(co) + "/d";
        }

        public static String getSecondaryFont(Context co) {
            return getFileDir(co) + "/e";
        }
    }


}