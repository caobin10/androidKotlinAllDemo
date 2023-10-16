package net.lrwm.zhlf.factory

import android.os.Build
import com.example.myapplication3.App
import com.example.myapplication3.greendao.DaoMaster
import com.example.myapplication3.greendao.DaoSession
import com.example.myapplication3.greendao.MyCodeDao


class DaoFactory private constructor(){//私有化构造方法

    companion object {
        val instant by lazy { DaoFactory() }
    }

    private fun getOpenHelper(dbName:String)= DaoMaster.DevOpenHelper(App.context, dbName)

    /**
     * staff数据库
     */
    private fun getSitCodeDaoSeesion(): DaoSession {
        val helper = getOpenHelper("test.db")
        val db = helper.readableDatabase
        //解决 Android9.0 找不到表
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging()
        }
        return DaoMaster(db).newSession()
    }

    /**
     * 状态code表
     */
    fun getMyCodeDao(): MyCodeDao {
        return getSitCodeDaoSeesion().myCodeDao
    }
}