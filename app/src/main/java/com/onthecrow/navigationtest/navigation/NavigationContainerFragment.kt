package com.onthecrow.navigationtest.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.onthecrow.navigationtest.R

abstract class NavigationContainerFragment : Fragment(), BecameActive, OnBackPressedListener, FragmentManager.OnBackStackChangedListener {

    protected abstract fun getFirstFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        childFragmentManager.addOnBackStackChangedListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState == null) {
            onBecameActive()
        }
        return inflater.inflate(R.layout.fragment_navigation_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showFragment()
    }

    override fun onBecameActive() {
        val fragment = getLastFragment()
        if (fragment is BecameActive && fragment.isResumed) {
            fragment.onBecameActive()
        }
    }

    private fun getLastFragment(): Fragment? {
        val fragments = childFragmentManager.fragments
        if (fragments.isNotEmpty()) {
            val li = fragments.listIterator(fragments.size)
            while (li.hasPrevious()) {
                return li.previous() ?: continue // it seems that after popBackStack null stays in the array
            }
        }
        return null
    }

    override fun onBackPressed(viaMenu: Boolean): Boolean {
        return if (!iterateFragmentsWithBack(childFragmentManager, viaMenu)) {
            childFragmentManager.popBackStackImmediate()
        } else {
            true
        }
    }

    private fun iterateFragmentsWithBack(fragmentManager: FragmentManager, viaMenu: Boolean): Boolean {
        val fragments = fragmentManager.fragments
        val li = fragments.listIterator(fragments.size)
        while (li.hasPrevious()) {
            val fragment = li.previous() ?: continue // it seems that after popBackStack null stays in the array
            if (fragment.isVisible) {
                if (fragment is OnBackPressedListener) {
                    // only one fragment can get OnBackPressed, but if it didn't work on it, it won't got to next fragment.
                    // It will only be worked here in activity
                    return if (fragment.onBackPressed(viaMenu)) {
                        true
                    } else {
                        break
                    }
                }
            }
        }
        return false
    }

    override fun onBackStackChanged() {
        onBecameActive()
    }

    fun showFragment() {
        val fragments = childFragmentManager.fragments
        if (fragments.isEmpty()) {
            val fragment = getFirstFragment()
            childFragmentManager
                    .beginTransaction()
                    .add(R.id.navigation_container_root, fragment, null)
                    .commit()
        }
    }

    fun replaceFragmentWithBackStack(fragment: Fragment, transitionView: View? = null) {
        val transaction = childFragmentManager.beginTransaction()
        if (transitionView != null) {
            transaction.addSharedElement(transitionView, ViewCompat.getTransitionName(transitionView).orEmpty())
        }
        transaction
                .addToBackStack(null)
                .replace(R.id.navigation_container_root, fragment, null)
                .commit()
    }

    open fun detachInsteadHide(): Boolean = false

    fun reselect() {
        val lastFragment = getLastFragment()
        if (lastFragment !is Reselectable || !lastFragment.onReselected()) {
            childFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}