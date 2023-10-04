package main

import java.time.DayOfWeek
import kotlin.random.Random

fun main(args: Array<String>) {
    //1
    val a = 2 ;
    val b = 3 ;
    println("1)") ;
    println("$a + $b = ${sum(a , b)}") ;

    //2
    println();
    println("2)") ;
    listExercises();

    //3
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

    //4
    println() ;
    println("4)") ;
    print("Write a message:") ;
    val msg = readln() ;
    val encodedMsg = messageCoding(msg , ::encodeMessage) ;
    print("Encoded message: $encodedMsg") ;
    println() ;
    print("Decoded message: ${messageCoding(encodedMsg , ::decodeMessage)}") ;

    //5
    println() ;
    println("5)") ;
    val numberList = listOf(1 , 2 , 3 , 4 , 5 , 6 , 7 , 8  , 9) ;
    print("List content: ") ;
    for ( number in numberList ) {
        print("$number ") ;
    }
    println() ;
    print("Even numbers: ") ;
    evenNumbers(numberList) ;

    //6
    println() ;
    println("6)") ;
    println("Using 'numberList' variable for this exercise");
    print("numberList content:") ;
    for ( number in numberList ) {
        print("$number ") ;
    }
    println() ;
    print("Doubled: ") ;
    doubleAndPrint(numberList) ;

    val daysOfWeek = mutableListOf("Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday" , "Saturday" , "Sunday") ;
    println() ;
    print("Days of week upper cased: ") ;
    upperCaseDaysOfWeek(daysOfWeek) ;

    println() ;
    print("Days of week first character capitalized: ") ;
    firstCharacterCapitalized(daysOfWeek) ;

    println() ;
    print("The length of days: ") ;
    lengthOfDays(daysOfWeek) ;

    println() ;
    print("The average lenght of days is ${averageLengthOfDays(daysOfWeek)}") ;

    //7
    println() ;
    println("7)") ;
    print("Removed days containing the letter n: ") ;
    daysOfWeek.removeIf{ it.contains('n')} ;
    for ( day in daysOfWeek ) {
        print("$day ") ;
    }

    println() ;
    for ( (index,day) in daysOfWeek.withIndex() ) {
        println("Item at $index is $day") ;
    }

    println();
    print("Sorted list: ") ;
    daysOfWeek.sort();
    for ( day in daysOfWeek ){
        print("$day ") ;
    }

    //8
    println();
    println("8)") ;
    val random = Random(System.currentTimeMillis()) ;
    val array = IntArray(10){random.nextInt(0 , 101)} ;
    print("Random array content: ") ;
    array.forEach { print("$it ") } ;

    println() ;
    print("Sorted: ") ;
    array.sort() ;
    array.forEach { print("$it ") } ;

    println() ;
    print("Does it contain any even numbers? ") ;
    print("${ containsEvenNumber(array) }") ;

    println() ;
    print("Are all the numbers even? ") ;
    print("${allNumberEven(array)}") ;

    println() ;
    print("Array content:") ;
    val averageOfArray = averageOfArrayNumbers(array) ;
    print("Average: $averageOfArray") ;

    println() ;
    

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

fun evenNumbers( numberList: List<Int> ) =  numberList.filter { it % 2 == 0 }.forEach{print("$it ")} ;

fun doubleAndPrint( numberList: List<Int> ) = numberList.map { it * 2 }.forEach{print("$it ")} ;

fun upperCaseDaysOfWeek( daysOfWeek: List<String> ) = daysOfWeek.map { it.uppercase() }.forEach{print("$it ")} ;

fun firstCharacterCapitalized( daysOfWeek: List<String> ) = daysOfWeek.map { print("${it[0].lowercase()} ") } ;

fun lengthOfDays( daysOfWeek: List<String> ) = daysOfWeek.map { print("$it -> ${it.length} ") } ;

fun averageLengthOfDays( daysOfWeek: List<String> ):Double {
    var averageLenght = 0.0 ;
    daysOfWeek.map {
        averageLenght += it.length ;
    }
    return averageLenght/7 ;
}

fun containsEvenNumber( array : IntArray ):Boolean = array.any{ it % 2 == 0 } ;

fun allNumberEven( array : IntArray ):Boolean = !array.any(){ it % 2 != 0 } ;

fun averageOfArrayNumbers( array : IntArray ):Double {
    var average = 0.0 ;

    array.forEach {
        print("$it ") ;
        average += it ;
    }

    return average/array.size ;
}

