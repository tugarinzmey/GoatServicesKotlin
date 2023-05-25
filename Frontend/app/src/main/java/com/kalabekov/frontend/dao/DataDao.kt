package com.kalabekov.frontend.dao
import com.kalabekov.frontend.models.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject


class DataDao {
    private val url = "https://naughty-teal-cougar.cyclic.app/clients"
    private val HTTPclient = OkHttpClient()
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()
    suspend fun getClients(): List<Client> {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .build()

            val response: Response = HTTPclient.newCall(request).execute()
            val responseData = response.body?.string()
            parseClients(responseData)
        }
    }

    private fun parseClients(json: String?): List<Client> {
        val clients = mutableListOf<Client>()

        json?.let {
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getString("_id")
                val firstName = jsonObject.getString("firstName")
                val lastName = jsonObject.getString("lastName")
                val email = jsonObject.getString("email")
                val phone = jsonObject.getString("phone")
                val status = jsonObject.getString("status")

                val client = Client(id, firstName, lastName, email, phone, status)
                clients.add(client)
            }
        }

        return clients
    }

    suspend fun deleteClient(client: Client): Boolean {
        return withContext(Dispatchers.IO) {
            val urlToDelete = "${url}/${client.id}"
            val request = Request.Builder()
                .url(urlToDelete)
                .delete()
                .build()

            val response: Response = HTTPclient.newCall(request).execute()
            response.isSuccessful
        }
    }
    suspend fun updateClient(client: Client): Boolean {
        return withContext(Dispatchers.IO) {

            val json = """
            {
                "_id": "${client.id}",
                "firstName": "${client.firstName}",
                "lastName": "${client.lastName}",
                "email": "${client.email}",
                "phone": "${client.phone}",
                "status": "${client.status}"
            }
        """.trimIndent()

            val requestBody = json.toRequestBody(jsonMediaType)

            val request = Request.Builder()
                .url(url)
                .put(requestBody)
                .build()

            val response: Response = HTTPclient.newCall(request).execute()
            response.isSuccessful
        }
    }
    suspend fun addClient(client: Client): Client? {
        return withContext(Dispatchers.IO) {
            val json = """
                {
                    "firstName": "${client.firstName}",
                    "lastName": "${client.lastName}",
                    "email": "${client.email}",
                    "phone": "${client.phone}",
                    "status": "${client.status}"
                }
            """.trimIndent()

            val requestBody = json.toRequestBody(jsonMediaType)

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val response: Response = HTTPclient.newCall(request).execute()
            val responseData = response.body?.string()

            responseData?.let {
                val jsonObject = JSONObject(it)
                val id = jsonObject.getString("id")
                val firstName = jsonObject.getString("firstName")
                val lastName = jsonObject.getString("lastName")
                val email = jsonObject.getString("email")
                val phone = jsonObject.getString("phone")
                val status = jsonObject.getString("status")

                Client(id, firstName, lastName, email, phone, status)
            }
        }
    }

}