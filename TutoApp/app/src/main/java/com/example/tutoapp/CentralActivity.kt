package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_central.*

class CentralActivity : AppCompatActivity() {

    private var selectedFragment: Fragment? = null
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    selectedFragment = HomeFragment()
                    replaceFragment(selectedFragment as HomeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.perfil -> {
                    selectedFragment = PerfilFragment()
                    replaceFragment(selectedFragment as PerfilFragment)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.search_tutors -> {
                    selectedFragment = SearchFragment()
                    replaceFragment(selectedFragment as SearchFragment)
                    return@OnNavigationItemSelectedListener true
                }
                else -> {
                    false
                }
            }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logUserVerify()
        setContentView(R.layout.activity_central)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var selectedItemId = savedInstanceState?.getInt("ID")

        when (selectedItemId) {
            R.id.home -> {
                selectedFragment = HomeFragment()
            }
            R.id.search_tutors -> {
                selectedFragment = SearchFragment()
            }
            R.id.perfil -> {
                selectedFragment = PerfilFragment()
            }
            else -> {
                selectedFragment = HomeFragment()
                bottomNavigation.menu.findItem(R.id.home).isChecked = true
            }
        }

        replaceFragment(selectedFragment!!)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("ID", bottomNavigation.selectedItemId)
    }
    private fun logUserVerify(){
        var uid = FirebaseAuth.getInstance().uid
        if(uid== null){
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
