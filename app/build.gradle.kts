import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services") // Firebase 관련 플러그인 추가
}
android {
    namespace = "com.example.hansotbob"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hansotbob"
        minSdk = 23
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

        buildFeatures{
            viewBinding = true
            compose = true
        }
    }
    composeOptions{
        kotlinCompilerExtensionVersion="1.5.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // library dependencies
    implementation("com.github.florent37:materialviewpager:1.1.0")
    implementation("com.flaviofaria:kenburnsview:1.0.7")
    implementation("com.jpardogo.materialtabstrip:library:1.1.0")
    implementation("com.github.bumptech.glide:glide:4.0.0")
    implementation("com.github.ViksaaSkool:AwesomeSplash:v1.0.0")
    implementation("com.ramotion.paperonboarding:paper-onboarding:1.1.3")
    implementation("com.github.a914-gowtham:compose-ratingbar:1.3.12")

    // default dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database.ktx)
//    implementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Compose dependencies
    implementation("androidx.activity:activity-compose:1.3.0-alpha07")
    implementation ("androidx.compose.ui:ui:1.5.2")
    implementation ("androidx.compose.material:material:1.5.2")
    implementation("androidx.compose.material3:material3:1.3.0-beta02")
    implementation("androidx.compose.material:material-icons-extended-android:1.6.7")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.2")
    implementation ("androidx.compose.runtime:runtime:1.5.2")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.2")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.5.2")

    // Compose testing dependencies
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.2")

    // Accompanist for Pager
    androidTestImplementation(libs.accompanist.pager)
    androidTestImplementation(libs.accompanist.pager.indicators)

    // Compose additional dependencies
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    debugImplementation("androidx.compose.ui:ui-tooling:1.6.7")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.7")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
}

apply(plugin = "com.google.gms.google-services")