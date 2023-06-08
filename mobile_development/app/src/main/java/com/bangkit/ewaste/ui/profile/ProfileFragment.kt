package com.bangkit.ewaste.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.data.response.user.LoginRequest
import com.bangkit.ewaste.databinding.FragmentProfileBinding
import com.bangkit.ewaste.utils.EcoViewModelFactory

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private var mainActivity: MainActivity? = null

    private val viewModel: ProfileViewModel by viewModels {
        EcoViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser()
            mainActivity?.finishMainActivity()
        }

        val uuid = viewModel.getUUID()
        viewModel.getUserByUUID("ede675ab-dd46-4dc5-8f97-ed4f0eef60fd")

        viewModel.user.observe(viewLifecycleOwner){ userResponse ->
            binding.apply {
                tvName.text = userResponse.nama.uppercase()
                tvContentName.text = userResponse.nama.uppercase()
                tvContentAddress.text = userResponse.alamat.uppercase()
                tvContentEmail.text = userResponse.email
                tvContentTelp.text = userResponse.noTelp
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}