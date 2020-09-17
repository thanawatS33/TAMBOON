package com.example.tamboon.ui.charities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tamboon.data.domain.GetCharitiesUseCase
import com.example.tamboon.data.model.response.CharityModel
import kotlinx.coroutines.launch

class CharitiesViewModel(private val getCharitiesUseCase: GetCharitiesUseCase) : ViewModel() {

    val currentList = MutableLiveData<List<CharityModel>>()

    init {
        fetchCharities()
    }

    private fun fetchCharities() {
        viewModelScope.launch {
            val result = getCharitiesUseCase()
            if (result.isSuccess) {
                currentList.value = result.data?.data
            }
        }
    }

}