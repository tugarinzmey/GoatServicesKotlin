package com.kalabekov.frontend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class ServiceFragment : Fragment() {

    private lateinit var serviceNameField: EditText
    private lateinit var servicePriceField: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_service, container, false)
        serviceNameField = view.findViewById(R.id.service_name)
        servicePriceField = view.findViewById(R.id.service_price)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ServiceFragment()
    }
}