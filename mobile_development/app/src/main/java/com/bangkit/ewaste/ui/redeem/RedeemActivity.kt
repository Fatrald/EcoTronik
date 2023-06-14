package com.bangkit.ewaste.ui.redeem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ewaste.databinding.ActivityRedeemBinding

class RedeemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRedeemBinding
    private lateinit var viewModel: RedeemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedeemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RedeemViewModel::class.java)

        val adapter = RedeemAdapter()
        binding.rvEdutron.adapter = adapter
        binding.rvEdutron.layoutManager = LinearLayoutManager(this)

        viewModel.redeemList.observe(this) { redeemList ->
            adapter.setData(redeemList)
        }
    }
}