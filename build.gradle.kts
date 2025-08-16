plugins {
    id("java")
}

group = "pentapulse.healthcheck"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.yaml.snakeyaml:2.2")
}

tasks.test {
    useJUnitPlatform()
}