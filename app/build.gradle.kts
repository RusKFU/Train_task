plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")
    id("dagger.hilt.android.plugin")

}

android {
    namespace = "com.example.train_task"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.train_task"
        minSdk = 24
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
        viewBinding = true

    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

        implementation ("androidx.core:core-ktx:1.9.0")
        implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
        implementation ("androidx.activity:activity-compose:1.6.1")
        implementation ("androidx.compose.ui:ui:1.3.3")
        implementation ("androidx.compose.ui:ui-tooling-preview:1.3.3")
        implementation ("androidx.compose.material:material:1.3.1")
    implementation(libs.androidx.material3.android)
    debugImplementation ("androidx.compose.ui:ui-tooling:1.3.3")
        debugImplementation ("androidx.compose.ui:ui-test-manifest:1.3.3")
        implementation ("androidx.compose.material:material-icons-extended:1.3.1")
        implementation ("androidx.compose.runtime:runtime-livedata:1.3.3")
        implementation ("androidx.navigation:navigation-compose:2.6.0-alpha04")
        implementation("androidx.compose.material:material:1.3.1")
        implementation ("androidx.hilt:hilt-navigation-compose:1.3.0-alpha02")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

        testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
        testImplementation("org.mockito:mockito-junit-jupiter:5.3.0")

        testImplementation("androidx.arch.core:core-testing:2.2.0")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

        implementation ("com.google.dagger:hilt-android:2.55")
        kapt ("com.google.dagger:hilt-android-compiler:2.55")
        kapt ("androidx.hilt:hilt-compiler:1.3.0-alpha02")

        implementation ("androidx.room:room-runtime:2.6.1")
        kapt ("androidx.room:room-compiler:2.6.1")
        implementation ("androidx.room:room-ktx:2.6.1")

        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
        implementation ("com.squareup.okhttp3:okhttp:4.9.0")
        implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

        implementation ("com.squareup.moshi:moshi:1.15.0")
        kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")



}