package com.onthecrow.navigationtest.test2

import android.view.MenuItem
import com.onthecrow.navigationtest.navigation.NavigationContainerFragment
import com.onthecrow.navigationtest.navigation.NavigationRouter

class Test2NavigationRouter(

    navigationActivity: Test2Activity

) : NavigationRouter(navigationActivity) {

    companion object {

        private const val FRAGMENT_TAG_TODAY = "fragment_tag_today"
        private const val FRAGMENT_TAG_INSPECTIONS = "fragment_tag_inspections"
        private const val FRAGMENT_TAG_TASKS = "fragment_tag_tasks"
        private const val FRAGMENT_TAG_MORE = "fragment_tag_more"
    }

    fun showToday() = showFragment<Test2Fragment1>(FRAGMENT_TAG_TODAY)

    fun showInspections() = showFragment<Test2Fragment2>(FRAGMENT_TAG_INSPECTIONS)

    fun showTasks() = showFragment<Test2Fragment3>(FRAGMENT_TAG_TASKS)

    fun showMore() = showFragment<Test2Fragment4>(FRAGMENT_TAG_MORE)

    override fun createFragmentByTag(tag: String): NavigationContainerFragment = when (tag) {
        FRAGMENT_TAG_TODAY -> Test2Fragment1()
        FRAGMENT_TAG_INSPECTIONS -> Test2Fragment2()
        FRAGMENT_TAG_TASKS -> Test2Fragment3()
        FRAGMENT_TAG_MORE -> Test2Fragment4()
        else -> super.createFragmentByTag(tag)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.test_2_menu_today -> showToday()
//            R.id.test_2_menu_inspections -> showInspections()
//            R.id.test_2_menu_tasks -> showTasks()
//            R.id.test_2_menu_more -> showMore()
//        }
        return super.onNavigationItemSelected(item)
    }
}