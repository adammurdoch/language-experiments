package search

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func Search(pattern string, path string) {
	fmt.Printf("Searching for '%s' in %s\n", pattern, path)

	file, err := os.Open(path)
	if err != nil {
		fmt.Printf("Error opening the file: %v\n", err)
		return
	}

	scanner := bufio.NewScanner(file)
	lineNumber := 1
	for scanner.Scan() {
		line := scanner.Text()
		if strings.Contains(line, pattern) {
			fmt.Printf("line %d: %s\n", lineNumber, line)
		}
		lineNumber++
	}

	if err := scanner.Err(); err != nil {
		fmt.Printf("Error reading the file: %v\n", err)
	}

	_ = file.Close()
}
