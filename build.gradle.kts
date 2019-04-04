plugins {
    `maven-publish`
    kotlin("jvm") version "1.3.11"
}

group = "com.plutux"
version = "1.2"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.mongodb:bson:3.3.0")
    implementation("org.apache.kafka:kafka_2.12:1.1.0")
    implementation("com.google.protobuf:protobuf-java:3.5.1")
    implementation("io.github.gaplotech:kotlin-protobuf-bson-codec:0.3.0")
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = if (project.version.toString().endsWith("SNAPSHOT"))
                uri("https://oss.sonatype.org/content/repositories/snapshots")
            else uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("SONATYPE_USER")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }
}