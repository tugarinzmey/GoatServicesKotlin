package com.kalabekov.frontend.client
import com.kalabekov.frontend.auth.StaticTokenManager
import com.kalabekov.frontend.dao.ApiBuilder
import com.kalabekov.frontend.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object StaticClientManager {
    val ITEMS: MutableList<Client> = ArrayList()
    val token = StaticTokenManager.getToken()

    init {
        fetchDataFromServer()
    }

     fun fetchDataFromServer() {
        val apiClient = ApiBuilder.buildApiClient()
        val call = apiClient.getClients("Bearer $token")
        call.enqueue(object : Callback<List<Client>> {
            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                if (response.isSuccessful) {
                    response.body()?.let { clients ->
                        ITEMS.clear()
                        for (client in clients) {
                            addItem(client)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
            }
        })
    }

     fun addItem(item: Client) {
        ITEMS.add(item)
    }

    fun deleteItem(item: Client){
        ITEMS.remove(item)
    }
    fun updateItem(newItem: Client) {
        val index = ITEMS.indexOfFirst { it._id == newItem._id }
        if (index != -1) {
            ITEMS[index] = newItem
        }
    }

}