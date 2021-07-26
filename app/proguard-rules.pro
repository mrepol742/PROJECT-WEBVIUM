#
#
# Copyright (c) 2021 Melvin Jones Repol (mrepol742.github.io). All rights reserved.
#
# License under the GNU General Public License, Version 3.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     https://www.gnu.org/licenses/gpl-3.0.en.html
#
# Unless required by the applicable law or agreed in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepclassmembers class * {
    @com.mrepol742.webvium.annotation.Keep <methods>;
}

-keep class com.mrepol742.webvium.BDMS
-keep class com.mrepol742.webvium.DDMS
-keep class com.mrepol742.webvium.PDMS
-keep class com.mrepol742.webvium.SDMS
-keep class com.mrepol742.webvium.HDMS

-keepattributes *Annotation*
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-repackageclasses 'com.mrepol742.webvium'
-obfuscationdictionary ObfuscationDictionary.txt
-packageobfuscationdictionary ObfuscationDictionary.txt
-classobfuscationdictionary ClassObfuscationDictionary.txt
-optimizationpasses 5
-allowaccessmodification
-dontpreverify
-renamesourcefileattribute ''