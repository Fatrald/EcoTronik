package com.bangkit.ewaste.ui.customviews

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bangkit.ewaste.R
import com.bangkit.ewaste.data.response.ecotronik.EcotronikResponseItem
import com.bangkit.ewaste.ui.form.FormActivityViewModel
import com.bangkit.ewaste.utils.EcoViewModelFactory
import com.bangkit.ewaste.utils.showToast

class CustomDialogManualFragment : DialogFragment() {
    private lateinit var spinner: Spinner
    private lateinit var tvValue: EditText
    private lateinit var wasteUUID : String
    private var transactionSubmitListener: TransactionSubmitListener? = null
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
            val spinnerData = mutableMapOf<String, String>()

            data.forEach { item ->
                spinnerData[item.uuidElect] = item.jenisElektronik
            }

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData.values.toTypedArray())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedKey = spinnerData.keys.toTypedArray()[position]
                    val selectedValue = spinnerData[selectedKey]
                    wasteUUID = selectedKey

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle the case when nothing is selected
                }
            }
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
            val wasteCount = tvValue.text.toString()
            val userUUID = viewModel.getUUID()
            viewModel.postTransaction("menunggu", wasteCount.toInt(), userUUID, wasteUUID )
            dismiss()
        }

        builder.setView(view)

        return builder.create()
    }

}

interface TransactionSubmitListener {
    fun onTransactionSubmitted()
}

