package com.example.tamboon.ui.charities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tamboon.data.domain.GetCharitiesUseCase
import com.example.tamboon.data.model.response.CharityModel
import com.example.tamboon.util.LoadingState
import kotlinx.coroutines.launch

class CharitiesViewModel(private val getCharitiesUseCase: GetCharitiesUseCase) : ViewModel() {

    val currentList = MutableLiveData<List<CharityModel>>()
    val loading = MutableLiveData<LoadingState>()

    init {
        fetchCharities()
    }

    private fun fetchCharities() {
        viewModelScope.launch {
            loading.value = LoadingState.LOADING
            val result = getCharitiesUseCase()
            loading.value = LoadingState.LOADED
            if (result.isSuccess) {
                currentList.value = result.data?.data
            }
        }
    }

}