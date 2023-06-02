package com.kalabekov.frontend.service

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

class ServiceFragment : Fragment() {

    private lateinit var serviceNameField: EditText
    private lateinit var servicePriceField: EditText

    private lateinit var deleteButton: Button
    private lateinit var editButton: Button
    private lateinit var addButton: Button

    val api = ApiBuilder.buildApiService()

    val token = StaticTokenManager.getToken()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_service, container, false)
        val serviceModel:Service? = arguments?.getParcelable("ServiceModel")
        val serviceId = serviceModel?._id

        serviceNameField = view.findViewById(R.id.service_name)
        servicePriceField = view.findViewById(R.id.service_price)

        deleteButton = view.findViewById(R.id.delete_service)
        editButton = view.findViewById(R.id.edit_service)
        addButton = view.findViewById(R.id.add_service)

        if(serviceModel!=null){
            addButton.visibility = View.GONE
            serviceNameField.setText(serviceModel.name)
            servicePriceField.setText(serviceModel.price.toString())
        }
        else{
            deleteButton.visibility = View.GONE
            editButton.visibility = View.GONE
        }
        deleteButton.setOnClickListener {
            serviceId?.let {
                val call = api.deleteService("Bearer $token", it)
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
            StaticServiceManager.deleteService(serviceModel!!)
            returnToList()

        }
        editButton.setOnClickListener {
           val updatedService = Service(serviceId,serviceNameField.text.toString(), servicePriceField.text.toString().toInt())
            val call = api.updateService("Bearer $token",updatedService)
            call.enqueue(object : Callback<Service> {
                override fun onResponse(call: Call<Service>, response: Response<Service>) {
                }

                override fun onFailure(call: Call<Service>, t: Throwable) {
                }
            })
            StaticServiceManager.updateService(updatedService)
            returnToList()
        }
        addButton.setOnClickListener {
            val service = Service.createWithoutId(serviceNameField.text.toString(), servicePriceField.text.toString().toInt())
            val call = api.addService("Bearer $token",service)
            call.enqueue(object : Callback<Service> {
                override fun onResponse(call: Call<Service>, response: Response<Service>) {
                }

                override fun onFailure(call: Call<Service>, t: Throwable) {
                }
            })
            StaticServiceManager.addService(service)
            returnToList()
        }


        return view
    }
    private fun returnToList() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, ServiceListFragment())
        transaction.commit()
    }
    companion object {
        @JvmStatic
        fun newInstance() = ServiceFragment()
    }
}