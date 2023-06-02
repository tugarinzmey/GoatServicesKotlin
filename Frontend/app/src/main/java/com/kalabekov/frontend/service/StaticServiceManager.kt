package com.kalabekov.frontend.service

import com.kalabekov.frontend.auth.StaticTokenManager
import com.kalabekov.frontend.dao.ApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object StaticServiceManager {

    val ServiceItems:MutableList<Service> = ArrayList()
    val token = StaticTokenManager.getToken()

    init {
        fetchDataFromServer()
    }

    fun fetchDataFromServer() {
        val apiService = ApiBuilder.buildApiService()


        val call = apiService.getServices("Bearer $token")
        call.enqueue(object : Callback<List<Service>> {
            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                if (response.isSuccessful) {
                    response.body()?.let { services ->
                        ServiceItems.clear()
                        for (service in services) {
                            addService(service)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
            }
        })
    }

    fun addService(item: Service) {
        ServiceItems.add(item)
    }
    fun deleteService(item: Service){
        ServiceItems.remove(item)
    }
    fun updateService(newService:Service){
        val index = ServiceItems.indexOfFirst { it._id == newService._id }
        if(index !=-1){
            ServiceItems[index] = newService
        }
    }
}