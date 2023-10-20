package main

import java.util.Random

class ItemRepository(var items: MutableList<Item>) {
    public fun randomItem():Item{
        val random = Random() ;
        val randomNumber = random.nextInt(this.items.size) ;
        return this.items.get(randomNumber) ;
    }
    public fun size():Int = this.items.size ;

    public fun save(item:Item):Unit {
        this.items.add(item) ;
    }
}