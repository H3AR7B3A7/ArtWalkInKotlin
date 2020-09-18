/*
 * Copyright 2020
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.0")

    defaultConfig {
        applicationId = "dev.shreyaspatil.foodium"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.plusAssign(
                    hashMapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
                    )
                )
            }
        }
    }

    buildFeatures.viewBinding = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(Dependencies.kotlin)

    // Coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // Android
    implementation(Android.appcompat)
    implementation(Android.activityKtx)
    implementation(Android.coreKtx)
    implementation(Android.constraintLayout)
    implementation(Android.swipeRefreshLayout)

    // Architecture Components
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.liveData)

    // Room components
    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.compiler)

    // Material Design
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.materialDialog)

    // Coil-kt
    implementation(Dependencies.coil)

    // Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.moshiRetrofitConverter)

    // Moshi
    implementation(Moshi.moshi)
    implementation(Moshi.codeGen)
    kapt(Moshi.codeGen)

    // Hilt + Dagger
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltViewModel)
    kapt(Hilt.daggerCompiler)
    kapt(Hilt.hiltCompiler)

    // Testing
    testImplementation(Testing.core)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.room)
    testImplementation(Testing.okHttp)
    testImplementation(Testing.jUnit)

    // Android Testing
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)
}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}
