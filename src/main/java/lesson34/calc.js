let queue = [];
let input = 0;
const calc = document.querySelector('.calc');
const keys = calc.querySelector('.buttons');

keys.addEventListener('click', event => {
    if (!event.target.closest('button')) return;
    const key = event.target;
    const keyValue = key.textContent;
    const { type } = key.dataset;

    if (type === 'clear') {
        queue = [];
        input = 0;
        document.getElementById("answer").innerHTML = "0";
    }

    if (type === 'number') {
        if (document.getElementById("answer").innerHTML === "ERROR" || (document.getElementById("answer").innerHTML === "0")) {
            document.getElementById("answer").innerHTML = "";
        }
        if (!(type === 'decimal') || !input.match(/[.]/)) {
            input += keyValue;
            document.getElementById("answer").innerHTML += keyValue;
        }
    }

    if (type === 'decimal') {
        if (!input.toString().includes('.')) {
            input += '.';
            document.getElementById("answer").innerHTML += '.';
        }
    }

    if (type === 'operator') {
        if (input !== 0 && input !== "-") {
            input = parseFloat(input);
            addToQueue(input);
            addToQueue(keyValue);
            document.getElementById("answer").innerHTML += keyValue;
            input = 0;
        }
        if (keyValue === '-' && isNaN(queue[0]) && input !== "-") {
            input = "-";
            document.getElementById("answer").innerHTML = "-";
        }
    }

    if (type === 'equal') {
        if (input !== 0) {
            input = parseFloat(input);
            addToQueue(input);
        }

        const firstNumber = parseFloat(queue[0]);
        const secondNumber = parseFloat(queue[2]);
        let answer = '';
        let dividedByZero = 0;

        switch (queue[1]) {
            case '+':
                answer = firstNumber + secondNumber;
                break;
            case '-':
                answer = firstNumber - secondNumber;
                break;
            case '/':
                if (firstNumber === 0 || secondNumber === 0) {
                    dividedByZero = 1;
                } else {
                    answer = firstNumber / secondNumber;
                }
                break;
            case 'x':
                answer = firstNumber * secondNumber;
                break;
        }

        if (dividedByZero === 1) {
            clearAll();
            document.getElementById("answer").innerHTML = "ERROR";
        } else {
            if (typeof answer === 'number') {
                answer = answer.toFixed(5);
                answer = parseFloat(answer);
            }
            document.getElementById("answer").innerHTML = answer;
            input = answer;
            queue = [];
        }
    }

    function addToQueue(input) {
        queue.push(input);
    }

    function clearAll() {
        queue = [];
        input = 0;
    }
});
