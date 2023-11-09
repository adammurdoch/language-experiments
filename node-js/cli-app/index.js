#!/usr/bin/env node

const fs = require('fs');
const readline = require('readline');
const program = require('commander');

program
    .name('cli-app')
    .description('Search for a string in a text file')
    .argument('<search-text>', 'The text to search for')
    .argument('<file>', 'The file to search')
    .action((search, file) => {
        doSearch(search, file)
    })
    .parse();

function doSearch(search, file) {
    console.log(`file=${file}, search=${search}`);

    const fileStream = fs.createReadStream(file);
    const rl = readline.createInterface({
        input: fileStream,
        crlfDelay: Infinity,
    });

    let lineCounter = 0;

    rl.on('line', (line) => {
        lineCounter++;
        if (line.includes(search)) {
            console.log(`Line ${lineCounter}: ${line}`);
        }
    });
}
