package com.kalabekov.frontend.payment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalabekov.frontend.R
import com.kalabekov.frontend.service.Service
import com.kalabekov.frontend.service.ServiceFragment
import com.kalabekov.frontend.service.ServiceRecyclerViewAdapter
import com.kalabekov.frontend.service.StaticServiceManager


class PaymentListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var refresher: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment_list, container, false)
        recyclerView = view.findViewById(R.id.list_payment)
        addButton = view.findViewById(R.id.add_payment_list)
        refresher = view.findViewById(R.id.refreshPayment)
        refresher.setOnRefreshListener {
            StaticPaymentManager.fetchDataFromServer()
            refresher.isRefreshing = false
        }
        val adapter = PaymentRecyclerViewAdapter(StaticPaymentManager.PaymentItems)
        recyclerView.adapter = adapter
        adapter.setOnClickListener(object: PaymentRecyclerViewAdapter.OnClickListener {
            override fun onClick(position: Int, model: Payment) {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val PaymentFragment = PaymentFragment()
                val args = Bundle()
                args.putParcelable("PaymentModel", model)
                PaymentFragment.arguments = args
                transaction.replace(R.id.container, PaymentFragment)
                transaction.commit()
            }
        })
        addButton.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, PaymentFragment())
            transaction.commit()
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) = PaymentListFragment()
    }
}