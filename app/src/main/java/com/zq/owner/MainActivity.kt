package com.zq.owner

import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.hjq.toast.ToastUtils
import com.zq.base.activity.BaseActivity
import com.zq.owner.databinding.AppActivityMainBinding
import com.zq.owner.ui.community.CommunityFragment
import com.zq.owner.ui.housekeeper.HousekeeperFragment
import com.zq.owner.ui.notice.NoticeFragment
import com.zq.owner.ui.service.ServiceFragment
import com.zq.owner.ui.user.UserFragment
import com.zq.owner.viewmodel.MainViewModel
import q.rorbin.badgeview.QBadgeView
import kotlin.system.exitProcess


class MainActivity : BaseActivity<MainViewModel, AppActivityMainBinding>() {


    private val mHomeFragment by lazy {
        CommunityFragment()
    }
    private val mServiceFragment by lazy {
        ServiceFragment()
    }
    private val mHousekeeperFragment by lazy {
        HousekeeperFragment()
    }
    private val mNoticeFragment by lazy {
        NoticeFragment()
    }
    private val mUserFragment by lazy {
        UserFragment()
    }

    private var fromFragment: Fragment = mHomeFragment


    private fun switchFragment(from: Fragment, to: Fragment) {
        if (from !== to) {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            if (!to.isAdded) {
                transaction.hide(from)
                transaction.add(R.id.container, to).commit()
            } else {
                transaction.hide(from)
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
            val fragCategory = when (it.itemId) {
                R.id.menu_home -> mHomeFragment
                R.id.menu_services -> mServiceFragment
                R.id.menu_housekeeper -> mHousekeeperFragment
                R.id.menu_message -> mNoticeFragment
                R.id.menu_account -> mUserFragment
                else -> mHomeFragment
            }
            switchFragment(fromFragment, fragCategory)
            fromFragment = fragCategory
            true
        }

    }


    override fun initData() {
//        setLoadSir(mDataBind.container)
//        mViewModel.load()
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

    private var exitTime: Long = 0 //退出activity计时

    //双击退出app事件
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                try {
                    ToastUtils.show("再按一次返回键确认退出")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                exitProcess(0)
            }
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}