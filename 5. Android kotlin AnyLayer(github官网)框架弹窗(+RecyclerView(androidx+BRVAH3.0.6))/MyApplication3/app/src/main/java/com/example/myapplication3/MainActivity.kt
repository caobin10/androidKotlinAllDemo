package com.example.myapplication3

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.util.showInpDefaultDialog
import com.example.myapplication3.util.showRadListDialog
import com.example.myapplication3.util.showRadListDialog2
import kotlinx.android.synthetic.main.activity_main.*
import per.goweii.anylayer.AnyLayer
import per.goweii.anylayer.dialog.DialogLayer
import per.goweii.anylayer.widget.SwipeLayout

class MainActivity : AppCompatActivity() {

    private var dialog: DialogLayer? = null
    private var list: MutableList<MyList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDatas()
        initListener()
    }

    private fun showDialog(): DialogLayer {
        return AnyLayer.popup(button1)
            .contentView(R.layout.dialog)
            .backgroundDimDefault()
            .gravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
    }

    private fun showDialog2(): DialogLayer {
        return AnyLayer.dialog()
            .contentView(R.layout.dialog)
            .backgroundDimDefault()
            .gravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
    }

    private fun initListener() {

        tv1.setOnClickListener {
            dialog = showDialog()
            dialog!!.animStyle(DialogLayer.AnimStyle.TOP)
            dialog!!.show()
        }
        tv2.setOnClickListener {
            dialog = showDialog2()
            dialog!!.animStyle(DialogLayer.AnimStyle.BOTTOM)
            dialog!!.show()
        }
        tv3.setOnClickListener {
            dialog = showDialog()
            dialog!!.animStyle(DialogLayer.AnimStyle.LEFT)
            dialog!!.show()
        }
        tv4.setOnClickListener {
            dialog = showDialog()
            dialog!!.animStyle(DialogLayer.AnimStyle.RIGHT)
            dialog!!.show()
        }
        tv5.setOnClickListener {
            dialog = showDialog()
            dialog!!.animStyle(DialogLayer.AnimStyle.ZOOM)
            dialog!!.show()
        }
        tv6.setOnClickListener {
            dialog = showDialog()
            dialog!!.animStyle(DialogLayer.AnimStyle.ALPHA)
            dialog!!.show()
        }

        tv7.setOnClickListener {
            showRadListDialog(textView,list,SwipeLayout.Direction.TOP,DialogLayer.AnimStyle.TOP)
        }
        tv8.setOnClickListener {
            showRadListDialog2(list,SwipeLayout.Direction.BOTTOM,DialogLayer.AnimStyle.BOTTOM)
        }
        tv9.setOnClickListener {
            showRadListDialog(textView,list,SwipeLayout.Direction.LEFT,DialogLayer.AnimStyle.LEFT)
        }
        tv10.setOnClickListener {
            showRadListDialog(textView,list,SwipeLayout.Direction.RIGHT,DialogLayer.AnimStyle.RIGHT)
        }
        tv11.setOnClickListener {
            showRadListDialog(textView,list,0,DialogLayer.AnimStyle.ZOOM)
        }
        tv12.setOnClickListener {
            showRadListDialog(textView,list,0,DialogLayer.AnimStyle.ALPHA)
        }

        tv13.setOnClickListener {
            showInpDefaultDialog()
        }
    }

    //Dict实体类设置数据，可以看到这数据有规律
    private fun initDatas() {
        var sc = MyList("item1", "1");list.add(sc)
        sc = MyList("item2", "2");list.add(sc)
        sc = MyList("item3", "3");list.add(sc)
        sc = MyList("item4", "4");list.add(sc)
        sc = MyList("item5", "5");list.add(sc)
        sc = MyList("item6", "6");list.add(sc)
        sc = MyList("item7", "7");list.add(sc)
        sc = MyList("item8", "8");list.add(sc)
        sc = MyList("item9", "9");list.add(sc)
        sc = MyList("item10", "10");list.add(sc)
        sc = MyList("item11", "11");list.add(sc)
    }
}

