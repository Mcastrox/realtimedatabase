package com.example.tutoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_central.*

class CentralActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.home ->{
                replaceFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.perfil -> {
                replaceFragment(PerfilFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.search -> {
                replaceFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            else ->{
                false
            }
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(HomeFragment())
    }
    private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction= supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
}
