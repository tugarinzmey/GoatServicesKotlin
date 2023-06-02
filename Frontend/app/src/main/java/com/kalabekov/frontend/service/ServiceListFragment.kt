package com.kalabekov.frontend.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalabekov.frontend.R
import com.kalabekov.frontend.client.StaticClientManager

class ServiceListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var refresher: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_service_list, container, false)

        refresher = view.findViewById(R.id.refreshService)
        refresher.setOnRefreshListener {
            StaticServiceManager.fetchDataFromServer()
            refresher.isRefreshing = false
        }
        addButton = view.findViewById(R.id.add_service_list)
        addButton.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, ServiceFragment())
            transaction.commit()
        }
        recyclerView = view.findViewById(R.id.list_service)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ServiceRecyclerViewAdapter(StaticServiceManager.ServiceItems)
        recyclerView.adapter = adapter
        adapter.setOnClickListener(object: ServiceRecyclerViewAdapter.OnClickListener {
            override fun onClick(position: Int, model: Service) {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val serviceFragment = ServiceFragment()
                val args = Bundle()
                args.putParcelable("ServiceModel", model)
                serviceFragment.arguments = args
                transaction.replace(R.id.container, serviceFragment)
                transaction.commit()
            }
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ServiceListFragment()
    }
}