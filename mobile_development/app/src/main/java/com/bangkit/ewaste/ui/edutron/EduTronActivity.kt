package com.bangkit.ewaste.ui.edutron

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.ewaste.R
import com.bangkit.ewaste.databinding.ActivityEduTronBinding
import com.bangkit.ewaste.databinding.ActivityHistoryBinding

class EduTronActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEduTronBinding
    private lateinit var adapter: EduTronAdapter
    private lateinit var viewModel: EduTronViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEduTronBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = EduTronAdapter()
        binding.rvEdutron.layoutManager = LinearLayoutManager(this)
        binding.rvEdutron.adapter = adapter

        viewModel = EduTronViewModel()

        // Observe the list of EduTrons from the ViewModel
        viewModel.eduTronList.observe(this) { eduTrons ->
            adapter.setEduTrons(eduTrons)
        }

        // Fetch the data
        viewModel.fetchEduTronData()
    }
}