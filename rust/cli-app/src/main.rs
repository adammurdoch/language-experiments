struct Cli {
    pattern: String,
    path: std::path::PathBuf,
}

fn main() {
    let pattern = std::env::args().nth(1).expect("no pattern given");
    let path = std::env::args().nth(2).expect("no path given");
    let args = Cli {
        pattern: pattern,
        path: std::path::PathBuf::from(path),
    };
    let message = format!("Searching for '{}' in {}", args.pattern, args.path.display());
    println!("{}", message);
}
