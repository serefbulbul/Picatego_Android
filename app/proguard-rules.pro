# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/serefbulbul/Documents/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.tubb.smrv.** { *; }
-keep class com.crashlytics.sdk.android.** { *; }
-keep class com.daimajia.swipelayout.** { *; }
-keep class com.miguelcatalan.** { *; }
-keep class com.google.zxing.** { *; }
-keep class com.nineoldandroids.** { *; }
-keep class com.daimajia.slider.** { *; }
-keep class com.github.chrisbanes.** { *; }
-keep class com.afollestad.** { *; }
-keep class com.github.PhilJay.** { *; }
-keep class com.github.rahatarmanahmed.** { *; }
-keep class org.honorato.multistatetogglebutton.** { *; }
-keep class com.afollestad.material-dialogs.** { *; }
-keep class com.bignerdranch.android.** { *; }
-keep class me.grantland.** { *; }
-keep class com.mikhaellopez.** { *; }
-keep class org.altbeacon.** { *; }
-keep class com.github.simbiose.** { *; }
-keep class com.code-troopers.betterpickers.** { *; }
-keep class de.hdodenhof.** { *; }
-keep class com.android.volley.** { *; }
-keep class com.truizlop.sectionedrecyclerview.** { *; }
-keep class com.prolificinteractive.** { *; }

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepattributes JavascriptInterface
-keep public class com.adesso.macfit.activities.JoinPaymentActivity$PaymentJavascriptInteface
-keep public class * implements com.adesso.macfit.activities.JoinPaymentActivity$PaymentJavascriptInteface
-keepclassmembers class com.adesso.macfit.activities.JoinPaymentActivity$PaymentJavascriptInteface {
    <methods>;
}

-keepattributes *Annotation*