#!/usr/bin/env node

const program = require('commander');
const search = require('search');

program
    .name('cli-app')
    .description('Search for a string in a text file')
    .argument('<search-text>', 'The text to search for')
    .argument('<file>', 'The file to search')
    .action((searchText, file) => {
        console.log(`Searching for '${searchText}' in ${file}`);
        search.search(searchText, file)
    })
    .parse();
