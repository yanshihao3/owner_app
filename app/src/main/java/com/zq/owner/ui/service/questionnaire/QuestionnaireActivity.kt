package com.zq.owner.ui.service.questionnaire


import com.zq.base.activity.BaseNoModelActivity
import com.zq.owner.R
import com.zq.owner.databinding.AppActivityQuestionnaireBinding

class QuestionnaireActivity : BaseNoModelActivity<AppActivityQuestionnaireBinding>() {


    override val layoutId: Int = R.layout.app_activity_questionnaire

    override fun initView() {
        mDataBind.toolbar.title.text = "问卷调查"
        mDataBind.toolbar.backIv.setOnClickListener {
            finish()
        }
    }

    override fun initData() {

    }
}