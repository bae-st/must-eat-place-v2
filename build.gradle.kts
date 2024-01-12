import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
  id("org.springframework.boot") version "3.2.1"
  id("io.spring.dependency-management") version "1.1.4"
  id("org.jlleitschuh.gradle.ktlint") version "10.2.0"

  kotlin("jvm") version "1.9.21"
  kotlin("plugin.spring") version "1.9.21"
  kotlin("plugin.jpa") version "1.9.21"
  kotlin("kapt") version "1.9.21"
  idea
}

group = "com.go"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("mysql:mysql-connector-java:8.0.31")
  implementation("org.springframework.boot:spring-boot-devtools")

  implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
  kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
  kapt("jakarta.annotation:jakarta.annotation-api")
  kapt("jakarta.persistence:jakarta.persistence-api")

  implementation("io.github.resilience4j:resilience4j-spring-boot2:2.2.0")
  implementation("io.github.resilience4j:resilience4j-all:2.2.0")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.mockk:mockk:1.13.8")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

allOpen {
  annotation("jakarta.persistence.Entity")
  annotation("jakarta.persistence.MappedSuperclass")
  annotation("jakarta.persistence.Embeddable")
}
