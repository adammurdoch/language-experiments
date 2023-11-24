samples {
    app("rust-cli", "rust/cli-app", "cargo run") {
        testCommand("cargo test")
    }
    lib("rust-search", "rust/search", "cargo test")
    app("go-cli", "go/cli-app", "go run .")
    app("node-js-cli", "node-js/cli-app", "node index.js")
    app("java-bazel-cli", "java/bazel", "bazel-bin/cli-app") {
        buildCommand("bazel build cli-app")
    }
}

fun samples(builder: SamplesBuilder.() -> Unit) {
    val samples = Samples(project)
    builder(samples)
    samples.registerTasks()
}

interface SamplesBuilder {
    fun app(name: String, directory: String, runCommand: String, config: AppBuilder.() -> Unit = {})

    fun app(name: String, directory: String, runCommand: List<String>, config: AppBuilder.() -> Unit = {})

    fun lib(name: String, directory: String, testCommand: String)
}

interface AppBuilder {
    fun buildCommand(command: String)

    fun testCommand(command: String)
}

class Samples(private val project: Project) : SamplesBuilder {
    private val samples = mutableListOf<Sample>()

    override fun app(name: String, directory: String, runCommand: String, config: AppBuilder.() -> Unit) {
        app(name, directory, runCommand.split(" "), config)
    }

    override fun app(name: String, directory: String, runCommand: List<String>, config: AppBuilder.() -> Unit) {
        val builder = DefaultAppBuilder()
        config(builder)
        samples.add(CliApp(name, project.file(directory), runCommand, builder.buildCommand, builder.testCommand))
    }

    override fun lib(name: String, directory: String, testCommand: String) {
        samples.add(Lib(name, project.file(directory), testCommand.split(" ")))
    }

    fun registerTasks() {
        val runTasks = samples.filterIsInstance<CliApp>().map { app ->
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

        val testTasks = samples.mapNotNull { sample ->
            if (sample.testCommand != null) {
                project.tasks.register("${sample.name}Test") {
                    doLast {
                        exec {
                            commandLine = sample.testCommand
                            workingDir = sample.directory
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

sealed class Sample(
    val name: String,
    val directory: File,
    val testCommand: List<String>?
)

class Lib(
    name: String,
    directory: File,
    testCommand: List<String>
) : Sample(name, directory, testCommand)

class CliApp(
    name: String,
    directory: File,
    val runCommand: List<String>,
    val buildCommand: List<String>?,
    testCommand: List<String>?
) : Sample(name, directory, testCommand)
