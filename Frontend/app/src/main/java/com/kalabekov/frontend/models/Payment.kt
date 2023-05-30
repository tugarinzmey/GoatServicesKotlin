package com.kalabekov.frontend.models

import java.util.*

class Payment( val _id: String, var client: Client, var service: Service, var date: Date, var quantity: Int, var amount:Int )   {

    fun getClientName() :String{
        return this.client.firstName
    }
    fun getServiceName():String{
        return this.service.name
    }
}