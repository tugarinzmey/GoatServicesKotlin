package com.kalabekov.frontend.placeholder

import com.kalabekov.frontend.models.Client
import kotlin.collections.ArrayList

object PlaceholderContent {


    val ITEMS: MutableList<Client> = ArrayList()


    init {
       val client1 = Client("abcd","Mark", "Lox", "Marklox@mail.ru", "880053535", "LOX!!!!")
        val client2 = Client("abcd2","Mark", "Lox", "Marklox@mail.ru", "880053535", "LOX!!!!")
        addItem(client1)
        addItem(client2)
    }

    private fun addItem(item:Client) {
        ITEMS.add(item)
    }

}