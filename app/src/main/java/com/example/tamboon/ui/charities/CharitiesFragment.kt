package com.example.tamboon.ui.charities

import android.os.Bundle
import android.view.View
import com.example.tamboon.R
import com.example.tamboon.databinding.FragmentCharitiesBinding
import com.example.tamboon.ui.base.BaseFragment
import com.example.tamboon.ui.charities.adapter.CharitiesAdapter
import kotlinx.android.synthetic.main.fragment_charities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharitiesFragment : BaseFragment<FragmentCharitiesBinding>() {

    private val vm: CharitiesViewModel by viewModel()
    private val charitiesAdapter by lazy {
        CharitiesAdapter()
    }

    override fun getLayout() = R.layout.fragment_charities

    override fun onViewModelBinding() {
        binding.vm = vm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = charitiesAdapter
        vm.currentList.observe(viewLifecycleOwner,{
            charitiesAdapter.setData(it)
        })
    }


}