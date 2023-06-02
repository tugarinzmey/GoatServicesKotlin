package com.kalabekov.frontend.service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Service(val _id: String?, val name: String, val price: Int) : Parcelable {
    companion object{
        fun createWithoutId(name: String, price: Int):Service{
            return Service(null, name, price)
        }
    }

    override fun toString(): String {
        return name
    }


}