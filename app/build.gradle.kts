plugins {
    id("com.github.ben-manes.versions")
    id("org.sonarqube") version "6.3.1.5724"
    jacoco
    checkstyle
    application
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jackson.databind)
    implementation(libs.picocli)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

sonar {
    properties {
        property("sonar.projectKey", "DmitriyKorchagin95_java-project-71")
        property("sonar.organization", "dmitriykorchagin95")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
