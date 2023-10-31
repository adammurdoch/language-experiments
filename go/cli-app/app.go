package main

import (
	"fmt"
	"log"
	"os"
)
import "github.com/urfave/cli/v2"

func main() {
	app := &cli.App{
		Name: "cli-app",
		//Usage: "fight the loneliness!",
		Action: func(*cli.Context) error {
			fmt.Println("Hello world!")
			return nil
		},
	}

	if err := app.Run(os.Args); err != nil {
		log.Fatal(err)
	}
}
