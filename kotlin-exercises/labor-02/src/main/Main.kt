package main

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Random

enum class DictionaryType {
    ARRAY_LIST ,
    TREE_SET ,
    HASH_SET
}
fun main(args: Array<String>) {
    //1
    println("1)") ;
    val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET);
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

    //2
    println() ;
    println("2)") ;
    "John Smith".monogram() ;
    println() ;
    joinStrings(listOf("apple" , "pear" , "melon")) ;
    print(longestString(listOf("apple" , "pear" , "strawberry" , "melon" ))) ;

    //3
    println() ;
    println("3)") ;
    val date = Date() ;
    println("Date: ${date.year}.${date.month}.${date.day}") ;
    println("Is it a leap year? ${date.isLeapYear()}" ) ;
    println("Is it a valid date? ${date.isValidDate()}") ;

    val validDates:MutableList<Date> = mutableListOf<Date>()  ;
    val random = Random() ;
    while ( validDates.size < 10 ) {
        val year = random.nextInt(2200) ;
        val month = random.nextInt(12) + 1 ;
        val day = random.nextInt(31)+1 ;
        val validDate = Date(year , month , day) ;
        if ( validDate.isValidDate() ) {
            validDates.add(validDate) ;
        }
    }

    println("Valid dates generated: ${validDates}") ;

    validDates.sort() ;
    println("Sorted valid dates:")
    validDates.forEach { date ->
        println("${date.year}.${date.month}.${date.day}")
    }

    val reversedDates = validDates.reversed() ;
    println("Reversed valid dates:") ;
    reversedDates.forEach { date ->
        println("${date.year}.${date.month}.${date.day}") ;
    }

    val customDateComparator = Comparator<Date> { date1, date2 ->
        if ( date1.day != date2.day ) {
            date1.day - date2.day ;
        }
        else {
            date1.year - date2.year
        }
    }

    validDates.sortWith(customDateComparator) ;
    println("Custom sort: ") ;
    validDates.forEach { date ->
        println("${date.year}.${date.month}.${date.day}") ; }

}

fun String.monogram() = split(" ").map { print(it[0]) } ;

fun joinStrings(list:List<String>) = println(list.joinToString("#")) ;

fun longestString( list:List<String> ) = list.max() ;

fun Date.isLeapYear():Boolean = year % 4 == 0 ;

fun Date.isValidDate(): Boolean {
    return year >= 0 && year < 2024 && month in 1..12 && day in 1..31
}
