
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

fun main() {

    CreateBaseEmployee( "addCream", "freezingStage")
}

sealed class OfficeTypeClass<OfficeType1> {
    data class OfficeData<OfficeType1>(val dc: OfficeType1): OfficeTypeClass<OfficeType1>()
    /*inline fun <OfficeType2> flatmap(f: (OfficeType1) -> OfficeTypeClass<OfficeType2>): OfficeTypeClass<OfficeType2> = when (this) {
            is OfficeData -> f(dc)
    }*/
}



//
//
//
//
//
/*****************************BASIC CONTROL STATEMENTS***************************************/

// a variable must be defined by using Var or val
// val to make a thing const / final
// can I make an object in C# constant? E.g. in Java making an object final means you cant change the reference but you can change the value of a property of that object ..
// but in java if you make that object immutable you cant change the object property also ..

/*
Array / list
- Use arrayOf or listOf to initialize
- You can have array of mixed types :)
- There is an intArrayOf for array of integers // what are the other types supported like this? What about if I want to create an array of a specific type of objects?
- There is also an arrayListOf for array list..
- Println is very smart .. You can simply print the list by just passing its name ..
○ You can concatenate the lists with a + operator
- Array is immutable .. I.e. if you create one, you cannot add / edit any element of that list.. But arrayList is mutable..
- There are some other methods in list. . Like sublist etc
*/

/*
If, else, when
    - When statement doesn’t need break to come out of a case like in C#
    - When statement can return a value as well .. Unlike C#
    ○ This is sort of extension of the C# statement where we do
    § <expr>? A : B
    Just that we can have more than 1 exprs here
    - If, else statements can also return values .. Unlike C#
    - It can also use 'in' expression to test if a number is in a range .. // a minor sugarcoat
    // When can also be used in place of normal if - else statement
        //fun mysqrt(a: DC) = when {
        //    a.data >= 0 -> {     // this is a condition followed by an arrow operator followed by execution statement(s)
        //        var y = kotlin.math.sqrt(a.data)
        //        var dc = DC(y,"ok")
        //        a.data = y
        //        a.rem = "OK1"
        //        TC.DC(a)
        //    }
        //    else -> {          // this is the regular else statement followed by arrow op followed by execution stmt
        //        TC.DC(DC(0.0,"notok"))
        //    }
        //}

Loops
- For / while
- You can use 'in' keyword
    ○ To iterate through a sequence of numbers
    ○ To iterate through all the chracters in a strig  // !! For (char in "a random string"):)
- You can name loops // outerLoop@ for(count in 1..100){ for(innerCount in 1..20){if something break @outer}}
    The scenario that this can be used I think are only break and continue

*/
//
//
//
//
//
//
/*********CLASSES AND TYPES OF CLASSES************/

////// DATA CLASSES
///// THEY CANNOT BE INHERITED >> BUT THEY CAN BE CREATED ONE INSIDE THE OTHER USING COMPOSITION >>>
data class Invoice(val name: String, val PO: String, val amount: Int) {

}

////// FOR A CLASS TO BE EXTENDED IT HAS TO BE 'OPEN' CLASS OR AN ABSTRACT CLASS
/////// SO A CLASS IS BY DEFAULT SEALED IN KOTLIN
open class Person(name: String, nationality : String)
{

}

abstract class LivingOrganism(){
    abstract var name: String

    fun nameOrganism(actualName: String): Boolean{
        name = actualName
        return true
    }
}

// WHILE DEFINING CLASS ITSELF YOU CAN DEFINE PARAMS >>
// THIS DEFINES THE CTOR OF THE CLASS >> // just like javascript
// ???? but can i have additional ctors defined for the same class?
// ALSO IF YOU ADD var OR val TO THE PARAMS THEY AUTOMATICALLY BECOME FIELDS OF THE CLASS!!!!!
    private class MatchingActivity (val scenario: String, var caller: String) {
        private var activityAlias: String = ""
    }
//
//
//
//
//
//
/********************FUNCTIONS, DELEGATES, HIGHER ORDER FUNCTIONS, ETC***********************/

// USE KEYWORK fun FOR FUNCTIONS
// Can have functions outside of a class // todo think more about this
// In the args we have to specify type of an arg .. // todo so kotlin cannot be a typeless language as Jaideep was saying?

