package com.example.myapplication3.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication3.R
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.item_banner.view.*

class RvAdapter(layoutResId: Int = R.layout.item_banner) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {

    override fun convert(holder: BaseViewHolder, item: String) {

        holder.itemView.run {

            textView.text = "第${holder.adapterPosition}个位置↓"

            val banners = mutableListOf<Int>()
            banners.add(R.mipmap.banner1)
            banners.add(R.mipmap.banner2)
            banners.add(R.mipmap.banner3)
            val arrayTitle = arrayListOf<String>("第一张标题", "第二张标题", "第三张标题")
            banner.run {

                //设置内置样式，共有六种可以点入方法内逐一体验使用。
                if (item == "style") {
                    //设置是否为自动轮播，默认是“是”。
                    isAutoPlay(BannerConfig.IS_AUTO_PLAY)
                    when (holder.adapterPosition) {
                        0 -> {
                            setBannerStyle(BannerConfig.TITLE_BACKGROUND) //标题_背景
                            setBannerTitles(arrayTitle)
                        }
                        1 -> {
                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR) //电路指示器
                            //设置指示器的位置，小点点，左中右。
                            setIndicatorGravity(BannerConfig.RIGHT)
                            setBannerTitles(arrayTitle)
                        }
                        2 -> {
                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR) //电路指示器标题
                            //设置指示器的位置，小点点，左中右。
                            setIndicatorGravity(BannerConfig.CENTER)
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }

                        3 -> {
                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR) //电路指示器标题
                            //设置指示器的位置，小点点，左中右。
                            setIndicatorGravity(BannerConfig.LEFT)
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }

                        4 -> {
                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE) //电路指示器标题
                            //设置指示器的位置，小点点，左中右。
                            setIndicatorGravity(BannerConfig.RIGHT)
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }
                        5 -> {
                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE) //电路指示器标题
                            //设置指示器的位置，小点点，左中右。
                            setIndicatorGravity(BannerConfig.CENTER)
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }
                        6 -> {
                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE) //电路指示器标题
                            //设置指示器的位置，小点点，左中右。
                            setIndicatorGravity(BannerConfig.LEFT)
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }

                        7 -> {
                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE) //电路指示器标题侧
                            //设置指示器的位置，小点点，左中右。
                            setIndicatorGravity(BannerConfig.RIGHT) //CENTER和LEFT不支持
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }
//                        8 -> {
//                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE) //电路指示器标题侧
//                            //设置指示器的位置，小点点，左中右。
//                            setIndicatorGravity(BannerConfig.CENTER)
//                            //设置轮播图的标题集合
//                            setBannerTitles(arrayTitle)
//                        }
//                        9 -> {
//                            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE) //电路指示器标题侧
//                            //设置指示器的位置，小点点，左中右。
//                            setIndicatorGravity(BannerConfig.LEFT)
//                            //设置轮播图的标题集合
//                            setBannerTitles(arrayTitle)
//                        }
//                        3 -> { setBannerStyle(BannerConfig.LEFT) }
//                        4 -> { setBannerStyle(BannerConfig.CENTER) }
//                        5 -> { setBannerStyle(BannerConfig.RIGHT) }
//                        4 -> { setBannerStyle(BannerConfig.TITLE_TEXT_COLOR) } //没用
//                        5 -> { setBannerStyle(BannerConfig.TITLE_TEXT_SIZE) } //没用
//                        6 -> { setBannerStyle(BannerConfig.TITLE_HEIGHT) } //标题_重量
                        8 -> {
                            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE) //数字指示器标题
                            setIndicatorGravity(BannerConfig.LEFT) //LEFT和CENTER不支持，RIGHT是默认的
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }
                        9 -> {
                            setBannerStyle(BannerConfig.NUM_INDICATOR) //数字指示器
                            setIndicatorGravity(BannerConfig.LEFT) //LEFT和CENTER不支持，RIGHT是默认的
                            //设置轮播图的标题集合
                            setBannerTitles(arrayTitle)
                        }
//                        9 -> { setBannerStyle(BannerConfig.DURATION) } //持续时间 没用
//                        10 -> { setBannerStyle(BannerConfig.PADDING_SIZE) } //填充大小 没用
//                        11 -> { setBannerStyle(BannerConfig.NOT_INDICATOR) } //无指示器
//                        12 -> { setBannerStyle(BannerConfig.TIME) } //没用
//                        16 -> { setBannerStyle(BannerConfig.IS_SCROLL) } //IS_滚动
//                        17 -> { setBannerStyle(BannerConfig.IS_AUTO_PLAY) } //没用
                    }
                }

                //设置图片加载器，图片加载器在下方
                setImageLoader(MyLoader())
                //设置图片网址或地址的集合
                setImages(banners)
                //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
                if (item == "animation") {
                    //设置是否为自动轮播，默认是“是”。
                    isAutoPlay(BannerConfig.IS_AUTO_PLAY)
                    when (holder.adapterPosition) {
                        0 -> {
                            setBannerAnimation(Transformer.Default)
                        } //默认
                        1 -> {
                            setBannerAnimation(Transformer.ZoomOutSlide)
                        } //缩小幻灯片
                        2 -> {
                            setBannerAnimation(Transformer.ZoomIn)
                        } //放大
                        3 -> {
                            setBannerAnimation(Transformer.ZoomOut)
                        } //焦距调整
                        4 -> {
                            setBannerAnimation(Transformer.FlipHorizontal)
                        } //水平翻转
                        5 -> {
                            setBannerAnimation(Transformer.FlipVertical)
                        } //垂直翻转
                        6 -> {
                            setBannerAnimation(Transformer.ForegroundToBackground)
                        } //前景到背景
                        7 -> {
                            setBannerAnimation(Transformer.BackgroundToForeground)
                        } //背景到前景
                        8 -> {
                            setBannerAnimation(Transformer.RotateUp)
                        } //向上旋转
                        9 -> {
                            setBannerAnimation(Transformer.RotateDown)
                        } //旋转向下
                        10 -> {
                            setBannerAnimation(Transformer.CubeIn)
                        } //立方英寸
                        11 -> {
                            setBannerAnimation(Transformer.CubeOut)
                        } //立方体输出
                        12 -> {
                            setBannerAnimation(Transformer.ScaleInOut)
                        } //放大/缩小
                        13 -> {
                            setBannerAnimation(Transformer.Stack)
                        } //堆栈
                        14 -> {
                            setBannerAnimation(Transformer.Tablet)
                        } //片
                        15 -> {
                            setBannerAnimation(Transformer.DepthPage)
                        } //深度第页
                        16 -> {
                            setBannerAnimation(Transformer.Accordion)
                        } //手风琴
                    }
                }
//                //设置轮播图的标题集合
//                setBannerTitles(arrayTitle)
                //设置轮播间隔时间
                setDelayTime(3000)
//                //设置指示器的位置，小点点，左中右。
//                setIndicatorGravity(BannerConfig.LEFT)

                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
//        banner.setOnBannerListener {
//            Log.d("=*=", "第几张" + it.dec())
//        }

                //必须最后调用的方法，启动轮播图。
                start()
                setOnBannerListener {
                    //图片点击事件
                    val banner = banners[it]
                    when (banner) {
                        R.mipmap.banner1 -> {
                            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
                        }
                        R.mipmap.banner2 -> {
//                    toast("hello")
                        }
                        R.mipmap.banner3 -> {
//                    toast("hello")
                        }
                    }
                }
            }
        }
    }

    //自定义的图片加载器
    class MyLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            val banner = path as Int
            imageView.load(banner)
        }
    }
}