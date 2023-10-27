#![allow(unused)]

use clap::Parser;
use std::io::BufRead;
use std::io::BufReader;
use std::fs::File;
use std::path::PathBuf;

#[derive(Parser)]
struct Cli {
    pattern: String,
    path: PathBuf,
}

fn main() {
    let args = Cli::parse();
    println!("Searching for '{}' in {}", args.pattern, args.path.display());

    let file = File::open(&args.path).expect("could not open file");
    let reader = BufReader::new(file);
    for line in reader.lines() {
        let text = line.expect("could not read file");
        if text.contains(&args.pattern) {
            println!("{}", text);
        }
    }
}
