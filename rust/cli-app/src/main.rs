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

fn main() -> std::io::Result<()> {
    let args = Cli::parse();
    println!("Searching for '{}' in {}", args.pattern, args.path.display());

    let file = File::open(&args.path)?;
    let reader = BufReader::new(file);
    for line in reader.lines() {
        let text = line.unwrap();
        if text.contains(&args.pattern) {
            println!("{}", text);
        }
    }
    Ok(())
}

#[test]
fn check_matches() {
}
