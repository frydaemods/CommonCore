plugins {
    java
    id("io.freefair.lombok") version "8.3"
    id("java-library")
    id("fabric-loom") version "1.4-SNAPSHOT"
}

group = property("maven_group")!!
version = "${property("fabric_version")!!}-SNAPSHOT"

apply(from = uri("https://files.frydae.dev/gradle/common.gradle"))
apply(from = uri("https://files.frydae.dev/gradle/publishing.gradle"))

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")

    modImplementation("dev.frydae:fcs-fabric:${version}")?.let { include(it) }
    include(api(group = "co.aikar", name = "taskchain-core", version = "3.7.2"))
    include(api(group = "co.aikar", name = "idb-core", version = "1.0.0-SNAPSHOT"))

    modImplementation("com.sk89q.worldedit:worldedit-fabric-mc1.20.4:7.2.18-SNAPSHOT")
    modImplementation("xyz.nucleoid:fantasy:0.5.0+1.20.4")?.let { include(it) }

    include(implementation(group = "org.apache.commons", name = "commons-lang3", version = "3.14.0"))
    include(api(group = "com.zaxxer", name = "HikariCP", version = "5.1.0"))
    include(api(group = "com.mysql", name = "mysql-connector-j", version = "8.2.0"))

    modImplementation("xyz.nucleoid:server-translations-api:2.2.0+1.20.3-rc1")?.let { include(it) }
}

loom {
    splitEnvironmentSourceSets()

    mods {
        create("beguild-common") {
            sourceSet(sourceSets.getByName("main"))
            sourceSet(sourceSets.getByName("client"))
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