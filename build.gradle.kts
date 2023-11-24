val apps = Apps(project) {
    app("rust-cli", "rust/cli-app", listOf("cargo", "run"))
    app("go-cli", "go/cli-app", listOf("go", "run", "."))
    app("node-js-cli", "node-js/cli-app", listOf("node", "index.js"))
    app("java-bazel-cli", "java/bazel", listOf("bazel-bin/cli-app"), listOf("bazel", "build", "cli-app"))
}
apps.registerTasks()

class Apps(private val project: Project, builder: Apps.() -> Unit) {
    val apps = mutableListOf<CliApp>()

    init {
        builder(this)
    }

    fun app(name: String, directory: String, runCommand: List<String>, buildCommand: List<String>? = null) {
        apps.add(CliApp(name, project.file(directory), runCommand, buildCommand))
    }

    fun registerTasks() {
        val runTasks = apps.map { app ->
            project.tasks.register(app.name) {
                doLast {
                    if (app.buildCommand != null) {
                        exec {
                            commandLine = app.buildCommand
                            workingDir = app.directory
                        }
                    }
                    exec {
                        commandLine = app.runCommand + listOf("car", "../../build.gradle.kts")
                        workingDir = app.directory
                    }
                }
            }
        }
        project.tasks.register("run") {
            dependsOn(runTasks)
        }
    }
}

class CliApp(
    val name: String,
    val directory: File,
    val runCommand: List<String>,
    val buildCommand: List<String>? = null
)
