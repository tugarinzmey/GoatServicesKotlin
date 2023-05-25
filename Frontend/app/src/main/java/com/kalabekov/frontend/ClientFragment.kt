package com.kalabekov.frontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.kalabekov.frontend.models.Client

class ClientFragment() : Fragment() {

    private lateinit var nameField: EditText
    private lateinit var lastNameField: EditText
    private lateinit var emailField: EditText
    private lateinit var phoneField: EditText
    private lateinit var statusField: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val clientModel: Client? = arguments?.getParcelable("clientModel")
        val view =  inflater.inflate(R.layout.fragment_client, container, false)
        nameField = view.findViewById(R.id.client_name)
        lastNameField = view.findViewById(R.id.client_lastname)
        emailField = view.findViewById(R.id.payment_date)
        phoneField = view.findViewById(R.id.payment_quantity)
        statusField = view.findViewById(R.id.payment_amount)

        nameField.setText(clientModel?.firstName)
        lastNameField.setText(clientModel?.lastName)
        emailField.setText(clientModel?.email)
        phoneField.setText(clientModel?.phone)
        statusField.setText(clientModel?.status)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientFragment()
    }
}