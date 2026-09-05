// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "9.4.0" apply false
}

buildscript {
    dependencies {
        classpath("io.github.xilinjia.krdb:gradle-plugin:3.3.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.4.10")
    }
}