package com.zq.owner.ui.user

import com.zq.base.fragment.BaseLazyFragment
import com.zq.owner.R
import com.zq.owner.databinding.AppFragmentUserBinding
import com.zq.owner.ui.user.viewmodel.UserViewModel

/**
 * @program: owner_app
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-06-09 17:29
 **/
class UserFragment :BaseLazyFragment<UserViewModel,AppFragmentUserBinding>() {


    override val layoutId: Int= R.layout.app_fragment_user

    override fun initView() {
    }

    override fun initData() {
    }
    override fun onFragmentFirstVisible() {
    }
}