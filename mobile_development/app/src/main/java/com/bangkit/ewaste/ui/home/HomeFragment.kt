package com.bangkit.ewaste.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bangkit.ewaste.R
import com.bangkit.ewaste.databinding.FragmentHomeBinding
import com.bangkit.ewaste.ui.admin.AdminActivity
import com.bangkit.ewaste.ui.customviews.CustomDialogFragment
import com.bangkit.ewaste.ui.edutron.EduTronActivity
import com.bangkit.ewaste.ui.form.FormActivity
import com.bangkit.ewaste.ui.history.HistoryActivity
import com.bangkit.ewaste.ui.redeem.RedeemActivity
import com.bangkit.ewaste.utils.EcoViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel : HomeViewModel by viewModels {
        EcoViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("StringFormatMatches")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        GET DATA PROFILE
        val uuid = viewModel.getUUID()
        viewModel.getUserByUUID(uuid)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.apply {
                tvName.text = user.nama.uppercase()
                tvEwastePoint.text = getString(R.string.ecopoint, user.jmlPoint)
                tvEwasteTransaction.text = user.transaksi?.count.toString()
                if (user.role == "admin"){
                    btnAdmin.visibility = View.VISIBLE
                    labelBtnAdmin.visibility = View.VISIBLE

                    btnAdmin.setOnClickListener {
                        startActivity(Intent(requireContext(), AdminActivity::class.java))
                    }
                }
            }
        }

        binding.btnScanner.setOnClickListener {
            val dialog = CustomDialogFragment()
            dialog.show(parentFragmentManager, "customDialog")
        }
        binding.btnForm.setOnClickListener {
            FormActivity.start(requireContext(), "manual")
        }
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryActivity::class.java))
        }
        binding.btnEdutron.setOnClickListener{
            startActivity(Intent(requireContext(), EduTronActivity::class.java))
        }
        binding.btnRedeem.setOnClickListener{
            startActivity(Intent(requireContext(), RedeemActivity::class.java))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}