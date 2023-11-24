apps {
    app("rust-cli", "rust/cli-app", "cargo run") {
        testCommand("cargo test")
    }
    app("go-cli", "go/cli-app", "go run .")
    app("node-js-cli", "node-js/cli-app", "node index.js")
    app("java-bazel-cli", "java/bazel", "bazel-bin/cli-app") {
        buildCommand("bazel build cli-app")
    }
}

fun apps(builder: AppsBuilder.() -> Unit) {
    val apps = Apps(project)
    builder(apps)
    apps.registerTasks()
}

interface AppsBuilder {
    fun app(name: String, directory: String, runCommand: String, config: AppBuilder.() -> Unit = {})

    fun app(name: String, directory: String, runCommand: List<String>, config: AppBuilder.() -> Unit = {})
}

interface AppBuilder {
    fun buildCommand(command: String)

    fun testCommand(command: String)
}

class Apps(private val project: Project) : AppsBuilder {
    private val apps = mutableListOf<CliApp>()

    override fun app(name: String, directory: String, runCommand: String, config: AppBuilder.() -> Unit) {
        app(name, directory, runCommand.split(" "), config)
    }

    override fun app(name: String, directory: String, runCommand: List<String>, config: AppBuilder.() -> Unit) {
        val builder = DefaultAppBuilder()
        config(builder)
        apps.add(CliApp(name, project.file(directory), runCommand, builder.buildCommand, builder.testCommand))
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

        val testTasks = apps.mapNotNull { app ->
            if (app.testCommand != null) {
                project.tasks.register("${app.name}Test") {
                    doLast {
                        exec {
                            commandLine = app.testCommand
                            workingDir = app.directory
                        }
                    }
                }
            } else {
                null
            }
        }
        project.tasks.register("test") {
            dependsOn(testTasks)
        }
    }

    private class DefaultAppBuilder : AppBuilder {
        var buildCommand: List<String>? = null
        var testCommand: List<String>? = null

        override fun buildCommand(command: String) {
            buildCommand = command.split(" ")
        }

        override fun testCommand(command: String) {
            testCommand = command.split(" ")
        }
    }
}

class CliApp(
    val name: String,
    val directory: File,
    val runCommand: List<String>,
    val buildCommand: List<String>?,
    val testCommand: List<String>?
)
