package com.kalabekov.frontend.payment

import com.kalabekov.frontend.auth.StaticTokenManager
import com.kalabekov.frontend.client.Client
import com.kalabekov.frontend.client.StaticClientManager
import com.kalabekov.frontend.dao.ApiBuilder
import com.kalabekov.frontend.service.Service
import com.kalabekov.frontend.service.StaticServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object StaticPaymentManager {
    val PaymentItems:MutableList<Payment> = ArrayList()
    val token = StaticTokenManager.getToken()
    init {
        StaticClientManager.fetchDataFromServer()
        StaticServiceManager.fetchDataFromServer()
        fetchDataFromServer()
    }

    fun fetchDataFromServer() {
        val apiPayment = ApiBuilder.buildApiPayment()
        val call = apiPayment.getPayments("Bearer $token")
        call.enqueue(object : Callback<List<Payment>> {
            override fun onResponse(call: Call<List<Payment>>, response: Response<List<Payment>>) {
                if (response.isSuccessful) {
                    response.body()?.let { payments ->
                        PaymentItems.clear()
                        for (payment in payments) {
                            addPayment(payment)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Payment>>, t: Throwable) {
            }
        })
    }
    fun addPayment(item: Payment) {
        PaymentItems.add(item)
    }
    fun deletePayment(item: Payment){
        PaymentItems.remove(item)
    }
    fun updatePayment(item: Payment){
        val index = PaymentItems.indexOfFirst { it._id == item._id }
        if(index!=-1){
            PaymentItems[index] = item
        }
    }
    fun getCLient(clientId:String):Client{
       val client = StaticClientManager.ITEMS.find { it._id == clientId }
        return client!!

    }
    fun getService(serviceId:String):Service{
        val service = StaticServiceManager.ServiceItems.find { it._id == serviceId }
        return service!!
    }
}