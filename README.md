# Language experiments

Some experiments implementing the same simple applications in different languages.
The goal of these experiments is to explore the tooling for the various language ecosystems, rather than the 
capabilities of the languages themselves.

The CLI application is a simple `grep` implementation. It uses an args parsing library and the language's stdlib.

# Rust

## CLI application

Setting up the environment:

```shell
> curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

Creating a new application:

```shell
> cargo new cli-app
> cd cli-app
```

This creates a very minimal 'hello world' CLI app, creating the directory.
Adds a `src/main.rs` source file.

The application can be run using:

```shell
> cargo run <pattern> <file>
```

Adding an external dependency:

```shell
> cargo add clap --features derive
```

This adds the most recent stable version of the dependency to the model.

### Notes

- The new application generated by `cargo new` is the minimum to get started
  - This reduces noise.
- Module dependencies are declared using a name and path to the module. The module does not need to be added to a workspace.
  - This reduces noise.
- External dependencies are declared using a name and version.
- Uses `crates.io` repository by default for resolving dependencies and publishing.
    - Not having to define this reduces noise.
- Unit test are mixed into the production source.

### TODO

- Use a workspace
- Use multiple targets and target dependent dependencies and source
- What happens when running `cargo add` in a workspace?
- Add integration tests
- Add some features
- Add documentation
- Implement a web app
- Go, KMP, Haskell, node.js, Java, C#, python, ruby, Swift

# Go

## CLI application

Setting up the environment:

- Download and run the [installer](https://go.dev/doc/install)

Creating a new application:

```shell
> mkdir cli-app
> cd cli-app
> go mod init example/cli-app
```

Then add a `.go` source file in the directory.

The application can be run using:

```shell
> go run . 
```

Adding a dependency:

- Add an import for the library in a source file.
- Run `go mod tidy` to update the mod file.
