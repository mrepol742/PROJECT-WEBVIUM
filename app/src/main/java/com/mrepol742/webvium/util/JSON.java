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

package com.mrepol742.webvium.util;

import android.database.Cursor;
import com.mrepol742.webvium.annotation.Keep;
import com.mrepol742.webvium.app.Sqlite;
import com.mrepol742.webvium.app.UpdateDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class JSON {

    @Keep
    private JSON() {

    }

    public static UpdateDataModel getUpdate(String sg, String build) {
        try {
            JSONObject a = new JSONObject(sg);
            JSONArray c = a.getJSONArray(build);
            JSONObject b = new JSONObject(c.get(0).toString());
            return new UpdateDataModel(b.getString("url"), b.getInt("name"), b.getInt("code"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(Cursor cursor, int id) {
        JSONObject json = new JSONObject();
        try {
            json.put("db", getName(id));
            while (cursor.moveToNext()) {
                JSONArray array = new JSONArray();
                JSONObject item = new JSONObject();
                if (id == 0) {
                    item.put(Sqlite.COL1_BOOKMARK, cursor.getString(1));
                    item.put(Sqlite.COL2_BOOKMARK, cursor.getString(2));
                } else if (id == 1) {
                    item.put(Sqlite.COL1_DOWNLOAD, cursor.getString(1));
                    item.put(Sqlite.COL2_DOWNLOAD, cursor.getString(2));
                    item.put(Sqlite.COL3_DOWNLOAD, cursor.getString(3));
                    item.put(Sqlite.COL4_DOWNLOAD, cursor.getString(4));
                } else if (id == 2) {
                    item.put(Sqlite.COL1_HISTORY, cursor.getString(1));
                    item.put(Sqlite.COL2_HISTORY, cursor.getString(2));
                    item.put(Sqlite.COL3_HISTORY, cursor.getString(3));
                } else {
                    item.put(Sqlite.COL1_SEARCH, cursor.getString(1));
                }
                array.put(item);
                json.put(Integer.toString(cursor.getInt(0)), array);
            }
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return "null";
    }

    public static String toString(Map<String, ?> map) {
        JSONObject json = new JSONObject();
        try {
            json.put("db", "sett");
            JSONArray array = new JSONArray();
            JSONObject item = new JSONObject();
            for (Map.Entry<String, ?> sg : map.entrySet()) {
                item.put(sg.getKey(), sg.getValue());
            }
            array.put(item);
            json.put("0", array);
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "null";
    }

    private static String getName(int id) {
        if (id == 0) {
            return Sqlite.TABLE_BOOKMARK;
        } else if (id == 1) {
            return Sqlite.TABLE_DOWNLOAD;
        } else if (id == 2) {
            return Sqlite.TABLE_HISTORY;
        }
        return Sqlite.TABLE_SEARCH;
    }
}
