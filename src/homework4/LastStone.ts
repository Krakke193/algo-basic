function processData(input: string) {
    const rawLines = input.split('\n');
    const [s, m, n] = rawLines[0].split(' ').map(str => Number.parseInt(str));
    const stonesToPick = rawLines[1].split(' ').map(str => Number.parseInt(str));

    const a: Array<number> = [];

    // one always present
    a[0] = 100;
    a[1] = 1;

    for (let i = 2; i <= n; i++) {
        for (let j = 0; j < stonesToPick.length; j++) {

            if (i - stonesToPick[j] >= 0) {
                a[i] = Math.max(0, a[i] == undefined ? 0 : a[i]);
                if (a[i - stonesToPick[j]] == 0 || a[i - stonesToPick[j]] == 100) {
                    a[i] = 1;
                }
            }
            // else: can't pick this one!
        }
    }

    let answer = 0;
    for (let i = m; i <= a.length; i++) {
        answer += a[i] == 1 ? 1 : 0;
    }
    console.log(answer);
}

processData(`3 5 7
1 3 4
`);