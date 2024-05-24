plugins {
    id("java")
    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.cloud:google-cloud-vision:3.37.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("commons-net:commons-net:3.7.2")
    implementation("org.jboss.logging:jboss-logging:3.5.3.Final")
    implementation("com.mysql:mysql-connector-j:8.0.33")
    implementation("com.microsoft.sqlserver:mssql-jdbc:12.2.0.jre11")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.test {
    useJUnitPlatform()
}