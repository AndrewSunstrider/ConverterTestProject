import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.implementation
import extensions.kapt


plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            buildConfigField("String", "CONVERTER_API_URL", "\"${project.evaluateAPIUrl()}\"")
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            buildConfigField("String", "CONVERTER_API_URL", "\"${project.evaluateAPIUrl()}\"")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.PLAY_CORE)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)

    kapt(AnnotationProcessorsDependencies.DAGGER)
}

fun Project.evaluateAPIUrl(): String =
    properties["testMode"]?.let { "http://localhost:4242" } ?: "https://open.exchangerate-api.com/v6/"
