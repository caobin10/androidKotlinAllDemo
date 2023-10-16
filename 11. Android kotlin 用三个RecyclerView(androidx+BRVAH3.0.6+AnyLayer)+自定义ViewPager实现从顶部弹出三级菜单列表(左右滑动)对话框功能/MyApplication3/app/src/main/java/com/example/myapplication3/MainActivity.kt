package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.bean.MenuData
import com.example.myapplication3.utils.OnResultCallback
import com.example.myapplication3.utils.showRadioListDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setOnClickListener {

            showRadioListDialog(this@MainActivity,textView,object : OnResultCallback<MenuData> {
                override fun onResult(menuData: MenuData) {
                    if (menuData != null){
                        textView.text = menuData.name //选中第三个菜单后，主页面的name设置为选中的name
                    }
                }
            })
        }
    }
}
