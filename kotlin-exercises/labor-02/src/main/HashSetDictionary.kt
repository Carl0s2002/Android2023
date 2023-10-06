package main

import java.io.File

class HashSetDictionary(fileName : String):IDictionary {

    val words = HashSet<String>() ;

    init {
        File(fileName).readLines().forEach{
            this.words.add(it) ;
        }
    }
    override fun add(word: String): Boolean {
        return this.words.add(word) ;
    }

    override fun find(word: String): Boolean {
        return this.words.contains(word) ;
    }

    override fun size(): Int {
        return this.words.size ;
    }
}