# Language experiments

Some experiments implementing the same simple applications in different languages.
The goal of these experiments is to explore the tooling for the various language ecosystems, rather than the 
capabilities of the languages themselves.

The CLI application is a simple `grep` implementation. It uses an external args parsing library and the stdlib.

# Rust

## CLI application

Setting up the environment:

```shell
> curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

Creating a new application:

```shell
> cargo new cli-app
```

This creates a very minimal 'hello world' CLI app.

The application can be run using:

```shell
> cargo run <args>
```

Adding a dependency:

```shell
> cargo add clap --features derive
```

This adds the most recent stable version of the dependency to the model.

The quickstart guide did not include the `--features` flag and so compilation failed.

TODO - what happens when in a workspace?
