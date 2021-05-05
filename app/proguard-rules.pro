-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepclassmembers class * {
    @com.mrepol742.webvium.annotation.release.Keep <methods>;
}

-keep class com.mrepol742.webvium.BDMS
-keep class com.mrepol742.webvium.DDMS
-keep class com.mrepol742.webvium.PDMS
-keep class com.mrepol742.webvium.SDMS
-keep class com.mrepol742.webvium.HDMS

-keepattributes *Annotation*
-repackageclasses 'com.mrepol742.webvium'
-obfuscationdictionary secondary-dictionary.txt
-packageobfuscationdictionary secondary-dictionary.txt
-classobfuscationdictionary primary-dictionary.txt
-optimizationpasses 5
-allowaccessmodification
-overloadaggressively
-renamesourcefileattribute "Copyright-2021-Melvin-Jones-Repol"