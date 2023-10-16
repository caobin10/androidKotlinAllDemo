package com.example.myapplication3.util

import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import java.util.regex.Matcher
import java.util.regex.Pattern


object KeyWordUtil {
    /**
     * 关键字高亮变色
     *
     * @param color   变化的色值
     * @param text    文字
     * @param keyword 文字中的关键字
     * @return 结果SpannableString
     */
    fun matcherSearchTitle(color: Int, text: String, keyword: String): SpannableString {
        var text = text
        var keyword = keyword
        val s = SpannableString(text)
        keyword = escapeExprSpecialWord(keyword)
        text = escapeExprSpecialWord(text)
        if (text.contains(keyword) && !TextUtils.isEmpty(keyword)) {
            try {
                val p: Pattern = Pattern.compile(keyword)
                val m: Matcher = p.matcher(s)
                while (m.find()) {
                    val start: Int = m.start()
                    val end: Int = m.end()
                    s.setSpan(
                        ForegroundColorSpan(color),
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            } catch (e: Exception) {
            }
        }
        return s
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return keyword
     */
    private fun escapeExprSpecialWord(keyword: String): String {
        var keyword = keyword
        if (!TextUtils.isEmpty(keyword)) {
            val fbsArr =
                arrayOf("\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|")
            for (key in fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key)
                }
            }
        }
        return keyword
    }
}
