plugins {
    kotlin("jvm") version "1.9.0"
    id("me.champeau.jmh") version "0.7.2"
}

group = "io.github.seggan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:1.33")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:1.33")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}