package com.demo.test.fragment

import com.demo.test.R

class Fragment3 : BaseFragment(){
    override fun layoutRes(): Int = R.layout.fragment3

    companion object {
        fun newInstance() = Fragment3()
    }

}