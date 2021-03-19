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

package com.droidmj.webvium.history;

import com.droidmj.webvium.annotation.release.Keep;

public class HistoryDataModel {
    public String ls;
    public String ls0;
    public String ls2;

    public HistoryDataModel(String ls, String ls0, String ls2) {
        this.ls = ls;
        this.ls0 = ls0;
        this.ls2 = ls2;
    }

    @Keep
    private HistoryDataModel() {
    }

}
