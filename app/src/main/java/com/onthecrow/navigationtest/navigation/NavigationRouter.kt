package com.onthecrow.navigationtest.navigation

import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class NavigationRouter(

        private val navigationActivity: NavigationActivity<*>

) : BottomNavigationView.OnNavigationItemSelectedListener {

    private val fragmentManager = navigationActivity.supportFragmentManager

    var currentFragment: NavigationContainerFragment? = null
        private set

    fun setCurrentFragmentByTag(currentFragmentTag: String) {
        currentFragment = findFragmentByTag(currentFragmentTag)
    }

    fun <T : NavigationContainerFragment> showFragment(tag: String) {
        var fragment = findFragmentByTag<T>(tag)
        if (currentFragment === fragment && currentFragment != null) {
            currentFragment!!.reselect()
            return
        }

        if (fragment == null) {
            fragment = findFragmentByTag<T>(tag)
            if (fragment == null) {
                @Suppress("UNCHECKED_CAST")
                fragment = createFragmentByTag(tag) as T
                addFragmentInternal(tag, fragment)
            } else {
                showFragmentInternal(fragment)
            }
        } else {
            showFragmentInternal(fragment)
        }

        if (fragment.activity != null) {
            fragment.showFragment()
            fragment.onBecameActive()
        }

        currentFragment = fragment
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : NavigationContainerFragment> findFragmentByTag(tag: String): T? {
        return fragmentManager.findFragmentByTag(tag) as T?
    }

    private fun <T : NavigationContainerFragment> addFragmentInternal(tag: String, fragment: T) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        hideCurrentFragment(fragmentTransaction)
        fragmentTransaction
            .add(navigationActivity.getContentLayoutId(), fragment, tag)
            .commit()
    }

    private fun <T : NavigationContainerFragment> showFragmentInternal(fragment: T) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        hideCurrentFragment(fragmentTransaction)
        when (fragment.detachInsteadHide()) {
            true -> fragmentTransaction.attach(fragment)
            else -> fragmentTransaction.show(fragment)
        }
        fragmentTransaction.commit()
    }

    private fun hideCurrentFragment(fragmentTransaction: FragmentTransaction) {
        if (currentFragment != null) {
            when (currentFragment!!.detachInsteadHide()) {
                true -> fragmentTransaction.detach(currentFragment!!)
                else -> fragmentTransaction.hide(currentFragment!!)
            }
        }
    }

    @CallSuper
    open fun createFragmentByTag(tag: String): NavigationContainerFragment {
        throw IllegalArgumentException("Unknown tag")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = true
}