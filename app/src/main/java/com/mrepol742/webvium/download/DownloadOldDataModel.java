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

package com.mrepol742.webvium.download;

public class DownloadOldDataModel {
    public final String oldTitle;
    public final String oldUrl;
    public final int oldDrawable;
    public final String oldTime;
    public final String oldSize;

    public DownloadOldDataModel(String oldTitle, String oldUrl, int oldDrawable, String oldTime, String oldSize) {
        this.oldTitle = oldTitle;
        this.oldUrl = oldUrl;
        this.oldDrawable = oldDrawable;
        this.oldTime = oldTime;
        this.oldSize = oldSize;
    }

}
