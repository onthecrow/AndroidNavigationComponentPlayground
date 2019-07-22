package com.onthecrow.navigationtest.navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment

abstract class NavigationFragment : Fragment(), BecameActive, OnBackPressedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.invalidateOptionsMenu()
        if (getMenuResource() != null) {
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (getMenuResource() != null) {
            inflater.inflate(getMenuResource()!!, menu)
        }
    }

    override fun onBecameActive() {
        activity?.invalidateOptionsMenu()
    }

    @MenuRes
    open fun getMenuResource(): Int? = null

    override fun onResume() {
        super.onResume()

        activity?.invalidateOptionsMenu()
    }

    override fun onBackPressed(viaMenu: Boolean): Boolean = false

    fun getContainerFragment(): NavigationContainerFragment {
        var parentFragment: Fragment? = this
        do {
            parentFragment = parentFragment?.parentFragment
        } while (parentFragment != null && parentFragment !is NavigationContainerFragment)

        return when (parentFragment != null) {
            true -> parentFragment as NavigationContainerFragment
            else -> throw IllegalStateException("NavigationFragment must be contained in NavigationContainerFragment, but it was not. Fragment's class name ${javaClass.name}")
        }
    }

    fun getNavigationActivity(): NavigationActivity<*> = requireActivity() as NavigationActivity<*>
}