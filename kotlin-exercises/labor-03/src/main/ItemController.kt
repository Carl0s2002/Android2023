package main

class ItemController(var itemService: ItemService) {
    public fun quiz(numberOfItems:Int):Unit {
        var correctAnswers = 0 ;
        val items:MutableList<Item> = itemService.selectRandomItems(numberOfItems) ;
        items.forEach {
            println(it.question);
            it.answers.forEach {
                println(it);
            }
            val answer = readln() ;
            if ( answer.toInt() == it.correct ) {
                correctAnswers++ ;
            }
        }
        println("Thanks for completing our quiz!") ;
        println("Here's your score:${correctAnswers}/${numberOfItems}") ;
    }
}