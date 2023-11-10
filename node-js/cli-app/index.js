#!/usr/bin/env node

const fs = require('fs');
const readline = require('readline');
const program = require('commander');
const search = require('search');

program
    .name('cli-app')
    .description('Search for a string in a text file')
    .argument('<search-text>', 'The text to search for')
    .argument('<file>', 'The file to search')
    .action((searchText, file) => {
        doSearch(searchText, file)
    })
    .parse();

function doSearch(searchText, file) {
    search.search(searchText, file)

    const fileStream = fs.createReadStream(file);
    const rl = readline.createInterface({
        input: fileStream,
        crlfDelay: Infinity,
    });

    let lineCounter = 0;

    rl.on('line', (line) => {
        lineCounter++;
        if (line.includes(searchText)) {
            console.log(`Line ${lineCounter}: ${line}`);
        }
    });
}
