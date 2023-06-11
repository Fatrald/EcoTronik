package com.bangkit.ewaste.ui.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bangkit.ewaste.R
import com.bangkit.ewaste.databinding.ActivityRegistrationBinding
import com.bangkit.ewaste.utils.EcoViewModelFactory

class RegistrationActivity : AppCompatActivity() {
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpAction()
    }

    private fun setUpAction() {
        val viewModelFactory = EcoViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
        binding.signupButton.setOnClickListener(){
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val passwordConfirmation = binding.passwordConfirmationEditText.text.toString()

            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            viewModel.registerUser(name, email, password,passwordConfirmation)

            Toast.makeText(this@RegistrationActivity, getString(R.string.registration_success), Toast.LENGTH_SHORT).show()

        }
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, RegistrationActivity::class.java)
            context.startActivity(intent)
        }
    }
}