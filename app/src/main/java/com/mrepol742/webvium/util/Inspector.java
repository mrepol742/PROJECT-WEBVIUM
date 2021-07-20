/*
 *
 * Copyright (c) 2021 Samiun Nafis (SamiunNafisgithub.io). All rights reserved.
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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.view.View.OnClickListener;
import android.view.View;
import com.mrepol742.webvium.R;
import com.mrepol742.webvium.BuildConfig;
import com.mrepol742.webvium.app.WebViews;

public class Inspector {
	/*
	private final Context ct;
	private final WebViews web;
	private final Activity act;
	private boolean isVisible = false;

    public Inspector(Context ct, Activity act, WebViews web) {
		this.ct = ct;
	    this.web = web;
		this.act = act;
	}

	public void editCode(final boolean inspect) throws NullPointerException {
		web.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageFinished(WebView view, String url) {
					String ev = "bool = " + inspect + ";" +
						"childNodesOfBody = document.body.childNodes;" +
						"" +
						"childNodesOfBody.forEach((children) => {" +
						"	children.addEventListener('click', () => {showCodes(children);});" +
						"});" +
						"" +
						"function showCodes(e) {" +
						"	if (!bool) {" +
						"		e.removeEventListener('click', showCodes); showCodes = ()=>{}" +
						"	} else {" +
						"		WebViumInspectElement.showCode( e.innerHTML );" +
						"       e.id = 'cGV3cGV3'; " +
						"	}" +
						"}";

					web.loadUrl("javascript:" + ev);
					super.onPageFinished(view, url);
				}
			});

		web.addJavascriptInterface(new Object() {
				@JavascriptInterface
				public void showCode(String code) {
					b("", code);
					isVisible = false;
				}
			}, "WebVium");
	}

	private void b(final Object title, final Object msg) {
		act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (!isVisible) {
						final Dialog b = new Dialog(ct);
						b.setContentView(R.layout.s01);
						Window w = b.getWindow();
						w.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

						final EditText codes = b.findViewById(R.id.dilEditText);
						Button ok = b.findViewById(R.id.dilButton);

						codes.setText(msg.toString());
                        
                        if (BuildConfig.DEBUG)
						    ok.setText("Inject");
                        else
                            ok.setText("OK");

						ok.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View p1) {
                                    if (BuildConfig.DEBUG) {
									    String inject = codes.getText().toString();
									    web.loadUrl("javascript:cGV3cGV3.innerHTML = `" + inject + "`;");
                                    }
                                    b.dismiss();
								}
							});
						b.setOnCancelListener(new DialogInterface.OnCancelListener() {

								@Override
								public void onCancel(DialogInterface p1) {
									isVisible = false;
								}
							});
						b.show();
						isVisible = true;
					}
				}
			});
	}

	 */
}
