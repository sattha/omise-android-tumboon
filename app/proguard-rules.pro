# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

#dagger2
-dontwarn com.google.errorprone.annotations.*
#rx
-dontwarn rx.internal.util.unsafe.**
#omise ??
-dontwarn co.omise.android.CardIO
-dontwarn co.omise.android.ui.**
# Guarding enumeration classes from ProGuard
# http://blog.osom.info/2014/02/guarding-enumeration-classes-from.html
-keepclassmembers,allowoptimization enum * {
      public static **[] values();
      public static ** valueOf(java.lang.String);
}
# ALSO REMEMBER KEEPING YOUR MODEL CLASSES
-keep class com.omise.mobile.omisetumboon.data.local.models.** { *; }
-keep class com.omise.mobile.omisetumboon.data.remote.models.** { *; }
-keepclassmembers class * implements android.os.Parcelable { static ** CREATOR; }
