val rustCli by tasks.registering {
    doLast {
        exec {
            commandLine = listOf("cargo", "run", "car", "../../build.gradle.kts")
            workingDir = file("rust/cli-app")
        }
    }
}

val goCli by tasks.registering {
    doLast {
        exec {
            commandLine = listOf("go", "run", ".", "car", "../../build.gradle.kts")
            workingDir = file("go/cli-app")
        }
    }
}

val nodeJsCli by tasks.registering {
    doLast {
        exec {
            commandLine = listOf("node", "index.js", "car", "../../build.gradle.kts")
            workingDir = file("node-js/cli-app")
        }
    }
}

val javaBazelCli by tasks.registering {
    doLast {
        exec {
            commandLine = listOf("bazel", "build", "cli-app")
            workingDir = file("java/bazel")
        }
        exec {
            commandLine = listOf("bazel-bin/cli-app", "car", "../../build.gradle.kts")
            workingDir = file("java/bazel")
        }
    }
}

tasks.register("run") {
    dependsOn(rustCli, goCli, nodeJsCli, javaBazelCli)
}