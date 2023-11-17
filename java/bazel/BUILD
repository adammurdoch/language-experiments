
java_binary(
    name = "cli-app",
    srcs = glob(["cli-app/src/main/java/sample/*.java"]),
    main_class = "sample.Main",
    deps = [":search", "@maven//:commons_cli_commons_cli"]
)

java_library(
    name = "search",
    srcs = glob(["search/src/main/java/*/*.java"]),
)
