package com.example.tamboon.ui.donation

import android.util.Log
import androidx.lifecycle.*
import com.example.tamboon.data.domain.DonationsUseCase
import com.example.tamboon.data.domain.GetIdTokenUseCase
import com.example.tamboon.data.domain.ValidateCreditCardUseCase
import com.example.tamboon.data.model.request.DonationsRequest
import com.example.tamboon.util.LoadingState
import com.example.tamboon.util.State
import kotlinx.coroutines.launch

class DonationViewModel(
    private val validateCreditCardUseCase: ValidateCreditCardUseCase,
    private val getIdTokenUseCase: GetIdTokenUseCase,
    private val donationsUseCase: DonationsUseCase
) :
    ViewModel() {

    val amount = MutableLiveData<String>()
    val cardNumber = MutableLiveData<String>()
    val cardName = MutableLiveData<String>()
    val expireDate = MutableLiveData<String>()
    val securityCode = MutableLiveData<String>()
    val donation: () -> Unit = this::donation
    val loading = MutableLiveData<LoadingState>()
    val openSuccessScreen = MutableLiveData<String>()

    private val isAmountValid: LiveData<State> = Transformations.map(amount) {
        if (it.isNullOrEmpty()) {
            State.INVALID
        } else {
            State.VALID
        }
    }

    val amountError: LiveData<String> = Transformations.map(isAmountValid) {
        if (it == State.VALID) {
            ""
        } else {
            "Please input amount"
        }
    }

    private val isCardNumberValid: LiveData<State> = Transformations.map(cardNumber) {
        val cardNumber = it.replace(" ", "")
        validateCreditCardUseCase(cardNumber)
    }

    val cardNumberError: LiveData<String> = Transformations.map(isCardNumberValid) {
        if (it == State.VALID) {
            ""
        } else {
            "Credit card number is invalid"
        }
    }

    private val isCardNameValid: LiveData<State> = Transformations.map(cardName) {
        if (it.isNullOrEmpty()) {
            State.INVALID
        } else {
            State.VALID
        }
    }

    val cardNameError: LiveData<String> = Transformations.map(isCardNameValid) {
        if (it == State.VALID) {
            ""
        } else {
            "Please input full name"
        }
    }

    private val isExpireDateValid: LiveData<State> = Transformations.map(expireDate) {
        val split = it.split("/")
        if (split.size == 2) {
            val month = split[0]
            val year = split[1]
            if (month.length == 2 && year.length == 2) {
                State.VALID
            } else {
                State.INVALID
            }
        } else {
            State.INVALID
        }
    }

    val expireDateError: LiveData<String> = Transformations.map(isExpireDateValid) {
        if (it == State.VALID) {
            ""
        } else {
            "Date is invalid"
        }
    }

    private val isSecurityCodeValid: LiveData<State> = Transformations.map(securityCode) {
        if (it.length == 3) {
            State.VALID
        } else {
            State.INVALID
        }
    }

    val securityCodeError: LiveData<String> = Transformations.map(isSecurityCodeValid) {
        if (it == State.VALID) {
            ""
        } else {
            "CVC is invalid"
        }
    }

    val isAllFormValid = MediatorLiveData<Boolean>().apply {
        addSource(isCardNumberValid) {
            checkAllFormValid()
        }
        addSource(isCardNameValid) {
            checkAllFormValid()
        }
        addSource(isExpireDateValid) {
            checkAllFormValid()
        }
        addSource(isSecurityCodeValid) {
            checkAllFormValid()
        }
    }

    private fun checkAllFormValid() {
        isAllFormValid.value = (isCardNumberValid.value == State.VALID
                && isCardNameValid.value == State.VALID
                && isExpireDateValid.value == State.VALID
                && isSecurityCodeValid.value == State.VALID)
    }

    private fun donation() {
        viewModelScope.launch {
            loading.value = LoadingState.LOADING
            val name = cardName.value ?: ""
            val number = cardNumber.value ?: ""
            val expireDate = expireDate.value ?: ""
            val securityCode = securityCode.value ?: ""
            val result = getIdTokenUseCase(name, number, expireDate, securityCode)
            if (result.isSuccess) {
                Log.i("id token", result.data.toString())
                val id = result.data ?: ""
                val amount = amount.value?.toInt() ?: 0
                val request = DonationsRequest(name, id, amount)
                val resultDonation = donationsUseCase(request)
                loading.value = LoadingState.LOADED
                if (resultDonation.isSuccess) {
                    openSuccessScreen.value = ""
                } else {
                    Log.i("id token error", resultDonation.message.toString())
                }
            } else {
                loading.value = LoadingState.LOADED
                Log.i("id token error", result.message.toString())
            }
        }
    }

}