package com.example.myapplication3.util

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.inputmethod.InputMethodManager


class KeyboardUtil(private val mContext:Activity) {

//    private var mContext: Activity?=null
    /**
     * 虚拟键盘高度
     */
    var virtualKeyboardHeight = 0

    /**
     * 屏幕高度
     */
    var screenHeight: Int

    /**
     * 屏幕6分之一的高度，作用是防止获取到虚拟键盘的高度
     */
    var screenHeight6: Int
    var rootView: View

    init {


        /**
         * 获取屏幕的高度,该方式的获取不包含虚拟键盘
         */
        screenHeight = mContext.resources.displayMetrics.heightPixels
        screenHeight6 = screenHeight / 6
        rootView = mContext.window.decorView
    }

    /**
     * @param listener
     */
    fun setOnKeyboardChangeListener(listener: KeyboardChangeListener?) {
        //当键盘弹出隐藏的时候会 调用此方法。
        rootView.viewTreeObserver.addOnGlobalLayoutListener(OnGlobalLayoutListener {
            /**
             * 回调该方法时rootView还未绘制，需要设置绘制完成监听
             */
            rootView.post(Runnable {
                val rect = Rect()
                /**
                 * 获取屏幕底部坐标
                 */
                rootView.getWindowVisibleDisplayFrame(rect)
                /**
                 * 获取键盘的高度
                 */
                val heightDifference: Int = screenHeight - rect.bottom
                if (heightDifference < screenHeight6) {
                    virtualKeyboardHeight = heightDifference
//                    if (listener != null) {
//                        listener.onKeyboardHide();
//                    }
                    listener?.onKeyboardHide()
                } else {
                    listener?.onKeyboardShow(heightDifference - virtualKeyboardHeight)
                }
            })
        })
    }

    /**
     * 软键盘状态切换监听
     */
    interface KeyboardChangeListener {
        /**
         * 键盘弹出
         *
         * @param keyboardHight 键盘高度
         */
        fun onKeyboardShow(keyboardHight: Int)

        /**
         * 键盘隐藏
         */
        fun onKeyboardHide()
    }

    companion object {
        /**
         * 显示软键盘
         *
         * @param context 当前Activity
         */
        fun showSoftInput(context: Context) {
            val inputMethodManager: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        /**
         * 隐藏软键盘
         *
         * @param activity 当前Activity
         */
        fun hideSoftInput(activity: Activity) {
            val inputMethodManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                activity.window.decorView.windowToken, 0
            )
        }

        fun showSoftInput(context: Context, view: View?) {
            val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
            //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }

        fun hideSoftInput(context: Context, view: View) {
            val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0) //强制隐藏键盘
        }

        fun isShowSoftInput(context: Context): Boolean {
            val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            //获取状态信息
            return imm.isActive() //true 打开
        }

        /**
         * 是否显示，显示则关闭，没显示则显示
         *
         * @param context
         */
        fun isShow(context: Context) {
            val imm: InputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}


