package com.bangkit.ewaste.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.user.UpdateUserRequest
import com.bangkit.ewaste.databinding.FragmentEditProfileBinding
import com.bangkit.ewaste.utils.EcoViewModelFactory

class EditProfileFragment : Fragment() {

    private var _binding : FragmentEditProfileBinding? = null

    private val binding get() = _binding!!

    private val viewModel : EditProfileViewModel by viewModels {
        EcoViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uuid = viewModel.getUUID()
        viewModel.getUserByUUID(uuid)

        viewModel.user.observe(viewLifecycleOwner){ user ->
            binding.apply {
                tvContentName.setText(user.nama)
                tvContentAddress.setText(user.alamat)
                tvContentEmail.setText(user.email)
                tvContentTelp.setText(user.noTelp)
            }
        }

        binding.btnSubmit.setOnClickListener {
            val nama = binding.tvContentName.text.toString()
            val alamat = binding.tvContentAddress.text.toString()
            val email = binding.tvContentEmail.text.toString()
            val no_telp = binding.tvContentTelp.text.toString()
            val updateUserRequest = UpdateUserRequest(
                nama, alamat, email, no_telp
            )
            findNavController().navigate(R.id.action_editProfileFragment_to_navigation_profile)
            viewModel.updateUser(uuid, updateUserRequest)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}