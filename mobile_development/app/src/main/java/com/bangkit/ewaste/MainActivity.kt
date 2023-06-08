package com.bangkit.ewaste

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit.ewaste.databinding.ActivityMainBinding
import com.bangkit.ewaste.ui.home.HomeFragment
import com.bangkit.ewaste.ui.login.LoginActivity
import com.bangkit.ewaste.utils.SharedPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        supportActionBar?.hide()
//
//        navView.setupWithNavController(navController)
        isLogin()
        setupView()

    }
    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun isLogin(){
        val sharedPref = SharedPreferences.initPreference(this, "SignIn")
        var token = sharedPref.getString("token","")

        if(token ==""){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
    }

}