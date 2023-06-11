package com.bangkit.ewaste.ui.form

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.ewaste.R
import com.bangkit.ewaste.databinding.ActivityFormResultBinding
import com.bangkit.ewaste.utils.ConstVal

class FormResultActivity : AppCompatActivity() {
    private var _activityFormResultBinding : ActivityFormResultBinding? = null
    private val binding get() = _activityFormResultBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityFormResultBinding = ActivityFormResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun start(context: Context, key : String) {
            val intent = Intent(context, FormResultActivity::class.java)
            intent.putExtra(ConstVal.KEY_FORM, key)
            context.startActivity(intent)
        }
    }
}