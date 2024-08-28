// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.google.services)
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

        classpath ("com.android.tools.build:gradle:7.0.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
}