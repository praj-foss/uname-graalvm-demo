plugins {
    java
    id("com.palantir.graal") version "0.7.0"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

graal {
    graalVersion("20.0.0")
    javaVersion("11")

    mainClass("in.praj.demo.Main")
    outputName("uname-graal")
    option("--no-fallback")
    option("--no-server")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.graalvm.sdk:graal-sdk:${graal.graalVersion.get()}")
}

tasks.register<Exec>("runNative") {
    commandLine("$buildDir/graal/${graal.outputName.get()}")
}