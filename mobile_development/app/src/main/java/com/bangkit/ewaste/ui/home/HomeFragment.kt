package com.bangkit.ewaste.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.user.LoginRequest
import com.bangkit.ewaste.databinding.FragmentHomeBinding
import com.bangkit.ewaste.ui.customviews.CustomDialogFragment
import com.bangkit.ewaste.ui.form.FormActivity
import com.bangkit.ewaste.utils.EcoViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var loginData : LoginRequest? = null

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

//        DEFINE USER
        val uuid = viewModel.getUUID()
        loginData = viewModel.getLoginData()
        viewModel.getUserByUUID(loginData!!.email, loginData!!.password, uuid)

        viewModel.user.observe(viewLifecycleOwner){ userResponse ->
            binding.tvName.text = userResponse.nama
            binding.tvEwastePoint.text = getString(R.string.ecopoint, userResponse.jmlPoint)
        }

        binding.btnScanner.setOnClickListener {
            val dialog = CustomDialogFragment()
            dialog.show(parentFragmentManager, "customDialog")
        }
        binding.btnForm.setOnClickListener {
            FormActivity.start(requireContext(), "manual")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}