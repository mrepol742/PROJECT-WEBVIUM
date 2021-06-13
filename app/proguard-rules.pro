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
-repackageclasses 'com.mrepol742.webvium'
-obfuscationdictionary obfuscation-dictionary.txt
-packageobfuscationdictionary obfuscation-dictionary.txt
-classobfuscationdictionary obfuscation-dictionary.txt
-optimizationpasses 5
-allowaccessmodification
-overloadaggressively
-renamesourcefileattribute ""