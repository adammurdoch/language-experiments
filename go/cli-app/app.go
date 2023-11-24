package main

import (
	"fmt"
	"log"
	"os"
)
import "example/search"
import "github.com/urfave/cli/v2"

func main() {
	app := &cli.App{
		Name: "cli-app",
		Action: func(cCtx *cli.Context) error {
			pattern := cCtx.Args().Get(0)
			path := cCtx.Args().Get(1)
			fmt.Printf("Searching for '%s' in %s\n", pattern, path)
			search.Search(pattern, path)
			return nil
		},
	}

	if err := app.Run(os.Args); err != nil {
		log.Fatal(err)
	}
}
