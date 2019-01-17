plugins {
    `maven-publish`
    kotlin("jvm") version "1.3.11"
}

group = "com.plutux"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.apache.kafka:kafka_2.12:1.1.+")
    implementation("com.google.protobuf:protobuf-java:3.5.+")
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
            credentials {
                username = System.getenv("SONATYPE_USER")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }
}