// TODO WHAT ARE MAP AND REDUCE AND FOLD FUNCTIONALITIES??

// TODO WHAT ARE THE OTHER USEFUL FUNCTIONS

// TODO UNDERSTAND VARARGS >> FUNCTIONS THAT CAN TAKE VARIABLE NUMBER OF ARGUMENTS >> JUST LIKE PRINT FUCTION IN EVERY LANGUAGE

// TODO INFIX NOTATION ... JUST FOR EASE OF CALLING THE FUNCTION

// TODO TAIL RECURSIVE FUNCTIONS

// todo know more about the scope of the functions .. in special cases like lambda in c#


// EXTENSION METHODS .. EXTENDING THE INT CLASS WITH A multi FUNCTION
// TODO HOW IS 'this' KEYWORD USED HERE?
fun Int.multiplyWith5() = this * 5


    fun callFunctionWithANamedParam()
    {
        // YOU CAN CHANGE THE ORDER OF PARAMS WHILE CALLING WITH NAMED PARAMS
        justPrintSomething(anIntegerValue = 25)
    }

    fun justPrintSomething(anIntegerValue: Int) {
        println(anIntegerValue)
    }


    // HIGHER ORDER FUNCTIONS .. JUST LIKE DELEGATES.. WHERE YOU SPECIFY THE SIGNATURE OF
    // THE FUNCTION.. YOU USE 'operation' KEYWORD FOR DEFINING DELEGATES
    // TODO can you add constraints here as well to the signature?
    fun addValue(operation:(Int,Int) -> Int):Int {
        return operation(10,20)
    }

// CONTRAVARIANCE AND COVARIANCE ARE THE SAME CONCEPTS THAT ARE DEFINED IN C# DELEGATES ..
// BASICALLY IF A DELEGATE TAKES APPLE AS IN PARAMS AND RETURNS MARKET TYPE
// YOU CAN PASS AN ACTUAL IMPLEMENTATION WHICH HAS A VARIANCE (I.E. IS DIFFERENT FROM THE ACTUAL DELEGATE TYPE)
// IN THAT YOU CAN PROVIDE A CONCRETE METHOD WHICH TAKES FRUITS AS INPUT (I.E. BASE CLASS OF APPLE) AND CAN
// RETURN SUPERMARKET (I.E DERIVED CLASS FROM MARKET). SO THAT CONCRETE METHOD WILL ACTUALLY GET APPLES AS INPUT BUT WILL
// TREAT THEM AS FRUITS IN ITS LOGIC AND RETURN A SUPERMARKET WHICH THE CALLER WILL INTERPRET AS A MARKET

//
//
//
//
//
//
/****************************OBJECT ORIENTATION CONCEPTS***************************/

// LET's DEFINE AN ABSTRACT CLASS THAT NOBODY CAN INSTANTIATE>> BUT JUST INHERIT FROM >.
// JUST LIKE JAVA
// THIS WILL SIMPLY MANDATE THAT EVERY EMPLOYEE SHOULD HAVE A NAME AND A COMPANY AND A COUNTRY
// AN ABSTRACT PROPERTY CAN RESIDE ONLY INSIDE AN ABSTRACT CLASS AND NOT INSIDE A CONCRETE CLASS
// WHICH IS THE SAME IN C# AS WELL
    enum class Country
    {
        US, UK, India
    }

    abstract class AbstractEmployee {
        abstract val name: String
        abstract var company: String
        abstract var country: Country
    }

// LET'S DEFINE AN OPEN CLASS WHICH CAN BE INHERITED
// THE PARAMS THAT WE ARE DEFINING WITH THE CLASS BECOME ITS 'PRIMARY CONSTRUCTOR'
// YOU CAN DEFINE CODE INSIDE ONE OR MORE 'init' BLOCKS WHICH WILL BE EXECUTED IN ORDER
// OF DEFINITION DURING THE CREATION OF THE OBJECT
// IF I ADD 'var' FOR A PARAM IN THE PRIMARY CTOR, THEN THAT AUTOMATICALLY BECOMES A FIELD IN MY CLASS
// I NEED TO HAVE MEMBERS FROM THE ABOVE ABSTRACT CLASS IN THIS BASE CLASS >> >SOME PARAM I AM DEFINING IN THE CTOR ITSELF WITH 'var'
// AND SOME PARAMS I AM DEFINING INSIDE THE CLASS WITH
    open class BaseEmployee(nameIn:String, override var company:String, override var country: Country, var salary: Int) : AbstractEmployee()
    {
        override val name: String = nameIn

        var alias: String = ""
        init {
            println("Creating an object of type base step. Currently nameAndStage has $alias")
        }
        init
        {
            alias = name + "@" + company + ".com";
            println("We created a String out of our params whch is $alias")
            println("Cool.. so multiple inits are executing fine .. ")
        }
    }

