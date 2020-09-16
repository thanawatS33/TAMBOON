package com.example.tamboon.ui.charitylist

import androidx.fragment.app.Fragment
import com.example.tamboon.R
import com.example.tamboon.databinding.FragmentCharitylistBinding
import com.example.tamboon.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharityListFragment : BaseFragment<FragmentCharitylistBinding>() {

    private val vm: CharityListViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_charitylist

    override fun onViewModelBinding() {
        binding.vm = vm
    }


}