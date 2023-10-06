package main

import java.io.File

class ListDictionary(fileName: String) :IDictionary {

    val words:MutableList<String> = mutableListOf() ;

    init {
        File(fileName).readLines().forEach{
            this.words.add(it) ;
        }
    }
    override fun add(word: String): Boolean {
        return this.words.add(word);
    }

    override fun find(word: String): Boolean {
        this.words.forEach{
            if ( it == word ) return true ;
        }
        return false ;
    }

    override fun size(): Int {
        return this.words.size ;
    }

}