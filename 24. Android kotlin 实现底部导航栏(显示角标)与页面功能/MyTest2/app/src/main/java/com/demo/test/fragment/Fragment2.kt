package com.demo.test.fragment

import com.demo.test.R

class Fragment2 : BaseFragment(){
    override fun layoutRes(): Int = R.layout.fragment2
    companion object {
        fun newInstance() = Fragment2()
    }
}