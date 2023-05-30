package com.kalabekov.frontend.placeholder
import com.kalabekov.frontend.dao.ApiBuilder
import com.kalabekov.frontend.models.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PlaceholderContent {
    val ITEMS: MutableList<Client> = ArrayList()
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0NmI4MWJlMGJiNTNlYzUzNzkxNzkwMSIsInVzZXJuYW1lIjoiYWJvYnVzIiwiaWF0IjoxNjg1MzcxMjk4LCJleHAiOjE2ODU0NTc2OTh9.8amW10RIrhvM5TNbd9tF2r-hIUl7LkI507NmnMUAX1o"

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
            PlaceholderContent.replaceItem(index, newItem)
        }
    }
    private fun replaceItem(index: Int, newItem: Client) {
        ITEMS[index] = newItem
    }

}