package main

fun main(args: Array<String>) {
    val items = mutableListOf(
        Item(
            answers = listOf("0. New York", "1. Los Angeles", "2. Chicago", "3. Houston"),
            correct = 0,
            question = "Which city is known as the 'Big Apple'?"
        ),
        Item(
            answers = listOf("0. Python", "1. Java", "2. C++", "3. Kotlin"),
            correct = 3,
            question = "Which programming language was developed by JetBrains?"
        ),
        Item(
            answers = listOf("0. Mount Everest", "1. K2", "2. Makalu", "3. Kangchenjunga"),
            correct = 0,
            question = "Which is the highest mountain in the world?"
        ),
        Item(
            answers = listOf("0. Red", "1. Green", "2. Blue", "3. Yellow"),
            correct = 2,
            question = "Which color is associated with the sky on a clear day?"
        ),
        Item(
            answers = listOf("0. Earth", "1. Mars", "2. Jupiter", "3. Saturn"),
            correct = 2,
            question = "Which planet is known as the 'Gas Giant'?"
        )
    )

    val itemRepository = ItemRepository(items) ;
    val itemService = ItemService(itemRepository) ;
    val itemController = ItemController(itemService) ;
    itemController.quiz(3) ;

}