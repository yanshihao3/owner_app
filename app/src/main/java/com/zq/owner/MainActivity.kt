package com.zq.owner

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.zq.base.activity.BaseActivity
import com.zq.owner.ui.community.CommunityFragment
import com.zq.owner.databinding.AppActivityMainBinding
import com.zq.owner.ui.housekeeper.HousekeeperFragment
import com.zq.owner.ui.notice.NoticeFragment
import com.zq.owner.ui.service.ServiceFragment
import com.zq.owner.ui.user.UserFragment
import com.zq.owner.viewmodel.MainViewModel
import q.rorbin.badgeview.QBadgeView


class MainActivity : BaseActivity<MainViewModel, AppActivityMainBinding>() {


    private val mHomeFragment by lazy {
        CommunityFragment()
    }
    private val mServiceFragment = ServiceFragment()
    private val mHousekeeperFragment = HousekeeperFragment()
    private val mNoticeFragment = NoticeFragment()
    private val mUserFragment = UserFragment()

    private var fromFragment: Fragment = mHomeFragment

    private fun switchFragment(from: Fragment?, to: Fragment) {
        if (from !== to) {
            val manger: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manger.beginTransaction()
            if (!to.isAdded) {
                if (from != null) {
                    transaction.hide(from)
                }
                transaction.add(R.id.container, to).commit()
            } else {
                if (from != null) {
                    transaction.hide(from)
                }
                transaction.show(to).commit()
            }

        }
    }


    override val layoutId: Int = R.layout.app_activity_main

    override fun initView() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, mHomeFragment)
        transaction.commit()
        mDataBind.bottomView.setOnNavigationItemSelectedListener {
            var fragCategory: Fragment? = null
            when (it.itemId) {
                R.id.menu_home -> fragCategory = mHomeFragment
                R.id.menu_services -> fragCategory = mServiceFragment
                R.id.menu_housekeeper -> fragCategory = mHousekeeperFragment
                R.id.menu_message -> fragCategory = mNoticeFragment
                R.id.menu_account -> fragCategory = mUserFragment
            }

            supportActionBar?.title = getString(R.string.menu_home)
            switchFragment(fromFragment, fragCategory!!)
            fromFragment = fragCategory
            true
        }
        immersionBar {
            keyboardEnable(false)
            transparentStatusBar()
            statusBarDarkFont(true, 0.2f)
        }
    }


    override fun initData() {
        setLoadSir(mDataBind.container)
        mViewModel.load()
    }

    override fun onRetryBtnClick() {
        showContent()
    }


    /**
     * BottomNavigationView显示角标
     *
     * @param viewIndex tab索引
     * @param showNumber 显示的数字，小于等于0是将不显示
     */
    private fun showBadgeView(viewIndex: Int, showNumber: Int) {
        // 具体child的查找和view的嵌套结构请在源码中查看
        // 从bottomNavigationView中获得BottomNavigationMenuView
        val menuView = mDataBind.bottomView.getChildAt(0) as BottomNavigationMenuView
        // 从BottomNavigationMenuView中获得childview, BottomNavigationItemView
        if (viewIndex < menuView.childCount) {
            // 获得viewIndex对应子tab
            val view: View = menuView.getChildAt(viewIndex)
            // 从子tab中获得其中显示图片的ImageView
            val icon: View = view.findViewById(com.google.android.material.R.id.icon)
            // 获得图标的宽度
            val iconWidth: Int = icon.getWidth()
            // 获得tab的宽度/2
            val tabWidth: Int = view.getWidth() / 2
            // 计算badge要距离右边的距离
            val spaceWidth = tabWidth - iconWidth

            // 显示badegeview
            QBadgeView(this).bindTarget(view)
                .setGravityOffset((spaceWidth + 50).toFloat(), 13f, false).badgeNumber =
                showNumber
        }
    }

}