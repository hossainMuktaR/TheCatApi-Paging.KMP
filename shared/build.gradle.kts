plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.touchlabSkie)
}

kotlin {
    androidTarget()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // for api call dependency
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            //coroutines
            implementation(libs.kotlinx.coroutines.core)

            //sqldelight
            implementation(libs.sqldelight.runtime)
        }
        androidMain.dependencies {
            // android ktor client
            implementation(libs.ktor.client.android)
            // android sqldelight driver
            implementation(libs.sqldelight.android.driver)

        }
        iosMain.dependencies {
            // ios ktor client
            implementation(libs.ktor.client.darwin)
            // ios sqldelight driver
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "com.hossian.thecatapikmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
sqldelight {
    databases {
        create("CatDatabase") {
            packageName.set("com.hossain.thecatapikmp.cache")
        }
    }
}
