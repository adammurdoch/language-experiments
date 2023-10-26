# Language experiments

Some experiments implementing the same simple application in different languages.

# Rust

### Getting started

Setting up the environment:

```shell
> curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

Creating a new application:

```shell
> cargo new cli-app
```

Creates a very minimal 'hello world' CLI app. Can run using:

```shell
> cargo run
```

Adding `clap` as a dependency:

```shell
> cargo add clap
```

TODO - what happens when in a workspace?
