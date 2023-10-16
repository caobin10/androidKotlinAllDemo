package com.demo.test.fragment

import com.demo.test.R

class Fragment4 : BaseFragment(){
    override fun layoutRes(): Int = R.layout.fragment4
    companion object {
        fun newInstance() = Fragment4()
    }
}