plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.br.films"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.br.films"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {
    implementation(platform(libs.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.activity)
    implementation(libs.material)
    implementation(libs.livedata)
    implementation(libs.viewmodel)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    implementation(libs.koin.androidx.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.runtime)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.coil.compose)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.mockito)
    implementation(libs.coroutines.test )
    implementation(libs.arch.core.test)
    implementation(libs.mockk)

    ksp(libs.koin.ksp.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}