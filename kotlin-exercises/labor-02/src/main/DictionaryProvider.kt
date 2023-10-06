package main

object DictionaryProvider {
    fun createDictionary( type: DictionaryType ):IDictionary {
        return when(type){
            DictionaryType.ARRAY_LIST -> ListDictionary("Dict.txt") ;
            DictionaryType.TREE_SET -> TreeSetDictionary("Dict.txt") ;
            DictionaryType.HASH_SET -> HashSetDictionary("Dict.txt") ;
            else -> ListDictionary("Dict.txt") ;
        }
    }
}