// LETS CREATE AN INSTANCE OF IT USING A GLOBAL FUNCTION..
    fun CreateBaseEmployee(stepName:String, stepStage:String)
    {
        // LETS CREATE A MUTABLE CLASS
        var newBaseEmployee = BaseEmployee("Pramit", "Amzn", Country.India, 1000)
        println("CreateBaseStep - created an instance of our base step object using the primary ctor. Will now access its members")
        println("The name, stage and the combined name and stage of the created base step are ${newBaseEmployee.alias}")
    }

    open class IndianEmployee(nameIn:String, override var company:String) : BaseEmployee(nameIn, company, Country.India, salary = 1000)
    {

    }

    open class USEmployee(nameIn:String, override var company:String) : BaseEmployee(nameIn, company, Country.US, salary = 1000)
    {

    }


// IN KOTLIN CLASSES DONT HAVE STATIC METHODS LIKE C# OR JAVA >>> THE STATIC METHODS SHOULD BE DEFINED GLOBALLY..





/********************************************* GENERICS **********************************/

// Generic types in Java are invariant, meaning that List<String> is not a subtype of List<Object>.
// since then I can add interpret a list of numbers as a list of objects and add a string (as an object)
// to that list.
// Basically that means if there is a generic class SalaryCalculator<T>
// then SalaryCalculator<IndiaEmployee> doesnt inherit from SalaryCalculator<AsiaEmployee>
// even though IndiaEmployee itself inherits from AsiaEmployee
// To cater to this scenario a new syntax was evolved ..
// SalaryCalculator<? Extends T> where you can specify that this would work for any class
// that inherits from that specific class
// There is a similar syntax for contravariance scenarios List<? super String>

// GENERIC CLASSES

    // Lets define a generic class..
    // This is a class which can be defined for any employee type..
    class EmployeePromotionDecider<E, P>  // E = employee type, P = promotion type .. but ideally I should put the type constraint here as well instead of just comment ..
    {
        fun IsEmployeeEligibleForPromotion(emmployee: E, promotion: P): Boolean{
            return false  // dummy implementation ..
            // Will typically a generic class be an interface or an abstract class? Since how will I define an actual method with a generic type?
            // But I can do things like create an instance of that type from the value provided.. etc ..
        }

        fun AddEmployeeToPromoList(employee: E){
            // someList.Add(employee)
            // So this is not a dummy implementation even with a generic method .. so some methods can be defined for non abstract generic classes as well ..
        }
    }

    // some generic parameter can only be 'out' .. that means they can only be returned by the methods inside that class but cant be consumed ..
    interface EmployeePromotionDecider1<out E>{  // E = lets say employee type
        // below is giving error because I am passing E in the in parameter of the function
        // fun getEmployeesForPromotion(employeeList: List<E>): List<E>

        // below is all right
        fun getEmployeesForPromotion(): List<E>

    }

    // The power of out is that now the class can be used for this scenario ..
    fun testOutClass(){
        var listOfInt: List<Int> = listOf(1,2,3)
        var listOfObjects: List<string> = listOf(1,2)
    }



//
//
//
//
//
//
/****************************** SOME USEFUL CLASSES AND FUNCTIONS **********************/


//// println used to print to the console ..
// todo how is it defined in global scope so that without importing anything I am able to use it?

// todo flatmap
// todo map
// todo reduce
// todo fold
//
//
//
//
//
//
/*********************************** ASYNC PROCESSING **********************************/

// todo deferred and future

//
//
//
//
//
//
/********************************** MONOIDS **************************************/

// I AM READING FROM https://arrow-kt.io/docs/patterns/monads/
