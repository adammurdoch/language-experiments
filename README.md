# Language experiments

Some experiments implementing the same simple applications in different languages.
The goal of these experiments is to explore the tooling for the various language ecosystems, rather than the 
capabilities of the languages themselves. For each language, the experiments evaluate:

- Installing the tools
- Creating a new CLI app and building and running that app
- Using an external library
- Using a local library
- Implementing and running tests
- Using an IDE

The CLI application is a simple `grep` implementation. It uses an CLI arg parsing library and the language's stdlib.

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

Creating a new library:

```shell
> cargo new --lib search
> cd search
```
 
Adding a local dependency:

```shell
> cargo add --path ../search
```

### Notes

- Tutorials focussed on building a particular type of app - CLI app, web app, network service
  - Makes it so much easier to learn the tool 
- The new application generated by `cargo new` is the minimum to get started
  - Creates the target directory
  - This reduces noise when learning the tool.
- Module dependencies are declared using a name and path to the module. The module does not need to be added to a workspace.
  - This reduces noise when learning the tool.
- External dependencies are declared using a name and version.
- Uses `crates.io` repository by default for resolving dependencies and publishing.
    - This simplifies using dependencies and publishing for most teams
- Source lives in `src` directory
  - This simplifies understanding where to place the source
- Unit test are mixed into the production source.
  - Does this mean production and unit test dependencies are not separated? 
- `cargo` command seems to honor the Rust version declared in the module file.
  - This simplifies switching between workspaces or modules that use different Rust versions
- `cargo run` command takes the app arguments directly
  - This simplifies using the app
- `cargo add` reduces noise when learning the tool.

### TODO - Rust CLI app

- Use a workspace
- Try using an IDE
- Use multiple targets and target dependent dependencies and source
- What happens when running `cargo add` in a workspace?
- Update an external dependency
- Add integration tests
- Add some features
- Add documentation
- Add examples

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

The application can be built using:

```shell
> go build
```

The application can be run using:

```shell
> go run . <pattern> <file>
```

Adding an external dependency:

- Add an import for the library in a source file.
- Run `go mod tidy` to update the mod file.

Creating a new library:

- This is basically the same as adding an application

Adding a local dependency:

- Add an import to the app source file
- `> go mod edit -replace example/search=../search`
- `> go mod tidy`

### Notes

- Everything seems to live in the module directory.
  - If this is the case, complicates understanding larger modules 

### TODO - Go CLI app

- Add some unit tests
- Use an IDE

## node.js + NPM

Setting up the environment:

Using nvm:
- Install nvm: `> curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash`
- Restart shell 
- Install node.js `> nvm install 20`

Creating a new application:

```shell
> mkdir cli-app
> cd cli-app
> npm init
```

- Answer the prompts, mostly using the defaults
- Add to `package.json`:

```
"bin": {
  "cli-app": "./index.js"
}
```

Running the app:

```shell
> node index.js
```

Or

```shell
> npm install -g .
> cli-app
```

Adding a dependency on a library:

- `const program = require('commander');`
- `npm install commander`

This also updates `package.json`

### Notes

- Very unclear how to implement a CLI app.
- Doesn't really build a CLI app, simply copies a Javascript file with a `#!` header
- Using the node.js installer lead to permission problems when running `npm install`. Using nvm resolved these problems.
- `npm help` notifies developer on the console that a new version is available, and instructions for how to do this

### TODO - node.js CLI app

- Add a local library
- Use a workspace

## TODO

- Implement a web app and web service in each language.
- KMP, Java + (Gradle, Bazel), Haskell, node.js + (npm, yarn, pnpm), Java, C#, python, ruby, Swift
- Other languages to look at
  - https://gleam.run
