package com.kalabekov.frontend.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Client(override var id: String,
                  var firstName: String,
                  var lastName: String,
                  var email: String,
                  var phone: String,
                  var status: String ) : Serializable, dbModel, Parcelable {


    override fun toString(): String {
        return "$firstName"
    }
}