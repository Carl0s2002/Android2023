package main

fun main(args: Array<String>) {
    val a = 2 ;
    val b = 3 ;
    println("1)") ;
    println("$a + $b = ${sum(a , b)}") ;

    println();
    println("2)") ;
    listExercises();

    println();
    println("3)") ;
    print("Choose a number:") ;
    val number = readln().toInt() ;
    if ( primeTest(number) ) {
        print("The number $number is a prime")
    }
    else {
        print("The number $number is not a prime") ;
    }
    println() ;
    print("Choose start range:") ;
    val start = readln().toInt() ;
    print("Choose end range:") ;
    val end = readln().toInt() ;
    primesBetweenRange(start , end) ;

    println() ;
    println("4)") ;
    print("Write a message:") ;
    val msg = readln() ;
    val encodedMsg = messageCoding(msg , ::encodeMessage) ;
    print("Encoded message: $encodedMsg") ;
    println() ;
    print("Decoded message: ${messageCoding(encodedMsg , ::decodeMessage)}") ;
}

fun sum( a:Int , b:Int ):Int = a + b ;

fun listExercises() {

    val daysOfWeek = listOf("Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday" , "Saturday" , "Sunday") ;
    print("List content:") ;
    for ( day in daysOfWeek) {
        print("$day ") ;
    }

    println();
    print("List content starting with 'T':") ;
    val startWithT = daysOfWeek.filter { it.startsWith("T") } ;
    for ( day in startWithT ) {
        print("$day ") ;
    }

    println() ;
    val containsEList = daysOfWeek.filter { it.contains("e") } ;
    print("List content containing 'e':") ;
    for ( day in containsEList ) {
        print("$day ") ;
    }

    println() ;
    val length6List = daysOfWeek.filter { it.length == 6 } ;
    print("List content with length being 6:") ;
    for ( day in length6List) {
        print("$day ") ;
    }
}

fun primeTest ( number:Int ):Boolean {
    var isPrime = true ;
    for ( i in 2..number/2 ) {
        if ( number % i == 0 ) {
            isPrime = false ;
            break;
        }
    }
    return isPrime ;
}

fun primesBetweenRange(start:Int , end:Int){
    println();
    print("Prime numbers between $start and $end:") ;
    for ( number in start..end ) {
        if ( primeTest(number) ) {
            print("$number ") ;
        }
    }
}

fun encodeMessage ( input:String ):String{
    val encodedMsg = input.map { char ->
        if ( char.isLetter() ) {
            if ( char == 'z' ) 'a' else if ( char == 'Z' ) 'A' else char + 1 ;
        }
        else {
            char
        }
    }
    return encodedMsg.joinToString("") ;
}

fun decodeMessage( encodedInput:String ):String {
    val decodedMsg = encodedInput.map { char ->
        if ( char.isLetter() ) {
            if ( char == 'a' ) 'z' else if ( char == 'A' ) 'Z' else char - 1 ;
        }
        else {
            char
        }
    }
    return decodedMsg.joinToString("") ;
}

fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}