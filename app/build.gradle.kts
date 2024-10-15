plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.plugin)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.android.multisys.exam"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.multisys.exam"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["appAuthRedirectScheme"] = "multisys-android://callback"
            manifestPlaceholders["hostname"] = "multisys-android://callback"
            buildConfigField("String", "API_URL", "\"https://www.reddit.com/\"")
            buildConfigField("String", "REDDIT_CLIENT_ID", "\"0exfaH0UyvbCt-CoRgt7Xg\"")
            buildConfigField("String", "REDDIT_REDIRECT_URI", "\"multisys-android://callback\"")
            buildConfigField("String", "REDDIT_AUTH_URL", "\"https://www.reddit.com/api/v1/authorize\"")
            buildConfigField("String", "REDDIT_TOKEN_URL", "\"https://www.reddit.com/api/v1/access_token\"")
            buildConfigField("String", "USER_AGENT", "\"MyAndroidApp by ComfortablePrint9770\"")
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["appAuthRedirectScheme"] = "multisys-android://callback"
            manifestPlaceholders["hostname"] = "multisys-android://callback"
            buildConfigField("String", "API_URL", "\"https://www.reddit.com/\"")
            buildConfigField("String", "REDDIT_CLIENT_ID", "\"0exfaH0UyvbCt-CoRgt7Xg\"")
            buildConfigField("String", "REDDIT_REDIRECT_URI", "\"multisys-android://callback\"")
            buildConfigField("String", "REDDIT_AUTH_URL", "\"https://www.reddit.com/api/v1/authorize\"")
            buildConfigField("String", "REDDIT_TOKEN_URL", "\"https://www.reddit.com/api/v1/access_token\"")
            buildConfigField("String", "USER_AGENT", "\"MyAndroidApp by ComfortablePrint9770\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // hilt
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.compiler)
//    implementation(libs.hilt.compose)

    // retrofit
    implementation(libs.okHttp)
    implementation(libs.retrofit)
    implementation(libs.gson.converter)

    // gson
    implementation(libs.gson)

    // timber
    implementation(libs.timber)

    // joda
    implementation(libs.joda)

    // navigation component
    implementation(libs.navigation)

    // coil
    implementation(libs.coil)

    // oauth
    implementation(libs.oAuth)
}