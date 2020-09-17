package com.example.tamboon.util.extension

import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.setCreditCardFormat() {
    addTextChangedListener(object : TextWatcher {

        private var lock = false

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // noop
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // noop
        }

        override fun afterTextChanged(s: Editable) {
            if (lock || s.length > 16) {
                return
            }
            lock = true
            for (i in 4 until s.length step 5) {
                if (s.toString()[i] != ' ') {
                    s.insert(i, " ")
                }
            }
            lock = false
        }

    })
}

fun TextInputEditText.setExpireDateFormat() {
    addTextChangedListener(object : TextWatcher {

        private var lock = false

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // noop
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            // noop
        }

        override fun afterTextChanged(s: Editable) {
            if (lock || s.length > 4) {
                return
            }
            lock = true
            if (s.length > 2 && s[2] != '/') {
                s.insert(2, "/")
            }
            lock = false
        }

    })
}