package com.example.myapplication3.factory

import android.os.Build
import com.example.myapplication3.App
import com.example.myapplication3.greendao.DaoMaster
import com.example.myapplication3.greendao.DaoSession
import com.example.myapplication3.greendao.DisBaseDao

class DaoFactory private constructor() {
    companion object {
        val instant by lazy { DaoFactory() }
    }

    fun getOpenHelper(dbName: String) = DaoMaster.DevOpenHelper(App.context, dbName)

    /**
     * staff数据库
     */
    fun getStaffDaoSeesion(): DaoSession {
        val helper = getOpenHelper("teststaff.db")
        val db = helper.readableDatabase
        //解决 Android9.0 找不到表
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging()
        }
        return DaoMaster(db).newSession()
    }

    /**
     * 基础信息
     */
    fun getDisBaseDao(): DisBaseDao {
        return getStaffDaoSeesion().disBaseDao
    }
}