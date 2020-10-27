package com.doctor.doctorsappointment.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.doctor.doctorsappointment.R
import com.doctor.doctorsappointment.utils.PreferenceManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeScreenActivity : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener
{

    lateinit var doctorId: String
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        onClickListener()
        navigationView.setNavigationItemSelectedListener(this)
        navController = findNavController(R.id.navHostFragment)
        Navigation.setViewNavController(navigationView, navController)
    }

    private fun onClickListener() {
        imageMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun getDoctorIdFromPref() {
        val doctorId = PreferenceManager.getDoctorId()
        Log.e("Bundle", "adocId:$doctorId")
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val id: Int = menuItem.getItemId()
        if (id == R.id.updateDoctorRegistration) {
           navController?.navigate(R.id.updateDataRegistrationFragment)
        } else if (id == R.id.homeScreen) {
            navController?.navigate(R.id.homeFragment)
        }
        drawerLayout.closeDrawers()
        return true
    }
}