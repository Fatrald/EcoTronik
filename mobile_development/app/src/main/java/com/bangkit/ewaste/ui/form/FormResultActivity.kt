package com.bangkit.ewaste.ui.form

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.databinding.ActivityFormResultBinding
import com.bangkit.ewaste.utils.ConstVal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormResultActivity : AppCompatActivity() {
    private var _activityFormResultBinding : ActivityFormResultBinding? = null
    private val binding get() = _activityFormResultBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityFormResultBinding = ActivityFormResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentTimeMillis = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val formattedDate = dateFormat.format(Date(currentTimeMillis))

        binding.tvDateValue.text = formattedDate
        val intent = intent
        val wasteCount = intent.getStringExtra("wasteCount")
        val wastePoint = intent.getStringExtra("wastePoint")
        binding.tvSumValue.text = wasteCount
        binding.tvPointValue.text = "$wastePoint EP"
    }

    override fun onBackPressed() {
        MainActivity.open(this)
        finish()
    }

    companion object {
        fun start(context: Context, key : String) {
            val intent = Intent(context, FormResultActivity::class.java)
            intent.putExtra(ConstVal.KEY_FORM, key)
            context.startActivity(intent)
        }
    }
}