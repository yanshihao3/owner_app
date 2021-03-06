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
     * BottomNavigationView????????????
     *
     * @param viewIndex tab??????
     * @param showNumber ??????????????????????????????0???????????????
     */
    private fun showBadgeView(viewIndex: Int, showNumber: Int) {
        // ??????child????????????view????????????????????????????????????
        // ???bottomNavigationView?????????BottomNavigationMenuView
        val menuView = mDataBind.bottomView.getChildAt(0) as BottomNavigationMenuView
        // ???BottomNavigationMenuView?????????childview, BottomNavigationItemView
        if (viewIndex < menuView.childCount) {
            // ??????viewIndex?????????tab
            val view: View = menuView.getChildAt(viewIndex)
            // ??????tab??????????????????????????????ImageView
            val icon: View = view.findViewById(com.google.android.material.R.id.icon)
            // ?????????????????????
            val iconWidth: Int = icon.getWidth()
            // ??????tab?????????/2
            val tabWidth: Int = view.getWidth() / 2
            // ??????badge????????????????????????
            val spaceWidth = tabWidth - iconWidth

            // ??????badegeview
            QBadgeView(this).bindTarget(view)
                .setGravityOffset((spaceWidth + 50).toFloat(), 13f, false).badgeNumber =
                showNumber
        }
    }

    private var exitTime: Long = 0 //??????activity??????

    //????????????app??????
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                try {
                    ToastUtils.show("?????????????????????????????????")
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