use std::io::BufRead;

pub fn search(pattern: String, input: &mut impl BufRead) {
    for line in input.lines() {
        let text = line.unwrap();
        if text.contains(&pattern) {
            println!("{}", text);
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn check_matches() {
        let mut input = "123".as_bytes() as &[u8];
        search("1".to_string(), &mut input);
    }
}
