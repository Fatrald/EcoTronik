package com.bangkit.ewaste.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bangkit.ewaste.MainActivity
import com.bangkit.ewaste.R
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

        binding.ibEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
        }

        val uuid = viewModel.getUUID()
        viewModel.getUserByUUID(uuid)

        viewModel.user.observe(viewLifecycleOwner){ userResponse ->
            binding.apply {
                tvName.text = userResponse.nama
                tvContentName.text = userResponse.nama
                tvContentAddress.text = userResponse.alamat
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