package com.example.myapplication3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.et_user_name
import kotlinx.android.synthetic.main.activity_main.til_name

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_user_name.setText("123")
        if (et_user_name.text.toString().trim().isNotEmpty()) et_user_name.requestFocus()
        et_user_name.addTextChangedListener(mTextWatcher)
    }

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            if (til_name.editText!!.text.length > til_name.counterMaxLength) {
                til_name.error = "输入内容超过上限"
            } else {
                til_name.error = null
            }
        }
    }
}