package com.example.myapplication3.utils

import android.content.Context
import com.example.myapplication3.bean.MenuData
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

/**
 * 读取menu
 */
object MenuUtil {
    //封装两个集合
    var position: MutableList<MenuData>? = null
    var positions: HashMap<Int, ArrayList<MenuData>?>? = null

    //获取单个menu
//    @JvmStatic
    fun getPositions(context: Context, flag: Int, fileName: String): List<MenuData>? {
        if (position == null) {
            initPositions(context, fileName)
        }
        return if (flag == 0) position else positions!![flag]
    }

    //根据fileName初始化单个menu的数据
    private fun initPositions(context: Context, fileName: String) {
        val industryString = readAssetsTXT(context, fileName)
        val strings = industryString!!.split(";".toRegex()).toTypedArray()
        position = ArrayList()
        positions = HashMap()
        for (i in strings.indices) {
            if (strings[i] == "") {
                continue
            }
            val items = strings[i].split(",".toRegex()).toTypedArray()
            val menuData = MenuData().apply {
                id = items[0].trim().toInt()
                name = items[1]
                flag = items[2].trim().toInt()
            }
            if (menuData.flag == 0) {
                position!!.add(menuData) //将数据添加到list里面
            } else {
                if (positions!![menuData.flag] == null) {
                    val menuDatas = ArrayList<MenuData>()
                    menuDatas.add(menuData) //再将单个menu存储在ArrayList，后续滑动回来的时候menu还在
                    positions!![menuData.flag] = menuDatas
                } else {
                    //不为空的情况下直接添加
                    positions!![menuData.flag]!!.add(menuData)
                }
            }
        }
    }

    private fun readAssetsTXT(context: Context, fileName: String): String? {
        return try {
            val assetManager = context.assets //获取assets文件下的资源
            val `is` = assetManager.open(fileName) //打开
            val bytes = ByteArray(1024)
            var leng: Int
            val baos = ByteArrayOutputStream()
            while (`is`.read(bytes).also { leng = it } != -1) {
                baos.write(bytes, 0, leng)
            }
            String(baos.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}