package com.example.tamboon.ui.base

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.tamboon.R

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    protected lateinit var binding: Binding

    abstract fun getLayout(): Int

    abstract fun onViewModelBinding()

    private val progressDialog by lazy {
        ProgressDialog(context).apply {
            isIndeterminate = true
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setOnShowListener {
                setContentView(R.layout.dialog_loading)
            }
        }
    }

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

    fun showLoading() {
        progressDialog.show()
    }

    fun hideLoading() {
        progressDialog.dismiss()
    }

}