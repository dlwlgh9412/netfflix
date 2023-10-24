plugins {
    java
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

buildscript {
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:3.1.4")
    }
}


group = "com.copago"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.springframework.security:spring-security-crypto")


    implementation("org.flywaydb:flyway-core:9.16.0")
    implementation("org.flywaydb:flyway-mysql:9.16.0")
    compileOnly("org.projectlombok:lombok:1.18.26")
    runtimeOnly("com.h2database:h2:2.1.214")
    runtimeOnly("com.mysql:mysql-connector-j:8.0.32")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//val profile = project.properties["profile"] as String? ?: "local"
//
//sourceSets {
//    main {
//        resources {
//            srcDirs("src/main/resources", "src/main/resources/db-$profile")
//        }
//    }
//
//    test {
//        java {
//            srcDir("src/test/java")
//        }
//    }
//}
//
//tasks {
//    withType<Copy> {
//        duplicatesStrategy = DuplicatesStrategy.INCLUDE
//    }
//}

tasks.jar {
    enabled = false
}