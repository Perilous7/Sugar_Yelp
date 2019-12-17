var age = 23;
function foo() {
    second();
    console.log(age);
    var age = 65;
    console.log(age);
}

function second(){
    console.log(age);
}
foo();
console.log(age);