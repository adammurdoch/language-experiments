package search;

import java.io.*;
import java.nio.file.*;

public class Search {
    public void search(String pattern, Path file) throws IOException {
        int lineNumber = 1;
        for (String line : Files.readAllLines(file)) {
            if (line.contains(pattern)) {
                System.out.println(String.format("line %d: %s", lineNumber, line.trim()));
            }
            lineNumber++;
        }
    }
}