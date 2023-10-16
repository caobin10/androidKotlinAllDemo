package com.example.myapplication3.util

import com.example.myapplication3.data.MyCode
import com.example.myapplication3.greendao.MyCodeDao
import net.lrwm.zhlf.factory.DaoFactory
import org.greenrobot.greendao.query.WhereCondition

/**
 * 获取 SitCode 节点数据
 */
fun getCodeNode(code: String?=null): MutableList<MyCode> {
    return if(code.isNullOrEmpty()){
        DaoFactory.instant.getMyCodeDao().queryBuilder().where(
            WhereCondition.StringCondition("length(code)==2")
        ).build().list()
    }else{
        DaoFactory.instant.getMyCodeDao().queryBuilder().where(
            MyCodeDao.Properties.Code.like("${code}_")
        ).build().list()
    }
}