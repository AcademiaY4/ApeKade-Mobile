import com.android.build.gradle.internal.packaging.defaultExcludes

plugins {
    alias(libs.plugins.android.application)
}
android {
    namespace = "com.app.apekade"
    compileSdk = 34
    packaging{
        resources {
            excludes.add("META-INF/androidx.cardview_cardview.version")
        }
        // Add more exclude statements if needed
    }
    defaultConfig {
        applicationId = "com.app.apekade"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.cardview.v7)
    implementation (libs.converter.gson)
    implementation (libs.retrofit)
    implementation (libs.logging.interceptor)
    implementation(libs.navigation.runtime)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.github.Inconnu08:android-ratingreviews:1.2.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.google.android.material:material:1.5.0")
}