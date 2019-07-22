package com.onthecrow.navigationtest.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

abstract class NavigationActivity<T : NavigationRouter> : AppCompatActivity(), OnBackPressedListener {

    lateinit var navigationRouter: T

    companion object {

        const val STATE_CURRENT_FRAGMENT_TAG = "state_current_fragment_tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigationRouter = createNavigationRouter()
        when (savedInstanceState) {
            null -> showInitialFragment()
            else -> savedInstanceState.getString(STATE_CURRENT_FRAGMENT_TAG)?.let(navigationRouter::setCurrentFragmentByTag)
        }
    }

    abstract fun showInitialFragment()

    abstract fun createNavigationRouter(): T

    abstract fun getContentLayoutId(): Int

    fun getCurrentFragmentTag(): String? = navigationRouter.currentFragment?.tag

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(STATE_CURRENT_FRAGMENT_TAG, getCurrentFragmentTag())
    }

    override fun onBackPressed() {
        onBackPressed(false)
    }

    override fun onBackPressed(viaMenu: Boolean): Boolean {
        if (!iterateFragmentsWithBack(supportFragmentManager, viaMenu)) {
            super.onBackPressed()
        }
        return true
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
}