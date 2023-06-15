package com.bangkit.ewaste.ui.redeem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ewaste.R
import com.bangkit.ewaste.adapter.RedeemAdapter
import com.bangkit.ewaste.databinding.ActivityRedeemBinding
import com.bangkit.ewaste.utils.EcoViewModelFactory

class RedeemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRedeemBinding
    private lateinit var viewModel: RedeemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedeemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

        val adapter = RedeemAdapter(viewModel)
        binding.rvEdutron.layoutManager = LinearLayoutManager(this)
        binding.rvEdutron.adapter = adapter

        val uuid = viewModel.getUUID()
        viewModel.getUserByUUID(uuid)
        viewModel.user.observe(this){ user ->
            binding.pointValue.text = getString(R.string.point_value, user.jmlPoint.toString())
        }

    }

    private fun setupViewModel() {
        val viewModelFactory = EcoViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[RedeemViewModel::class.java]
    }
}