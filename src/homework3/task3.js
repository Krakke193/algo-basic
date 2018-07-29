'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', inputStdin => {
    inputString += inputStdin;
});

process.stdin.on('end', _ => {
    inputString = inputString.replace(/\s*$/, '')
        .split('\n')
        .map(str => str.replace(/\s*$/, ''));

    main();
});

function readLine() {
    return inputString[currentLine++];
}

function tuple(a, b) {
  return Object.freeze([a, b])
}

// Complete the lilysHomework function below.
function lilysHomework(arr) {
  let swapsAsc = 0;
  let swapsDesc = 0;
  const arrAsc = arr.slice();
  const arrDesc = arr.slice();
  const sorted = arr.slice();
  let indexes = {};
  
  for(let i=0; i<arrAsc.length; i++) {
    indexes[arrAsc[i]] = i;
  }
  
  sorted.sort((a, b) => a - b)
  for (let i=0; i<arrAsc.length; i++) {
    if (arrAsc[i] !== sorted[i]) {
      const temp = arrAsc[i]; 
      arrAsc[i] = sorted[i]; // swap sorted item into the array
      arrAsc[indexes[arrAsc[i]]] = temp; // place remebered element onto the place of element in the sorted arr
      
      // update indexes!
      const tempIdx = indexes[arrAsc[i]];
      indexes[arrAsc[i]] = i;
      indexes[temp] = tempIdx;
      
      swapsAsc++;
    }
  }
  
  indexes = {};
  for(let i=0; i<arrDesc.length; i++) {
    indexes[arrDesc[i]] = i;
  }
  
  sorted.sort((a, b) => b - a)
  for (let i=arrDesc.length - 1; i>=0; i--) {
    if (arrDesc[i] !== sorted[i]) {
      const temp = arrDesc[i]; 
      arrDesc[i] = sorted[i];
      arrDesc[indexes[arrDesc[i]]] = temp;
      
      const tempIdx = indexes[arrDesc[i]];
      indexes[arrDesc[i]] = i;
      indexes[temp] = tempIdx;
      
      swapsDesc++;
    }
  }
  
  return Math.min(swapsAsc, swapsDesc);
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine(), 10);

    const arr = readLine().split(' ').map(arrTemp => parseInt(arrTemp, 10));

    let result = lilysHomework(arr);

    ws.write(result + "\n");

    ws.end();
}
