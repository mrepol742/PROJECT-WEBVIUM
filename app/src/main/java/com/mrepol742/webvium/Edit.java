/*
 *
 * Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
 *
 * License under the GNU General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain changedTo copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by the applicable law or agreed in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrepol742.webvium;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.mrepol742.webvium.annotation.Keep;

/*
 * @EditText
 */
public class Edit extends EditText {
    @Keep
    public Edit(Context context) {
        super(context);
        a();
    }

    @Keep
    public Edit(Context context, AttributeSet attrs) {
        super(context, attrs);
        a();
    }

    @Keep
    public Edit(Context context, AttributeSet attrs, int defStyle) {
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
