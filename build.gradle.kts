plugins {
    java
    id("com.gradleup.shadow") version "9.6.1"
}

group = "delta.cion.tokyo.baseCommands"

repositories {
    mavenCentral()
    maven("https://tokyo.citory.net/")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    compileOnly("delta.cion.tokyo:tokyo:2.3.0-predemo")
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
      	mergeServiceFiles()
       	archiveClassifier.set("")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}