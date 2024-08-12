plugins {
    id ("com.android.application")
}

android {
    namespace = "com.example.prestamos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.prestamos"
        minSdk = 32
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
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation (group = "androidx.core", name = "core-ktx", version = "1.12.0")
    implementation (group = "androidx.appcompat", name = "appcompat", version = "1.6.1")
    implementation (group = "com.google.android.material", name = "material", version = "1.10.0")
    implementation (group = "androidx.constraintlayout", name= "constraintlayout", version=  "2.1.4")
    implementation (group = "com.squareup.retrofit2", name = "retrofit", version= "2.9.0")
    implementation (group= "com.squareup.retrofit2", name = "converter-gson", version = "2.9.0")
    implementation (group = "androidx.recyclerview", name = "recyclerview", version = "1.2.1")

    implementation (group=  "androidx.appcompat",name= "appcompat" , version= "1.6.1")
    implementation (group = "androidx.core", name = "core-ktx", version = "1.9.0")
    testImplementation (group = "junit", name = "junit", version = "4.13.2")
    androidTestImplementation (group = "androidx.test.ext", name = "junit", version = "1.1.5")
    androidTestImplementation (group = "androidx.test.espresso", name = "espresso-core", version ="3.5.1")
}
