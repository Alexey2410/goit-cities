plugins {
    id("java")
}

group = "org.goit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Source: https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.14.0")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}