package com.example.tamboon.ui.charities

import com.example.tamboon.R
import com.example.tamboon.databinding.FragmentCharitiesBinding
import com.example.tamboon.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharitiesFragment : BaseFragment<FragmentCharitiesBinding>() {

    private val vm: CharitiesViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_charities

    override fun onViewModelBinding() {
        binding.vm = vm
    }


}