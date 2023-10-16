package com.example.myapplication3.file

import android.content.Context
import java.io.FileOutputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream

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
//            staff.db  disabler.db 只需缓存一次
//                if (cacheVerCode > 0 && (dbName == "teststaff.db" || dbName == "disabler.db")) {
//                    continue
//                }
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
}