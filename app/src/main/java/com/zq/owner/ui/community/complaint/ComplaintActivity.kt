package com.zq.owner.ui.community.complaint

import android.content.Intent
import android.graphics.Color
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.wanglu.photoviewerlibrary.PhotoViewer
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityComplaintBinding
import com.zq.owner.ui.community.report.ImageAdapter
import com.zq.owner.ui.community.report.model.ImageInfo
import com.zq.owner.utils.FileUtils
import java.util.ArrayList

/**
 * 投诉建议界面
 */
class ComplaintActivity : BaseNoModelActivity<AppActivityComplaintBinding>() {


    override val layoutId: Int = R.layout.app_activity_complaint

    private val gridLayoutManager by lazy {
        GridLayoutManager(mActivityContext, 3)
    }
    private val adapter by lazy {
        ImageAdapter()
    }

    private val listData = arrayListOf<ImageInfo>()

    private lateinit var config: ISListConfig

    private val REQUEST_LIST_CODE = 0
    private lateinit var pvOptions: OptionsPickerView<String>

    override fun initView() {
        ISNav.getInstance().init { _, path, imageView ->
            imageView.load(FileUtils.getImageContentUri(mActivityContext, path))
        }
        initImagePicker()
        initAreaPicker()
        val recyclerView = mDataBind.recyclerView
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter

        adapter.setOnItemChildClickListener { adapter, view, position ->
            val imageInfo = listData[position]

            if (imageInfo.isShowAdd) {
                ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
            } else {
                val list = arrayListOf<String>()
                listData.forEach {
                    list.add(it.url)
                }
                PhotoViewer
                    .setData(list)
                    .setCurrentPage(position)
                    .setImgContainer(recyclerView)
                    .setShowImageViewInterface(object : PhotoViewer.ShowImageViewInterface {
                        override fun show(iv: ImageView, url: String) {
                            iv.load(FileUtils.getImageContentUri(mActivityContext, url))
                        }
                    })
                    .start(this)
            }
        }
    }

    private fun initImagePicker() {
        // 自由配置选项
        // 自由配置选项
        config = ISListConfig.Builder()
            // 是否多选, 默认true
            .multiSelect(true)
            // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
            .rememberSelected(true)
            // “确定”按钮背景色
            .btnBgColor(Color.GRAY)
            // “确定”按钮文字颜色
            .btnTextColor(Color.BLUE)
            // 使用沉浸式状态栏
            .statusBarColor(Color.parseColor("#3F51B5"))
            // 返回图标ResId
            .backResId(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            // 标题
            .title("图片")
            // 标题文字颜色
            .titleColor(Color.WHITE)
            // TitleBar背景色
            .titleBgColor(Color.parseColor("#3F51B5"))
            // 裁剪大小。needCrop为true的时候配置
            .cropSize(1, 1, 200, 200)
            .needCrop(false)
            // 第一个是否显示相机，默认true
            .needCamera(true)
            // 最大选择图片数量，默认9
            .maxNum(3)
            .build()
    }

    //初始化区域选择器
    private fun initAreaPicker() {
        //条件选择器
        //条件选择器
        pvOptions = OptionsPickerBuilder(
            this
        ) { options1, _, _, _ -> //返回的分别是三个级别的选中位置

        }.build()
        pvOptions.setPicker(mutableListOf("客厅", "厨房", "卫生间", "卧室", "阳台"))

    }

    override fun initData() {
        listData.add(ImageInfo(true, ""))
        adapter.data = listData
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择结果回调
        // 图片选择结果回调
        if (requestCode === REQUEST_LIST_CODE && resultCode === RESULT_OK && data != null) {
            val pathList: ArrayList<String>? = data.getStringArrayListExtra("result")
            if (pathList != null) {
                for (path in pathList) {
                    listData.add(0, ImageInfo(false, path))
                }
                adapter.notifyDataSetChanged()
            }
        }
    }
}