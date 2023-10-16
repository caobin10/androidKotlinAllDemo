package com.example.myapplication3

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import cn.jzvd.JzvdStd

/**
 * 这里可以监听到视频播放的生命周期和播放状态
 * 所有关于视频的逻辑都应该写在这里
 * Created by Nathen on 2017/7/2.
 */
class MyJzvdStd : JzvdStd {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    override fun init(context: Context) {
        super.init(context)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        val i = v.id
        if (i == cn.jzvd.R.id.fullscreen) {
            Log.i(TAG, "onClick: fullscreen button")
        } else if (i == cn.jzvd.R.id.start) {
            Log.i(TAG, "onClick: start button")
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        super.onTouch(v, event)
        val id = v.id
        if (id == cn.jzvd.R.id.surface_container) {
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    if (mChangePosition) {
                        Log.i(TAG, "Touch screen seek position")
                    }
                    if (mChangeVolume) {
                        Log.i(TAG, "Touch screen change volume")
                    }
                }
            }
        }
        return false
    }

    override fun getLayoutId(): Int {
        return cn.jzvd.R.layout.jz_layout_std
    }

    override fun startVideo() {
        super.startVideo()
        Log.i(TAG, "startVideo")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        super.onStopTrackingTouch(seekBar)
        Log.i(TAG, "Seek position ")
    }

    override fun gotoScreenFullscreen() {
        super.gotoScreenFullscreen()
        Log.i(TAG, "goto Fullscreen")
    }

    override fun gotoScreenNormal() {
        super.gotoScreenNormal()
        Log.i(TAG, "quit Fullscreen")
    }

    override fun autoFullscreen(x: Float) {
        super.autoFullscreen(x)
        Log.i(TAG, "auto Fullscreen")
    }

    override fun onClickUiToggle() {
        super.onClickUiToggle()
        Log.i(TAG, "click blank")
    }

    //onState 代表了播放器引擎的回调，播放视频各个过程的状态的回调
    override fun onStateNormal() {
        super.onStateNormal()
    }

    override fun onStatePreparing() {
        super.onStatePreparing()
    }

    override fun onStatePlaying() {
        super.onStatePlaying()
    }

    override fun onStatePause() {
        super.onStatePause()
    }

    override fun onStateError() {
        super.onStateError()
    }

    override fun onStateAutoComplete() {
        super.onStateAutoComplete()
        Log.i(TAG, "Auto complete")
    }

    //changeUiTo 真能能修改ui的方法
    override fun changeUiToNormal() {
        super.changeUiToNormal()
    }

    override fun changeUiToPreparing() {
        super.changeUiToPreparing()
    }

    override fun changeUiToPlayingShow() {
        super.changeUiToPlayingShow()
    }

    override fun changeUiToPlayingClear() {
        super.changeUiToPlayingClear()
    }

    override fun changeUiToPauseShow() {
        super.changeUiToPauseShow()
    }

    override fun changeUiToPauseClear() {
        super.changeUiToPauseClear()
    }

    override fun changeUiToComplete() {
        super.changeUiToComplete()
    }

    override fun changeUiToError() {
        super.changeUiToError()
    }

    override fun onInfo(what: Int, extra: Int) {
        super.onInfo(what, extra)
    }

    override fun onError(what: Int, extra: Int) {
        super.onError(what, extra)
    }
}
