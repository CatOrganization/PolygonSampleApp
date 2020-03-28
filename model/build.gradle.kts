import io.polygon.android.build.Dependencies

plugins {
    `java-library`
    kotlin("jvm")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Dependencies.KotlinVersion}")
}
