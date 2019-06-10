






/*********************** CURRYING IN JS *****************************/

// What I understood from the FP udemy course is that a function with 
// multiple params can be partially called passing the value of one or more params
// which can return a function taking the rest of the params later on. 
    
    // The original function without 'partial application' would be like: 
    function GiveSalaryToEmployee(employeeName, salaryAmount){
        Console.log("paying " + salaryAmount + " to " + employeeName)
    }
    // with partial application / currying it would look something like 
    function GiveSalaryToEmployeeIn2Calls(employeeName){
        return function(salaryAmount){
            // IMPORTANT - notice that the employeeName from the outer function is used here .. 
            // the outer function is almost like an object where it stores the data passed to it 
            // at some point of time.. 
            Console.log("paying " + salaryAmount + " to " + employeeName)
        }
    }
    // the caller of the curry functions would look something like .. 
    var partialFunctionWithPartialContext = GiveSalaryToEmployeeIn2Calls("Sudip")
    // now this returned inner function will remember for ever the param that the outer function was called with.. 
    // so that the inner function can be caller at a later point of time like below
    partialFunctionWithPartialContext(1000) // in january
    partialFunctionWithPartialContext(2000) // in february 
    
// TODO - so is closure a kind of currying? Though the diff is probably that in currying the context from 
// the outer function passed to the inner function is an outer function param but in closure it is a local var 
// of the outer function

// TODO - what is the main use of currying really? 
    
// Looks like currying is a first class construct in FP languages! 
