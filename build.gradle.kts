// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.12.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.0" apply false
}

buildscript {
    dependencies {
        classpath("io.github.xilinjia.krdb:gradle-plugin:3.2.8")
    }
}