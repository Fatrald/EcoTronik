package com.bangkit.ewaste.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.data.response.user.LoginRequest
import com.bangkit.ewaste.databinding.ActivityLoginBinding
import com.bangkit.ewaste.ui.registration.RegistrationActivity
import com.bangkit.ewaste.utils.EcoViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel : LoginViewModel
    private var loginData : LoginRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupViewModel()
        setupAction()
        setupDefaultUserData()

        binding.tvRegistration.setOnClickListener {
            RegistrationActivity.open(this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setupDefaultUserData() {
        loginData = loginViewModel.getLoginData()
        binding.emailEditText.setText(loginData?.email)
        binding.passwordEditText.setText(loginData?.password)
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val remember = binding.checkBox.isChecked

            if (remember) {
                loginData = LoginRequest(email, password)
                loginViewModel.setLoginData(loginData!!)
            }

            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            loginViewModel.loginUser(email, password)

        }
    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

}