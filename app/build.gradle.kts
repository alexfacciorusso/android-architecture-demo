plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.alexfacciorusso.architecturedemo"
        minSdkVersion(16)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    lintOptions.isCheckReleaseBuilds = false

    buildTypes.getByName("release") {
        minifyEnabled(false)
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }

    buildFeatures {
        viewBinding = true
    }
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}

hilt {
    enableExperimentalClasspathAggregation = true
}

dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    val kotlinVersion = "1.5.10"

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")

    // region Hilt
    val hiltVersion = "2.37"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-compiler:$hiltVersion")

    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptTest("com.google.dagger:hilt-compiler:$hiltVersion")
    // endregion

    // region Kotest
    val kotestVersion = "4.6.0"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")

    androidTestImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    // endregion

    // region MockK
    val mockkVersion = "1.11.0"
    testImplementation("io.mockk:mockk:$mockkVersion")
    androidTestImplementation("io.mockk:mockk-android:$mockkVersion")
    // endregion

    // region Fragment & navigation testing
    val fragmentVersion = "1.3.4"
    debugImplementation("androidx.fragment:fragment-testing:$fragmentVersion")

    val navigationVersion = "2.3.5"
    androidTestImplementation("androidx.navigation:navigation-testing:$navigationVersion")
    // endregion

}