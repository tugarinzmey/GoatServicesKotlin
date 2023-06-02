package com.kalabekov.frontend.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kalabekov.frontend.R
import com.kalabekov.frontend.auth.StaticTokenManager
import com.kalabekov.frontend.client.Client
import com.kalabekov.frontend.client.StaticClientManager
import com.kalabekov.frontend.dao.ApiBuilder
import com.kalabekov.frontend.service.Service
import com.kalabekov.frontend.service.StaticServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PaymentFragment : Fragment() {

    private lateinit var clientSpinner: Spinner
    private lateinit var serviceSpinner: Spinner
    private lateinit var dateField: TextView
    private lateinit var amountField: TextView
    private lateinit var quantityField: EditText
    private lateinit var totalText:TextView

    private lateinit var deleteButton: Button
    private lateinit var editButton: Button
    private lateinit var addButton: Button

    val api = ApiBuilder.buildApiPayment()
    val token = StaticTokenManager.getToken()

    fun formatDate(date: Date): String {
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return outputFormat.format(date)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_payment, container, false)
        val paymentModel: Payment? = arguments?.getParcelable("PaymentModel")

        clientSpinner = view.findViewById(R.id.payment_client)
        val paymentClientAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, StaticClientManager.ITEMS)
        clientSpinner.adapter = paymentClientAdapter
        serviceSpinner = view.findViewById(R.id.payment_service)
        val paymentServiceAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, StaticServiceManager.ServiceItems)
        serviceSpinner.adapter = paymentServiceAdapter
        dateField = view.findViewById(R.id.payment_date)
        amountField = view.findViewById(R.id.payment_total)
        totalText = view.findViewById(R.id.totalText)

        quantityField = view.findViewById(R.id.payment_quantity)
        deleteButton = view.findViewById(R.id.delete_payment)
        editButton = view.findViewById(R.id.edit_payment)
        addButton = view.findViewById(R.id.add_payment)


        if(paymentModel!=null){
            addButton.visibility = View.GONE
            clientSpinner.setSelection(StaticClientManager.ITEMS.indexOf(StaticPaymentManager.getCLient(paymentModel.clientId)))
            serviceSpinner.setSelection(StaticServiceManager.ServiceItems.indexOf(StaticPaymentManager.getService(paymentModel.serviceId)))
            dateField.text = formatDate(paymentModel.date!!)
            amountField.text = paymentModel.total.toString()
            quantityField.setText (paymentModel.quantity.toString())

        }
        else{
            totalText.visibility = View.GONE
            dateField.visibility = View.GONE
            amountField.visibility = View.GONE
            deleteButton.visibility = View.GONE
            editButton.visibility = View.GONE
        }
        deleteButton.setOnClickListener {
            paymentModel!!._id?.let {
                val call = api.deletePayment("Bearer $token", it)
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
            StaticPaymentManager.deletePayment(paymentModel!!)
            returnToList()

        }
        editButton.setOnClickListener {
            val selectedClient: Client = clientSpinner.selectedItem as Client
            val selectedService:Service = serviceSpinner.selectedItem as Service
            val updatedPayment = Payment.createPaymentCandidate(
                paymentModel!!._id!!,
                selectedClient._id!!,
                selectedService._id!!,
                quantityField.text.toString().toInt()
            )
            val call = api.updatePayment("Bearer $token",updatedPayment)
            call.enqueue(object : Callback<Payment> {
                override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                }

                override fun onFailure(call: Call<Payment>, t: Throwable) {
                }
            })
            StaticPaymentManager.updatePayment(Payment(
                paymentModel._id,
                selectedClient._id!!,
                selectedService._id!!,
                paymentModel.date,
                quantityField.text.toString().toInt(),
                quantityField.text.toString().toInt()*selectedService.price
            ))
            returnToList()
        }
        addButton.setOnClickListener {
            val selectedClient: Client = clientSpinner.selectedItem as Client
            val selectedService:Service = serviceSpinner.selectedItem as Service
            val payment = Payment.createAddPaymentCandidate(
                selectedClient._id!!,
                selectedService._id!!,
                quantityField.text.toString().toInt() )
            val call = api.addPayment("Bearer $token",payment)
            call.enqueue(object : Callback<Payment> {
                override fun onResponse(call: Call<Payment>, response: Response<Payment>) {
                }

                override fun onFailure(call: Call<Payment>, t: Throwable) {
                }
            })
            StaticPaymentManager.fetchDataFromServer()
            returnToList()
        }


        return view
    }
    private fun returnToList() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, PaymentListFragment())
        transaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PaymentFragment()
    }
}