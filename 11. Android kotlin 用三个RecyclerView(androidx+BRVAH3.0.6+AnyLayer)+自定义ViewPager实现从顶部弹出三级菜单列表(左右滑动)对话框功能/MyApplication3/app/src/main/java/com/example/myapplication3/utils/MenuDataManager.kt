package com.example.myapplication3.utils

import android.content.Context
import com.example.myapplication3.bean.MenuData
import com.example.myapplication3.utils.MenuUtil.getPositions

/**
 * 菜单数据管理类
 */
class MenuDataManager  //构造器
{
    //使用枚举找
    enum class MenuType(  //菜单数据放在txt
        var fileName: String
    ) {
        POSITION_FUNCTION("position_function.txt");
    }

    //获取列数据
    fun getTripleColumnData(mContext: Context?, flag: Int): List<MenuData>? {
        //把所有的menu的filName给MenuUtil解析
        return getPositions(mContext!!, flag, MenuType.POSITION_FUNCTION.fileName)
    }

    companion object {
        //单例
        private var mInstance: MenuDataManager? = null
        val instance: MenuDataManager?
            get() {

                mInstance = mInstance ?: MenuDataManager()

//                if (mInstance == null) {
//                    mInstance = MenuDataManager()
//                }
                return mInstance
            }
    }
}