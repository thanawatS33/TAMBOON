package com.example.tamboon.ui.charities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import co.omise.android.ui.CreditCardActivity
import co.omise.android.ui.OmiseActivity
import com.example.tamboon.BuildConfig
import com.example.tamboon.R
import com.example.tamboon.databinding.FragmentCharitiesBinding
import com.example.tamboon.ui.base.BaseFragment
import com.example.tamboon.ui.charities.adapter.CharitiesAdapter
import com.example.tamboon.util.LoadingState
import kotlinx.android.synthetic.main.fragment_charities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharitiesFragment : BaseFragment<FragmentCharitiesBinding>() {

    private val vm: CharitiesViewModel by viewModel()
    private val charitiesAdapter by lazy {
        CharitiesAdapter(this::onClickItem)
    }

    override fun getLayout() = R.layout.fragment_charities

    override fun onViewModelBinding() {
        binding.vm = vm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = charitiesAdapter
        vm.currentList.observe(viewLifecycleOwner, {
            charitiesAdapter.setData(it)
        })
        vm.loading.observe(viewLifecycleOwner, {
            if (it == LoadingState.LOADING) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun onClickItem(id: Int) {
        findNavController(this).navigate(R.id.action_charityListFragment_to_donationFragment)
    }

}