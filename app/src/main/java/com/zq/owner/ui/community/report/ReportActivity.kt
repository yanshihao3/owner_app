package com.zq.owner.ui.community.report

import android.content.Intent
import android.graphics.Color
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.wanglu.photoviewerlibrary.PhotoViewer
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import com.zq.base.activity.BaseActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityReportBinding
import com.zq.owner.ui.community.report.model.ImageInfo
import com.zq.owner.ui.community.report.viewmodel.ReportViewModel
import com.zq.owner.utils.FileUtils
import java.util.*

/**
 * 报事报修界面
 */
class ReportActivity : BaseActivity<ReportViewModel, AppActivityReportBinding>() {
    override val layoutId: Int = R.layout.app_activity_report
    private lateinit var pvTime: TimePickerView
    private lateinit var pvOptions: OptionsPickerView<String>
    private val gridLayoutManager by lazy {
        GridLayoutManager(mActivityContext, 3)
    }
    private val adapter by lazy {
        ImageAdapter()
    }

    private val listData = arrayListOf<ImageInfo>()

    private lateinit var config: ISListConfig

    private val REQUEST_LIST_CODE = 0


    override fun initView() {
        mDataBind.toolbar.title.text = "报事报修"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
        // 自定义图片加载器
        // 自定义图片加载器
        ISNav.getInstance().init { _, path, imageView ->
            imageView.load(FileUtils.getImageContentUri(mActivityContext, path))
        }
        initImagePicker()
        initTimePicker()
        initAreaPicker()
        mDataBind.selectedDate.setOnClickListener {
            pvTime.show()
        }
        mDataBind.selectedArea.setOnClickListener {
            pvOptions.show()
        }
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
        config = ISListConfig.Builder()
            // 是否多选, 默认true
            .multiSelect(true)
            // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
            .rememberSelected(true)
            // “确定”按钮背景色
            .btnBgColor(Color.parseColor("#3F51B5"))
            // “确定”按钮文字颜色
            .btnTextColor(Color.WHITE)
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

    override fun initData() {
        listData.add(ImageInfo(true, ""))
        adapter.data = listData
        adapter.notifyDataSetChanged()
    }

    //初始化时间选择器

    private fun initTimePicker() {
        val startDate: Calendar = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.set(2100, 12, 31)
        pvTime = TimePickerBuilder(this) { date, v ->

        }.setType(booleanArrayOf(true, true, true, true, true, false)) //分别对应年月日时分秒，默认全部显示
            .setTitleText("选择时间") //标题文字
            .setOutSideCancelable(false) //点击屏幕，点在控件外部范围时，是否取消显示
            .setRangDate(startDate, endDate) //起始终止年月日设定
            .setLabel(" ", " ", " ", " ", " ", " ")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 图片选择结果回调
        if (requestCode === REQUEST_LIST_CODE && resultCode === RESULT_OK && data != null) {
            val pathList: ArrayList<String>? = data.getStringArrayListExtra("result")
            if (pathList != null) {
                listData.clear()
                for (path in pathList) {
                    listData.add(ImageInfo(false, path))
                }
                judgeNumber()
            }
        }
    }

    /**
     * 判断当前图片个个数
     */
    private fun judgeNumber() {
        if (listData.size < 3) {
            listData.add(ImageInfo(true, ""))
        }
        adapter.notifyDataSetChanged()
    }

}