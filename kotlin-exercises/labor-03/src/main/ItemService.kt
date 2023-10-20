package main

class ItemService(var itemRepository: ItemRepository) {
    public fun selectRandomItems(itemsNumber:Int):MutableList<Item>{
        var items:MutableList<Item> = mutableListOf() ;
        for ( i in 1..itemsNumber ) {
            while ( true ) {
                val newItem = itemRepository.randomItem() ;
                if ( !items.contains(newItem) ){
                    items.add(newItem);
                    break ;
                }
            }
        }
        return items ;
    }
}