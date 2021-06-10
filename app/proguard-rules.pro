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
