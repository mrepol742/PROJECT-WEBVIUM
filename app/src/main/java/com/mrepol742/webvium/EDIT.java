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

package com.mrepol742.webvium;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.mrepol742.webvium.annotation.release.Keep;

// @Class EditText
public class EDIT extends EditText {
    @Keep
    public EDIT(Context context) {
        super(context);
        a();
    }

    @Keep
    public EDIT(Context context, AttributeSet attrs) {
        super(context, attrs);
        a();
    }

    @Keep
    public EDIT(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        a();
    }

    protected void a() {
        if (Build.VERSION.SDK_INT >= 26) {
            setImportantForAutofill(EditText.IMPORTANT_FOR_AUTOFILL_NO);
        }
        setImeOptions(EditorInfo.IME_FLAG_NO_FULLSCREEN);
        setMaxLines(1);
        setEllipsize(TextUtils.TruncateAt.END);

    }


}
