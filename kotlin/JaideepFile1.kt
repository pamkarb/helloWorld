// THERE ARE 3 PARTS THAT I CAN SEE IN THE LOGIC >>>
// 1 = THE MODEL / DATA CLASSES
// 2 = THE ACTUAL FUNCTIONS WHICH HAVE THE LOGIC >> WHICH SHOULD BE IDEALLY PURE >>>
// 3 = THE TYPE CLASSES WHICH ARE THE ORCHESTRATORS I.E. BIND ONE OR MORE ACTUAL FUNCTIONS TO THE ACTUAL MODEL OR DATA CLASSES.


/*************** MODULE 1 - BELOW ARE DATA CLASSES - WHICH ARE THE MODELS ********************/
data class NumberDataClass (var data: Double, var rem: String)

// invoice item data class
data class InvoiceItemCore (val invoiceItemId: String, val originalCost: Int, var POId: Int){
    var revisedCost:Int = originalCost
}
data class InvoiceCore(val invoiceId: String, val invoiceItems : List<InvoiceItemCore>){
    var status: String = "open" // should be enum.. but this rough
}


/************************* MODULE 2 - BELOW ARE THE ACTUAL IMPLEMENTATIONS WHICH ARE HARD WIRED TO THE ACTUAL DATA CLASSES **********************/
fun mysqrt(a: NumberDataClass) = when {
    // if num greater than zero then calculate sqrt else return invalid value..
    // todo MINOR - why cant we use exceptions in our methods instead of storing invalid value in results?
    a.data >= 0 -> {
        var y = kotlin.math.sqrt(a.data)
        var dc = NumberDataClass(y,"ok")
        a.data = y          // todo MINOR - we created a new var but not using it? and we are mutating the old var
        a.rem = "OK1"
        GenericActivityTypeClass.ActivityInnerData(a)  // then I am instantiating a new inner data class
    }
    else -> {
        GenericActivityTypeClass.ActivityInnerData(NumberDataClass(0.0,"notok"))
    }
}

fun myinv(a: NumberDataClass) = when {
    // do basic inverse if the data > 0 and create
    a.data > 0 -> {
        var y: Double = 1/a.data
        a.data = y
        a.rem = a.rem + "OK2"
        GenericActivityTypeClass.ActivityInnerData(a)
    }
    else -> {
        GenericActivityTypeClass.ActivityInnerData(NumberDataClass(0.0,"notok"))
    }
}

/************************** MODULE 3 - THE TYPE CLASSES WHICH SHOULD BIND THE MODELS TO THE DATA CLASSES ***************************/

// the type class basically contains data of any random type.. and enables any third party to apply any method on that data ..but constrains the method to be of a certain signature
sealed class GenericActivityTypeClass<A> {
    data class ActivityInnerData<A>(val dc: A): GenericActivityTypeClass<A>()   // todo DISCUSS: Why does the activity inner data inherit the activity type class?
    inline fun <B> ApplyActionOnInnerData(delegateFunction: (A) -> GenericActivityTypeClass<B>): GenericActivityTypeClass<B> = when (this) {
        // todo MINOR - why are there 2 diff types A and B here? Our callers anyway always have both the types same.. Is it a monad thingie?
        is ActivityInnerData -> delegateFunction(dc)  // TODO DISCUSS when will this condition ever be false?
    }
}

// APPLY IS A GENERIC UTILITY METHOD TO APPLY MULTIPLE METHODS OF THE SAME SIGNATURE ON SOME INPUT DATA CONTAINED IN A TYPE CLASS >>
// todo - MINOR instead of taking a generic type class as param, we can define this method inside the generic type class to operate on its inner data directly ..
// todo - MINOR here the input functions have A = B though/...
// todo - MINOR can we name a delegate in kotlin .. like ActivityTypeClassDelegate = <T> -> GenericActivityTypeClass<T> so that we enforce the input and return types to be same..
fun <T> apply(input: GenericActivityTypeClass<T>, fns: List<(T) -> GenericActivityTypeClass<T>>): GenericActivityTypeClass<T> =
    fns.fold(input, { inp, fn -> inp.ApplyActionOnInnerData(fn) } )
//    fns.fold(input) { inp, fn -> inp.ApplyActionOnInnerData(fn) }

fun main(args: Array<String>) {

    var mlFun: List<(NumberDataClass) -> GenericActivityTypeClass<NumberDataClass>> = mutableListOf()
    // todo DISCUSS why are our signatures from input data -> type class, and not input data type -> result data type?
    mlFun += ::mysqrt
    mlFun += ::myinv
    // todo DISCUSS in our actual large scale what will the logic to DECIDE which series of functions should be applied to a given incoming data?

    // Input
    var inp: GenericActivityTypeClass<NumberDataClass>
    // create an instance of the data class ..
    var dc = NumberDataClass(-100.0,"")
    // create an instance of the type class .. todo DISCUSS - how is this any diff from creating an instance of an object?
    inp = GenericActivityTypeClass.ActivityInnerData(dc)
    println(inp.dc.data)
    println(inp.dc.rem)

    // Composition
    val out = apply(inp,mlFun) as GenericActivityTypeClass.ActivityInnerData
//    var out = inp.ApplyActionOnInnerData(::mysqrt).ApplyActionOnInnerData(::myinv) as TC.DC
    // Output
    println(out.dc.data)
    println(out.dc.rem)

}





