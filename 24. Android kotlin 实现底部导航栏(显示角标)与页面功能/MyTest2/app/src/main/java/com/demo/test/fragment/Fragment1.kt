package com.demo.test.fragment

import com.demo.test.R

class Fragment1 : BaseFragment() {
    override fun layoutRes(): Int = R.layout.fragment1

    companion object {
        fun newInstance() = Fragment1()
    }
}