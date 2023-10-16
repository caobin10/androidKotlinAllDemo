package com.example.myapplication3.banner.adapter

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.bumptech.glide.Glide
import com.example.myapplication3.R
import com.example.myapplication3.DataBean
import com.example.myapplication3.banner.viewholder.ImageHolder
import com.example.myapplication3.banner.viewholder.VideoHolder
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.util.BannerUtils

class MultipleTypesAdapter(private val context: Context, mData: MutableList<DataBean>?) : BannerAdapter<DataBean, RecyclerView.ViewHolder>(mData) {

    private val mVHMap = SparseArray<RecyclerView.ViewHolder>()


    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        when (viewType) {
            1 -> return BannerImageHolder(BannerUtils.getView(parent!!, R.layout.banner_image))
            2 -> return VideoHolder(BannerUtils.getView(parent!!, R.layout.banner_video))
        }
        return BannerImageHolder(BannerUtils.getView(parent!!, R.layout.banner_image))
    }

    override fun getItemViewType(position: Int): Int {
        //先取得真实的position,在获取实体
        return getData(getRealPosition(position)).viewType;
        //直接获取真实的实体
//        return getRealData(position).viewType
        //或者自己直接去操作集合
//        return mDatas.get(getRealPosition(position)).viewType;
    }

    override fun onBindView(imageHolder: RecyclerView.ViewHolder, data: DataBean, position: Int, size: Int) {
        val viewType = imageHolder.itemViewType
        when (viewType) {
            1 -> {
//                val imageHolder: ImageHolder = imageHolder as ImageHolder
                val imageHolder: BannerImageHolder = imageHolder as BannerImageHolder
                mVHMap.append(position, imageHolder)
//                imageHolder.imageView.setImageResource(data.imageRes)
                //通过图片加载器实现圆角，你也可以自己使用圆角的imageview，实现圆角的方法很多，自己尝试哈
                Glide.with(imageHolder.itemView)
                    .load(data.imageUrl.toString())
//                    .thumbnail(Glide.with(imageHolder.itemView).load(R.drawable.loading))
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE) //                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                    .into(imageHolder.imageView)
            }
            2 -> {
                val videoHolder: VideoHolder = imageHolder as VideoHolder
                mVHMap.append(position, videoHolder)
                videoHolder.player.setUp(data.videoUrl, "")
                //增加封面
                Glide.with(context).load(data.imageUrl).into(videoHolder.player.posterImageView)
            }
        }
    }

    fun getVHMap(): SparseArray<RecyclerView.ViewHolder>? {
        return mVHMap
    }

    class VideoHolder(view: View) : RecyclerView.ViewHolder(view) {
        var player: JzvdStd

        init {
            player = view.findViewById(R.id.player)
        }
    }
}