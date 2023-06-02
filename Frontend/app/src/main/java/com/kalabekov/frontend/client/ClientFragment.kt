package com.kalabekov.frontend.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.kalabekov.frontend.R
import com.kalabekov.frontend.auth.StaticTokenManager
import com.kalabekov.frontend.dao.ApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientFragment() : Fragment() {

    private lateinit var nameField: EditText
    private lateinit var lastNameField: EditText
    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText
    private lateinit var statusField: EditText

    private lateinit var deleteButton: Button
    private lateinit var editButton: Button
    private lateinit var addButton: Button



    val api = ApiBuilder.buildApiClient()

    val token = StaticTokenManager.getToken()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val clientModel: Client? = arguments?.getParcelable("clientModel")
        val clientId = clientModel?._id
        val view =  inflater.inflate(R.layout.fragment_client, container, false)
        nameField = view.findViewById(R.id.client_name)
        lastNameField = view.findViewById(R.id.client_lastname)
        emailField = view.findViewById(R.id.client_email)
        phoneField = view.findViewById(R.id.client_phone)
        statusField = view.findViewById(R.id.client_status)

        editButton = view.findViewById(R.id.edit_client)
        deleteButton = view.findViewById(R.id.delete_client)
        addButton = view.findViewById(R.id.add_client)

        if(clientModel!=null){
            addButton.visibility = View.GONE
            nameField.setText(clientModel?.firstName)
            lastNameField.setText(clientModel?.lastName)
            emailField.setText(clientModel?.email)
            phoneField.setText(clientModel?.phone)
            statusField.setText(clientModel?.status)
        }
        else{
            deleteButton.visibility = View.GONE
            editButton.visibility = View.GONE
        }



        deleteButton.setOnClickListener {
            clientId?.let {
                val call = api.deleteClient("Bearer $token", it)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                        } else {
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                    }
                })
            }
            StaticClientManager.deleteItem(clientModel!!)
            returnToList()
        }


        editButton.setOnClickListener {
            val updatedClient = clientId?.let { it1 ->
                Client(
                    it1,
                    nameField.text.toString(),
                    lastNameField.text.toString(),
                    emailField.text.toString(),
                    phoneField.text.toString(),
                    statusField.text.toString()
                )
            }

            val call = api.updateClient("Bearer $token", updatedClient)
            call.enqueue(object : Callback<Client> {
                override fun onResponse(call: Call<Client>, response: Response<Client>) {
                }

                override fun onFailure(call: Call<Client>, t: Throwable) {
                }
            })

            if (updatedClient != null) {
                StaticClientManager.updateItem(updatedClient)
            }

            returnToList()
        }

        addButton.setOnClickListener {
            val firstName = nameField.text.toString()
            val lastName = lastNameField.text.toString()
            val email = emailField.text.toString()
            val phone = phoneField.text.toString()
            val status = statusField.text.toString()

            val client = Client.createWithoutId(firstName, lastName, email, phone, status)

            val call = api.addClient("Bearer $token", client)
            call.enqueue(object : Callback<Client> {
                override fun onResponse(call: Call<Client>, response: Response<Client>) {
                    if (response.isSuccessful) {
                        val addedClient = response.body()
                        if (addedClient != null) {
                            StaticClientManager.addItem(addedClient)

                        }
                    } else {

                    }
                }

                override fun onFailure(call: Call<Client>, t: Throwable) {
                }
            })
            returnToList()
        }



        return view
    }

    private fun returnToList() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, ClientListFragment())
        transaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientFragment()
    }
}