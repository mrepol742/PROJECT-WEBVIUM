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

package com.mrepol742.webvium.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.webkit.JavascriptInterface;

import com.mrepol742.webvium.MAIN;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.bookmark.BookmarkHelper;
import com.mrepol742.webvium.history.HistoryHelper;
import com.mrepol742.webvium.search.SearchHelper;
import com.mrepol742.webvium.util.Base64;
import com.mrepol742.webvium.util.Domain;

import java.util.ArrayList;
import java.util.Objects;

public class SearchJSI {
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
    private SearchHelper searchHelper;
    private SharedPreferences sharedPreferences;
    private Context context;

    public SearchJSI(Context context) {
        this.context = context;
        this.searchHelper = SearchHelper.getInstance(context.getApplicationContext());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Keep
    private SearchJSI() {
    }

    @JavascriptInterface
    public void saveQuery(String sg) {
        searchHelper.c(sg);
    }

    @JavascriptInterface
    public String query() {
        ArrayList<String> ls = new ArrayList<>();
        Cursor res = searchHelper.getReadableDatabase().rawQuery("SELECT * FROM " +
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
        if (sharedPreferences.getBoolean("showHTT", false)) {
            HistoryHelper d1 = HistoryHelper.getInstance(context.getApplicationContext());
            Cursor rest = d1.getReadableDatabase().rawQuery("SELECT * FROM " +
                    Sqlite.TABLE_HISTORY +
                    " ORDER BY " +
                    "_id" +
                    " DESC ", null);
            if (rest.getCount() != 0) {
                boolean bn = sharedPreferences.getBoolean("showLKS", false);
                while (rest.moveToNext()) {
                    if (bn) {
                        ls.add(Base64.encode(rest.getString(1)));
                    }
                    ls.add(Base64.encode(rest.getString(2)));
                }
            }
            rest.close();
        }
        if (sharedPreferences.getBoolean("showBKM", false)) {
            BookmarkHelper d3 = BookmarkHelper.getInstance(context.getApplicationContext());
            Cursor rest1 = d3.getReadableDatabase().rawQuery("SELECT * FROM " +
                    Sqlite.TABLE_BOOKMARK +
                    " ORDER BY " +
                    "_id" +
                    " DESC ", null);
            if (rest1.getCount() != 0) {
                boolean bn = sharedPreferences.getBoolean("showLKS", false);
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
        switch (Objects.requireNonNull(sharedPreferences.getString("searchP", ""))) {
            case MAIN.SE_DUCKDUCKGO:
                return searchEngine[1];
            default:
            case MAIN.SE_GOOGLE:
                return searchEngine[0] + searchPath[0];
            case MAIN.SE_BING:
                return searchEngine[2] + searchPath[0];
            case MAIN.SE_YAHOO:
                return searchPath[1];
            case MAIN.SE_ASK:
                return searchPath[2];
            case MAIN.SE_AOL:
                return searchPath[3];
            case MAIN.SE_BAIDU:
                return searchEngine[6] + searchPath[4];
            case MAIN.SE_WOLFRAMALPHA:
                return searchEngine[7] + searchPath[5];
            case MAIN.SE_DISCOVERAPP:
                return searchEngine[8] + searchPath[6];
            case MAIN.SE_ECOSIA:
                return searchEngine[9] + searchPath[0];
            case MAIN.SE_STACKOVERFLOW:
                return searchEngine[10] + searchPath[0];
            case MAIN.SE_YOUTUBE:
                return searchEngine[11] + searchPath[7];
            case MAIN.SE_GITHUB:
                return searchEngine[12] + searchPath[0];
            case MAIN.SE_FACEBOOK:
                return searchEngine[13] + searchPath[8];
        }
    }
}
