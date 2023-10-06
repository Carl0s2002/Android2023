package main

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar

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
    


}

fun String.monogram() = split(" ").map { print(it[0]) } ;

fun joinStrings(list:List<String>) = println(list.joinToString("#")) ;

fun longestString( list:List<String> ) = list.max() ;

fun Date.isLeapYear():Boolean = year % 4 == 0 ;

