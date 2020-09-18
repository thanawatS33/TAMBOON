package com.example.tamboon.ui.donation

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tamboon.R
import com.example.tamboon.databinding.FragmentDonationBinding
import com.example.tamboon.ui.base.BaseFragment
import com.example.tamboon.util.LoadingState
import com.example.tamboon.util.extension.hideKeyboardOnEditorEnterAction
import com.example.tamboon.util.extension.setCreditCardFormat
import com.example.tamboon.util.extension.setExpireDateFormat
import kotlinx.android.synthetic.main.fragment_donation.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DonationFragment : BaseFragment<FragmentDonationBinding>() {

    private val vm: DonationViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_donation

    override fun onViewModelBinding() {
        binding.vm = vm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        vm.loading.observe(viewLifecycleOwner, {
            if (it == LoadingState.LOADING) {
                showLoading()
            } else {
                hideLoading()
            }
        })
        vm.openSuccessScreen.observe(viewLifecycleOwner, {
            findNavController(this).navigate(R.id.action_donationFragment_to_successFragment)
        })
        vm.openDialog.observe(viewLifecycleOwner, { message ->
            showDialog(message)
        })
    }

    private fun initView() {
        amountEditText.apply {
            setOnFocusChangeListener { _, isFocus ->
                if (!isFocus) {
                    vm.amount.value = amountEditText.text.toString()
                }
            }
        }
        creditCardEditText.apply {
            setCreditCardFormat()
            setOnFocusChangeListener { _, isFocus ->
                if (!isFocus) {
                    vm.cardNumber.value = creditCardEditText.text.toString()
                }
            }
        }
        cardNameEditText.apply {
            setOnFocusChangeListener { _, isFocus ->
                if (!isFocus) {
                    vm.cardName.value = cardNameEditText.text.toString()
                }
            }
        }
        expireDateEditText.apply {
            setExpireDateFormat()
            setOnFocusChangeListener { _, isFocus ->
                if (!isFocus) {
                    vm.expireDate.value = expireDateEditText.text.toString()
                }
            }
        }
        securityCodeEditText.apply {
            hideKeyboardOnEditorEnterAction()
            setOnFocusChangeListener { _, isFocus ->
                if (!isFocus) {
                    vm.securityCode.value = securityCodeEditText.text.toString()
                }
            }
        }
    }

    private fun showDialog(message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle("System alert")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}