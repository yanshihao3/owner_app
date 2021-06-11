package com.zq.owner.ui.community

import coil.load
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.ui.community.viewmodel.CommunityViewModel
import com.zq.owner.databinding.AppFragmentCommunityBinding


class CommunityFragment : BaseLazyFragment<CommunityViewModel, AppFragmentCommunityBinding>() {


    override val layoutId: Int = R.layout.app_fragment_community

    var imageUrls = listOf(
        "https://img.zcool.cn/community/01b72057a7e0790000018c1bf4fce0.png",
        "https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg",
        "https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg",
        "https://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg"
    )

    override fun initView() {

    }

    override fun initData() {

    }

    override fun onFragmentFirstVisible() {
        val banner = mDataBind?.banner
        banner?.apply {
            addBannerLifecycleObserver(this@CommunityFragment)
            indicator = CircleIndicator(mContext)
            setAdapter(object : BannerImageAdapter<String>(imageUrls) {
                override fun onBindView(
                    holder: BannerImageHolder,
                    data: String,
                    position: Int,
                    size: Int
                ) {
                    holder.imageView.load(data)
                }
            })
        }
    }

}