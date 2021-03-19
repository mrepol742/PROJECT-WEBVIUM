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

package com.droidmj.webvium.telemetry;

import com.droidmj.webvium.annotation.release.Keep;
import com.droidmj.webvium.io.Files;

public class BuildProp {
    @Keep
    private BuildProp() {
    }

    public static String getBuildInfo() {
        return Files.read("/default.prop", Files.br) +
                "\n\n\n" +
                Files.read("/system/build.prop", Files.br);
    }
}