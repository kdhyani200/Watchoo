plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "kd.dhyani.project0024"
    compileSdk = 36

    defaultConfig {
        applicationId = "kd.dhyani.project0024"
        minSdk = 31
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // ✅ Added
    implementation(libs.androidx.navigation.compose)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.adapter.rxjava3)
    implementation(libs.rxjava3)
    implementation(libs.rxandroid3)
    implementation(libs.koin.androidx.compose)
    implementation(libs.coil.compose)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.androidx.media3.common)

    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.36.0")

    // ✅ Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espressao.core)

    // This is the dependency for the @Preview annotation itself
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.7") // Use your Compose version

    // THIS IS THE LINE YOU NEED TO ADD
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.7")
}

