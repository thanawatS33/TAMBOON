package com.example.tamboon.ui.donation

import android.os.Bundle
import android.view.View
import co.omise.android.models.CardBrand
import com.example.tamboon.R
import com.example.tamboon.databinding.FragmentDonationBinding
import com.example.tamboon.ui.base.BaseFragment
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
            setOnFocusChangeListener { _, isFocus ->
                if (!isFocus) {
                    vm.securityCode.value = securityCodeEditText.text.toString()
                }
            }
        }
    }
}