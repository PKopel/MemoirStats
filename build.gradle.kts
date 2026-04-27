// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "9.2.0" apply false
}

buildscript {
    dependencies {
        classpath("io.github.xilinjia.krdb:gradle-plugin:3.3.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.3.20")
    }
}