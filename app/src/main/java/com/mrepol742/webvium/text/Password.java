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

package com.mrepol742.webvium.text;


import android.text.method.PasswordTransformationMethod;
import android.view.View;

public class Password extends PasswordTransformationMethod {
    public CharSequence getTransformation(CharSequence source, View view) {
        return source;

    }
}