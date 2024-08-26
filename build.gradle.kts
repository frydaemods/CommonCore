plugins {
    java
    id("io.freefair.lombok") version "8.10"
    id("java-library")
    id("fabric-loom") version "1.7-SNAPSHOT"
}

group = property("maven_group")!!
version = "${property("fabric_version")!!}-SNAPSHOT"

apply(from = uri("https://files.frydae.dev/gradle/publishing.gradle"))

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

    modImplementation("dev.frydae:fcs-fabric:${version}")?.let { include(it) }
}

repositories {
    maven { url = uri("https://maven.isxander.dev/releases") }
    maven { url = uri("https://maven.isxander.dev/snapshots") }
    maven { url = uri("https://maven.nucleoid.xyz/") }
    maven { url = uri("https://maven.enginehub.org/repo") }
    maven { url = uri("https://maven.frydae.dev/releases/") }
    maven { url = uri("https://maven.frydae.dev/snapshots/") }
    maven { url = uri("https://ci.emc.gs/nexus/content/groups/aikar/") }
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

loom {
    splitEnvironmentSourceSets()

    accessWidenerPath = file("src/main/resources/beguild-common.accesswidener")

    mods {
        register("beguild-common") {
            sourceSet("main")
            sourceSet("client")
        }
    }
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(getProperties())
            expand(mutableMapOf(
                    "version" to project.version
            ))
        }
    }

    test {
        useJUnitPlatform()
    }

    jar {
        from("LICENSE")
    }
}