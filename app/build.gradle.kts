plugins {
    id("com.github.ben-manes.versions") version "0.53.0"
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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
    implementation("info.picocli:picocli:4.7.7")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.0.0")
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