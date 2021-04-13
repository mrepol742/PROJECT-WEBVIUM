-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepclassmembers class * {
    @com.droidmj.webvium.annotation.release.Keep <methods>;
}

-keep class * {
    @com.droidmj.webvium.annotation.release.Keep <methods>;
}

-keep class com.droidmj.webvium.BDMS
-keep class com.droidmj.webvium.DDMS
-keep class com.droidmj.webvium.PDMS
-keep class com.droidmj.webvium.SDMS
-keep class com.droidmj.webvium.HDMS

-keepattributes *Annotation*
-repackageclasses 'com.droidmj.webvium'
-obfuscationdictionary secondary-obfuscation.txt
-packageobfuscationdictionary secondary-obfuscation.txt
-classobfuscationdictionary primary-obfuscation.txt
-optimizationpasses 5
-allowaccessmodification
-overloadaggressively
-renamesourcefileattribute "PROPERTY_OF_MELVIN_JONES_REPOL"