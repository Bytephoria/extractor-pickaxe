import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    java
    id("de.eldoria.plugin-yml.paper") version("0.7.1")
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.8-SNAPSHOT")
}

paper {

    val finalProjectName = rootProject.name.replace("-", "");

    main = "${rootProject.group}.${finalProjectName}.PaperPlugin"
    version = rootProject.version.toString()
    description = "Allows players to break spawner blocks with special pickaxes, even in WorldGuard regions."
    author = "iAmForyyDev_"
    website = "https://bytephoria.team"
    apiVersion = "1.19"

    serverDependencies {
        register("WorldGuard") {
            required = false
            joinClasspath = true
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))