package sample;

import search.Search;
import java.io.*;
import java.nio.file.*;
import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String[] arguments = cmd.getArgs();
        if (arguments.length != 2) {
            System.out.println("USAGE: cli-app <pattern> <file>");
            System.exit(1);
        }
        String pattern = arguments[0];
        Path file = new File(cmd.getArgs()[1]).toPath();
        System.out.println(String.format("Searching for '%s' in %s", pattern, file));
        new Search().search(pattern, file);
    }
}