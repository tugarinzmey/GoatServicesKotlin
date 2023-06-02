package com.kalabekov.frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kalabekov.frontend.client.ClientListFragment
import com.kalabekov.frontend.payment.PaymentFragment
import com.kalabekov.frontend.payment.PaymentListFragment
import com.kalabekov.frontend.service.ServiceListFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(ClientListFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Client_menu_button -> {
                    loadFragment(ClientListFragment())
                    true
                }
                R.id.Payment_menu_button -> {
                    loadFragment(PaymentListFragment())
                    true
                }
                R.id.Service_menu_button -> {
                    loadFragment(ServiceListFragment())
                    true
                }
                else -> {false}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}