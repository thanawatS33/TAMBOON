package com.example.tamboon.util.extension

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("onClickAction")
fun Button.onClickAction(f: () -> Unit) {
    setOnClickListener {
        f()
    }
}
