use std::io::BufRead;

pub fn search(pattern: String, input: &mut impl BufRead) {
    let mut line_number = 1;
    for line in input.lines() {
        let text = line.unwrap();
        if text.contains(&pattern) {
            println!("line {}: {}", line_number, text);
        }
        line_number += 1;
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
