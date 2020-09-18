package com.example.tamboon.ui.success

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.tamboon.R
import com.example.tamboon.databinding.FragmentSuccessBinding
import com.example.tamboon.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_success.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuccessFragment : BaseFragment<FragmentSuccessBinding>() {

    private val vm: SuccessViewModel by viewModel()

    override fun getLayout() = R.layout.fragment_success

    override fun onViewModelBinding() {
        binding.vm = vm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonClose.setOnClickListener {
            findNavController(this).popBackStack(R.id.charityListFragment, false)
        }
    }


}