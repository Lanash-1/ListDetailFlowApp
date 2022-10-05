package com.example.listdetailflowapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.listdetailflowapp.viewmodel.FilesViewModel
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: FilesViewModel by activityViewModels()

    var date: Int = 0
    var month: Int = 0
    var year: Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DAY_OF_MONTH)

        this.date = viewModel.date
        this.month = viewModel.month
        this.year = viewModel.year

        return DatePickerDialog(requireContext(), this,year, month, date)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        viewModel.date = p3
        viewModel.month = p2 + 1
        viewModel.year = p1
    }

    override fun onCancel(dialog: DialogInterface) {
        viewModel.date = date
        viewModel.month = month
        viewModel.year = year
        super.onCancel(dialog)
    }

}