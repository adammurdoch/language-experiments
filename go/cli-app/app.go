package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
)
import "example/search"
import "github.com/urfave/cli/v2"

func main() {
	app := &cli.App{
		Name: "cli-app",
		Action: func(cCtx *cli.Context) error {
			pattern := cCtx.Args().Get(0)
			path := cCtx.Args().Get(1)
			search.Search(pattern, path)
			xsearch(pattern, path)
			return nil
		},
	}

	if err := app.Run(os.Args); err != nil {
		log.Fatal(err)
	}
}

func xsearch(pattern string, path string) {
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
