
const fs = require('fs');
const readline = require('readline');

exports.search = (searchText, file) => {
    console.log(`file=${file}, search=${searchText}`);

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
