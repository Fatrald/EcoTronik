package com.bangkit.ewaste.ui.customviews

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponseItem
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.showToast

class CustomDialogManualFragment : DialogFragment() {
    private lateinit var spinner: Spinner
    private lateinit var tvValue: EditText
    private lateinit var ecotronik: LiveData<List<EcotronikResponseItem>>
    private val viewModel: ManualFragmentViewModel by viewModels {
        EcoViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getEcotronik()
        return inflater.inflate(R.layout.fragment_custom_dialog_manual, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_custom_dialog_manual, null)

        tvValue = view.findViewById(R.id.value)
        spinner = view.findViewById(R.id.option_waste)


        viewModel.getEcotronik()
        viewModel.ecotronik.observe(this) { data ->
            val spinnerData = data.map { it.jenisElektronik }.toTypedArray()

            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        var count = tvValue.text.toString().toInt()
        view.findViewById<ImageButton>(R.id.btn_add).setOnClickListener {
            count++
            tvValue.setText(count.toString())
        }

        view.findViewById<ImageButton>(R.id.btn_minus).setOnClickListener {
            if (count != 0) {
                count--
                tvValue.setText(count.toString())
            } else {
                count = 0
                tvValue.setText("0")
            }
        }

        view.findViewById<Button>(R.id.btn_post_waste).setOnClickListener {
//            val wasteType = spinner.selectedItem.toString()
            val wasteCount = tvValue.text.toString()
//            context?.showToast("$wasteType : $wasteCount")
            context?.showToast(wasteCount)
            dismiss()
        }

        builder.setView(view)

        return builder.create()
    }

    override fun onDestroyView() {
        ecotronik.removeObservers(this)
        super.onDestroyView()
    }
}