function processData(input: string) {
  const byLines = input.split("\n");
  const [n, zeros, ones] = byLines[0].split(" ").map(num => Number.parseInt(num));

  const lines = byLines.slice(1);

  const result = countLines(lines, zeros, ones, 0);
  console.log(result);
}

function countLines(lines: Array<string>, zerosLeft: number, onesLeft: number, counter: number): number {
  if (lines.length == 0 || zerosLeft == 0 || onesLeft == 0) {
    return counter;
  }

  let copy = lines.slice();

  const line = copy.splice(0, 1);
  const [zeros, ones] = countNumbers(line[0]);

  let picked = 0;
  let notPicked = 0;

  if (zerosLeft - zeros < 0 || onesLeft - ones < 0) {
    // can't pick this one, but maybe can pick the next line
    notPicked = countLines(copy, zerosLeft, onesLeft, counter);
  } else {
    picked = countLines(copy, zerosLeft - zeros, onesLeft - ones, counter + 1);
    notPicked = countLines(copy, zerosLeft, onesLeft, counter);
  }

  return Math.max(notPicked, picked);
}

function countNumbers(line: string): [number, number] {
  let numRepr = line.split('').map(str => Number.parseInt(str));
  let zeros = 0;
  let ones = 0;

  numRepr.forEach(num => {
    num == 0
      ? zeros++
      : ones++;
  });

  return [zeros, ones];
}

const data = `3 2 4
00
110
101`;
processData(data);