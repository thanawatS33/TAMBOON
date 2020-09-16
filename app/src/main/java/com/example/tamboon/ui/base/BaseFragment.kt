package com.example.tamboon.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<Binding : ViewDataBinding>:Fragment() {

    protected lateinit var binding: Binding

    abstract fun getLayout(): Int

    abstract fun onViewModelBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false) as Binding
        binding.lifecycleOwner = this
        onViewModelBinding()
        return binding.root
    }

}