function processData(input: string) {
    const byLines = input.split("\n");
    const [n, zeros, ones] = byLines[0].split(" ");

    const lines = byLines.slice(1);

    lines.forEach(line => {
        const [zeros, ones] = countNumbers(line);
        console.log(`line: ${line}. zeros:${zeros}|ones:${ones}`);
    });
}

function countNumbers(line: string): [number, number] {
    let numRepr = line.split('').map(str => parseInt(str));
    let zeros = 0;
    let ones = 0;

    numRepr.forEach(num => {
        num == 0
            ? zeros++
            : ones++;
    });

    return [zeros, ones];
}

const data = '3 3 1\n1\n00\n100';
processData(data);