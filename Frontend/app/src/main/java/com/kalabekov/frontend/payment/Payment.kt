package com.kalabekov.frontend.payment

import android.os.Parcelable
import com.kalabekov.frontend.service.Service
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Payment(val _id: String?, var clientId:String, var serviceId:String, var date: Date?, var quantity: Int, var total:Int? ) :
    Parcelable {
    companion object{
        fun createAddPaymentCandidate(clientId: String,serviceId: String,quantity: Int): Payment {
            return Payment(null, clientId, serviceId,null, quantity, null)
        }
        fun createPaymentCandidate(_id:String, clientId: String, serviceId: String, quantity: Int):Payment{
            return Payment(_id, clientId, serviceId, null, quantity, null)
        }
    }


}