use clap::Parser;
use std::io::BufRead;
use std::io::BufReader;
use std::io::Result;
use std::fs::File;
use std::path::PathBuf;

#[derive(Parser)]
struct Cli {
    pattern: String,
    path: PathBuf,
}

fn main() -> Result<()> {
    let args = Cli::parse();
    println!("Searching for '{}' in {}", args.pattern, args.path.display());

    let file = File::open(&args.path)?;
    let mut reader = BufReader::new(file);
    search(args.pattern, &mut reader);
    Ok(())
}

fn search(pattern: String, input: &mut impl BufRead) {
    for line in input.lines() {
        let text = line.unwrap();
        if text.contains(&pattern) {
            println!("{}", text);
        }
    }
}

#[test]
fn check_matches() {
    let mut input = "123".as_bytes() as &[u8];
    search("1".to_string(), &mut input);
}
