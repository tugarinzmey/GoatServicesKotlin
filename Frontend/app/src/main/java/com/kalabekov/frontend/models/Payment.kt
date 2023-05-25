package com.kalabekov.frontend.models

import java.util.*

class Payment(override val id: String, var client: Client, var service: Service, var date: Date, var quantity: Int, var amount:Int ) : dbModel {

    fun getClientName() :String{
        return this.client.firstName
    }
    fun getServiceName():String{
        return this.service.name
    }
}