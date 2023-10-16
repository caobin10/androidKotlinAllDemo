package com.example.myapplication3.file

import android.content.Context
import java.io.InputStream
import java.io.OutputStream
import java.io.File
import java.io.FileOutputStream

object File {

    /**
     * 拷贝assets 文件到 databases 目录
     */
    fun copyDbFileFromAsset(context: Context, dbMap: Map<String, Boolean?>) {

        var `is`: InputStream
        var os: OutputStream
        val dbDir = File(context.filesDir.parent + "/databases")
        if (!dbDir.exists()) {
            dbDir.mkdir()
        }
        var outDbFile: File

//        val verCode = getVersionCode()
//        val cacheVerCode = getCacheVerCode()

        for ((dbName) in dbMap) {
//            if (cacheVerCode < verCode) {

            outDbFile = File(dbDir, dbName)
            try {
                os = FileOutputStream(outDbFile)
                val buffer = ByteArray(1024)
                var length: Int
                `is` = context.assets.open("db/$dbName")
                while (`is`.read(buffer).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
                `is`.close()
                os.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
//        setCacheVerCode(verCode)
//
//        if (cacheVerCode < AppConfig.CLEAR_DATA_VERSION) {
//            //指定 某个版本 删除本地数据
//            clearData()
//        } else {
//            //删除不在地区范围的数据
//            deleteDbData()
//        }


    //特殊处理  DisDetail 增加字段serFundSource
//        if (cacheVerCode < 5001) {
//            val helper = DaoFactory.instant.getOpenHelper("teststaff.db")
//            val db = helper.writableDatabase
//            try {
//                db.execSQL("ALTER TABLE DisDetail ADD 'serFundSource' TEXT ")
//            } catch (e: Exception) {
//            }
//        }
//    }
}