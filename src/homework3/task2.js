function Cmp(large, small) {
  if (large - small < Cmp.prototype.min) {
    Cmp.prototype.min = large - small
    Cmp.prototype.pairs = []
    Cmp.prototype.pairs.push([large, small])
  } else if (large - small == Cmp.prototype.min) {
    Cmp.prototype.pairs.push([large, small])
  }
}

Cmp.prototype.min = Number.MAX_SAFE_INTEGER;
Cmp.prototype.pairs = [];

function processData(input) {
  let [n, data] = input.split('\n');
  data = data.split(' ').map(str => +str)
  data.sort((a, b) => a - b)
  
  for(i=data.length; i>0; i--) {
    Cmp(data[i], data[i-1])
  }
  
  const answer = []
  const { pairs } = Cmp.prototype
  for(i=pairs.length - 1; i>=0; i--) {
    answer.push(pairs[i][1])
    answer.push(pairs[i][0])
  }
  
  console.log(answer.join(' '))
} 

process.stdin.resume();
process.stdin.setEncoding("ascii");
_input = "";
process.stdin.on("data", function (input) {
  _input += input;
});

process.stdin.on("end", function () {
  processData(_input);
});

