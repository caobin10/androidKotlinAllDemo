package com.example.myapplication3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lxj.statelayout.StateLayout

abstract class BaseFragment : Fragment() {
    var vieww: View? = null
    var isInit = false
    var stateLayout: StateLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (vieww == null) {
            vieww = inflater.inflate(layoutId, container, false)
            stateLayout = StateLayout(requireContext()).wrap(vieww).showLoading()
        }
        return stateLayout
    }

    override fun onResume() {
        super.onResume()
        safeInit()
    }

    private fun safeInit() {
        if (userVisibleHint && vieww != null) {
            if (!isInit) {
                isInit = true
                init(vieww)
                stateLayout!!.postDelayed({ stateLayout!!.showContent() }, 300)
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        safeInit()
    }

    protected abstract val layoutId: Int
    abstract fun init(view: View?)
    fun toast(msg: String?) {
//        Toast.makeText(XPopupApp.context, msg, Toast.LENGTH_SHORT).show();
    }
}
