package com.kalabekov.frontend

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalabekov.frontend.models.Client
import com.kalabekov.frontend.placeholder.PlaceholderContent

class ClientList : Fragment() {

        private lateinit var recyclerView: RecyclerView
        private lateinit var addButton: Button
        private lateinit var refresher:SwipeRefreshLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_client_list, container, false)

        refresher = view.findViewById(R.id.refreshClients)
        refresher.setOnRefreshListener {
            PlaceholderContent.fetchDataFromServer()
            refresher.isRefreshing = false
        }

        addButton = view.findViewById(R.id.add_client_list)
        addButton.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, ClientFragment())
            transaction.commit()
        }

        recyclerView = view.findViewById(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerViewAdapter(PlaceholderContent.ITEMS)
        recyclerView.adapter = adapter
        adapter.setOnClickListener(object: RecyclerViewAdapter.OnClickListener{
            override fun onClick(position: Int, model: Client) {
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                val clientFragment = ClientFragment()
                val args = Bundle()
                args.putParcelable("clientModel", model)
                clientFragment.arguments = args
                transaction.replace(R.id.container, clientFragment)
                transaction.commit()
            }
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = ClientList()
    }
}