// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.11.1" apply false
    id("org.jetbrains.kotlin.android") version "2.1.21" apply false
}

buildscript {
    dependencies {
        classpath("io.github.xilinjia.krdb:gradle-plugin:3.2.7")
    }
}