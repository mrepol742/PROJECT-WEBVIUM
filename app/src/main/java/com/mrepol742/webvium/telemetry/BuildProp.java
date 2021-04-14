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

package com.mrepol742.webvium.telemetry;

import com.mrepol742.webvium.annotation.release.Keep;
import com.mrepol742.webvium.io.Files;

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